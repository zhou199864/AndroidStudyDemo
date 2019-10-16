package cn.ich.firstcodeversiontwodemo.internet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.ich.firstcodeversiontwodemo.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HttpURLConnectionActivity extends AppCompatActivity {

    private Button btn_request;

    private TextView tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);
        btn_request = findViewById(R.id.btn_send_request);
        tv_back = findViewById(R.id.tv_back);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendRequest();
                HttpUtil.sengHttpRequest("Https://www.baidu.com", new CallBack() {
                    @Override
                    public void onFinish(String s) {
                        show(s);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }
    public void sendRequest(){
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    show(response.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void show(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_back.setText(s);
            }
        });
    }
}
