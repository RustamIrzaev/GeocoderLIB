package com.irzaevrustam.geocoderlib.enums;

/** Stores additional data about the specified location. */
public enum LocationType {
	NONE,
	/** Indicates that the returned result is a precise geocode for 
	 * which we have location information accurate down to street 
	 * address precision. */
	ROOFTOP,
	/** Indicates that the returned result reflects an approximation 
	 * (usually on a road) interpolated between two precise points 
	 * (such as intersections). Interpolated results are generally 
	 * returned when rooftop geocodes are unavailable for a street 
	 * address. */
	RANGE_INTERPOLATED,
	/** Indicates that the returned result is the geometric center 
	 * of a result such as polyline (for example, a street) or 
	 * polygon (region). */
	GEOMETRIC_CENTER,
	/** Indicates that the returned result is approximate. */
	APPROXIMATE
}
