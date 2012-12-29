package com.irzaevrustam.geocoderlib;

import com.irzaevrustam.geocoderlib.enums.GeocodingStatus;

public interface GeocoderListener {
	/** Fires when parser obtain any of statuses.
	 * @param status {@link com.irzaevrustam.geocoderlib.enums.GeocodingStatus} */
	public void onAddressGotStatus(GeocodingStatus status);
	
	/** Fires when something goes wrong.
	 * @param message Message indicates the problem. */
	public void onAddressGotFailedStatus(String message);
	
	/** Fires when parser got more than one result.
	 * @param result All obtained results. */
	public void onAddressGotResults(GeocoderResult result);
	
	/** Fires when parser obtain result.
	 * Also this listener can fire if more than one item was parsed and 
	 * in case of additional dialog with address selection needs to be shows.
	 * @param result Current address. */
	public void onAddressGotResult(GeocoderItem result);
	
	/** Fires when no address was found. */
	public void onAddressZeroResult();
	
	/** Internet issues. */
	public void onConnectionFailed();
}
