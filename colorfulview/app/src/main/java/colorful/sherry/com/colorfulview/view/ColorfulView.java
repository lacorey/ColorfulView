package colorful.sherry.com.colorfulview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by sinye on 15/7/27.
 */
public class ColorfulView extends ViewGroup {
    public static final String TAG = "ColorfulView";

    public ColorfulView(Context context) {
        super(context);
    }

    public ColorfulView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean bl, int l, int t, int r, int b) {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        for(int i=0;i<cCount;i++){
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;
            if(i == 0){
                cl += cParams.leftMargin;
                ct += cParams.topMargin;
            }
            if(i == 1){
                cl = getWidth() - cWidth  - cParams.rightMargin;
                ct = cParams.topMargin;
            }
            if(i == 2){
                cl += cParams.leftMargin;
                ct = getHeight() - cHeight - cParams.bottomMargin;
            }
            if(i == 3){
                cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
                ct = getHeight() - cHeight - cParams.bottomMargin;
            }

            cr += cWidth + cl;
            cb += cHeight + ct;

            childView.layout(cl,ct,cr,cb);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"---------onMeasure");
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int cCount = getChildCount();
        int width = 0;
        int height = 0;

        int cWidth = 0;
        int cHeight = 0;

        int tWidth = 0;
        int bWidth = 0;
        int lHeight = 0;
        int rHeight = 0;

        MarginLayoutParams cParams = null;

        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();
            if(i == 0 || i == 1){
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin ;
            }
            if(i == 2 || i == 3){
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if(i == 0 || i == 2){
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
            if(i == 1 || i == 3){
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
        }

        width = Math.max(tWidth,bWidth);
        height = Math.max(lHeight,rHeight);

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? sizeWidth : width,
                heightMode == MeasureSpec.EXACTLY ? sizeHeight : height);

        Log.e(TAG,"--Mode.Exacly="+(widthMode == MeasureSpec.EXACTLY) + "   sizeWidth="+sizeHeight + "   width="+width);


    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
