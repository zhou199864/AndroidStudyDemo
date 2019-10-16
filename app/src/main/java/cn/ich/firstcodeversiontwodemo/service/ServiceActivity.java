package cn.ich.firstcodeversiontwodemo.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import cn.ich.firstcodeversiontwodemo.R;

public class ServiceActivity extends AppCompatActivity {

    private Button btn_start_service,btn_stop_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        btn_start_service = findViewById(R.id.btn_start_service);
        btn_stop_service = findViewById(R.id.btn_stop_service);
        btn_start_service.setOnClickListener(v -> {
//                Intent intent = new Intent(getApplicationContext(),MyService.class);
//                startService(intent);
            Log.d("ServiceActivity","Thread id is "+Thread.currentThread().getId());
            Intent intent = new Intent(getApplicationContext(),MyIntentSeriver.class);
            startService(intent);
        });
        btn_stop_service.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MyService.class);
            stopService(intent);
        });
    }
}
