package auto.com.check;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyService extends Service {
    // GPSTracker class
    private GpsInfo gps;

    public MyService() {

    }
    Handler handler = new Handler();
    Runnable checkR = new Runnable() {
        @Override
        public void run() {
            gps = new GpsInfo(MyService.this);
            // GPS 사용유무 가져오기
            if (gps.isGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

            /*txtLat.setText(String.valueOf(latitude));
            txtLon.setText(String.valueOf(longitude));*/
                GraphDBAdapter dbAdapter = new GraphDBAdapter(MyService.this);
                dbAdapter.open();
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

                double dist = distance(latitude,longitude,Sharedpreference.getSharedPrefX(MyService.this),Sharedpreference.getSharedPrefY(MyService.this),"meter");
                //100미터이상일경우
                if(dist>100) {

                    Toast.makeText(
                            getApplicationContext(),
                            "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude+"NO",
                            Toast.LENGTH_LONG).show();
                    System.out.println("당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude+"NO");
                }
                //100미터이내일겨우
                else{
                    long agotime = Sharedpreference.getSharedPrefLastTime(MyService.this);
                    if(System.currentTimeMillis()-agotime<120000){
                        Sharedpreference.setSharedPrefTime(MyService.this, (int)(System.currentTimeMillis()-Sharedpreference.getSharedPrefStartTime(MyService.this))/60000);

                        dbAdapter.updateEntry(Sharedpreference.getSharedPrefIdx(MyService.this) + "", sdfTime.format(System.currentTimeMillis()), Sharedpreference.getSharedPrefTime(MyService.this)+"");

                    }else{
                        dbAdapter.createEntry(sdfDate.format(System.currentTimeMillis()), sdfTime.format(new Date(System.currentTimeMillis())), sdfTime.format(System.currentTimeMillis()), "0");
                        int lastidx = dbAdapter.lastIndex();
                        Sharedpreference.setSharedPrefIdx(MyService.this,lastidx);
                        Sharedpreference.setSharedPrefTime(MyService.this, 0);
                        Sharedpreference.setSharedPrefStartTime(MyService.this, System.currentTimeMillis());
                    }
                    Sharedpreference.setSharedPrefLastTime(MyService.this,System.currentTimeMillis());
                    Toast.makeText(
                            getApplicationContext(),
                            "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude+"OK",
                            Toast.LENGTH_LONG).show();
                    System.out.println("당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude+"OK");
                }


            } else {
                // GPS 를 사용할수 없으므로
                //gps.showSettingsAlert();
            }

            handler.postDelayed(checkR,60000);
        }
    };
    public void onCreate() {
        super.onCreate();
        handler.removeCallbacks(checkR);
        handler.post(checkR);



    }

    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(checkR);


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lon1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lon2 지점 2 경도
     * @param unit 거리 표출단위
     * @return
     */
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }


    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


}
