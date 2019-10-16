package cn.ich.firstcodeversiontwodemo.filesave;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import cn.ich.firstcodeversiontwodemo.R;

public class FileSaveActivity extends AppCompatActivity {

    private EditText ed_save;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);
        init();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = ed_save.getText().toString().trim();
                save(data);
//                SharedPreFerence();
            }
        });
    }
    public void init(){
        ed_save = findViewById(R.id.ed_save);
        btn_save = findViewById(R.id.btn_save);
    }
    public void save(String data){
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SharedPreFerence(){
        SharedPreferences.Editor editor = getSharedPreferences("test",MODE_PRIVATE).edit();
        editor.putString("name","john");
        editor.putString("sex","man");
        editor.putString("age","18");
        editor.apply();
    }
}
