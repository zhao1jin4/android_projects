package org.zh.a_junittest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pre = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString("username", "hr");
        editor.commit();

        //this.getSharedPreferences("myfilename", MODE_PRIVATE);

        setContentView(R.layout.main);
    }
}