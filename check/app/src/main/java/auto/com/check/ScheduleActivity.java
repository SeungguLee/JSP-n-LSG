package auto.com.check;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView list_schedule;
    Button btn_add_record;
    ArrayList<String> alList = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initRes();

    }
    public void onStart(){
        super.onStart();
        getData();
    }
    //메모리스트 내용 가져오기
    private void getData() {
        SchaduleDBAdapter dbAdapter = new SchaduleDBAdapter(this);
        dbAdapter.open();

        Cursor c = dbAdapter.fetchAllEntry();
        //재갱신시 초기화
        alList.clear();
        while(c.moveToNext()){
            String str = c.getString(1);
            alList.add(str);
        }

        adapter.notifyDataSetChanged();
    }


    private void initRes() {
        list_schedule = (ListView)findViewById(R.id.list_schedule);
        list_schedule.setOnItemClickListener(this);
        btn_add_record = (Button)findViewById(R.id.btn_add_record);
        btn_add_record.setOnClickListener(this);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,alList);
        list_schedule.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_add_record){
            //쓰기로 이동
            Intent intent = new Intent(ScheduleActivity.this,ScheduleWriteActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        //해당 날짜로 쓰기로 이동
        Intent intent = new Intent(ScheduleActivity.this,ScheduleWriteActivity.class);
        intent.putExtra("strDate",alList.get(pos));
        startActivity(intent);
    }
}
