package httpapplication.nicechina.com.httpapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import httpapplication.nicechina.com.httpapplication.model.StationData;
import httpapplication.nicechina.com.httpapplication.socket.NSTCPSocket;
import httpapplication.nicechina.com.httpapplication.socket.NSTCPSocketForLong;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText m_editText = null;
    private Button m_button = null;
    private Handler m_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(MainActivity.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
        }
    };
    private Handler m_dataHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            processData(msg.obj.toString());
        }
    };
    private void processData(String data){
//        try {
            System.out.println(data);
//            JSONObject obj = new JSONObject(new String(data));
//            JSONArray array = obj.getJSONArray("children");
//            LinkedList<StationData> stationList = new LinkedList<StationData>();
//            for(int i = 0 ; i < array.length() ; i++){
//                JSONObject station = array.getJSONObject(i);
//                StationData stationData = new StationData();
//                stationData.m_name = station.getString("name");
//                stationData.m_desc = station.getString("desc");
//                stationData.m_subip = station.getString("subip");
//                stationData.m_port = station.getString("port");
//                stationData.m_gatewayip = station.getString("gatewayip");
//                stationData.m_localip = station.getString("localip");
//                stationData.m_ver = station.getString("ver");
//                stationList.push(stationData);
//            }
//        }catch (JSONException ex){
//            System.out.println(ex.getMessage());
//        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.testButton)
        {
            NSTCPSocketForLong.getInstance().sendData("{\"cmd\":\"listSubstation\"}");
        }
        else if(v.getId() == R.id.testButton2)
        {
            NSTCPSocketForLong.getInstance().sendData("{\"cmd\":\"listDevice\",\"sub\":\"测试子站\",\"scd\":\"station.scd\"}");
        }
        else if(v.getId() == R.id.testButton3)
        {
            NSTCPSocketForLong.getInstance().sendData("{\"cmd\":\"start\",\"name\":\"测试子站\",\"localip\":\"192.168.12.56\",\"inst\":\"1\",\"scd\":\"station.scd\",\"ver\":\"MMS\",\"ied\":\"PL2211A\",\"dsc\":\"#1主变保护A套\",\"ip\":\"192.168.10.21\",\"sets\":\"\",\"user\":\"测试人\"}");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NSTCPSocketForLong.getInstance().initErrorHandler(m_handler);
        NSTCPSocketForLong.getInstance().initDataHandler(m_dataHandler);
        setContentView(R.layout.mainform);
        m_editText = (EditText)findViewById(R.id.testUser);
        m_button = (Button)findViewById(R.id.testButton);
        m_button.setOnClickListener(this);
        ((Button)findViewById(R.id.testButton2)).setOnClickListener(this);
        ((Button)findViewById(R.id.testButton3)).setOnClickListener(this);
    }

}
