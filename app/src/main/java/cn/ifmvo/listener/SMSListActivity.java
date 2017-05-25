package cn.ifmvo.listener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class SMSListActivity extends AppCompatActivity {

    private ListView smsListView;
    private SMSAdapter smsAdpter;
    private RexseeSMS rsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_list_view);
        smsListView = (ListView) findViewById(R.id.sms_list);
        smsAdpter = new SMSAdapter(SMSListActivity.this);
        rsms = new RexseeSMS(SMSListActivity.this);
        List<SMSBean> list_mmt = rsms.getThreadsNum(rsms.getThreads(0));
        // 注入短信列表数据
        smsAdpter.assignment(list_mmt);
        // 填充数据
        smsListView.setAdapter(smsAdpter);
        // 短信列表项点击事件
        smsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Map<String, String> map = new HashMap<String, String>();
                SMSBean sb = (SMSBean) smsAdpter.getItem(position);
//                map.put("phoneNumber", sb.getAddress());
//                map.put("threadId", sb.getThread_id());

                Intent intent = new Intent(SMSListActivity.this, MessageBoxList.class);
                intent.putExtra("phoneNumber", sb.getAddress());
                intent.putExtra("threadId", sb.getThread_id());
                startActivity(intent);

//                BaseIntentUtil.intentSysDefault(SMSListActivity.this,
//                        MessageBoxList.class, map);
            }
        });
    }
}
