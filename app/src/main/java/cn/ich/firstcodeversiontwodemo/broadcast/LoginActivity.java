package cn.ich.firstcodeversiontwodemo.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.ich.firstcodeversiontwodemo.R;

public class LoginActivity extends BaseActivity {

    private EditText ed_userName,ed_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_userName.getText().toString().trim();
                String password = ed_password.getText().toString().trim();
                if("admin".equals(name) && "admin".equals(password)){
                    startActivity(new Intent(LoginActivity.this,BroadcastActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"userName or Password Error Please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void init(){
        ed_password = findViewById(R.id.ed_password);
        ed_userName = findViewById(R.id.ed_username);
        btn_login = findViewById(R.id.btn_login);
    }
}
