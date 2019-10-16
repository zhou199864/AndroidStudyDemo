package cn.ich.firstcodeversiontwodemo.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.ich.firstcodeversiontwodemo.R;

/**
 *Activity 和 Fragment 进行通信使用回调接口
 */
public class AFragment extends Fragment {

    private TextView tvTitle;

    private Button btn_change,btn_jump,btn_Msg;

    private BFragment bFragment;

    private IOnMessageClick messageClick;

    //向Fragment传递参数
    public static AFragment putExtra(String str){
        AFragment aFragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString("str",str);
        aFragment.setArguments(bundle);
        return aFragment;
    }

    public interface IOnMessageClick{
        void onClick(String str);
    }

    //返回一个视图文件
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("AFragment","---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }
    //当View被创建 do something
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tvTitle);
        btn_jump = view.findViewById(R.id.fragJump);
        btn_change = view.findViewById(R.id.changeTV);
        btn_Msg = view.findViewById(R.id.sendMsg);
        btn_Msg.setOnClickListener(v -> {
//            ((BottomNativeActivity)getActivity()).setData("Message");
            messageClick.onClick("Hello World !");

        });
        btn_jump.setOnClickListener(v -> {
            if (bFragment == null){
                bFragment = new BFragment();
            }
            Fragment  fragment = getFragmentManager().findFragmentByTag("a");
            if (fragment != null) {
                getFragmentManager().beginTransaction().hide(fragment).add(R.id.frameLayout, bFragment).addToBackStack(null).commitAllowingStateLoss();
            }else {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, bFragment).addToBackStack(null).commitAllowingStateLoss();
            }
        });
        btn_change.setOnClickListener(v -> {
            tvTitle.setText("new char");
        });
        if (getArguments() != null){
            tvTitle.setText(getArguments().getString("str"));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            messageClick = (IOnMessageClick) context;
        }catch (ClassCastException e){
            throw new ClassCastException("Activity 必须实现这个接口");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
