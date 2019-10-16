package cn.ich.firstcodeversiontwodemo.internet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import cn.ich.firstcodeversiontwodemo.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * api需要24
 */
public class OkHttpActivity extends AppCompatActivity {

    private TextView tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        tv_back = findViewById(R.id.tv_ok_back);
        findViewById(R.id.btn_send_ok_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OkHttp();
                HttpUtil.OkHttpSendRequest("Https://www.baidu.com", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        show(response.body().string());
                    }
                });
            }
        });
    }
    public void OkHttp(){
        new Thread(){
            @Override
            public void run() {
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .build();
                try {
                    Response response = httpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    show(responseData);
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
