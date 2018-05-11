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
        String memo = Preference.read(this);
        Log.d("SimpleMemo","memo="+memo);
    }

    // 액티비티 이동
    public void goPost(View view){
        // 1.인텐트 생성 - 시스템 메시지 클래스
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
        // 2. 시스템에 인텐트 전달
        startActivity(intent);
    }
}
