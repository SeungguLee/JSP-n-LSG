package auto.com.check;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_main,btn_gps,btn_memo,btn_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initRes();
        //gps서비스 종료후 재시작
        Intent intent2 = new Intent("auto.com.check.MyService");
        intent2.setPackage("auto.com.check");
        stopService(intent2);
        startService(intent2);
        //퍼미션체크 6.0이상부터 사용자가 권한주기 위하여 함수 구현
        setTedCheckPermission();
    }

    private void initRes() {
        btn_main = (Button)findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);
        btn_gps = (Button)findViewById(R.id.btn_gps);
        btn_gps.setOnClickListener(this);
        btn_memo = (Button)findViewById(R.id.btn_memo);
        btn_memo.setOnClickListener(this);
        btn_end = (Button)findViewById(R.id.btn_end);
        btn_end.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_main){
            //메인버튼
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            startActivity(intent);
        }else if(view==btn_memo){
            //메모버튼
            Intent intent = new Intent(MenuActivity.this,ScheduleActivity.class);
            startActivity(intent);
        }
        else if(view == btn_gps){
            //gps설정버튼
            Intent intent = new Intent(MenuActivity.this,GPSSelAcitivy.class);
            startActivity(intent);
        }else if(view== btn_end){
            //종료버튼
            Intent intent2 = new Intent("auto.com.check.MyService");
            intent2.setPackage("auto.com.check");
            stopService(intent2);
            Toast.makeText(MenuActivity.this,"서비스가 종료되었습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    //마시멜로우 권한관련
    public void setTedCheckPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

                Toast.makeText(MenuActivity.this, "권한이 모두 설정되었습니다.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MenuActivity.this, "아래 권한이 없습니다.\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };


        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("이 앱를 사용하시려면 권한이 필요합니다.")
                .setDeniedMessage("만약 이앱의 권한을 원하지 않는 다면, 설정-권한에서 설정하세요.")
                .setGotoSettingButtonText("이동")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }
}
