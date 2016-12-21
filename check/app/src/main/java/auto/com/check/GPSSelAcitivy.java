package auto.com.check;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//gps정보 입력부분
public class GPSSelAcitivy extends AppCompatActivity implements View.OnClickListener {
    EditText et_x,et_y;
    Button btn_sel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpssel);
        initRes();
        initData();


    }
    //저장된 데이터 표시
    private void initData() {
        et_x.setText(Sharedpreference.getSharedPrefX(GPSSelAcitivy.this)+"");
        et_y.setText(Sharedpreference.getSharedPrefY(GPSSelAcitivy.this)+"");

    }

    private void initRes() {
        //버튼 아이디 및 클릭 리스너 등록
        et_x = (EditText)findViewById(R.id.et_x);
        et_y = (EditText)findViewById(R.id.et_y);
        btn_sel = (Button)findViewById(R.id.btn_sel);
        btn_sel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_sel){
            //좌표선택
            try {
                Sharedpreference.setSharedPrefX(GPSSelAcitivy.this, Float.parseFloat(et_x.getText().toString()));
                Sharedpreference.setSharedPrefY(GPSSelAcitivy.this, Float.parseFloat(et_y.getText().toString()));
                Toast.makeText(GPSSelAcitivy.this,"해당좌표로 선택되었습니다.",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
