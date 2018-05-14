package com.kodonho.android.simplememo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Preference {
    public static final String DELIMETER = ":::";
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
    // 메모수정
    public static void modify(String key, String memo, Context context){
        // 1.2. 현재 날짜시간을 가져온다
        long now = System.currentTimeMillis();
        memo = memo + DELIMETER + now;
        memo = memo + DELIMETER + key;
        SharedPreferences sharedPref = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        sharedPref.edit().putString(key, memo).commit();
    }
    // 메모쓰기
    public static void write(String memo, Context context){
        int count = increaseCount(context); // 메모번호를 가져오고
        String key = "memo_"+count;
        // 1.2. 현재 날짜시간을 가져온다
        modify(key, memo, context);
    }
    // 메모삭제
    public static void remove(String key, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        sharedPref.edit().remove(key).commit();
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
            // 중간에 데이터가 삭제되었으면 목록에 더하지 않는다
            String memo = Preference.read("memo_"+i,context);
            if(memo != null && !"".equals(memo))
                result.add(memo);
        }
        return result;
    }
}
