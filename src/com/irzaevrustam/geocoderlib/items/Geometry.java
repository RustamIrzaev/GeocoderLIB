package com.irzaevrustam.geocoderlib.items;

import com.irzaevrustam.geocoderlib.enums.LocationType;

public class Geometry {
	private double mLocationLat;
	private double mLocationLon;
	private LocationType mLocationType = LocationType.NONE;
	private Viewport mViewport;
	
	public Geometry() {
	}
	
	public Geometry(double locationLat, double locationLon, LocationType locationType, Viewport viewport) {
		mLocationLat = locationLat;
		mLocationLon = locationLon;
		mLocationType = locationType;
		mViewport = viewport;
	}
	
	public double getLocationLat() {
		return mLocationLat;
	}
	
	public void setLocationLat(double mLocationLat) {
		this.mLocationLat = mLocationLat;
	}
	
	public double getLocationLon() {
		return mLocationLon;
	}
	
	public void setLocationLon(double mLocationLon) {
		this.mLocationLon = mLocationLon;
	}
	
	public LocationType getLocationType() {
		return mLocationType;
	}
	
	public void setLocationType(LocationType mLocationType) {
		this.mLocationType = mLocationType;
	}
	
	public Viewport getViewport() {
		return mViewport;
	}
	
	public void setViewport(Viewport mViewport) {
		this.mViewport = mViewport;
	}
}