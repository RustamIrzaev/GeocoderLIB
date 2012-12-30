package com.irzaevrustam.geocoderlib;

import com.irzaevrustam.geocoderlib.enums.GeocodingLanguage;
import com.irzaevrustam.geocoderlib.enums.GeocodingType;
import com.irzaevrustam.geocoderlib.enums.GeocodingTypes;

import java.util.ArrayList;

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

	public void setLanguage(GeocodingLanguage mLanguage) {
		this.mLanguage = mLanguage;
	}

	public GeocodingType getGeocodingType() {
		return mGeocodingType;
	}

	public void setGeocodingType(GeocodingType mGeocodingType) {
		this.mGeocodingType = mGeocodingType;
	}

	public boolean isSensor() {
		return mSensor;
	}

	public void setSensor(boolean mSensor) {
		this.mSensor = mSensor;
	}

	public int getConnectionTimeout() {
		return mConnectionTimeout;
	}

	public void setConnectionTimeout(int mTimeout) {
		this.mConnectionTimeout = mTimeout;
	}

	public int getSocketTimeout() {
		return mSocketTimeout;
	}

	public void setSocketTimeout(int mSocketTimeout) {
		this.mSocketTimeout = mSocketTimeout;
	}

	public ArrayList<GeocodingTypes> getValidAccuracies() {
		return mValidAccuracies;
	}

	public void setValidAccuracies(ArrayList<GeocodingTypes> mValidAccuracies) {
		this.mValidAccuracies = mValidAccuracies;
	}

	public void addValidAccuracy(GeocodingTypes type) {
		mValidAccuracies.add(type);
	}
}