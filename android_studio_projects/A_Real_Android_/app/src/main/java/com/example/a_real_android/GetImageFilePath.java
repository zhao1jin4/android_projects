package com.example.a_real_android;
 
import android.os.Environment;
import android.util.Log;
 
import java.io.File;
import java.util.ArrayList;
 
public class GetImageFilePath {
    //获取相册camera 图片路径
    static ArrayList<String> imageList = new ArrayList<>() ;
    public static ArrayList<String> getFilePath() {
        File file= new File(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera");
        File[] dirEpub = file.listFiles();
        if (dirEpub.length != 0){
            for (int i = 0; i < dirEpub.length; i++){
                String fileName = dirEpub[i].toString();
                imageList.add(fileName);
                Log.i("File", "File name = " + fileName);
            }
        }
        return imageList;
    }
}