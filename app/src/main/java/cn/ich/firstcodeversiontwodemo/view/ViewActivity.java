package cn.ich.firstcodeversiontwodemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.ich.firstcodeversiontwodemo.R;
import cn.ich.firstcodeversiontwodemo.util.ToastUtil;

public class ViewActivity extends AppCompatActivity {

    private CustomView cmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        cmView = findViewById(R.id.cm_view);
    }
}
