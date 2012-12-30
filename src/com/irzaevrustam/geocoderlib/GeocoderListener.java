package com.irzaevrustam.geocoderlib;

import com.irzaevrustam.geocoderlib.enums.GeocodingStatus;

public interface GeocoderListener {
	public void onAddressGotStatus(GeocodingStatus status);
	
	public void onAddressGotFailedStatus(String message);
	
	public void onAddressGotResults(GeocoderResult result);
	
	public void onAddressGotResult(GeocoderItem result);
	
	public void onAddressZeroResult();
	
	public void onConnectionFailed();
}