package cn.ich.firstcodeversiontwodemo.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 对setDoInput  setDoOutput 方法解释
 *
 * HttpURLConnection conn = (HttpURLConnection)url.openConnection();
 * url.openConnection()得到的是URLConnection对象，次类有setDoOutput(）和setDoInput()
 *
 * get请求用不到conn.getOutputStream()，因为参数直接追加在地址后面，因此默认是false。
 * post请求（比如：文件上传）需要往服务区传输大量的数据，这些数据是放在http的body里面的，因此需要在建立连接以后，往服务端写数据。
 *
 * 因为总是使用conn.getInputStream()获取服务端的响应，因此默认值是true。
 */

public class HttpUtil {
    public static void sengHttpRequest(String address,CallBack callBack){
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);    //以后就可以使用conn.getOutputStream().write()
                    connection.setDoOutput(true);      //以后就可以使用conn.getInputStream().read();
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(callBack != null){
                        callBack.onFinish(response.toString());
                    }
                } catch (MalformedURLException e) {
                    if(callBack != null){
                        callBack.onError(e);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }
    public static void OkHttpSendRequest(String adress,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(adress)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
