package kr.co.korea.sjlim;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
* HOT SUB 뷰 어뎁터
* @author GS ITM 임성진
* @version 1.0.0
* @since 2019-02-13 오전 9:29
**/
public class StretchingViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<String> subViewInfoList = new ArrayList<>();    //  서브 뷰 정보 리스트

    /**
    * 생성자 (파라미터 뷰 리스트)
    * @author GS ITM 임성진
    * @version 1.0.0
    * @since 2019-02-13 오전 9:27
    **/
    public StretchingViewAdapter(ArrayList<String> subViewInfoList) {
        this.subViewInfoList = subViewInfoList;
    }

    /**
    * 생성자 (파라미터 없음)
    * @author GS ITM 임성진
    * @version 1.0.0
    * @since 2019-02-13 오전 9:31
    **/
    public StretchingViewAdapter() {
        this.subViewInfoList = subViewInfoList;
    }

    /**
    * 뷰 정보 개수 반환
    * @author GS ITM 임성진
    * @version 1.0.0
    * @since 2019-02-13 오전 9:27
    **/
    @Override
    public int getItemCount() {
        return subViewInfoList == null ? 0 : subViewInfoList.size();
    }


    /**
    * SUB 뷰에 맞게 뷰 홀더를 만든다.
    * @author GS ITM 임성진
    * @version 1.0.0
    * @since 2019-02-13 오전 9:26
    **/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new childViewHolder(view);
    }


    /**
    * 뷰 타입에 맞게 레이아웃 설정
    * @author GS ITM 임성진
    * @version 1.0.0
    * @since 2019-02-13 오전 9:26
    **/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int listPosition) {

    }



    /**
    * Top Banner View Holder
    * @author D
    * @version 1.0.0
    * @since 2019-02-13 오전 9:22
    **/
    static class childViewHolder extends RecyclerView.ViewHolder {

        public childViewHolder(View itemView) {
            super(itemView);
        }
    }


    /**
    * 뷰 정보를 추가한다.
    * @author GS ITM 임성진
    * @version 1.0.0
    * @since 2019-02-13 오전 9:30
    **/
    public void addSubViewInfo(String childView){
        this.subViewInfoList.add(childView);
    }
}
