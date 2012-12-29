package com.irzaevrustam.geocoderlib;

import com.irzaevrustam.geocoderlib.enums.GeocodingLanguage;
import com.irzaevrustam.geocoderlib.enums.GeocodingType;
import com.irzaevrustam.geocoderlib.enums.GeocodingTypes;

import java.util.ArrayList;

/** Options for Geocoder. */
public class GeocoderOptions {
	private GeocodingLanguage mLanguage = GeocodingLanguage.EN;
	private GeocodingType mGeocodingType = GeocodingType.BY_ADDRESS;
	private boolean mSensor = true;
	private int mConnectionTimeout = 20000;
	private int mSocketTimeout = 20000;
	private boolean mForceSelectAddressDialog = true;
	private ArrayList<GeocodingTypes> mValidAccuracies = new ArrayList<GeocodingTypes>();
	
	public GeocoderOptions() {
	}

	public boolean isForceSelectAddressDialog() {
		return mForceSelectAddressDialog;
	}

	public void setForceSelectAddressDialog(boolean mForceSelectAddressDialog) {
		this.mForceSelectAddressDialog = mForceSelectAddressDialog;
	}

	public GeocodingLanguage getLanguage() {
		return mLanguage;
	}

	/** Set locale in which parser need to extract data.
	 * @param mLanguage Language */
	public void setLanguage(GeocodingLanguage mLanguage) {
		this.mLanguage = mLanguage;
	}

	public GeocodingType getGeocodingType() {
		return mGeocodingType;
	}

	/** Set type of geocoding: via address or via it coordinates.
	 * @param mGeocodingType type of geocoding */
	public void setGeocodingType(GeocodingType mGeocodingType) {
		this.mGeocodingType = mGeocodingType;
	}

	public boolean isSensor() {
		return mSensor;
	}

	/** Set sensor type. 
	 * For mobile devices this parameter must be set to true.
	 * @param mSensor mode of sensor */
	public void setSensor(boolean mSensor) {
		this.mSensor = mSensor;
	}

	public int getConnectionTimeout() {
		return mConnectionTimeout;
	}

	/** Set connection timeout.
	 * @param mTimeout Timeout in msecs */
	public void setConnectionTimeout(int mTimeout) {
		this.mConnectionTimeout = mTimeout;
	}

	public int getSocketTimeout() {
		return mSocketTimeout;
	}

	/** Set socket timeout.
	 * @param mSockeTimeout Timeout in msecs */
	public void setSocketTimeout(int mSocketTimeout) {
		this.mSocketTimeout = mSocketTimeout;
	}

	public ArrayList<GeocodingTypes> getValidAccuracies() {
		return mValidAccuracies;
	}

	/** Set one or more accuracy levels.
	 * @param mValidAccuracies */
	public void setValidAccuracies(ArrayList<GeocodingTypes> mValidAccuracies) {
		this.mValidAccuracies = mValidAccuracies;
	}
	
	/** Add accuracy level.
	 * @param type */
	public void addValidAccuracy(GeocodingTypes type) {
		mValidAccuracies.add(type);
	}
}
