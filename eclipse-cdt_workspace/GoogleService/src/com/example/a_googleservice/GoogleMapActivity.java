package com.example.a_googleservice;


import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;

//未测试,不能代理上网
public class GoogleMapActivity extends MapActivity //Activity
{
	GeoPoint begin;
	GeoPoint end;
	TextView textView;
	WebView webView;
	
	Projection projection;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        //useWebView();
        //helloGoogleMap();
        myGoogleMap();
    }
    void myGoogleMap()
    {
    	MapView mapView = (MapView) findViewById(R.id.mapView);
    	mapView.setBuiltInZoomControls(true);//使用缩放工具
    	projection =mapView.getProjection();
    	
    	begin=new GeoPoint(31, 121);
    	end=new GeoPoint(41, 131);
    	
    	List <Overlay> allOverlay=mapView.getOverlays();
        allOverlay.add(new PointOverlay(begin));
        allOverlay.add(new PointOverlay(end));
        allOverlay.add(new LineOverlay(begin, end));
    	
    	MapController controller=mapView.getController();
    	controller.animateTo(begin);
    	controller.setZoom(12);
    
    }
    void helloGoogleMap()
    {

        MapView mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);//使用缩放工具
      
       Drawable drawable=getResources().getDrawable(R.drawable.ic_launcher);
       GeoPoint geo=new GeoPoint(31, 121);
       OverlayItem overlayItem =new OverlayItem(geo,"title","content");
       
       HelloItemizedOverlay helloOverlay=new HelloItemizedOverlay(drawable, this);
       helloOverlay.addOverlay(overlayItem);
       
       List <Overlay> allOverlay=mapView.getOverlays();
       allOverlay.add(helloOverlay);
    }
    void useWebView()
    {
        textView=(TextView)this.findViewById(R.id.textView1);
        webView=(WebView)this.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/google_map_js_v3/map-simple.html") ;//android_asset对应assets目录,也可是http://
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	@Override
	protected boolean isRouteDisplayed() //MapActivity 
	{
		return false;
	}
	
	
	
	 
    class LineOverlay extends  Overlay
    {
    	GeoPoint beginGeo;
    	GeoPoint endGeo;
    	public LineOverlay(GeoPoint begin, GeoPoint end) {
    		super();
    		this.beginGeo = begin;
    		this.endGeo = end;
    	}
    	@Override
    	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    		super.draw(canvas,mapView,shadow);
    		Paint paint=new Paint();
    		paint.setColor(Color.BLUE);
    		paint.setStyle(Paint.Style.FILL_AND_STROKE);
    		paint.setStrokeWidth(2);
    		Point begin =new Point();
    		Point end =new Point();
    		Path path=new Path();
    	//	projection.fromPixels(arg0, arg1)
    		projection.toPixels(beginGeo, begin);//把GeoPoint纬经度 转换为屏幕坐标
    		projection.toPixels(endGeo, end);
    		path.moveTo(begin.x,begin.y);
    		path.lineTo(end.x,end.y);
    		canvas.drawPath(path, paint);
    	}
    	
    }
    
    
    class PointOverlay extends  Overlay
    {
    	GeoPoint geoPoint;
    	public PointOverlay(GeoPoint point) {
    		this.geoPoint=point;
    	}
    	@Override
    	public void draw(Canvas canvas, MapView mapView, boolean shadow) 
    	{
    		super.draw(canvas,mapView,shadow);
    		Point point =new Point();
    		projection.toPixels(geoPoint, point);//把GeoPoint纬经度 转换为屏幕坐标
    		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    		Paint paint=new Paint();
    		canvas.drawBitmap(bitmap , point.x , point.y , paint);
    	}
    	
    }
}
