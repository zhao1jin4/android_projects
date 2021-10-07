package org.zhaojin.webview_sensor_drag_anim;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class AnimActivity extends Activity
{
	ViewFlipper flipper;
	Animation inR2LAnim;
	Animation inL2RAnim;
	Animation outR2LAnim;
	Animation outL2RAnim;
	float startX=0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation);

		//tween();
		//frame();

		flipper=(ViewFlipper)this.findViewById(R.id.anim_flipper);

		inR2LAnim=AnimationUtils.loadAnimation(this, R.anim.in_r2l_flipper);
		inL2RAnim=AnimationUtils.loadAnimation(this, R.anim.in_l2r_flipper);
		outL2RAnim=AnimationUtils.loadAnimation(this, R.anim.out_l2r_flipper);
		outR2LAnim=AnimationUtils.loadAnimation(this, R.anim.out_r2l_flipper);

	}
	private void tween()
	{
		Animation tweenAnim=AnimationUtils.loadAnimation(this,R.anim.tween_motion);
		tweenAnim.setFillAfter(true);//动画结尾的形态做为以后显示的形态,而不是动画开始的形态

		//手工写程序做动画
		Animation transAnim=new TranslateAnimation(0,200,0,200);
		transAnim.setDuration(3000);

		Animation rotateAnim=new RotateAnimation(0f, 720f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		rotateAnim.setStartOffset(3000);
		rotateAnim.setDuration(5000);

		ImageView img=(ImageView)this.findViewById(R.id.anim_ImageView);
		img.startAnimation(tweenAnim);

		//img.startAnimation(transAnim);//如果把两个动画一起放???
		//img.startAnimation(rotateAnim);
	}
	private void frame()
	{
		TextView textView=(TextView)this.findViewById(R.id.anim_TextView);
		textView.setBackgroundResource(R.drawable.animation_list);
		final AnimationDrawable animDrawable=(AnimationDrawable)textView.getBackground();

		//getMainLooper().myQueue();//得到主线程的消息队列,在所有消息队列都处理完成空闲时
		Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
			public boolean queueIdle() {
				animDrawable.start();//启动动画
				return false;//表示从消息队列中删除,即只做一次
			}
		});

	}
	public boolean onTouchEvent(MotionEvent event) //重写Activity的方法
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN)
			startX=event.getX();
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			if(event.getX() >startX)//到第二页   ->
			{
				flipper.setInAnimation(inL2RAnim);
				flipper.setOutAnimation(outL2RAnim);
				flipper.showNext();

			}else if(event.getX() < startX)//到第一页    <-
			{
				flipper.setInAnimation(inR2LAnim);
				flipper.setOutAnimation(outR2LAnim);
				flipper.showPrevious();
			}
		}
		//return true;
		return super.onTouchEvent(event);
	}

}
