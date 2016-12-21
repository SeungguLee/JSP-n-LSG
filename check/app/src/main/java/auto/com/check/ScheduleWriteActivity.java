package auto.com.check;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleWriteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_text;
    Button btn_write;
    boolean isUpdate;
    String strDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_write);
        initRes();
        getData();
    }

    private void getData() {
        //날짜가 있을경우 해당 날짜로 가져오기
        if(getIntent().getStringExtra("strDate")!=null && !getIntent().getStringExtra("strDate").equals("")) {
            strDate = getIntent().getStringExtra("strDate");
        }
        //없을경우 오늘 날짜로 설정하기
        else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            strDate = sdf.format(new Date());
        }
        SchaduleDBAdapter dbAdapter = new SchaduleDBAdapter(this);
        dbAdapter.open();
        Cursor c= dbAdapter.selectDateEntry(strDate);
        if(c.getCount()==0){
            //데이터가 없을경우 입력설정
            isUpdate = false;
        }else{
            //데이터가 있을경우 수정설정
            isUpdate = true;
            if(c.moveToNext()) {
                et_text.setText(c.getString(2));
            }
        }

    }


    private void initRes() {
        et_text =(EditText)findViewById(R.id.et_text);
        btn_write = (Button)findViewById(R.id.btn_write);
        btn_write.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_write){
            SchaduleDBAdapter dbAdapter = new SchaduleDBAdapter(this);
            dbAdapter.open();
            if(isUpdate){
                //수정하기
                dbAdapter.updateEntry(strDate,et_text.getText().toString());
            }else{
                //입력하기
                dbAdapter.createEntry(strDate,et_text.getText().toString());
            }
            //입력시 종료
            finish();
        }

    }
}
