package com.irzaevrustam.geocoderlib;

import com.irzaevrustam.geocoderlib.enums.GeocodingStatus;
import com.irzaevrustam.geocoderlib.enums.GeocodingType;
import com.irzaevrustam.geocoderlib.enums.GeocodingTypes;
import com.irzaevrustam.geocoderlib.items.AddressComponent;
import com.irzaevrustam.geocoderlib.enums.LocationType;

import java.util.ArrayList;
import java.util.Collections;

public class AddressHelper {
	public static final String TAG_ADDRESS_OR_LATLNG = "{address_or_latlng}";
	public static final String TAG_SENSOR = "{sensor}";
	public static final String TAG_LATLNG = "{latlng}";
	//TODO Not implemented yet.
//	public static final String TAG_BOUNDS = "{bounds}";
	//TODO Not implemented yet.
//	public static final String TAG_REGION = "{region}";
	public static final String TAG_TYPE = "{type}";
	public static final String TAG_LANGUAGE = "{language}";
	public static final String TYPE_LANGUAGE = "language";
	public static final String TYPE_ADDRESS = "address";
	public static final String TYPE_LATLNG = "latlng";

	public static final String GAPI_URL = "http://maps.googleapis.com/maps/api/geocode/json?" +
			TAG_TYPE + "=" + TAG_ADDRESS_OR_LATLNG + "&sensor=" + TAG_SENSOR;

//	public static final String MESSAGE_INVALID_REQUEST = "Invalid request.";

	public static String buildUrl(GeocoderOptions options) {
		String url = GAPI_URL;

		if (options == null) {
			url = url.replace(TAG_SENSOR, "true");
			url = url.replace(TAG_TYPE, TYPE_ADDRESS);
		} else {
			url = url.replace(TAG_SENSOR, options.isSensor() ? "true" : "false");
			if (options.getGeocodingType() == GeocodingType.BY_ADDRESS) {
				url = url.replace(TAG_TYPE, TYPE_ADDRESS);
			} else {
				url = url.replace(TAG_TYPE, TYPE_LATLNG);
			}

			String language = options.getLanguage().toString();
			if (language.length() == 4) {
				language = (language.substring(0, 1) + "-" + language.substring(2, 3));
			}
			url = url + "&" + TYPE_LANGUAGE + "=" + language;
		}

		return url;
	}

	public static GeocodingStatus stringToStatus(String source) {
		if (source.equalsIgnoreCase(GeocodingStatus.INVALID_REQUEST.name())) {
			return GeocodingStatus.INVALID_REQUEST;
		} else if (source.equalsIgnoreCase(GeocodingStatus.OK.name())) {
			return GeocodingStatus.OK;
		} else if (source.equalsIgnoreCase(GeocodingStatus.OVER_QUERY_LIMIT.name())) {
			return GeocodingStatus.OVER_QUERY_LIMIT;
		} else if (source.equalsIgnoreCase(GeocodingStatus.REQUEST_DENIED.name())) {
			return GeocodingStatus.REQUEST_DENIED;
		} else if (source.equalsIgnoreCase(GeocodingStatus.ZERO_RESULTS.name())) {
			return GeocodingStatus.ZERO_RESULTS;
		}

		return GeocodingStatus.INVALID_REQUEST;
	}

	private static GeocodingTypes stringToTypes(String source) {
		if (source.equalsIgnoreCase(GeocodingTypes.ADMINISTRATIVE_AREA_LEVEL_1.name())) {
			return GeocodingTypes.ADMINISTRATIVE_AREA_LEVEL_1;
		} else if (source.equalsIgnoreCase(GeocodingTypes.ADMINISTRATIVE_AREA_LEVEL_2.name())) {
			return GeocodingTypes.ADMINISTRATIVE_AREA_LEVEL_2;
		} else if (source.equalsIgnoreCase(GeocodingTypes.ADMINISTRATIVE_AREA_LEVEL_3.name())) {
			return GeocodingTypes.ADMINISTRATIVE_AREA_LEVEL_3;
		} else if (source.equalsIgnoreCase(GeocodingTypes.AIRPORT.name())) {
			return GeocodingTypes.AIRPORT;
		} else if (source.equalsIgnoreCase(GeocodingTypes.COLLOQUIAL_AREA.name())) {
			return GeocodingTypes.COLLOQUIAL_AREA;
		} else if (source.equalsIgnoreCase(GeocodingTypes.COUNTRY.name())) {
			return GeocodingTypes.COUNTRY;
		} else if (source.equalsIgnoreCase(GeocodingTypes.FLOOR.name())) {
			return GeocodingTypes.FLOOR;
		} else if (source.equalsIgnoreCase(GeocodingTypes.INTERSECTION.name())) {
			return GeocodingTypes.INTERSECTION;
		} else if (source.equalsIgnoreCase(GeocodingTypes.LOCALITY.name())) {
			return GeocodingTypes.LOCALITY;
		} else if (source.equalsIgnoreCase(GeocodingTypes.NATURAL_FEATURE.name())) {
			return GeocodingTypes.NATURAL_FEATURE;
		} else if (source.equalsIgnoreCase(GeocodingTypes.NEIGHBORHOOD.name())) {
			return GeocodingTypes.NEIGHBORHOOD;
		} else if (source.equalsIgnoreCase(GeocodingTypes.PARK.name())) {
			return GeocodingTypes.PARK;
		} else if (source.equalsIgnoreCase(GeocodingTypes.POINT_OF_INTEREST.name())) {
			return GeocodingTypes.POINT_OF_INTEREST;
		} else if (source.equalsIgnoreCase(GeocodingTypes.POLITICAL.name())) {
			return GeocodingTypes.POLITICAL;
		} else if (source.equalsIgnoreCase(GeocodingTypes.POST_BOX.name())) {
			return GeocodingTypes.POST_BOX;
		} else if (source.equalsIgnoreCase(GeocodingTypes.POSTAL_CODE.name())) {
			return GeocodingTypes.POSTAL_CODE;
		} else if (source.equalsIgnoreCase(GeocodingTypes.PREMISE.name())) {
			return GeocodingTypes.PREMISE;
		} else if (source.equalsIgnoreCase(GeocodingTypes.ROOM.name())) {
			return GeocodingTypes.ROOM;
		} else if (source.equalsIgnoreCase(GeocodingTypes.ROUTE.name())) {
			return GeocodingTypes.ROUTE;
		} else if (source.equalsIgnoreCase(GeocodingTypes.STREET_ADDRESS.name())) {
			return GeocodingTypes.STREET_ADDRESS;
		} else if (source.equalsIgnoreCase(GeocodingTypes.STREET_NUMBER.name())) {
			return GeocodingTypes.STREET_NUMBER;
		} else if (source.equalsIgnoreCase(GeocodingTypes.SUBLOCALITY.name())) {
			return GeocodingTypes.SUBLOCALITY;
		} else if (source.equalsIgnoreCase(GeocodingTypes.SUBPREMISE.name())) {
			return GeocodingTypes.SUBPREMISE;
		}

		return GeocodingTypes.NONE;
	}

	public static LocationType stringToLocationType(String source) {
		if (source.equalsIgnoreCase(LocationType.APPROXIMATE.name())) {
			return LocationType.APPROXIMATE;
		} else if (source.equalsIgnoreCase(LocationType.GEOMETRIC_CENTER.name())) {
			return LocationType.GEOMETRIC_CENTER;
		} else if (source.equalsIgnoreCase(LocationType.RANGE_INTERPOLATED.name())) {
			return LocationType.RANGE_INTERPOLATED;
		} else if (source.equalsIgnoreCase(LocationType.ROOFTOP.name())) {
			return LocationType.ROOFTOP;
		}

		return LocationType.NONE;
	}

	public static String messageAccordingToStatus(GeocodingStatus status) {
		return status.name();
	}

	public static ArrayList<GeocodingTypes> decodeTypes(String source) {
		ArrayList<GeocodingTypes> result = new ArrayList<GeocodingTypes>();

		source = source.trim().replace("[", "").replace("]", "").replaceAll("\"", "");
		String[] typesSplitted = source.split(",");
        for (String aTypesSplitted : typesSplitted) {
            result.add(AddressHelper.stringToTypes(aTypesSplitted));
        }

		return result;
	}

	public static String typesToString(ArrayList<GeocodingTypes> types) {
		String result = "";

		for (int i = 0; i < types.size(); i++) {
			result += types.get(i);

			if (i != types.size() -1) {
				result += ", ";
			}
		}

		return result;
	}
	
	public static String getSpecificData(GeocoderItem item, GeocodingTypes...geocodingTypes) {
		ArrayList<GeocodingTypes> geoTypes = new ArrayList<GeocodingTypes>();
        Collections.addAll(geoTypes, geocodingTypes);
		
		for (int i = 0; i < item.getAddressComponents().size(); i++) {
			AddressComponent address = item.getAddressComponents().get(i);
			if (address.getTypes().containsAll(geoTypes)) {
				return item.getAddressComponents().get(i).getLongName();
			}
		}
		
		return "";
	}
}