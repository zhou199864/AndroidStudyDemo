package cn.ich.firstcodeversiontwodemo.media;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.ich.firstcodeversiontwodemo.R;

/**
 * music player
 */
public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_player,btn_stop,btn_pause;

    private ListView lv;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private List<String> musicPath = new ArrayList<>();

    private List<String> musicName = new ArrayList<>();

    private String[] pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        init();
        initMediaPlayer();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MusicPlayerActivity.this,"选中"+pass[position],Toast.LENGTH_SHORT).show();
                System.out.println(musicPath.get(position));
                try {
                    //将路径设置进播放器
                    mediaPlayer.setDataSource(musicPath.get(position));
                    //准备进入状态
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_player.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
    }

    public void initMediaPlayer(){
        //设置路径
        File file = new File("storage"+File.separator+"sdcard0"+File.separator+"Music");
        File[] files = file.listFiles();
        pass = file.list();
        String rex = "\\w*\\.mp3";
        for (int i = 0; i < pass.length; i++){
            if(pass[i].matches(rex)){
                //判断是否是.mp3文件
                musicName.add(pass[i]);
                musicPath.add(String.valueOf(files[i]));
            }
        }
        ListShow();
    }
    public void ListShow(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MusicPlayerActivity.this,android.R.layout.simple_list_item_1,musicName);
        lv.setAdapter(arrayAdapter);
    }
    public void init(){
        btn_pause = findViewById(R.id.btn_pause);
        btn_player = findViewById(R.id.btn_player);
        btn_stop = findViewById(R.id.btn_stop);
        lv = findViewById(R.id.lv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_player:
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.btn_pause:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.btn_stop:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
