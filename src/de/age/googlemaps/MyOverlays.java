package de.age.googlemaps;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyOverlays extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> mOverlays =  new ArrayList<OverlayItem>();

	public MyOverlays(Drawable defaultMarker) {
		super(boundCenter(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addOverlay(OverlayItem overlayItem) {
		mOverlays.add(overlayItem);
		populate();
	}


}
