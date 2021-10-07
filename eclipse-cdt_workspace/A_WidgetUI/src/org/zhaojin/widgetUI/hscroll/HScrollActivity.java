package  org.zhaojin.widgetUI.hscroll ;

import java.util.HashMap;
import java.util.Map;



import org.zhaojin.widgetUI.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

 
public class HScrollActivity extends Activity implements ViewFactory {

	public ImageSwitcher imageSwitcher = null; // 图片切换
	private HScrollLayout movieLayout = null;
	private HScrollAdapter adapter = null;
	private IntentFilter intentFilter = null;
	private BroadcastReceiver receiver = null;
	private int nCount = 0;
	// pic in the drawable
	
	 Integer[] images = {
             R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
             R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
             R.drawable.sample_6, R.drawable.sample_7};
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.h_scroll);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
		imageSwitcher.setFactory(this);
		movieLayout = (HScrollLayout) findViewById(R.id.movieLayout);
		adapter = new HScrollAdapter(this);
		for (int i = 0; i < images.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", images[i]);
			// map.put("image", getResources().getDrawable(images[i]));
			map.put("index", (i+1));
			adapter.addObject(map);
		}
		movieLayout.setAdapter(adapter);
		// 设置当前显示的图片
		imageSwitcher.setImageResource(images[nCount]);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (receiver == null) {
			receiver = new UpdateImageReceiver();
		}
		registerReceiver(receiver, getIntentFilter());
	}

	/**
	 * 创建一个消息过滤器
	 * 
	 * @return
	 */
	private IntentFilter getIntentFilter() {
		if (intentFilter == null) {
			intentFilter = new IntentFilter();
			intentFilter.addAction("UPDATE_IMAGE_ACTION");
		}
		return intentFilter;
	}
	
	/**
	 * 创建一个用于添加到视图转换器(ViewSwitcher)中的新视图
	 */
	@Override
	public View makeView() {
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setBackgroundColor(0xFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		return imageView;
	}
	
	class UpdateImageReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals("UPDATE_IMAGE_ACTION")){
				int index = intent.getIntExtra("index", Integer.MAX_VALUE);
				imageSwitcher.setImageResource(images[index-1]);
				System.out.println("UpdateImageReceiver----" + index);
			}
		}
		
	}
}