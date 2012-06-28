package de.age.car2goil2k2;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyOverlays extends ItemizedOverlay<OverlayItem> {
	
//	GeoPoint p;
	Context context;
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

	public MyOverlays(Drawable defaultMarker, Context context) {
		super(boundCenter(defaultMarker));
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		
		if (!shadow) {
			super.draw(canvas, mapView, false);
		}
		
//		Point iconPoint = new Point();
//		mapView.getProjection().toPixels(p, iconPoint);
		
		
	}



	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}
	
	public void adOverlay(OverlayItem overlayItem) {
		
		mOverlays.add(overlayItem);
		populate();
	}
	
	
	
	@Override
		protected boolean onTap(int index) {
			// TODO Auto-generated method stub
		
		OverlayItem item = (OverlayItem) mOverlays.get(index); 
		 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//		 	dialog.setItems(r, listener)
	       dialog.setTitle(item.getTitle());
	       dialog.setMessage(item.getSnippet());
//	       dialog.setView(view);
	       dialog.show();
	       return true;

		}

}
