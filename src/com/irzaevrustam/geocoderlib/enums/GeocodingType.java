package com.irzaevrustam.geocoderlib.enums;

/** Type of geocoding: by address ({@linkplain BY_ADDRESS}) or by coordinates ({@linkplain BY_COORDINATES}). */
public enum GeocodingType {
	/** Address as an input parameter. Output will contain all results with coordinates if found. */
	BY_ADDRESS,
	/** Coordinates as an input parameter. In this case output will contain addresses. */
	BY_COORDINATES
}
