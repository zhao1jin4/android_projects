 package com.yarin.android.Examples_03_04;  
  
 import android.app.Activity;  
 import android.content.Intent;  
 import android.os.Bundle;  
 import android.util.Log;  
 import android.view.View;  
import android.widget.Button;  
import android.widget.TextView;
  
 public class Activity03 extends Activity  
 {  
       
     public void onCreate(Bundle savedInstanceState)  
     {  
         super.onCreate(savedInstanceState);  
         TextView view=new TextView(this);
         view.setText("“˛ Ω“‚Õº_Pause ,Activity Style");
         setContentView(view);  
     }  
 } 