package com.example.a_real_android;
 
import android.view.ViewGroup;
 
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
 
import java.util.List;
 
public  class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> layoutList;
//    private List<String> titleList;
    public MyPagerAdapter(FragmentManager manager, List<Fragment> layoutList ){
        super(manager);
        this.layoutList = layoutList;
//        this.titleList = titleList;
    }
    @Override
    public int getCount() {
        // 页面数
        return layoutList.size();
    }
 
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return layoutList.get(position);
    }
 
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }
}