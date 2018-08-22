package httpapplication.nicechina.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import httpapplication.nicechina.com.mylibrary.FileActivity;
public class MainActivity extends AppCompatActivity {

    private Button m_button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_button = (Button)findViewById(R.id.button1);
        m_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FileActivity.class);
                startActivity(intent);
            }
        });
    }
}
