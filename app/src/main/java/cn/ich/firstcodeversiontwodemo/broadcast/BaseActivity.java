package cn.ich.firstcodeversiontwodemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import cn.ich.firstcodeversiontwodemo.ActivityCollector;

public class BaseActivity extends AppCompatActivity {

    private OffLine offLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("cn.ich.firstcodeversiontwodemo.FORCE_OFFLINE");
        offLine = new OffLine();
        registerReceiver(offLine,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(offLine != null){
            unregisterReceiver(offLine);
            offLine = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class OffLine extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("Warning")
                    .setMessage("Offline")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, which) -> {
                        ActivityCollector.finishAll();
                        Intent intent1 = new Intent(context,LoginActivity.class);
                        context.startActivity(intent1);
                    });
            builder.show();
        }
    }

}
