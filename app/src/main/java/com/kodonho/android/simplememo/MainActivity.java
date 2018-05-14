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

public class MainActivity extends AppCompatActivity implements CustomAdapter.Callback{

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
        adapter = new CustomAdapter(this);
        // 4. 아답터를 리사이클러뷰에 연결
        recyclerView.setAdapter(adapter);
        // 5. 레이아웃 매니저를 연결
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 6. 아답터에 데이터 넣기 & 갱신 자동
        adapter.setDataAndRefresh(list);
    }

    // 액티비티 이동
    public void goPost(View view){
        // 1.인텐트 생성 - 시스템 메시지 클래스
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.MODE,DetailActivity.MODE_NEW);
        // 2. 시스템에 인텐트 전달
        startActivityForResult(intent, REQ_DETAIL);
    }

    public static final int REQ_DETAIL = 99;
    public static final int REQ_EDIT = 10000;
    public static final int REQ_WRITE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQ_DETAIL:
                case REQ_EDIT:
                    List<String> list = Preference.getList(this);
                    adapter.setDataAndRefresh(list);
                    break;
            }
        }else{
            // cancel 처리
        }
    }
    @Override
    public void goEdit(String memoKey) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.MODE, DetailActivity.MODE_EDIT);
        intent.putExtra(DetailActivity.KEY,memoKey);
        startActivityForResult(intent, REQ_EDIT);
    }
}
