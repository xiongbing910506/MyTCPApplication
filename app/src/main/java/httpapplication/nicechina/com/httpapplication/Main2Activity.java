package httpapplication.nicechina.com.httpapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.koushikdutta.async.http.body.JSONObjectBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import httpapplication.nicechina.com.httpapplication.model.NSListItem;
import httpapplication.nicechina.com.httpapplication.model.NSStationItem;
import httpapplication.nicechina.com.httpapplication.model.StationData;
import httpapplication.nicechina.com.httpapplication.socket.NSTCPSocket;
import httpapplication.nicechina.com.httpapplication.socket.NSTCPSocketForLong;

public class Main2Activity extends AppCompatActivity {

    private ListView m_listView = null;
    NSStationItem listItem = new NSStationItem(this);
    private Handler m_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            processData(msg.obj.toString());
        }
    };
    private void processData(String data){
        try {
            JSONObject obj = new JSONObject(new String(data));
            JSONArray array = obj.getJSONArray("children");
            LinkedList<StationData> stationList = new LinkedList<StationData>();
            for(int i = 0 ; i < array.length() ; i++){
                JSONObject station = array.getJSONObject(i);
                StationData stationData = new StationData();
                stationData.m_name = station.getString("name");
                stationData.m_desc = station.getString("desc");
                stationData.m_subip = station.getString("subip");
                stationData.m_port = station.getString("port");
                stationData.m_gatewayip = station.getString("gatewayip");
                stationData.m_localip = station.getString("localip");
                stationData.m_ver = station.getString("ver");
                stationList.push(stationData);
            }
            listItem.initStationList(stationList);
        }catch (JSONException ex){
            System.out.println(ex.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        m_listView = (ListView)findViewById(R.id.listView);
        m_listView.setAdapter(listItem);
        m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                System.out.println(id);
                System.out.println(parent.getAdapter().getItem(position));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        NSTCPSocketForLong.getInstance().initDataHandler(m_handler);
        NSTCPSocketForLong.getInstance().sendData("{\"cmd\":\"listSubstation\"}");
    }

    @Override
    protected void onStop() {
        super.onStop();
        NSTCPSocketForLong.getInstance().clearDataHandler();
    }
}
