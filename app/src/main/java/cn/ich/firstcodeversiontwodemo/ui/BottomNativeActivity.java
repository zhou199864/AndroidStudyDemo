package cn.ich.firstcodeversiontwodemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ich.firstcodeversiontwodemo.R;
import cn.ich.firstcodeversiontwodemo.fragement.AFragment;
import cn.ich.firstcodeversiontwodemo.fragement.BFragment;
import cn.ich.firstcodeversiontwodemo.modle.User;
import cn.ich.firstcodeversiontwodemo.util.FastJsonUtil;
import cn.ich.firstcodeversiontwodemo.util.GsonUtil;
import cn.ich.firstcodeversiontwodemo.util.ToastUtil;

public class BottomNativeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, AFragment.IOnMessageClick {

    private FrameLayout frameLayout;

    private BottomNavigationView navigationView;

    private AFragment aFragment;

    private BFragment bFragment;

    private TextView tvTitle;

    private GsonUtil gsonUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_native);
        frameLayout = findViewById(R.id.frameLayout);
        tvTitle = findViewById(R.id.tvTitle);
        gsonUtil = GsonUtil.getInstance();
        aFragment = AFragment.putExtra("Extra");
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,aFragment,"a").commitAllowingStateLoss();
        navigationView = findViewById(R.id.Native_column);
        navigationView.setOnNavigationItemSelectedListener(this);


    }

    public void showBottomDialog(){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View bottomView = LayoutInflater.from(this).inflate(R.layout.bottom_dialog,null,false);
        Button btn1 = bottomView.findViewById(R.id.bottom_Btn_1);
        btn1.setOnClickListener(view -> {
            Log.d("BottomActivity","Btn1");
        });
        dialog.setContentView(bottomView);
        dialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                showBottomDialog();
                return  true;
            case R.id.navigation_dashboard:
                tvTitle.setText(gsonUtil.toJson(empowerJson(1,1,"hello")));
                return  true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    }
    public void switchFragment(Fragment fragment){

    }
    public void setData(String text){
        tvTitle.setText(text);
    }

    public Map<String,Object> empowerJson(int page, int limit, String... userCode) {
        Map<String, Object> jsonMap = new HashMap<>();
        if (userCode.length > 0) {
            jsonMap.put("UserCode", userCode[0]);
        }
        jsonMap.put("Page", page);
        jsonMap.put("Limit", limit);
        return jsonMap;
    }

    @Override
    public void onClick(String str) {
        tvTitle.setText(str);
    }

}
