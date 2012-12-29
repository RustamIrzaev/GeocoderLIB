package com.irzaevrustam.geocoderlib.enums;

/** Within the Geocoding response object contains the status of the request, and may contain 
 * debugging information to help you track down why Geocoding is not working. */
public enum GeocodingStatus {
	/** Indicates that no errors occurred. The address was successfully parsed and at least 
	 * one geocode was returned. */
	OK,
	/** Indicates that the geocode was successful but returned no results. This may occur if 
	 * the geocode was passed a non-existent {@linkplain address} or a {@linkplain latlng} in a 
	 * remote location. */
	ZERO_RESULTS,
	/** Indicated that you are over your quota. */
	OVER_QUERY_LIMIT,
	/** Indicates that your request was denied, generally because of lack of a {@linkplain sensor} 
	 * parameter. */
	REQUEST_DENIED,
	/** Generally indicates that the query ({@linkplain address} or {@linkplain latlng}) is missing. */
	INVALID_REQUEST
}
