package cn.ich.firstcodeversiontwodemo.internet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.ich.firstcodeversiontwodemo.R;

public class InternetActivity extends AppCompatActivity {

    private Button btn_http_url_connection,btn_okhttp,btn_xml_analyze,btn_json_analyze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        init();
        btn_http_url_connection.setOnClickListener(v -> startActivity(new Intent(InternetActivity.this,HttpURLConnectionActivity.class)));
        btn_okhttp.setOnClickListener(v -> startActivity(new Intent(InternetActivity.this,OkHttpActivity.class)));
        btn_xml_analyze.setOnClickListener(v -> startActivity(new Intent(InternetActivity.this,XMLAnalyzeActivity.class)));
        btn_json_analyze.setOnClickListener(v -> startActivity(new Intent(InternetActivity.this,JsonActivity.class)));
    }
    public void init(){
        btn_http_url_connection = findViewById(R.id.btn_http_url_connection);
        btn_okhttp = findViewById(R.id.btn_okhttp);
        btn_xml_analyze = findViewById(R.id.btn_xml_analyze);
        btn_json_analyze = findViewById(R.id.btn_json_analyze);
    }
}
