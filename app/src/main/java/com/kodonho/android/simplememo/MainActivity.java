package com.kodonho.android.simplememo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 리사이클러뷰를 연결
        recyclerView = findViewById(R.id.recyclerView);
        // 2. 데이터생성
        List<String> list = Preference.getList(this);
        // 3. 아답터 생성
        adapter = new CustomAdapter();
        // 4. 아답터를 리사이클러뷰에 연결
        recyclerView.setAdapter(adapter);
        // 5. 레이아웃 매니저를 연결
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 6. 아답터에 데이터 넣기 & 갱신 자동
        adapter.setDataAndRefresh(list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> list = Preference.getList(this);
        adapter.setDataAndRefresh(list);
    }

    // 액티비티 이동
    public void goPost(View view){
        // 1.인텐트 생성 - 시스템 메시지 클래스
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
        // 2. 시스템에 인텐트 전달
        startActivity(intent);
    }
}
