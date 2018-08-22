package httpapplication.nicechina.com.httpapplication.model;
import httpapplication.nicechina.com.httpapplication.R;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import httpapplication.nicechina.com.httpapplication.MyView;

public class NSListItem extends BaseAdapter {

    public NSListItem(Context context)
    {
        m_context = context;
        for(int i = 0 ; i < 1000 ; i++)
        {
            list[i] = ">>>>>>>>" + i;
        }
        System.out.println(">>>>>>>>>>>>>>>>");
        System.out.println(list);
    }

    private Context m_context = null;

    private String[] list = new String[1000];

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            View view = View.inflate(m_context,R.layout.list_item,null);
            TextView view1 = (TextView)view.findViewById(R.id.name);
            view1.setText(list[position]);
            view1 = (TextView)view.findViewById(R.id.sex);
            view1.setText("男");
            view1 = (TextView)view.findViewById(R.id.name);
            view1.setText("南京");
            convertView = view;
        }
        else
        {
            System.out.println(">>>>>>>>>>>");
        }
        return convertView;
    }
}
