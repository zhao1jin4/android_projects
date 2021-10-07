package com.example.a_googleservice;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class HelloItemizedOverlay extends ItemizedOverlay<OverlayItem> 
{
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mContext;
	
	public HelloItemizedOverlay(Drawable defaultMarker)
	{
		super(boundCenterBottom(defaultMarker));//定位标记
	}
	public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
		}
	
	public void addOverlay(OverlayItem overlayItem) {
	    mOverlays.add(overlayItem);
	    populate();//必须先要调用这个方法
	}
	
	
	@Override
	protected OverlayItem createItem(int i)
	{
		return mOverlays.get(i);
	}
	@Override
	public int size() {
		 return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) 
	{
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
}
