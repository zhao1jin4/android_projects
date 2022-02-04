package org.zh.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class ReadOtherSharedPreference {
    @Test
    public void testReadA_SharedPreferences() throws Exception {

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();//生成代码使用的
        Context appContext = InstrumentationRegistry.getInstrumentation().getContext();

        //如果自已是Activity还可以用

        //A_ShredPreferences 必须 是MODE_WORLD_READABLE
        Context otherContext = context.createPackageContext("us.imnet.iceskysl.db", Context.CONTEXT_IGNORE_SECURITY);
        SharedPreferences p = otherContext.getSharedPreferences("SETTING_Infos", Context.MODE_PRIVATE);
        Map all = p.getAll();
        Assert.assertTrue(all.size() > 0);
    }
}
