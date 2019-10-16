package cn.ich.firstcodeversiontwodemo.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import cn.ich.firstcodeversiontwodemo.R;

public class PopupWindowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private EditText editText;

    private ImageButton imageButton;

    private PopupWindow popupWindow;

    private SwipeRefreshLayout refreshRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
//        recyclerView = findViewById(R.id.rv_pop);
        editText = findViewById(R.id.ed_pop);
        imageButton = findViewById(R.id.img_drop);
        refreshRv = findViewById(R.id.refreshRv);
        imageButton.setOnClickListener(v -> {
//            show();
        });
        refreshRv.setColorSchemeResources(R.color.colorPrimary);
        refreshRv.setOnRefreshListener(() -> {
            refreshShow();
        });
    }
    public List<String> stringList(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            list.add(i+"");
        }
        return list;
    }
    public void show(){
        initRecycle();
        popupWindow = new PopupWindow(recyclerView,editText.getWidth(),400);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(editText,2,-5);
    }
    public void initRecycle(){
//        recyclerView = new RecyclerView(this);
        recyclerView = findViewById(R.id.rv_pop);
        recyclerView.setLayoutManager(new LinearLayoutManager(PopupWindowActivity.this));
        recyclerView.setAdapter(new PopAdapter(PopupWindowActivity.this, stringList(), pos -> {
        }));
    }
    public void refreshShow(){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    initRecycle();
                    refreshRv.setRefreshing(false);
                });
            }
        }.start();
    }
}
