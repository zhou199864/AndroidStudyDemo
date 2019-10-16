package cn.ich.firstcodeversiontwodemo.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.ich.firstcodeversiontwodemo.R;
import cn.ich.firstcodeversiontwodemo.modle.Chat;

public class XRecycleViewActivity extends AppCompatActivity {

    private XRecyclerView xRecyclerView;

    private PopAdapter popAdapter;

    private CommonAdapter adapter;

    private List<Chat> list = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adapter.notifyDataSetChanged();
                    xRecyclerView.refreshComplete();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycle_view);
//        fun();
//        xRecyclerView = findViewById(R.id.xRecycleView);
//        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        MultiItemTypeAdapter multiItemTypeAdapter = new MultiItemTypeAdapter(this,list);
//        multiItemTypeAdapter.addItemViewDelegate(new SentMsg());
//        multiItemTypeAdapter.addItemViewDelegate(new ReceiveMsg());
//        xRecyclerView.setAdapter(multiItemTypeAdapter);
//        xRecyclerView.setLoadingMoreEnabled(true);
//        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
//        xRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
//        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                fun();
//                handler.sendEmptyMessage(1);
//                Toast.makeText(XRecycleViewActivity.this,"onRefresh",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLoadMore() {
//                Toast.makeText(XRecycleViewActivity.this,"onLoadMore",Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    public void fun(){
        list.add(new Chat(Chat.SEND,"Hello"));
        list.add(new Chat(Chat.RECEIVE,"Yes"));
        list.add(new Chat(Chat.RECEIVE,"It's me"));
        list.add(new Chat(Chat.SEND,"Test"));
        list.add(new Chat(Chat.SEND,"Git"));
    }
    class SentMsg implements ItemViewDelegate<Chat>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.sent_msg;
        }

        @Override
        public boolean isForViewType(Chat item, int position) {
            if (item.getType() == Chat.SEND){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public void convert(ViewHolder holder, Chat chat, int position) {
            holder.setText(R.id.tv_se,chat.getMsg());
        }
    }
    class ReceiveMsg implements ItemViewDelegate<Chat>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.recvice_msg;
        }

        @Override
        public boolean isForViewType(Chat item, int position) {
            if (item.getType() == Chat.RECEIVE){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public void convert(ViewHolder holder, Chat chat, int position) {
            holder.setText(R.id.tv_re,"R" + chat.getMsg());
        }
    }
}
