package kr.co.korea.sjlim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import kr.co.korea.sjlim.stretchingtoprecyclerview.StretchingToTopRecylcerView;

public class MainActivity extends AppCompatActivity {

    private StretchingToTopRecylcerView sttvSubViewList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 서브 뷰 리스트
        this.sttvSubViewList = (StretchingToTopRecylcerView) this.findViewById(R.id.sttr_list);
        this.sttvSubViewList.setLayoutManager(new LinearLayoutManager(this));
        this.sttvSubViewList.setItemAnimator(new DefaultItemAnimator());

        this.sttvSubViewList.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return false;
            }
        });


        // 어뎁터 생성
        StretchingViewAdapter stretchingViewAdapter = new StretchingViewAdapter();

        // 뷰 순서에 맞게 나열하면 순서에 맞게 뷰가 추가된다.
        stretchingViewAdapter.addSubViewInfo("child1");
        stretchingViewAdapter.addSubViewInfo("child2");
        stretchingViewAdapter.addSubViewInfo("child3");


        // 어뎁터 추가
        this.sttvSubViewList.setAdapter(stretchingViewAdapter);
        stretchingViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        // 스크린이 터치되었다고 알린다.
        this.sttvSubViewList.onScreenTouch(ev.getAction(), ev.getX(), ev.getY());

        return super.dispatchTouchEvent(ev);
    }
}
