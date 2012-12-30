package com.irzaevrustam.geocoderlib;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import com.irzaevrustam.geocoderlib.enums.GeocodingLanguage;
import com.irzaevrustam.geocoderlib.enums.GeocodingStatus;
import com.irzaevrustam.geocoderlib.enums.GeocodingTypes;
import com.irzaevrustam.geocoderlib.items.AddressComponent;
import com.irzaevrustam.geocoderlib.items.Geometry;
import com.irzaevrustam.geocoderlib.items.Viewport;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ValidateAddress implements AddressDialog.AddressDialogListener {
	private GeocoderOptions mGeocoderOptions;
	private String mAddress;
	private String mUrl;
	private String mCountry;
	private GeocoderListener mListener;
	private boolean mNeedProgressDialog = false;
	private final Context mContext;
	private ProgressDialog mProgressDialog;
	private boolean mIsInternetConnectionFails = false;
	private ArrayList<String> mAvailableCountries = new ArrayList<String>();

	public ValidateAddress(Context context, GeocoderListener listener) {
		mContext = context;
		mListener = listener;
	}

	public ValidateAddress(Context context, GeocoderListener listener, boolean needProgressDialog) {
		mContext = context;
		mListener = listener;
		mNeedProgressDialog = needProgressDialog;
	}
	
	public ValidateAddress(Context context, GeocoderListener listener, boolean needProgressDialog,
			ArrayList<String> availableCountries) {
		mContext = context;
		mListener = listener;
		mNeedProgressDialog = needProgressDialog;
		mAvailableCountries = availableCountries;
	}

	public ValidateAddress(Context context, GeocoderListener listener, boolean needProgressDialog,
			GeocoderOptions options) {
		mContext = context;
		mListener = listener;
		mNeedProgressDialog = needProgressDialog;
		mGeocoderOptions = options;
	}

	public ValidateAddress(Context context, GeocoderListener listener, GeocoderOptions options) {
		mContext = context;
		mListener = listener;
		mGeocoderOptions = options;
	}

	public GeocoderOptions getGeocoderOptions() {
		return mGeocoderOptions;
	}

	public void setGeocoderOptions(GeocoderOptions mGeocoderOptions) {
		this.mGeocoderOptions = mGeocoderOptions;
	}

	public String getAddress() {
		return mAddress;
	}

	public boolean isNeedProgressDialog() {
		return mNeedProgressDialog;
	}

	public void setNeedProgressDialog(boolean mNeedProgressDialog) {
		this.mNeedProgressDialog = mNeedProgressDialog;
	}

	public ArrayList<String> getAvailableCountries() {
		return mAvailableCountries;
	}

	public void setAvailableCountries(ArrayList<String> countries) {
		this.mAvailableCountries = countries;
	}

	public GeocoderListener getListener() {
		return mListener;
	}

	public void setListener(GeocoderListener mListener) {
		this.mListener = mListener;
	}

	private StringBuilder buildMultipleAddress(View...editTextArray) {
		StringBuilder fullAddress = new StringBuilder();
		for (View item : editTextArray) {
			if (item instanceof EditText || item instanceof AutoCompleteTextView || 
					item instanceof MultiAutoCompleteTextView) {
                fullAddress.append(((EditText) item).getText().toString()).append(" ");
			}
		}
		
		return fullAddress;
	}

	public void validate(String country, View...editTextArray) {
		if (editTextArray.length == 0) {
			mListener.onAddressGotFailedStatus("Address is empty.");
			return;
		}

		mCountry = country;
		validate(country + " " + buildMultipleAddress(editTextArray));
	}

	public void validate(View... editTextArray) {
		if (editTextArray.length == 0) {
			mListener.onAddressGotFailedStatus("Address is empty.");
			return;
		}

		validate(buildMultipleAddress(editTextArray).toString());
	}

	public void validate(String address) {
		assert mListener != null;
		
		if (address.equals("")) {
			mListener.onAddressGotFailedStatus("Address is empty.");
			return;
		}

		if (mNeedProgressDialog && mContext == null) {
			mListener.onAddressGotFailedStatus("Context not set.");
			return;
		}

		if (mGeocoderOptions == null) {
			mGeocoderOptions = new GeocoderOptions();
			mGeocoderOptions.setLanguage(GeocodingLanguage.EN);
			mGeocoderOptions.addValidAccuracy(GeocodingTypes.STREET_NUMBER);
			mGeocoderOptions.addValidAccuracy(GeocodingTypes.STREET_ADDRESS);
		}

		mAddress = address;
		mUrl = AddressHelper.buildUrl(mGeocoderOptions)
				.replace(AddressHelper.TAG_ADDRESS_OR_LATLNG, mAddress)
				.replace(" ", "+");
		Log.d("url", mUrl);

		new validateTask().execute();
	}

	private class validateTask extends AsyncTask<Void, Void, GeocoderResult> {
		@Override
		protected GeocoderResult doInBackground(Void... params) {
			String responseString;
			GeocoderResult result = new GeocoderResult();

			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(mUrl);
				HttpParams httpParams = httpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, mGeocoderOptions.getConnectionTimeout());
				HttpConnectionParams.setSoTimeout(httpParams, mGeocoderOptions.getSocketTimeout());

				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				responseString = httpClient.execute(httpGet, responseHandler);
				Log.d("response", responseString);

				JSONObject object = new JSONObject(responseString);
				GeocodingStatus status =
						AddressHelper.stringToStatus(object.getString("status"));
				result.setStatus(status);

				mListener.onAddressGotStatus(status);

				if (status != GeocodingStatus.OK) {
					if (status != GeocodingStatus.ZERO_RESULTS) {
						mIsInternetConnectionFails = true;
						mListener.onAddressGotFailedStatus(AddressHelper.messageAccordingToStatus(status));
						return null;
					} else {
						mIsInternetConnectionFails = true;
						mListener.onAddressZeroResult();
						return null;
					}
				}

				JSONArray results = object.getJSONArray("results");
				int len = results.length();
				Log.d("", "Found results: " + len);

				for (int i = 0; i < len; i++) {
					JSONObject resultObject = results.getJSONObject(i);
					GeocoderItem item = new GeocoderItem();
					item.setFormattedAddress(resultObject.getString("formatted_address"));
					item.setTypes(AddressHelper.decodeTypes(resultObject.get("types").toString()));
					if (resultObject.has("partial_match")) {
						item.setPartialMatch(resultObject.getString("partial_match").equalsIgnoreCase("true"));
					}

					JSONArray components = resultObject.getJSONArray("address_components");
					for (int j = 0; j < components.length(); j++) {
						JSONObject component = components.getJSONObject(j);
						AddressComponent addressComponent = new AddressComponent();
						String longName = component.getString("long_name");
						String shortName = component.getString("short_name");
						addressComponent.setLongName(longName);
						addressComponent.setShortName(shortName);
						addressComponent.setTypes(AddressHelper.decodeTypes(component.get("types").toString()));

						item.addAddressComponent(addressComponent);
					}

					Geometry geometry = new Geometry();
					JSONObject geometryObject = resultObject.getJSONObject("geometry");
					JSONObject locationObject = geometryObject.getJSONObject("location");
					geometry.setLocationLat(locationObject.getDouble("lat"));
					geometry.setLocationLon(locationObject.getDouble("lng"));
					geometry.setLocationType(AddressHelper.stringToLocationType(
							geometryObject.getString("location_type")));

					Viewport viewport = new Viewport();
					JSONObject viewportObject = geometryObject.getJSONObject("viewport");
					JSONObject southWestObject = viewportObject.getJSONObject("southwest");
					viewport.setSouthWestLat(southWestObject.getDouble("lat"));
					viewport.setSouthWestLon(southWestObject.getDouble("lng"));
					JSONObject northEastObject = viewportObject.getJSONObject("northeast");
					viewport.setNorthEastLat(northEastObject.getDouble("lat"));
					viewport.setNorthEastLon(northEastObject.getDouble("lng"));
					geometry.setViewport(viewport);
					item.setGeometry(geometry);

					if (mGeocoderOptions.getValidAccuracies().size() > 0 &&
							item.isValid(mGeocoderOptions.getValidAccuracies())) {
						Log.d("", "accuracies size is normal");
						if (mAvailableCountries.size() == 0) {
							result.addItem(item);
						} else {
							if (mAvailableCountries.contains(item.getCountry()) && 
									item.getCountry().equalsIgnoreCase(mCountry)) {
								Log.d("", "country added && it's correct with an item");
								result.addItem(item);	
							} else {
								Log.d("", "country not in list");
							}
						}
					} else if (mGeocoderOptions.getValidAccuracies().size() == 0) {
						Log.d("", "accuracies size is 0");
						result.addItem(item);
					} else {
						Log.d("", "error with accuracies");
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
				if (e instanceof UnknownHostException || e instanceof IOException) {
					mIsInternetConnectionFails = true;
					mListener.onConnectionFailed();
				} else {
					//XXX hack to avoid double firing other callbacks
					mIsInternetConnectionFails = true;
					mListener.onAddressGotFailedStatus("Some error beat us.");
				}
			}

			return result;
		}

		@Override
		protected void onPreExecute() {
			if (mNeedProgressDialog && mContext != null) {
				mProgressDialog = ProgressDialog.show(mContext, "", "Validating address...");
			}

			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(GeocoderResult result) {
			super.onPostExecute(result);

			if (mProgressDialog != null && mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}

			if (mIsInternetConnectionFails) {
				return;
			}
			
			if (result == null) {
				mListener.onAddressGotFailedStatus("Result is null");
				return;
			}

			if (result.size() >= 1) {
				if (result.size() > 1 || (result.size() >= 1 && mGeocoderOptions.isForceSelectAddressDialog())) {
					new AddressDialog(mContext, result, ValidateAddress.this);
				}
			} else {
				if (result.size() > 0) {
					mListener.onAddressGotResult(result.getItem(0));
				} else {
					mListener.onAddressZeroResult();
				}
			}
		}
	}

	@Override
	public void onAddressSelected(GeocoderItem address) {
		mListener.onAddressGotResult(address);
	}

	@Override
	public void onAddressFailed(String message) {
		mListener.onAddressGotFailedStatus(message);
	}
	
	@Override
	public void onAddressCanceled() {
	}
}