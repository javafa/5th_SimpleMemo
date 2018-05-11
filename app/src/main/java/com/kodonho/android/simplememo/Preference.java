package com.kodonho.android.simplememo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Preference {
    private static final String FILENAME = "simplememo";
    private static final String COUNT = "COUNT";
    // 최종 메모 번호 읽기
    public static int getCount(Context context){
        return context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE).getInt(COUNT,0);
    }
    // 메모 번호증가
    public static int increaseCount(Context context){
        SharedPreferences pref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        int count = pref.getInt(COUNT, 0) + 1;
        pref.edit().putInt(COUNT, count).commit();
        return count;
    }
    // 메모쓰기
    public static void write(String memo, Context context){
        int count = increaseCount(context); // 메모번호를 가져오고
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
    // 메모 목록을 가져오는 함수
    public static List<String> getList(Context context){
        List<String> result = new ArrayList<>();
        int count = Preference.getCount(context);
        for(int i=1; i<=count; i++){
            result.add(Preference.read("memo_"+i,context));
        }
        return result;
    }
}
