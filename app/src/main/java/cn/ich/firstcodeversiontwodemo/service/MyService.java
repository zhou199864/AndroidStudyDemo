package cn.ich.firstcodeversiontwodemo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import cn.ich.firstcodeversiontwodemo.R;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //发送一个前台活动
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(),"1")
                .setContentTitle("This is Title")
                .setContentText("This is content")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(2,notification);
        Log.d("MyService","---onCreate---");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","---onStartCommand---");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","---onDestroy---");
    }
}
