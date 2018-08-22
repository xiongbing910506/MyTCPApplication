package httpapplication.nicechina.com.mylibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.net.URLConnection;
import java.util.LinkedList;

import httpapplication.nicechina.com.mylibrary.R;

import httpapplication.nicechina.com.mylibrary.model.FileAdapter;

public class FileActivity extends AppCompatActivity {


    private LinkedList<View> m_pathViewList = new LinkedList<View>();
    private LinearLayout m_linearLayout = null;
    private FileAdapter m_fileAdapter = new FileAdapter(this);

    private ListView m_listView = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileselectform);
        m_linearLayout = (LinearLayout)findViewById(R.id.filePathLayout);
        m_listView = (ListView)findViewById(R.id.fileList);
        m_listView.setAdapter(m_fileAdapter);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(m_fileAdapter.isFile(position)){
                    FileActivity.this.openFile(m_fileAdapter.getItemFilePath(position));
                }
                else {
                    FileActivity.this.updateFilePathLayout(m_fileAdapter.getItemFilePath(position));
                    m_fileAdapter.initData(m_fileAdapter.getItemFilePath(position));
                }
            }
        };
        m_listView.setOnItemClickListener(onItemClickListener);
        File rootFile = Environment.getExternalStorageDirectory();
        m_fileAdapter.initData(rootFile.getAbsolutePath());
        this.updateFilePathLayout(rootFile.getAbsolutePath());
    }

    private void openFile(String filePath){
        File file = new File(filePath);
//        Uri uri = Uri.fromFile(file);
        Uri uri = FileProvider.getUriForFile(this,getApplicationInfo().packageName + ".fileprovider",file);  //
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        Intent.FLAG_ACTIVITY_NEW_TASK
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);   //权限必须设置
        String mimeType = URLConnection.guessContentTypeFromName(filePath);
        intent.setDataAndType(uri,mimeType);
        startActivity(intent);
    }

    private void updateFilePathLayout(String filePath){
        String rootFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        if(filePath.startsWith(rootFilePath)){
            filePath = filePath.replaceFirst(rootFilePath,"内部存储");
        }
        String[] filePortList = filePath.split("/");
        m_linearLayout.removeAllViews();
        if(filePortList != null) {
            for (int i = 0; i < filePortList.length; i++) {
                View view = View.inflate(this, R.layout.filepathbutton, null);
                view.setClickable(true);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(">>>>>>>>>>");
                    }
                });
                m_linearLayout.addView(view);
                TextView text = (TextView)view.findViewById(R.id.filePath);
                text.setText(filePortList[i]);
            }
        }
        else{
            System.out.println("目录分解为空");
        }
    }
}
