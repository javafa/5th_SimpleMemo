package com.kodonho.android.simplememo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private static final String FILENAME = "simplememo";
    // 메모 번호조회
    public static int getMemocount(Context context){
        SharedPreferences pref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        // 1. 현재 메모번호를 가져온다
        int count = pref.getInt("COUNT", 0) + 1;
        // 2. 현재 메모번호를 저장한다
        pref.edit().putInt("COUNT", count).commit();
        // 3. 값 리턴
        return count;
    }
    // 메모쓰기
    public static void write(String memo, Context context){
        int count = getMemocount(context); // 메모번호를 가져오고
        String key = "memo_"+count;
        putString(key, memo, context);
    }
    // 메모읽기
    public static String read(String key, Context context){
        // 1.쉐어드프리퍼런스 가져오기
        SharedPreferences pref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        // 2. 키를 이용해서 값 불러오기
        String result = pref.getString(key, "");
        // 3. 불러온값 리턴
        return result;
    }
    // 문자쓰기
    public static void putString(String key, String value, Context context){
        // 1. 쉐어드프리퍼런스 가져오기
        SharedPreferences sharedPref = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        // 2. 값을 입력할때 사용하는 에디터 열기
        SharedPreferences.Editor editor = sharedPref.edit();
        // 3. 에디터를 통해 값을 저장
        editor.putString(key, value);
        // 4. 저장된 내용 승인
        editor.commit();
    }
}
