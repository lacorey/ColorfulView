package colorful.sherry.com.colorfulview.viewgroup;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinye on 15/8/12.
 */
public class FlowLayout extends ViewGroup{
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=0;
        int height = 0;
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;

        int childSize = getChildCount();
        for(int i=0;i<childSize;i++){
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            int childWidth = child.getMeasuredWidth()+params.leftMargin+params.rightMargin;
            int childHeight = child.getMeasuredHeight()+params.topMargin+params.bottomMargin;
            Log.e("Layout","--childW="+child.getMeasuredWidth()+"   childH="+child.getMeasuredHeight());
            if((lineWidth+childWidth) > sizeWidth){
                height+= lineHeight;
                width = Math.max(lineWidth,childWidth);
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else{
                lineHeight = Math.max(lineHeight,childHeight);
                Log.e("Layout","--l="+params.leftMargin+"  t="+params.topMargin+"  r="+params.rightMargin+"  b="+params.bottomMargin);
                child.setLayoutParams(params);
                lineWidth += childWidth;

            }

            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
            if (i == childSize - 1)
            {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        Log.e("Layout","--sizewidth="+sizeWidth+"  height="+height);
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? sizeWidth : width, heightMode == MeasureSpec.EXACTLY ? sizeHeight : height);


    }

    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mAllViews.clear();
        mLineHeight.clear();
        int lineWidth = 0;
        int lineHeight = 0;
        int width = getWidth();

        int count = this.getChildCount();

        List<View> childList = new ArrayList<View>();
        for(int i=0;i<count;i++){
            View child = this.getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+params.leftMargin+params.rightMargin;
            int childHeight = child.getMeasuredHeight()+params.topMargin+params.bottomMargin;
            if(lineWidth+childWidth > width){
                mLineHeight.add(lineHeight);
                mAllViews.add(childList);
                childList = new ArrayList<View>();
                lineWidth = 0;

            }
            lineHeight = Math.max(lineHeight,childHeight);
            lineWidth += childWidth;
            childList.add(child);
        }
        // 记录最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(childList);


        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++)
        {
            // 每一行的所有的views
            childList = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            Log.e("TAG", "第" + i + "行 ：" + childList.size() + " , " + childList);
            Log.e("TAG", "第" + i + "行， ：" + lineHeight);

            // 遍历当前行所有的View
            for (int j = 0; j < childList.size(); j++)
            {
                View child = childList.get(j);
                if (child.getVisibility() == View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc =lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                Log.e("TAG", child + " , l = " + lc + " , t = " + t + " , r ="
                        + rc + " , b = " + bc);

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }






    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet atts) {
        return new MarginLayoutParams(getContext(),atts);
    }

}
