package kr.co.korea.sjlim.stretchingtoprecyclerview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
* 천천히 사라지는 애니메이션
* @author 임성진(sjlim)
* @version 1.0.0
* @since 2019-02-13 오후 7:48
**/
public class ShowAnim extends Animation {

    private int targetHeight;
    private View view;
    private int originHeight;

    public ShowAnim(View view, int targetHeight) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.originHeight = view.getHeight();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        int calHeight = this.originHeight - this.targetHeight;

        this.view.getLayoutParams().height = this.originHeight - (int) (calHeight * interpolatedTime);
        this.view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

}