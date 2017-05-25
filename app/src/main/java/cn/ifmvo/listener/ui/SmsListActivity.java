package cn.ifmvo.listener.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ifmvo.matthew.base.activity.ExBaseRecyclerViewActivity;

import java.util.ArrayList;

import cn.ifmvo.listener.R;
import cn.ifmvo.listener.bean.Sms;
import cn.ifmvo.listener.view.StatePopWindow;
import space.sye.z.library.adapter.BaseViewHolder;
import space.sye.z.library.adapter.QuickRecycleAdapter;

/**
 * Created by 陈序员 on 2017/5/23.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class SmsListActivity extends ExBaseRecyclerViewActivity implements StatePopWindow.OnSelectStateListener{

    QuickRecycleAdapter<Sms> adapter;
//    String phone;
    ArrayList<Sms> list = new ArrayList<>();

    StatePopWindow statePopWindow;

    int state = 0;

//    int POS_0 = 0;
//    int POS_1 = 1;
//    int POS_2 = 2;
//    int POS_3 = 3;
//    int POS_4 = 4;

    @Override
    public void initedView() {

        setTitle("所有");

//        layTopBar.setVisibility(View.GONE);
//        btnTopL.setVisibility(View.GONE);

        tvTopTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_order_state_arrow, 0);

        tvTopTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (statePopWindow == null){
                    statePopWindow = new StatePopWindow(getContext());
                    statePopWindow.setOnSelectStateListener(SmsListActivity.this);
                }

                statePopWindow.showAsDropDown(layTopBar);
            }
        });

        Intent intent = getIntent();
        list = (ArrayList<Sms>) intent.getSerializableExtra("smsList");
//        Logger.e(phone+"---------");
    }

    @Override
    public void getData(int indexPage, int pageSize) {

        adapter.clear();

        if (state != 0){

            ArrayList<Sms> newList = new ArrayList<>();
            String IF = "";

            switch (state){
                case 0:
                    IF = "";
                    break;
                case 1:
                    IF = "地点1报警，请注意";
                    break;
                case 2:
                    IF = "地点2报警，请注意";
                    break;
                case 3:
                    IF = "地点3报警，请注意";
                    break;
                case 4:
                    IF = "紧急通知，请注意";
                    break;
                default:
            }

            for (Sms sms : list){
                if (sms.content.equals(IF)){
                    newList.add(sms);
                }
            }
            adapter.addAll(newList);
        }else{
            adapter.addAll(list);
        }

        setCanLoadMore(false);


    }


//    public List<Sms> handleData(){
//        list.clear();
//        adapter.clear();

//        String IF = "";
//
//        switch (state){
//            case 1:
//                IF = "地点1报警，请注意";
//                break;
//            case 2:
//                IF = "地点2报警，请注意";
//                break;
//            case 3:
//                IF = "地点3报警，请注意";
//                break;
//            case 4:
//                IF = "紧急通知，请注意";
//                break;
//            default:
//        }

//        return list;
//    }

    @Override
    public RecyclerView.Adapter getAdapter(){
        adapter = new QuickRecycleAdapter<Sms>(getContext(), R.layout.list_item_sms) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, Sms item, int viewType) {
//                holder.setText(R.id.tvPhone, item.phone);
                holder.setText(R.id.tvContent, item.content);
                holder.setText(R.id.tvDate, item.sendDate);

            }
        };
        return adapter;
    }

    @Override
    public void onListItemClick(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onSelectState(int position) {
        switch (position){
            case 0:
                setTitle("所有");
                state = 0;
                break;
            case 1:
                setTitle("地点1");
                state = 1;
                break;
            case 2:
                state = 2;

                setTitle("地点2");
                break;
            case 3:
                state = 3;

                setTitle("地点3");
                break;
            case 4:
                state = 4;

                setTitle("紧急");
                break;
            default:
        }

        getData(0, 0);
    }
}
