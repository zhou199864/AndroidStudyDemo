package cn.ich.firstcodeversiontwodemo.internet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.ich.firstcodeversiontwodemo.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        sengRequestJson();

    }
    public void sengRequestJson(){
        new Thread(){
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.100.2:8080/get_data.json")
                        .build();
                try {
                    Response responseData = client.newCall(request).execute();
                    String data = responseData.body().string();
//                    pareJsonAnaylze(data);
                    pareGsonAnalyze(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    //Json解析
    public void pareJsonAnaylze(String s){
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String version = jsonObject.getString("version");
                Log.d("Json Back Data","id:"+id);
                Log.d("Json Back Data","version:"+version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //Gson 解析
    public void pareGsonAnalyze(String s){
        Gson gson = new Gson();
        List<GsonAnalyze> data = gson.fromJson(s,new TypeToken<List<GsonAnalyze>>(){}.getType());
        for (GsonAnalyze gsonAnalyze:data) {
            Log.d("Gson Analyze Data","id:"+gsonAnalyze.getId());
            Log.d("Gson Analyze Data","version:"+gsonAnalyze.getVersion());
        }
    }
}
