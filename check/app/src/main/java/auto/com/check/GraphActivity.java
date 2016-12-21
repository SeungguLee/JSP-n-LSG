package auto.com.check;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GraphActivity extends AppCompatActivity {
    //그래프 가로 최대 크기
    private int GRAPH_X_MAX_COUNT = 30;
    //가로 내용 넣을 리스트
    private LineGraphSeries<DataPoint> mSeries1;

    //마지막 값
    private double graphLastXValue = 0d;
    //그래프뷰
    GraphView graph;
    //해당 달
    String month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        //선택된 달 가져오기
        month = getIntent().getStringExtra("strMonth");


        setData();


    }


    //그래프 설정
    private void setGrapth() {
        graph = (GraphView) findViewById(R.id.graph);

        graph.addSeries(mSeries1);

        //graph.getViewport().setMaxXAxisSize(GRAPH_X_MAX_COUNT);
        graph.getViewport().setXAxisBoundsManual(true);


        graph.getLegendRenderer().setVisible(false);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(GRAPH_X_MAX_COUNT);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);

    }
    //데이터 설정
    private void setData() {
        GraphDBAdapter dbAdapter = new GraphDBAdapter(this);
        dbAdapter.open();
        Cursor c = dbAdapter.fetchDateEntry(month);
        mSeries1 = new LineGraphSeries<>();

         GRAPH_X_MAX_COUNT = c.getCount();
        graphLastXValue = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        while(c.moveToNext()){


            try {
                System.out.println("c.getString"+sdf.parse(c.getString(1))+" "+c.getInt(4));
                mSeries1.appendData(new DataPoint(graphLastXValue, c.getInt(4)), false,10);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mSeries1.setDrawDataPoints(true);
            //mSeries1.setDataPointsRadius();

            graphLastXValue += 1d;

        }
        //가져온 데이터의 개수가 1이상일경우만  그래프 설정
        if(c.getCount()>0){
            setGrapth();
        }

    }
}
