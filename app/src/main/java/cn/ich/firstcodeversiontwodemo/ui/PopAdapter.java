package cn.ich.firstcodeversiontwodemo.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import cn.ich.firstcodeversiontwodemo.R;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.PopViewHolder> {

    private Context context;
    private List<String> stringList;
    private onItemClick onItemClick;

    public PopAdapter(Context context, List<String> stringList,onItemClick onItemClick) {
        this.context = context;
        this.stringList = stringList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public PopAdapter.PopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new PopViewHolder(LayoutInflater.from(context).inflate(R.layout.rvpop_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopAdapter.PopViewHolder viewHolder, int i) {
        viewHolder.textView.setText(stringList.get(i));
        viewHolder.itemView.setOnClickListener(v -> {
            onItemClick.onClick(i);
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
    class PopViewHolder extends RecyclerView.ViewHolder{

        private ImageButton imageButton;

        private TextView textView;

        public PopViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.img_pop_item);
            textView = itemView.findViewById(R.id.tv_pop_item);
        }
    }
    public interface onItemClick{
        void onClick(int pos);
    }
}
