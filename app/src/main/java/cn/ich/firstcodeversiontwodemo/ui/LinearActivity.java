package cn.ich.firstcodeversiontwodemo.ui;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.ich.firstcodeversiontwodemo.R;

public class LinearActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        recyclerView = findViewById(R.id.rv_linear);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,10,ContextCompat.getColor(this,R.color.colorAccent)));
        recyclerView.setAdapter(new LinAdapter());
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividedHeight));
        }
    }
    class LinAdapter extends RecyclerView.Adapter<LinAdapter.MyViewHolder>{

        @NonNull
        @Override
        public LinAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(LinearActivity.this).inflate(R.layout.rv_linear,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull LinAdapter.MyViewHolder viewHolder, int i) {
            int num = 0;
            viewHolder.textView.setText("Message" + num ++);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{

            public TextView textView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.tv_linear);
            }
        }
    }
}
