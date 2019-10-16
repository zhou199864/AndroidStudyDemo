package cn.ich.firstcodeversiontwodemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Map;

public class ListenerAdapter extends RecyclerView.Adapter<ListenerAdapter.ListenerViewHolder> {

    private Context context;
    private Map<Integer,String> mapList;
    private OnItemClickListener onItemClickListener;

    public ListenerAdapter(Context mcontext,Map<Integer,String> mapList){
        this.mapList = mapList;
        this.context = mcontext;
    }
    public void SetOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ListenerAdapter.ListenerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListenerViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_btn_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListenerAdapter.ListenerViewHolder viewHolder, final int i) {
        viewHolder.button.setText(mapList.get(i));
        viewHolder.button.setOnClickListener(v -> onItemClickListener.OnClick(i));
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    class ListenerViewHolder extends RecyclerView.ViewHolder{
        private Button button;
        public ListenerViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btn_item);
        }
    }
    public interface OnItemClickListener{
        void OnClick(int pos);
    }
}
