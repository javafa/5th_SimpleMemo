package com.kodonho.android.simplememo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 1. 현재 메모 번호를 가져와서, 번호 개수만큼 반복하면서 출력
        int count = Preference.getCount(this);
        for(int i=1; i<=count; i++){
            String memo = Preference.read("memo_"+i,this);
            Log.d("SimpleMemo","no="+i+", "+memo);
        }

    }

    // 액티비티 이동
    public void goPost(View view){
        // 1.인텐트 생성 - 시스템 메시지 클래스
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
        // 2. 시스템에 인텐트 전달
        startActivity(intent);
    }
}
