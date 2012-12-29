package com.irzaevrustam.geocoderlib.items;

import com.irzaevrustam.geocoderlib.enums.GeocodingTypes;

import java.util.ArrayList;

public class AddressComponent {
	private String mLongName;
	private String mShortName;
	private ArrayList<GeocodingTypes> mTypes = new ArrayList<GeocodingTypes>();
	
	public AddressComponent() {
	}
	
	public AddressComponent(String longName, String shortName, ArrayList<GeocodingTypes> types) {
		mLongName = longName;
		mShortName = shortName;
		mTypes = types;
	}
	
	public String getLongName() {
		return mLongName;
	}
	
	public void setLongName(String mLongName) {
		this.mLongName = mLongName;
	}
	
	public String getShortName() {
		return mShortName;
	}
	
	public void setShortName(String mShortName) {
		this.mShortName = mShortName;
	}
	
	public ArrayList<GeocodingTypes> getTypes() {
		return mTypes;
	}
	
	public void setTypes(ArrayList<GeocodingTypes> mTypes) {
		this.mTypes = mTypes;
	}
	
	public void addType(GeocodingTypes type) {
		mTypes.add(type);
	}
}