package cn.ich.firstcodeversiontwodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cn.ich.firstcodeversiontwodemo.api.LBSActivity;
import cn.ich.firstcodeversiontwodemo.broadcast.BroadcastActivity;
import cn.ich.firstcodeversiontwodemo.datashare.RunTimePermissionTestActivity;
import cn.ich.firstcodeversiontwodemo.filesave.FileSaveActivity;
import cn.ich.firstcodeversiontwodemo.filesave.LitePalActivity;
import cn.ich.firstcodeversiontwodemo.internet.InternetActivity;
import cn.ich.firstcodeversiontwodemo.media.MediaActivity;
import cn.ich.firstcodeversiontwodemo.service.ServiceActivity;
import cn.ich.firstcodeversiontwodemo.ui.BottomNativeActivity;
import cn.ich.firstcodeversiontwodemo.ui.DrawerLayoutActivity;
import cn.ich.firstcodeversiontwodemo.ui.NavigationViewActivity;
import cn.ich.firstcodeversiontwodemo.ui.PopupWindowActivity;
import cn.ich.firstcodeversiontwodemo.ui.XRecycleViewActivity;
import cn.ich.firstcodeversiontwodemo.view.ViewActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_list_btn;

    private Map<Integer, String> mapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        manager.cancel(1);3
        addName();
        rv_list_btn = findViewById(R.id.rv_list_btn);
        rv_list_btn.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        ListenerAdapter listenerAdapter = new ListenerAdapter(MainActivity.this, mapList);
        rv_list_btn.setAdapter(listenerAdapter);
        listenerAdapter.SetOnItemClickListener(pos -> {
            Intent intent = null;
            switch (pos) {
                case ActivityNameInteger.BrodcastActivity:
                    intent = new Intent(MainActivity.this, BroadcastActivity.class);
                    break;
                case ActivityNameInteger.FileSavaActivity:
                    intent = new Intent(MainActivity.this, FileSaveActivity.class);
                    break;
                case ActivityNameInteger.RunTimerPermissionTestActivity:
                    intent = new Intent(MainActivity.this, RunTimePermissionTestActivity.class);
                    break;
                case ActivityNameInteger.MediaActivity:
                    intent = new Intent(MainActivity.this, MediaActivity.class);
                    break;
                case ActivityNameInteger.InternetActivity:
                    intent = new Intent(MainActivity.this, InternetActivity.class);
                    break;
                case ActivityNameInteger.ServiceActivity:
                    intent = new Intent(MainActivity.this, ServiceActivity.class);
                    break;
                case ActivityNameInteger.LitePalActivity:
                    intent = new Intent(MainActivity.this, LitePalActivity.class);
                    break;
                case ActivityNameInteger.PopupWindowActivity:
                    intent = new Intent(MainActivity.this, PopupWindowActivity.class);
                    break;
                case ActivityNameInteger.DrawerLayoutActivity:
                    intent = new Intent(MainActivity.this, DrawerLayoutActivity.class);
                    break;
                case ActivityNameInteger.NavigationViewActivity:
                    intent = new Intent(MainActivity.this, NavigationViewActivity.class);
                    break;
                case ActivityNameInteger.BottomNavigationActivity:
                    intent = new Intent(MainActivity.this, BottomNativeActivity.class);
                    break;
                case ActivityNameInteger.XRecycleViewActivity:
                    intent = new Intent(MainActivity.this, XRecycleViewActivity.class);
                    break;
                case ActivityNameInteger.ViewActivity:
                    intent = new Intent(MainActivity.this, ViewActivity.class);
                    break;
                case ActivityNameInteger.LBSActivity:
                    intent = new Intent(MainActivity.this, LBSActivity.class);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "未找到对应Activity", Toast.LENGTH_SHORT).show();
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }
        });
    }

    public void addName() {
        mapList = new HashMap<>();
        mapList.put(ActivityNameInteger.BrodcastActivity, "BroadcastActivity");
        mapList.put(ActivityNameInteger.FileSavaActivity, "FileSaveActivity");
        mapList.put(ActivityNameInteger.RunTimerPermissionTestActivity, "RunTimerPermissionTestActivity");
        mapList.put(ActivityNameInteger.MediaActivity, "MediaActivity");
        mapList.put(ActivityNameInteger.InternetActivity, "InternetActivity");
        mapList.put(ActivityNameInteger.ServiceActivity, "ServiceActivity");
        mapList.put(ActivityNameInteger.LitePalActivity, "LitePalActivity");
        mapList.put(ActivityNameInteger.PopupWindowActivity, "PopupWindowActivity");
        mapList.put(ActivityNameInteger.DrawerLayoutActivity, "DrawerLayoutActivity");
        mapList.put(ActivityNameInteger.NavigationViewActivity, "NavigationViewActivity");
        mapList.put(ActivityNameInteger.BottomNavigationActivity, "BottomNavigationActivity");
        mapList.put(ActivityNameInteger.XRecycleViewActivity, "XRecycleViewActivity");
        mapList.put(ActivityNameInteger.ViewActivity, "ViewActivity");
        mapList.put(ActivityNameInteger.LBSActivity, "LBSActivity");
    }
}
