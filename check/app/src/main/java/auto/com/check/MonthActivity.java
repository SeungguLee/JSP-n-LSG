package auto.com.check;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MonthActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_month;
    ListView list_graph;
    ArrayAdapter adapter;
    ArrayList<String> alList =  new ArrayList<>();
    Button btn_graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        getData();
        initRes();
    }
    //해당 달 데이터 가져오기
    private void getData() {
        GraphDBAdapter dbAdapter = new GraphDBAdapter(this);
        dbAdapter.open();
        String getMonth = getIntent().getStringExtra("strMonth");
        Cursor c = dbAdapter.fetchDateEntry(getMonth);
        while(c.moveToNext()){
            String str = c.getString(1)+" "+c.getString(2)+"~"+c.getString(3)+" "+c.getString(4)+"분";
            alList.add(str);
        }
    }
    //리소스 초기화
    private void initRes() {
        tv_month = (TextView)findViewById(R.id.tv_month);
        tv_month.setText(getIntent().getStringExtra("strMonth"));
        list_graph  = (ListView)findViewById(R.id.list_graph);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,alList);
        list_graph.setAdapter(adapter);
        btn_graph = (Button)findViewById(R.id.btn_graph);
        btn_graph.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btn_graph){
            //그래프 화면으로 이동
            Intent intent = new Intent(MonthActivity.this,GraphActivity.class);
            intent.putExtra("strMonth",getIntent().getStringExtra("strMonth"));
            startActivity(intent);
        }

    }
}
