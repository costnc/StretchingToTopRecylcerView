package kr.co.korea.sjlim.stretchingtoprecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;


/**
 * RecyclerView 의 첫번째 뷰를 Stretching을 하기 위한 RecyclerView
 * @author 임성진(sjlim/costnc)
 * @version 1.0.0
 * @since 2019-02-13 오후 7:21
 **/
public class StretchingToTopRecylcerView extends RecyclerView {

    private final static float DEFAULT_TOP_MAX_HEIGHT_SCALE = (float) 1.5;    // 상위 뷰 높이가 최대 얼마 만큼 증가할것인지 설정
    private final static int DEFAULT_TOP_STEP_SIZE = 2;                         // 상위 뷰가 얼마씩 높이가 증가할것인지 설정

    private float topMaxHeightScale = DEFAULT_TOP_MAX_HEIGHT_SCALE;          // 상위 뷰 높이가 최대 얼마 만큼 증가할것인지 설정
    private int topStepSize = DEFAULT_TOP_STEP_SIZE;                           // 상위 뷰가 얼마씩 높이가 증가할것인지 설정

    private int currentScrollY = 0;                                      // 현재 스크롤 Y 값
    private int initTopViewHeight = 0;                                  // 초기 상단 뷰 높이
    private float beforeTouchY = 0;                                     // 이전 화면 터치한 Y 값

    private View vStretchingTopView = null; // 늘릴 최상위 뷰

    public StretchingToTopRecylcerView(Context context) {
        super(context);
        init();
    }

    public StretchingToTopRecylcerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StretchingToTopRecylcerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 초기화
     * @author 임성진(sjlim/costnc)
     * @version 1.0.0
     * @since 2019-02-13 오후 8:02
     **/
    private void init(){

        // 스크롤 변경 리스너 등록
        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 현재 스크롤 Y 값 기록
                currentScrollY = dy;

            }
        });

    }
    /**
     * 스크린 터치시 호출됨
     *
     * @author 임성진(sjlim/costnc)
     * @version 1.0.0
     * @since 2019-02-13 오후 2:57
     **/
    synchronized public void onScreenTouch(final int action, float x, float y) {

        // 첫번째 뷰 홀더(최상위 뷰)를 가져온다. (viewHolder가 없을 수도 있으므로, 매번 가져온다.)
        RecyclerView.ViewHolder viewHolder = this.findViewHolderForLayoutPosition(0);

        if (viewHolder != null) {

            this.vStretchingTopView = viewHolder.itemView;

            ViewGroup.LayoutParams layoutParams = null;

            // recycler가 최상단까지 갔는 상태
            if (this.currentScrollY <= 0 && this.beforeTouchY != 0) {

                final float calY = this.beforeTouchY - y;

                if (calY < 0) {

                    if (this.vStretchingTopView != null) {

                        switch (action) {

                            // 누르고 있을때 뷰를 스케일링한다.
                            case MotionEvent.AXIS_PRESSURE:

                                layoutParams = this.vStretchingTopView.getLayoutParams();

                                if (initTopViewHeight == 0) {
                                    initTopViewHeight = this.vStretchingTopView.getMeasuredHeight();
                                }

                                int calHeight = this.vStretchingTopView.getMeasuredHeight() + topStepSize;

                                // 증가되는 부모 뷰 높이는 최대 높이를 넘기지 못하도록 한다.
                                if ((this.initTopViewHeight * topMaxHeightScale) >= calHeight) {

                                    // 부모 뷰의 높이를 조절한다.
                                    layoutParams.height = calHeight;
                                    this.vStretchingTopView.setLayoutParams(layoutParams);

                                    // 자식 뷰는 스케일 효과를 준다.
                                    ViewGroup vgView = (ViewGroup) this.vStretchingTopView;
                                    View childVIew = vgView.getChildAt(0);
                                    childVIew.setScaleX(childVIew.getScaleX() + (float) 0.01);
                                    childVIew.setScaleY(childVIew.getScaleY() + (float) 0.01);
                                }

                                break;

                            // 손을 놓았을때, 뷰를 원위치 시킨다.
                            case MotionEvent.ACTION_UP:

                                // 높이를 애니멩션 효과를 주어, 원위치 시킨다.
                                Animation heightAnim = new ShowAnim(this.vStretchingTopView, initTopViewHeight);
                                heightAnim.setDuration(200);
                                heightAnim.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        beforeTouchY = 0;
                                        initTopViewHeight = 0;
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });

                                // 애니메이션 시작
                                this.vStretchingTopView.startAnimation(heightAnim);

                                // 자식 뷰의 스케일을 원위치 시킨다.
                                ViewGroup vgView = (ViewGroup) this.vStretchingTopView;
                                View childVIew = vgView.getChildAt(0);
                                childVIew.animate().scaleX(1);
                                childVIew.animate().scaleY(1);

                                break;
                            default:
                                break;

                        }

                    }

                }

            } else if (this.currentScrollY <= 0 && this.beforeTouchY == 0) {

                this.beforeTouchY = y;

            } else if (this.currentScrollY <= 0) {

                this.beforeTouchY = 0;

            }
        }
    }

    public float getTopMaxHeightScale() {
        return topMaxHeightScale;
    }

    public void setTopMaxHeightScale(float topMaxHeightScale) {
        this.topMaxHeightScale = topMaxHeightScale;
    }

    public int getTopStepSize() {
        return topStepSize;
    }

    public void setTopStepSize(int topStepSize) {
        this.topStepSize = topStepSize;
    }
}
