package cn.ich.firstcodeversiontwodemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import cn.ich.firstcodeversiontwodemo.ActivityCollector;
import cn.ich.firstcodeversiontwodemo.R;

public class BroadcastActivity extends BaseActivity {

    private IntentFilter filter;

    private NetWorkMode netWorkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BroadcastActivity.this,LoginActivity.class));
            }
        });
        /**
         * 理解一下强制用户关闭的广播
         * 1、新建一个活动管理器
         * 2、新建一个活动总类所有类都要继承活动总类
         * 3、编写登录界面
         * 4、在活动总类里面编写广播的内容
         */
        findViewById(R.id.btn_offline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("cn.ich.firstcodeversiontwodemo.FORCE_OFFLINE");
                sendBroadcast(intent);
                Toast.makeText(BroadcastActivity.this,"SendSuccess",Toast.LENGTH_SHORT).show();
            }
        });
        //实例化一个IntentFilter
//        filter = new IntentFilter();
//        //动态添加活动
//        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        netWorkMode = new NetWorkMode();
//        //注册广播
//        registerReceiver(netWorkMode,filter);
    }

    //创建一个广播收集器 创建一个类并继承BroadcastReceiver 重写onReceive中的方法即可
    class NetWorkMode extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //销毁广播
//        unregisterReceiver(netWorkMode);
//    }
}
