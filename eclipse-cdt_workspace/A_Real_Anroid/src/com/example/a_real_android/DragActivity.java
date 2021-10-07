package com.example.a_real_android;


import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/*
public class DragActivity extends Activity
{
	ImageView img;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new MyDragView(this));//��дonTouchEvent,ͬOnTouchListener
        
        setContentView(R.layout.touch_drag);
        img=(ImageView)this.findViewById(R.id.touch_drag);
        img.setOnTouchListener(new MyTouchListener());
    }
    
    //Ҫ���������ϳ��߽�
    private class MyTouchListener implements OnTouchListener
    {
    	PointF point=new PointF();
    	Matrix matrix=new Matrix();//������
    	Matrix previous=new Matrix();//ÿ���ƶ�������һ���ƶ������ŵ�
    	
    	boolean isZoom=false;
    	float previousZoomDistance;
    	PointF midPoint;
		public boolean onTouch(View v, MotionEvent event) 
		{
			//switch ( event.getAction()& MotionEvent.ACTION_MASK )
			switch (event.getActionMasked())
			{
			case MotionEvent.ACTION_DOWN:
				isZoom=false;
				
				previous.set(img.getImageMatrix());//������ǰ���ƶ�
				point.set(event.getX(),event.getY());
				break;
			case MotionEvent.ACTION_MOVE:  //���α�����
				if(isZoom)//����
				{
					if(distance(event)>10)//��ֹһ����ָ����������
					{
						float zoomDistance=distance(event);
						float zoom=zoomDistance/previousZoomDistance;
						matrix.set(previous);//������һ������
						matrix.postScale(zoom, zoom,midPoint.x,midPoint.y );
					}
				}else//�ƶ�
				{
					float distanceX=event.getX()-point.x;
					float distanceY=event.getY()-point.y;
					//��εõ��ֻ���Ļ��С��
					
					matrix.set(previous);//������һ���ƶ�
					matrix.postTranslate(distanceX, distanceY);
				}
				break;
			case MotionEvent.ACTION_POINTER_DOWN://�Զ�㴥��
				isZoom=true;
				previousZoomDistance=distance(event);
				if(distance(event)>10)//��ֹһ����ָ����������
				{
					midPoint=mid(event);
					previous.set(img.getMatrix());//������ǰ������
				}
				break;
			case MotionEvent.ACTION_UP:
				break;	
			case MotionEvent.ACTION_POINTER_UP:
				break;	
			default:
				break;
			}
			
			img.setImageMatrix(matrix);//ʹͼƬ�任
			
			return true; //����true��ʾ��������¼�
		}
    	public float distance(MotionEvent event)
    	{
    		float x=event.getX(1)-event.getX(0);
    		float y=event.getY(1)-event.getY(0);
    		float result=FloatMath.sqrt(x*x+y*x);
    		return result;
    	}
    	public PointF mid(MotionEvent event)
    	{
    		//float distance=point.length(event.getX(), event.getY());
    		
    		float x=(event.getX(1)+event.getX(0) )/2;
    		float y=(event.getY(1)-event.getY(0))/2;
    		PointF result=new PointF();
    		result.set(x, y);
    		return result;
    	}
    }
 }   
    */
public class DragActivity extends Activity  
{  
    private static final int NONE = 0;  
    private static final int DRAG = 1;  
    private static final int ZOOM = 2;  
  
    private int mode = NONE;  
    private float oldDist;  
    private Matrix matrix = new Matrix();  
    private Matrix savedMatrix = new Matrix();  
    private PointF start = new PointF();  
    private PointF mid = new PointF();  
  
    @Override  
    public void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.touch_drag);  
        ImageView view = (ImageView) findViewById(R.id.touch_drag);  
        view.setOnTouchListener(new OnTouchListener()  
        {  
            @Override  
            public boolean onTouch(View v, MotionEvent event)  
            {  
                ImageView view = (ImageView) v;  
                switch (event.getAction() & MotionEvent.ACTION_MASK)  
                {  
                case MotionEvent.ACTION_DOWN:  
                    savedMatrix.set(matrix);  
                    start.set(event.getX(), event.getY());  
                    mode = DRAG;  
                    break;  
                case MotionEvent.ACTION_UP:  
                case MotionEvent.ACTION_POINTER_UP:  
                    mode = NONE;  
                    break;  
                case MotionEvent.ACTION_POINTER_DOWN:  
                    oldDist = spacing(event);  
                    if (oldDist > 10f)  //��ֹһ����ָ����������
                    {  
                        savedMatrix.set(matrix);  
                        midPoint(mid, event);  
                        mode = ZOOM;  
                    }  
                    break;  
                case MotionEvent.ACTION_MOVE:  
                    if (mode == DRAG)  
                    {  
                        matrix.set(savedMatrix);  
                        matrix.postTranslate(event.getX() - start.x,  
                                event.getY() - start.y);  
                    }  
                    else if (mode == ZOOM)  
                    {  
                        float newDist = spacing(event);  
                        if (newDist > 10f)  //��ֹһ����ָ����������
                        {  
                            matrix.set(savedMatrix);  
                            float scale = newDist / oldDist;  
                            matrix.postScale(scale, scale, mid.x, mid.y);  
                        }  
                    }  
                    break;  
                }  
                // ͼƬ���þ���  
                view.setImageMatrix(matrix);  
                return true;   //����true��ʾ��������¼�
            }  
  
            private float spacing(MotionEvent event)  
            {  
                float x = event.getX(0) - event.getX(1);  
                float y = event.getY(0) - event.getY(1);  
                return FloatMath.sqrt(x * x + y * y);  
            }  
  
            private void midPoint(PointF point, MotionEvent event)  
            {  
                float x = event.getX(0) + event.getX(1);  
                float y = event.getY(0) + event.getY(1);  
                point.set(x / 2, y / 2);  
            }  
        });  
    }  
} 
    

