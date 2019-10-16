package cn.ich.firstcodeversiontwodemo.common;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import cn.ich.firstcodeversiontwodemo.modle.Chat;

public class ReceiveMessage implements ItemViewDelegate<Chat> {
    @Override
    public int getItemViewLayoutId() {
        return 0;
    }

    @Override
    public boolean isForViewType(Chat item, int position) {
        return false;
    }

    @Override
    public void convert(ViewHolder holder, Chat chat, int position) {

    }
}
