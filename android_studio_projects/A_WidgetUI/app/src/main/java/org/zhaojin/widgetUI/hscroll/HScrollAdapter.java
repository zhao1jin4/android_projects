package org.zhaojin.widgetUI.hscroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zhaojin.widgetUI.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
public class HScrollAdapter extends BaseAdapter {

	private List<Map<String,Object>> list;
	private Context context;
	public HScrollAdapter(Context context){
		this.context=context;
		this.list=new ArrayList<Map<String,Object>>();
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Map<String,Object> getItem(int location) {
		return list.get(location);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	public void addObject(Map<String,Object> map){
		list.add(map);
		notifyDataSetChanged();
	}
	
	  
	@Override
	public View getView(int location, View arg1, ViewGroup arg2) {
		
		
	 ImageView image = new ImageView(context);

		   Integer[] mThumbIds = {
		            R.drawable.sample_thumb_0, R.drawable.sample_thumb_1,
		            R.drawable.sample_thumb_2, R.drawable.sample_thumb_3,
		            R.drawable.sample_thumb_4, R.drawable.sample_thumb_5,
		            R.drawable.sample_thumb_6, R.drawable.sample_thumb_7};

		   image.setImageResource(mThumbIds[location]);
		   image.setAdjustViewBounds(true);
		   image.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		   image.setBackgroundResource(R.drawable.picture_frame);
         
         //---------
//		Map<String,Object> map=getItem(location);  
//		//image.setBackground((Drawable)map.get("image"));
//		image.setBackgroundResource((Integer) map.get("image"));
		   
		   
		   
		return image;
	}

}