package cn.ich.firstcodeversiontwodemo.media;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

 import cn.ich.firstcodeversiontwodemo.MainActivity;
import cn.ich.firstcodeversiontwodemo.R;

public class NotificationActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this,"2");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        findViewById(R.id.btn_notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个意图用于PendingIntent使用
                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                //PendingIntent相当于一个延迟的Intent
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this,0,intent,0);
                //创建一个Notify对象
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //设置基本属性
                Notification builder = new NotificationCompat.Builder(NotificationActivity.this,"1")
                        .setContentTitle("This is Title")
                        .setContentText("This is Content")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
//                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
//                        .setVibrate(new long[]{0,1000,1000,1000})
                        .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                        .setAutoCancel(true)
                        .build();
                //显示一个通知
                notificationManager.notify(1,builder);
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
            }
        });

    }
}
