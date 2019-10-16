package cn.ich.firstcodeversiontwodemo.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast mToast;

    public static void shortMessage(Context context,String tips){
        if (mToast == null){
            Toast.makeText(context,tips,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(tips);
        }
        mToast.show();
    }
}
