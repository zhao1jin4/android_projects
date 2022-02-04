package org.zh.webview_sensor_drag_anim;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DrawableActivity extends Activity {
    ImageView img;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable);

        img = (ImageView) this.findViewById(R.id.layer_img);

    }

    public void swith(View v)//在布局中配置按钮的处理方法,带参数 View v
    {
        //--------------------layer
        LayerDrawable layer = (LayerDrawable) this.getResources().getDrawable(R.drawable.layer_list, null);//theme参数为null不能主题影响
        Drawable drawable = this.getResources().getDrawable(R.drawable.girl_1, null);
        layer.setDrawableByLayerId(R.id.photo, drawable);
        img.setImageDrawable(layer);

        //--------------------level_list
        ImageView level_img = (ImageView) this.findViewById(R.id.level_img);
        LevelListDrawable levelDrawable = (LevelListDrawable) level_img.getDrawable();
        levelDrawable.setLevel(15);//根据level  显示对应的照片

    }

    public void transaction(View v) {
        //------------transition
        Button btn = (Button) this.findViewById(R.id.button_transaction);
        TransitionDrawable transDrawable = (TransitionDrawable) btn.getBackground();
        transDrawable.startTransition(5000);

        //------------clip
        ImageView clip_img = (ImageView) this.findViewById(R.id.clip_img);
        ClipDrawable clip_drawable = (ClipDrawable) clip_img.getDrawable();
        clip_drawable.setLevel(clip_drawable.getLevel() + 1000);//默认是0,全clip不可见的,10,000是不clip是全可见的

        //------------scale
        ImageView scale_img = (ImageView) this.findViewById(R.id.scale_img);
        ScaleDrawable scale_drawable = (ScaleDrawable) scale_img.getDrawable();
        scale_drawable.setLevel(scale_drawable.getLevel() + 1000);


    }

}
