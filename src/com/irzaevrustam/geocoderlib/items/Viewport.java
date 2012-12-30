package com.irzaevrustam.geocoderlib.items;

public class Viewport {
	private double mSouthWestLat;
	private double mSouthWestLon;
	private double mNorthEastLat;
	private double mNorthEastLon;
	
	public Viewport() {
	}
	
	public Viewport(double southWestLat, double southWestLon, double northEastLat, double northEastLon) {
		mSouthWestLat = southWestLat;
		mSouthWestLon = southWestLon;
		mNorthEastLat = northEastLat;
		mNorthEastLon = northEastLon;
	}
	
	public double getSouthWestLat() {
		return mSouthWestLat;
	}
	
	public void setSouthWestLat(double mSouthWestLat) {
		this.mSouthWestLat = mSouthWestLat;
	}
	
	public double getSouthWestLon() {
		return mSouthWestLon;
	}
	
	public void setSouthWestLon(double mSouthWestLon) {
		this.mSouthWestLon = mSouthWestLon;
	}
	
	public double getNorthEastLat() {
		return mNorthEastLat;
	}
	
	public void setNorthEastLat(double mNorthEastLat) {
		this.mNorthEastLat = mNorthEastLat;
	}
	
	public double getNorthEastLon() {
		return mNorthEastLon;
	}
	
	public void setNorthEastLon(double mNorthEastLon) {
		this.mNorthEastLon = mNorthEastLon;
	}
}