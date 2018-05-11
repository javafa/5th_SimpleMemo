package com.kodonho.android.simplememo;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//3. 아답터를 구현 - 아답터를 상속받고, 내가 정의한 Holder를 제네릭으로 선언한다
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    // 4. 아답터에서 사용할 데이터 선언
    List<String> list = new ArrayList<>();

    // 8. 아답터에 데이터를 세팅하고 아답터를 갱신한다
    public void setDataAndRefresh(List<String> list){
        this.list = list;
        notifyDataSetChanged(); // 변경된 데이터를 화면에 반영
    }

    // 6. 목록에서 사용할 아이템 레이아웃.xml을 클래스로 변환하고 instance화해서 메모리에 로드한다
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 6.1 xml파일을 변환한다
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // 6.2 변환된 view를 Holder에 담는다
        Holder holder = new Holder(view);
        // 6.3 holder를 리턴한다
        return holder;
    }
    // 7. 생성된 Holder에 데이터를 세팅한다
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        // 7.1 데이터목록에 있는 데이터를 개별로 한개씩 꺼낸다
        String memo = list.get(position);
        // 7.2 화면에 데이터를 세팅
        holder.textMemo.setText(memo);
        holder.textNo.setText( (position+1) + "" );
    }

    // 5. 목록에 출력되는 아이템의 개수
    @Override
    public int getItemCount() {
        return list.size();
    }

    // 1. Holder를 먼저 만든다 - 아이템 레이아웃 관리
    public class Holder extends RecyclerView.ViewHolder{
        // 2. 아이템 레이아웃에서 사용하는 위젯을 모두 선언
        TextView textNo, textMemo, textDate;
        ConstraintLayout item;
        public Holder(View itemView) {
            super(itemView);
            // 3. findViewById로 소스코드와 화면을 연결
            textNo = itemView.findViewById(R.id.textNo);
            textMemo = itemView.findViewById(R.id.textMemo);
            textDate = itemView.findViewById(R.id.textDate);
            item = itemView.findViewById(R.id.item);
        }
    }
}
