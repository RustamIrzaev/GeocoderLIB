package com.irzaevrustam.geocoderlib.enums;

public enum GeocodingTypes {
	NONE,
	/** Indicates a precise street address. */
	STREET_ADDRESS,
	/** Indicates a named route (such as "US 101"). */
	ROUTE,
	/** Indicates a major intersection, usually of two major roads. */
	INTERSECTION,
	/** Indicates a political entity. usually, this type indicates a polygon of some 
	 * civil administration. */
	POLITICAL,
	/** Indicates the national political entity, and is typically the highest order 
	 * type returned by the Geocoder. */
	COUNTRY,
	/** Indicates a first-order civil entity below the country level. Within the 
	 * United States, these administrative levels are states. Not all nations exhibit 
	 * these administrative levels. */
	ADMINISTRATIVE_AREA_LEVEL_1,
	/** Indicates a second-order civil entity below the country level. Within the 
	 * United States, these administrative levels are states. Not all nations exhibit 
	 * these administrative levels. */
	ADMINISTRATIVE_AREA_LEVEL_2,
	/** Indicates a third-order civil entity below the country level. This type 
	 * indicates a minor civil division. Not all nations exhibit these 
	 * administrative levels.*/
	ADMINISTRATIVE_AREA_LEVEL_3,
	/** Indicates a commonly-used alternative name for the entity. */
	COLLOQUIAL_AREA,
	/** Indicates an incorporated city or town political entity. */
	LOCALITY,
	/** Indicates an first-order civil entity below a locality. */
	SUBLOCALITY,
	/** Indicates a named neighborhood. */
	NEIGHBORHOOD,
	/** Indicates a named location, usually a building or collection of buildings
	 * with a common name. */
	PREMISE,
	/** Indicates a first-order entity below a named location, usually a singular
	 * building within a collection of buildings with a common name. */
	SUBPREMISE,
	/** Indicates a postal code as used to address postal mail within the country. */
	POSTAL_CODE,
	/** Indicates a prominent natural feature. */
	NATURAL_FEATURE,
	/** Indicates an airport. */
	AIRPORT,
	/** Indicates a named park. */
	PARK,
	/** Indicates a named point of interest. Typically, these "POI"s are prominent
	 * local entities that don't easily fit in another category such as "Empire
	 * State Building" or "Statue of Liberty". */
	POINT_OF_INTEREST,
	/** Indicates a specific postal box. */
	POST_BOX,
	/** Indicates the precise street number. */
	STREET_NUMBER,
	/** Indicates the floor of a building address. */
	FLOOR,
	/** Indicates the room of a building address. */
	ROOM
}