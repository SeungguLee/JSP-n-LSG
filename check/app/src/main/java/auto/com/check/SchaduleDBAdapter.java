package auto.com.check;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


public class SchaduleDBAdapter {

    //메모데이터베이스
    private static final String DATABASE_NAME = "sche_data.db";
    private static final String DATABASE_TABLE = "tb_sche_data";

    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + " (" + DataEntry.DATA_IDX
            + " INTEGER primary key, "+ DataEntry.DATA_DATE

            + " TEXT, " + DataEntry.DATA_TEXT
            + " TEXT);";
    private static final String TAG = "TEMPDBAdapter";

    public String[] COLUMNS = new String[]{DataEntry.DATA_IDX, DataEntry.DATA_DATE,
             DataEntry.DATA_TEXT
    };
    private String[] CountCOLUMNS = new String[]{"count(idx)"
    };
    private Context mContext;
    private GraphDatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public SchaduleDBAdapter(Context context) {
        mContext = context;
    }

    public SchaduleDBAdapter open() throws SQLException {
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

    //입력하기
    public long createEntry(String date,String text) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(DataEntry.DATA_DATE,date);
        initialValues.put(DataEntry.DATA_TEXT, text);


        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    //업데이트하기
    public long updateEntry(String date,String text) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(DataEntry.DATA_TEXT, text);

        return mDb.update(DATABASE_TABLE, initialValues, DataEntry.DATA_DATE + " = '" + date+"'", null);

    }
    //해당 날짜로 데이터확인하기
    public Cursor selectDateEntry(String date) {
        //
        Cursor qu = mDb.query(DATABASE_TABLE, COLUMNS,
                DataEntry.DATA_DATE + " = '" + date+"'",
                null, null, null, null);

        return qu;

    }
    //해당 인덱스로 데이터확인하기
    public Cursor selectIDXEntry(int nIdx) {
        //
        Cursor qu = mDb.query(DATABASE_TABLE, COLUMNS,
                DataEntry.DATA_IDX + " = " + nIdx,
                null, null, null, null);

        return qu;

    }


    //모든 데이터 지우기
    public int DeleteAllEntry() {
        return mDb.delete(DATABASE_TABLE, null, null);
    }




    //모든데이터 확인하기
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
        //시간
        public static final String DATA_TEXT ="data_text";

        
    }

}
