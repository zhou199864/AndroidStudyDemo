package cn.ich.firstcodeversiontwodemo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;
import cn.ich.firstcodeversiontwodemo.R;

public class DownloadService extends Service {

    private DownloadAsyncTask downloadAsyncTask;

    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onSuccess() {
            downloadAsyncTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            Toast.makeText(getApplicationContext(),"Download Success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadAsyncTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(getApplicationContext(),"Download Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadAsyncTask = null;
            Toast.makeText(getApplicationContext(),"Download Paused",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadAsyncTask = null;
            stopForeground(true);
            Toast.makeText(getApplicationContext(),"Download Cancel",Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder binder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return  binder;
    }

    class DownloadBinder extends Binder{
        public void startDownload(String url){
            if(downloadAsyncTask == null){
                downloadUrl = url;
                downloadAsyncTask = new DownloadAsyncTask(listener);
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(getApplicationContext(),"Downloading...",Toast.LENGTH_SHORT).show();
            }
        }
        public void pauseDownload(){
            if(downloadAsyncTask != null){
                downloadAsyncTask.pauseDownload();
            }
        }
        public void cancelDownload(){
            if(downloadAsyncTask != null){
                downloadAsyncTask.cancelDownload();
            }
            if(downloadAsyncTask != null){
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                if(file.exists()){
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);
                Toast.makeText(getApplicationContext(),"Canceled",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public Notification getNotification(String title,int progress){
        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"1")
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi);
        if (progress >= 0){
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }
    public NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
}
