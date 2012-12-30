package com.irzaevrustam.geocoderlib;

import com.irzaevrustam.geocoderlib.enums.GeocodingTypes;
import com.irzaevrustam.geocoderlib.items.AddressComponent;
import com.irzaevrustam.geocoderlib.items.Geometry;

import java.util.ArrayList;

public class GeocoderItem {
	private ArrayList<GeocodingTypes> mTypes = new ArrayList<GeocodingTypes>();
	private String mFormattedAddress;
	private boolean mPartialMatch;
	private ArrayList<AddressComponent> mAddressComponents = new ArrayList<AddressComponent>();
	private Geometry mGeometry;

	public ArrayList<GeocodingTypes> getTypes() {
		return mTypes;
	}

	public boolean isPartialMatch() {
		return mPartialMatch;
	}

	public void setPartialMatch(boolean mPartialMatch) {
		this.mPartialMatch = mPartialMatch;
	}

	public void setTypes(ArrayList<GeocodingTypes> mTypes) {
		this.mTypes = mTypes;
	}

	public String getFormattedAddress() {
		return mFormattedAddress;
	}

	public void setFormattedAddress(String mFormattedAddress) {
		this.mFormattedAddress = mFormattedAddress;
	}

	public ArrayList<AddressComponent> getAddressComponents() {
		return mAddressComponents;
	}

	public void setAddressComponents(ArrayList<AddressComponent> mAddressComponents) {
		this.mAddressComponents = mAddressComponents;
	}

	public void addAddressComponent(AddressComponent addressComponent) {
		mAddressComponents.add(addressComponent);
	}

	public Geometry getGeometry() {
		return mGeometry;
	}

	public void setGeometry(Geometry mGeometry) {
		this.mGeometry = mGeometry;
	}

	public String getCity() {
		if (!AddressHelper.getSpecificData(this, GeocodingTypes.POLITICAL, GeocodingTypes.LOCALITY).equals("")) {
			return AddressHelper.getSpecificData(this, GeocodingTypes.POLITICAL, GeocodingTypes.LOCALITY);
		} else {
			return AddressHelper.getSpecificData(this, GeocodingTypes.POLITICAL, GeocodingTypes.SUBLOCALITY);
		}
	}

	public String getCountry() {
		return AddressHelper.getSpecificData(this, GeocodingTypes.POLITICAL, GeocodingTypes.COUNTRY);
	}

	public String getStreet() {
		return AddressHelper.getSpecificData(this, GeocodingTypes.ROUTE);
	}

	public String getStreetNumber() {
		return AddressHelper.getSpecificData(this, GeocodingTypes.STREET_NUMBER);
	}
	
	public String getStreetPlusNumber() {
		String subs = getStreetNumber() + " " + getStreet(); 
		if (getFormattedAddress().contains(subs)){
			return getStreetNumber() + " " + getStreet();
		}
		return getStreet() + " " + getStreetNumber();
	}

	public String getTypesToString() {
		return AddressHelper.typesToString(mTypes);
	}

	public boolean isValid(ArrayList<GeocodingTypes> validAccuracies) {
		boolean isContain = false;
		for (GeocodingTypes typeAcc : validAccuracies) {
			for (GeocodingTypes typeCurr : mTypes) {
				if (typeAcc.equals(typeCurr)) {
					isContain = true;
				}
			}
		}

		return isContain;
	}
}