package cn.ich.firstcodeversiontwodemo.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create by zy on 08/25/19
 */
public class GsonUtil {

    private static GsonUtil mInstance;

    private static Gson mgson;

    /**
     * 单例模式
     * @return
     */
    public static GsonUtil getInstance(){
        return initInstance(null);
    }

    /**
     * 构造方法获得gson实例
     * @param gson
     */
    public GsonUtil(Gson gson){
        if (gson == null){
            this.mgson = new Gson();
        }else {
            this.mgson = gson;
        }
    }

    /**
     * 初始化单例
     * @param gson
     * @return
     */
    public static GsonUtil initInstance(Gson gson){
        if (mInstance == null){
            synchronized (GsonUtil.class){
               if (mInstance == null){
                   mInstance = new GsonUtil(gson);
               }
            }
        }
        return mInstance;
    }

    /**
     * 转换成json数据
     * @param obj
     * @return
     */
    public String toJson(Object obj){
        if (mgson != null) {
            return mgson.toJson(obj);
        }
        return null;
    }

    /**
     * 转换成java对象
     * @param jsonData
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T ToBean(String jsonData,Class<T> tClass){
        if (mgson != null){
            return mgson.fromJson(jsonData,tClass);
        }
        return null;
    }

    /**
     * 转换成List
     * @param jsonData
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> toList(String jsonData,Class<T> tClass){
        if (mgson != null) {
            List<T> list = new ArrayList<T>();
            JsonArray array = new JsonParser().parse(jsonData).getAsJsonArray();
            for (final JsonElement elem : array) {
                list.add(mgson.fromJson(elem, tClass));
            }
            return list;
        }
        return null;
    }

    /**
     * 转成list中有map的
     * @param gsonString
     * @return
     */
    public <T> List<Map<String, T>> toListMap(String gsonString) {
        List<Map<String, T>> list = null;
        if (mgson != null) {
            list = mgson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }
    /**
     * 转成map的
     * @param gsonString
     * @return
     */
    public <T> Map<String, T> toMap(String gsonString) {
        Map<String, T> map = null;
        if (mgson != null) {
            map = mgson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
