package auto.com.check;

import android.content.Context;
import android.content.SharedPreferences;


public class Sharedpreference {

	final private static String PREFNAME_NAME = "SHARE_NAME";

	final private static String PREFKEY_X = "data_x";
	final private static String PREFKEY_Y = "data_y";

	final private static String PREFKEY_LAST_TIME = "data_last_time";
	final private static String PREFKEY_IDX = "data_idx";

	final private static String PREFKEY_TIME = "data_time";
	final private static String PREFKEY_START_TIME = "data_start_time";

	final private static String PREFKEY_END = "end";

	//GPS설정한X 저장하기
	public static void setSharedPrefX(Context context, float value){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putFloat(PREFKEY_X, value);
		prefEditor.commit();
	}
	//GPS설정한X 가져오기
	public static float getSharedPrefX(Context context){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		return pref.getFloat(PREFKEY_X,0.0f);
	}
	//GPS설정한Y 저장하기
	public static void setSharedPrefY(Context context, float value){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putFloat(PREFKEY_Y, value);
		prefEditor.commit();
	}
	//GPS설정한Y 가져오기
	public static float getSharedPrefY(Context context){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		return pref.getFloat(PREFKEY_Y,0.0f);
	}

	//마지막 저장된 시간 저장하기
	public static void setSharedPrefLastTime(Context context, long value){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putLong(PREFKEY_LAST_TIME, value);
		prefEditor.commit();
	}
	//마지막 저장된 시간 가져오기
	public static long getSharedPrefLastTime(Context context){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		return pref.getLong(PREFKEY_LAST_TIME, 0L);
	}
	//현재 저장되고 있는 인덱스 저장하기
	public static void setSharedPrefIdx(Context context, int value){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putInt(PREFKEY_IDX, value);
		prefEditor.commit();
	}
	//현재 저장되고 있는 인덱스 가져오기
	public static int getSharedPrefIdx(Context context){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		return pref.getInt(PREFKEY_IDX, -1);
	}

	//현재 저장하고 있는 시간 저장하기
	public static void setSharedPrefTime(Context context, int value){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putInt(PREFKEY_TIME, value);
		prefEditor.commit();
	}
	//현재 저장하고 있는 시간 가져오기
	public static int getSharedPrefTime(Context context){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		return pref.getInt(PREFKEY_TIME, 0);
	}

	//위치에 처음 있던 시간 저장하기
	public static void setSharedPrefStartTime(Context context, long value){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();
		prefEditor.putLong(PREFKEY_START_TIME, value);
		prefEditor.commit();
	}
	//위치에 처음있던 시간 가져오기
	public static long getSharedPrefStartTime(Context context){
		SharedPreferences pref = context.getSharedPreferences(PREFNAME_NAME, Context.MODE_PRIVATE);
		return pref.getLong(PREFKEY_START_TIME, 0);
	}



}
