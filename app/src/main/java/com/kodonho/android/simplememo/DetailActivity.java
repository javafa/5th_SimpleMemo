package com.kodonho.android.simplememo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {
    EditText editMemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editMemo = findViewById(R.id.editMemo);
    }
    // 저장 버튼
    public void save(View view){
        // 1.1. 화면에서 입력된 글자를 가져오고
        String memo = editMemo.getText().toString();
        // 2.핸드폰에 글자를 저장한다
        Preference.write(memo,this);
        // 3.현재 쓰기 액티비티를 종료한다
        finish();
    }
}
