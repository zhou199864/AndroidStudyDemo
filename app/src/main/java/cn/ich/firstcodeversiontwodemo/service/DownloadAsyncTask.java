package cn.ich.firstcodeversiontwodemo.service;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 使用AsyncTask 对界面UI进行实时更改
 */
//传入三个泛型参数 (1)String 表示传入后台的字符串 (2)Integer 表示当前的下载进度 (3)Integer 表示下载的返回结果
public class DownloadAsyncTask extends AsyncTask<String,Integer,Integer> {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener listener;
    private boolean isCancel = false;
    private boolean isPaused = false;
    private int lastProgress;
    //构造中传入接口回调
    public DownloadAsyncTask(DownloadListener listener){
        this.listener = listener;
    }
    /**
     * 所有代码在子线程中运行，处理所有的耗时操作
     * 在后台执行具体下载逻辑
     * @param strings  此参数可传可不传传入就是数组
     * @return
     */
    @Override
    protected Integer doInBackground(String... strings) {
        InputStream in = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try {
            long downloadedLength = 0;
            String downloadUrl = strings[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if(file.exists()){
                downloadedLength = file.length();
            }
            //获取当前文件的总长度
            long contentLength = getContentLength(downloadUrl);
            //判断是否存在并返回相应的状态
            if(contentLength == 0){
                return TYPE_FAILED;
            }else if(contentLength == downloadedLength){
                return TYPE_SUCCESS;
            }
            //进行断点续传
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(downloadUrl)
                    .addHeader("RANG","bytes="+downloadedLength+"-")
                    .build();
            Response response = client.newCall(request).execute();
            if(response != null){
                in = response.body().byteStream();
                saveFile = new RandomAccessFile(file,"rw");
                saveFile.seek(downloadedLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = in.read()) != -1){
                    if(isCancel){
                        return TYPE_CANCELED;
                    }else if (isPaused){
                        return TYPE_PAUSED;
                    }else {
                        total += len;
                    }
                    saveFile.write(b,0,len);
                    int progress = (int) ((total + downloadedLength) * 100 /contentLength);
                    publishProgress(progress);
                }
            }
            response.body().close();
            return TYPE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(in != null){
                    in.close();
                }
                if (saveFile != null){
                    saveFile.close();
                }
                if(isCancel && file != null){
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return TYPE_FAILED;
        }
    }

    /**
     * 在publishProgress()方法后执行
     * 对进度进行更新
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress > lastProgress){
            listener.onProgress(progress);
        }
    }

    /**
     * 在后台任务开始执行时调用，用于进行界面的初始化操作如显示进度条
     * 对结果进行通知
     * @param integer
     */
    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
                default:
                    break;
        }
    }

    public void pauseDownload(){
        isPaused = true;
    }
    public void cancelDownload(){
        isCancel = true;
    }
    //获取下载文件的总长度
    public long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response responseData = client.newCall(request).execute();
        if(request != null && responseData.isSuccessful()){
            long contentLength = responseData.body().contentLength();
            responseData.body().close();
            return contentLength;
        }
        return 0;
    }
}
