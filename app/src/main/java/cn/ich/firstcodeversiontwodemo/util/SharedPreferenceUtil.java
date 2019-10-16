package cn.ich.firstcodeversiontwodemo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreference 文件存储工具类
 */
public class SharedPreferenceUtil {

    private Context context;

    private String fileName;

    private SharedPreferences.Editor editor;

    private SharedPreferences preferences;

    private static SharedPreferenceUtil sharedPreferenceUtil;

    public SharedPreferenceUtil(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
        editor = context.getSharedPreferences(fileName,Context.MODE_PRIVATE).edit();
        preferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }

    /**
     * 向文件中写入值
     * @param key   key
     * @param value 输入的值
     */
    public void putValues(String key,Object value){
        if (value instanceof String){
            editor.putString(key, (String) value);
        }else if (value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if (value instanceof Long){
            editor.putLong(key, (Long) value);
        }else if (value instanceof Float){
            editor.putFloat(key, (Float) value);
        }
        editor.apply();
    }

    /**
     *获取文件中的值
     * @param key 输入文件代表的key
     * @param defaultObject 传入默认值如"" 0 false等
     * @return
     */
    public Object readValues(String key,Object defaultObject){
        if (defaultObject instanceof String){
            return preferences.getString(key, (String) defaultObject);
        }else if (defaultObject instanceof Integer){
            return preferences.getInt(key, (Integer) defaultObject);
        }else if (defaultObject instanceof Boolean){
            return preferences.getBoolean(key, (Boolean) defaultObject);
        }else if (defaultObject instanceof Long){
            return preferences.getLong(key, (Long) defaultObject);
        }else if (defaultObject instanceof Float){
            return preferences.getFloat(key, (Float) defaultObject);
        }else {
            return null;
        }
    }

    /**
     * 移除某个key对应的值
     * @param key
     */
    public void remove(String key){
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     */
    public void clean(){
        editor.clear();
        editor.apply();
    }

    /**
     * 判断某个key是否存在
     * @param key
     * @return
     */
    public boolean contains(String key){
        return preferences.contains(key);
    }

}
