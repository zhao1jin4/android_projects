package org.zhaojin.i18n;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class A_I18N_StyleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(this);
        textView.setText(R.string.hello);
       // textView.setId(34);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(textView, textParams);
        
        setContentView(linearLayout, layoutParams);
        
       setContentView(R.layout.main);
        
    }
}