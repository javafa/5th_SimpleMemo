package com.kodonho.android.simplememo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private static final String FILENAME = "simplememo";
    // 메모 번호조회
    public static int getMemocount(Context context){
        SharedPreferences pref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        int count = pref.getInt("COUNT", 0) + 1;
        pref.edit().putInt("COUNT", count).commit();
        return count;
    }
    // 메모쓰기
    public static void write(String memo, Context context){
        int count = getMemocount(context); // 메모번호를 가져오고
        String key = "memo_"+count;
        SharedPreferences sharedPref = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        sharedPref.edit().putString(key, memo).commit();
    }
    // 메모읽기
    public static String read(String key, Context context){
        SharedPreferences pref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        String result = pref.getString(key, "");
        return result;
    }
}
