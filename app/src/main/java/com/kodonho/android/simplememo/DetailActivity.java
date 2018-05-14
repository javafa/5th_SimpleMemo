package com.kodonho.android.simplememo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.PreparedStatement;

public class DetailActivity extends AppCompatActivity {
    public static final String MODE = "mode";
    public static final String KEY = "key";
    public static final int MODE_NEW = 1;
    public static final int MODE_EDIT = 2;

    EditText editMemo;
    int mode = 0;
    String key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editMemo = findViewById(R.id.editMemo);

        Intent intent = getIntent(); // MainActivity에서 값을 받아서 처리
        mode = intent.getIntExtra(MODE, 1);

        switch(mode){
            case MODE_EDIT:
                key = intent.getStringExtra(KEY);
                String temp = Preference.read(key, this);
                editMemo.setText(temp.split(Preference.DELIMETER)[0]);
                break;
        }
    }
    // 저장 버튼
    public void save(View view){
        // 1.1. 화면에서 입력된 글자를 가져오고
        String memo = editMemo.getText().toString();
        // 2.핸드폰에 글자를 저장한다

        switch(mode){
            case MODE_NEW:
                Preference.write(memo, this);
                break;
            case MODE_EDIT:
                Preference.modify(key,memo,this);
                break;
        }
        // 3.현재 쓰기 액티비티를 종료한다
        // 호출한 액티비티로 값을 돌려줄때
//        Intent intent = new Intent();
//        intent.putExtra("result","결과값");
        setResult(RESULT_OK);

        finish();
    }
}
