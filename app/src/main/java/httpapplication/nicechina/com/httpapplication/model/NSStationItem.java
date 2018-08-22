package httpapplication.nicechina.com.httpapplication.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import httpapplication.nicechina.com.httpapplication.R;

public class NSStationItem extends BaseAdapter {
    private Context m_context = null;
    private LinkedList<StationData> m_stationList = null;
    public NSStationItem(Context context){
        m_context = context;
    }

    public void initStationList(LinkedList<StationData> stationList){
        m_stationList = stationList;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if(null != m_stationList){
           return m_stationList.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(null != m_stationList){
            return m_stationList.get(position);
        }
        else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(m_context,R.layout.station_listitem,null);
            TextView view = (TextView)convertView.findViewById(R.id.stationName);
            if(null != view){
                view.setText(m_stationList.get(position).m_name);
            }
        }
        else{
            TextView view = (TextView)convertView.findViewById(R.id.stationName);
            if(null != view){
                view.setText(m_stationList.get(position).m_name);
            }
        }
        return convertView;
    }
}
