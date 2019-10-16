package cn.ich.firstcodeversiontwodemo.media;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.ich.firstcodeversiontwodemo.R;

public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        findViewById(R.id.btn_Jump_Notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MediaActivity.this,NotificationActivity.class));
            }
        });
        findViewById(R.id.btn_Jump_Camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MediaActivity.this, InvokeCameraActivity.class));
            }
        });
        findViewById(R.id.btn_Jump_Music).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MediaActivity.this,MusicPlayerActivity.class));
            }
        });
    }
}
