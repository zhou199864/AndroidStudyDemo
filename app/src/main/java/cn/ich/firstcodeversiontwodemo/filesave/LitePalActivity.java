package cn.ich.firstcodeversiontwodemo.filesave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

import cn.ich.firstcodeversiontwodemo.R;

public class LitePalActivity extends AppCompatActivity {

    public static final String TGA = "LitePalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
        findViewById(R.id.btn_create).setOnClickListener(v -> {
            LitePal.getDatabase();
        });
        findViewById(R.id.btn_litesave).setOnClickListener(v -> {
            User user = new User();
            user.setId(2);
            user.setUsername("root");
            user.setPassword("1234");
            user.save();
        });
        findViewById(R.id.btn_update).setOnClickListener(v ->{
            User user = new User();
            user.setPassword("123");
            user.updateAll("id = ?","1");
        });
        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            LitePal.deleteAll(User.class,"id = ?","1");
        });
        findViewById(R.id.btn_query).setOnClickListener(v -> {
            List<User> list = LitePal.findAll(User.class);
            for (User user :
                    list) {
                Log.d(TGA,user.getUsername());
                Log.d(TGA,user.getPassword());
                Log.d(TGA, String.valueOf(user.getId()));
            }
        });
    }
}
