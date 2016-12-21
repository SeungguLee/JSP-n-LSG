package auto.com.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_month_1, btn_month_2, btn_month_3, btn_month_4, btn_month_5, btn_month_6, btn_month_7, btn_month_8, btn_month_9, btn_month_10, btn_month_11, btn_month_12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRes();



    }

    private void initRes() {
        btn_month_1 = (Button) findViewById(R.id.btn_month_1);
        btn_month_2 = (Button) findViewById(R.id.btn_month_2);
        btn_month_3 = (Button) findViewById(R.id.btn_month_3);
        btn_month_4 = (Button) findViewById(R.id.btn_month_4);
        btn_month_5 = (Button) findViewById(R.id.btn_month_5);
        btn_month_6 = (Button) findViewById(R.id.btn_month_6);
        btn_month_7 = (Button) findViewById(R.id.btn_month_7);
        btn_month_8 = (Button) findViewById(R.id.btn_month_8);
        btn_month_9 = (Button) findViewById(R.id.btn_month_9);
        btn_month_10 = (Button) findViewById(R.id.btn_month_10);
        btn_month_11 = (Button) findViewById(R.id.btn_month_11);
        btn_month_12 = (Button) findViewById(R.id.btn_month_12);

        btn_month_1.setOnClickListener(this);
        btn_month_2.setOnClickListener(this);
        btn_month_3.setOnClickListener(this);
        btn_month_4.setOnClickListener(this);
        btn_month_5.setOnClickListener(this);
        btn_month_6.setOnClickListener(this);
        btn_month_7.setOnClickListener(this);
        btn_month_8.setOnClickListener(this);
        btn_month_9.setOnClickListener(this);
        btn_month_10.setOnClickListener(this);
        btn_month_11.setOnClickListener(this);
        btn_month_12.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        //해당 달 데이터로 이동
        Intent intent = new Intent(MainActivity.this, MonthActivity.class);
        if (view == btn_month_1) {
            intent.putExtra("strMonth", "201601");
        } else if (view == btn_month_2) {
            intent.putExtra("strMonth", "201602");
        } else if (view == btn_month_3) {
            intent.putExtra("strMonth", "201603");
        } else if (view == btn_month_4) {
            intent.putExtra("strMonth", "201604");
        } else if (view == btn_month_5) {
            intent.putExtra("strMonth", "201605");
        } else if (view == btn_month_6) {
            intent.putExtra("strMonth", "201606");
        } else if (view == btn_month_7) {
            intent.putExtra("strMonth", "201607");
        } else if (view == btn_month_8) {
            intent.putExtra("strMonth", "201608");
        } else if (view == btn_month_9) {
            intent.putExtra("strMonth", "201609");
        } else if (view == btn_month_10) {
            intent.putExtra("strMonth", "201610");
        } else if (view == btn_month_11) {
            intent.putExtra("strMonth", "201611");
        } else if (view == btn_month_12) {
            intent.putExtra("strMonth", "201612");
        }
        startActivity(intent);

    }



}
