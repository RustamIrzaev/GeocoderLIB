package com.irzaevrustam.geocoderlib;

import com.irzaevrustam.geocoderlib.enums.GeocodingStatus;

import java.util.ArrayList;

public class GeocoderResult {
	private GeocodingStatus mStatus;
	private ArrayList<GeocoderItem> mItems = new ArrayList<GeocoderItem>();

	public GeocodingStatus getStatus() {
		return mStatus;
	}

	public void setStatus(GeocodingStatus mStatus) {
		this.mStatus = mStatus;
	}

	public ArrayList<GeocoderItem> getItems() {
		return mItems;
	}

	public void setItems(ArrayList<GeocoderItem> mItems) {
		this.mItems = mItems;
	}
	
	public void addItem(GeocoderItem item) {
		mItems.add(item);
	}
	
	public GeocoderItem getItem(int pos) {
		return mItems.get(pos);
	}
	
	public int size() {
		return mItems.size();
	}
}