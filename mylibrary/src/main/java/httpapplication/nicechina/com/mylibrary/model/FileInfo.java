package httpapplication.nicechina.com.mylibrary.model;


import android.content.Context;

import java.io.File;

import httpapplication.nicechina.com.mylibrary.R;

public class FileInfo {
    public String fileName;
    public String filePath;
    public long lastModified;
    public long length;
    public int recId;
    public boolean isFile;

    public FileInfo(Context context,File file){
        fileName = file.getName();
        filePath = file.getAbsolutePath();
        lastModified = file.lastModified();
        length = file.length();
        recId = getResIdByFileName(context,file);
        isFile = file.isFile();
    }

    public static int getResIdByFileName(Context context, File file){
        if(file.isDirectory()){
            return R.drawable.file_dir;
        }
        else {
            String name = file.getName();
            String resourceName = "file_" + name.substring(name.lastIndexOf(".") + 1);
            int resID = context.getResources().getIdentifier(resourceName, "drawable", context.getApplicationInfo().packageName);
            if(0==resID){
                return R.drawable.file_unknow;
            }
            else {
                return resID;
            }
        }
    }
}
