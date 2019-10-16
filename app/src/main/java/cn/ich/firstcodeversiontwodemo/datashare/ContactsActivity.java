package cn.ich.firstcodeversiontwodemo.datashare;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.ich.firstcodeversiontwodemo.R;

public class ContactsActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<String> contactsList = new ArrayList<>();
    private Button btn_show_contacts;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        btn_show_contacts = findViewById(R.id.btn_show_contacts);
        listView = findViewById(R.id.lv);
        btn_show_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readcontacts();
                adapter = new ArrayAdapter<String>(ContactsActivity.this,android.R.layout.simple_list_item_1,contactsList);
                listView.setAdapter(adapter);
            }
        });
    }
    private void readcontacts(){
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor != null){
                while (cursor.moveToNext()){
                    ///此处并没有使用Uri.Parse方法传入一个Uri而是直接使用的是一个系统常量（就是一个Uri只不过是封装好的）
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add("Name:"+name+"\n"+"Phone Number:"+number);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }

    }
}
