package auto.com.check;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


public class GraphDBAdapter {

    //그래프 데이터베이스
    private static final String DATABASE_NAME = "graph_data.db";
    private static final String DATABASE_TABLE = "tb_graph_data";

    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + " (" + DataEntry.DATA_IDX
            + " INTEGER primary key, "+ DataEntry.DATA_DATE
            + " TEXT, "+ DataEntry.DATA_START_TIME
            + " TEXT, " + DataEntry.DATA_END_TIME
            + " TEXT, " + DataEntry.DATA_TIME
            + " INTEGER);";
    private static final String TAG = "TEMPDBAdapter";

    public String[] COLUMNS = new String[]{DataEntry.DATA_IDX, DataEntry.DATA_DATE,
            DataEntry.DATA_START_TIME, DataEntry.DATA_END_TIME,DataEntry.DATA_TIME
    };
    private String[] CountCOLUMNS = new String[]{"count(idx)"
    };
    private Context mContext;
    private GraphDatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public GraphDBAdapter(Context context) {
        mContext = context;
    }

    public GraphDBAdapter open() throws SQLException {
        try {
            mDbHelper = new GraphDatabaseHelper(mContext);
            mDb = mDbHelper.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        if (mDbHelper != null)
            mDbHelper.close();
    }

    //데이터 입력하기(초기 데이터)
    public long createEntry(String date, String start, String end,String time) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(DataEntry.DATA_DATE,date);
        initialValues.put(DataEntry.DATA_START_TIME, start);
        initialValues.put(DataEntry.DATA_END_TIME, end);
        initialValues.put(DataEntry.DATA_TIME, time);


        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    //마지막 데이터 확인(데이터 업데이트를 위해 필요)
    public int lastIndex(){
        Cursor c = mDb.query(DATABASE_TABLE, COLUMNS, null, null, null, null, DataEntry.DATA_IDX + " DESC");
        if(c.moveToNext()){
            return  c.getInt(0);
        }
        return -1;
    }
    //데이터 업데이트
    public long updateEntry(String idx, String end,String time) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(DataEntry.DATA_END_TIME, end);
        initialValues.put(DataEntry.DATA_TIME, time);

        return mDb.update(DATABASE_TABLE, initialValues, DataEntry.DATA_IDX + " = " + idx, null);

    }


    //해당 인덱스로만 데이터 확인
    public Cursor selectIDXEntry(int nIdx) {
        //
        Cursor qu = mDb.query(DATABASE_TABLE, COLUMNS,
                DataEntry.DATA_IDX + " = " + nIdx,
                null, null, null, null);

        return qu;

    }

    //해당 날짜로 데이터 확인
    public Cursor fetchDateEntry(String date) {
        return mDb.rawQuery("Select * from " + DATABASE_TABLE + " where data_date LIKE '" + date + "%%'", null);
    }
    //모든데이터 지우기
    public int DeleteAllEntry() {
        return mDb.delete(DATABASE_TABLE, null, null);
    }




    //모든데이터 확인
    public Cursor fetchAllEntry() {
        //return mDb.rawQuery("Select * from "+DATABASE_TABLE,null);
        return mDb.query(DATABASE_TABLE, COLUMNS, null, null, null, null, null);
    }

    private class GraphDatabaseHelper extends SQLiteOpenHelper {

        public GraphDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destory all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

    }






    public class DataEntry implements BaseColumns {
        //인덱스
        public static final String DATA_IDX = "idx";
        //날짜
        public static final String DATA_DATE = "data_date";
        //시작시간
        public static final String DATA_START_TIME = "data_start_time";
        //종료시간
        public static final String DATA_END_TIME = "data_end_time";
        //시간
        public static final String DATA_TIME ="data_time";

        
    }

}
