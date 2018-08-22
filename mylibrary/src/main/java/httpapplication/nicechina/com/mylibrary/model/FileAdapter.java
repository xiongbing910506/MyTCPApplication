package httpapplication.nicechina.com.mylibrary.model;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import httpapplication.nicechina.com.mylibrary.R;

class FileInfoCompare implements Comparator<FileInfo> {

    @Override
    public int compare(FileInfo o1, FileInfo o2) {
        if(o1.isFile && !o2.isFile){
            return 1;
        }else if(!o1.isFile && o2.isFile){
            return -1;
        }
        else {
            if (o1.fileName.toUpperCase().compareTo(o2.fileName.toUpperCase()) > 0) {
                return 1;
            } else if (o1.fileName.toUpperCase().compareTo(o2.fileName.toUpperCase()) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

public class FileAdapter extends BaseAdapter {
    private Context m_context = null;
    private LinkedList<FileInfo> m_fileInfoList = new LinkedList<FileInfo>();
    private LinkedList<String> m_filterList = new LinkedList<String>();
    private FileFilter m_filter = null;

    public FileAdapter(Context context){
        m_context = context;
    }

    /**
     *
     * @param filters 格式：["xlsx","xls"]
     */
    public void initFilter(final String[] filters){
        m_filterList.clear();
        for(int i = 0 ; i < filters.length ; i++){
            m_filterList.push(filters[i].toUpperCase());
        }
        if(0 == m_filterList.size())
        {
            m_filter = null;
        }
        else {
            m_filter = new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.isFile()) {
                        if (0 == m_filterList.size()) {
                            return true;
                        } else {
                            String[] list = pathname.getName().split(".");
                            String filter = list[list.length - 1].toUpperCase();
                            if (m_filterList.contains(filter)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else {
                        return true;
                    }
                }
            };
        }
    }

    /**
     *
     * @param filePath :文件全路径
     */
    public void initData(String filePath){
        m_fileInfoList.clear();
        this.notifyDataSetChanged();
        File file = new File(filePath);
        File[] fileList = null;
        if(null == m_filter){
            fileList = file.listFiles();
        }
        else{
            fileList = file.listFiles(m_filter);
        }
        for(int i = 0 ; i < fileList.length ; i++){
            FileInfo fileInfo = new FileInfo(m_context,fileList[i]);
            m_fileInfoList.push(fileInfo);
        }
        Collections.sort(m_fileInfoList,new FileInfoCompare());
        this.notifyDataSetChanged();
    }

    public String getItemFilePath(int position){
        return m_fileInfoList.get(position).filePath;
    }

    public boolean isFile(int position){
        return m_fileInfoList.get(position).isFile;
    }

    @Override
    public int getCount() {
        return m_fileInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return m_fileInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView = View.inflate(m_context, R.layout.fileitem,null);
            FileInfo fileInfo = m_fileInfoList.get(position);
            ImageView image = (ImageView)convertView.findViewById(R.id.fileIcon);
            image.setImageResource(fileInfo.recId);
            TextView text= (TextView)convertView.findViewById(R.id.fileName);
            text.setText(fileInfo.fileName);
        }
        else{
            FileInfo fileInfo = m_fileInfoList.get(position);
            ImageView image = (ImageView)convertView.findViewById(R.id.fileIcon);
            image.setImageResource(fileInfo.recId);
            TextView text= (TextView)convertView.findViewById(R.id.fileName);
            text.setText(fileInfo.fileName);
        }
        return convertView;
    }
}
