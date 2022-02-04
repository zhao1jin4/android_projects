package org.zh.mycomponents;
 

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;



public class ImageShowActivity  extends Activity implements
AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
	CheckBox plain_cb;
	CheckBox serif_cb;
	CheckBox italic_cb;
	CheckBox bold_cb;

 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.image_show);
        setTitle("ImageShowActivity");

        mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        
        
        
        
        
        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.hScrollView);
        LinearLayout bottom = (LinearLayout) findViewById(R.id.bottom);
    	  Integer[] mThumbIds = {
                R.drawable.sample_thumb_0, R.drawable.sample_thumb_1,
                R.drawable.sample_thumb_2, R.drawable.sample_thumb_3,
                R.drawable.sample_thumb_4, R.drawable.sample_thumb_5,
                R.drawable.sample_thumb_6, R.drawable.sample_thumb_7};

          Integer[] mImageIds = {
                R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
                R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7};
          
          for(int i=0;i<mImageIds.length;i++)
          {
        	  ImageView view = new ImageView(this);
        	 
        	  view.setBackgroundColor(0xFF000000);
        	  view.setBackground(getResources().getDrawable(mThumbIds[i],null));
        	  view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        	  view.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT, ImageSwitcher.LayoutParams.MATCH_PARENT));
        	  view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// mSwitcher.setImageResource(mImageIds[i]);
				}
        	  });
        	//view.setPadding(20, 0, 0,0 );
        	  bottom.addView(view);
          }
    }

    public void onItemSelected(AdapterView parent, View v, int position, long id) {
        mSwitcher.setImageResource(mImageIds[position]);
    }

    public void onNothingSelected(AdapterView parent) {
    }

    public View makeView() {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT,
        		ImageSwitcher.LayoutParams.MATCH_PARENT));
        return i;
    }

    private ImageSwitcher mSwitcher;

    public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new ImageSwitcher.LayoutParams(
            		ImageSwitcher.LayoutParams.WRAP_CONTENT, ImageSwitcher.LayoutParams.WRAP_CONTENT));
            i.setBackgroundResource(R.drawable.picture_frame);
            return i;
        }

        private Context mContext;

    }
 
	private Integer[] mThumbIds = {
            R.drawable.sample_thumb_0, R.drawable.sample_thumb_1,
            R.drawable.sample_thumb_2, R.drawable.sample_thumb_3,
            R.drawable.sample_thumb_4, R.drawable.sample_thumb_5,
            R.drawable.sample_thumb_6, R.drawable.sample_thumb_7};

    private Integer[] mImageIds = {
            R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
            R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7};

 
}