package colorful.sherry.com.colorfulview.viewgroup.scrollerlaunch;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by sinye on 15/8/24.
 */
public class LaunchLayout extends ViewGroup {
    private Context mContext;
    private int mScreenHeight, mScreenWidth;
    private Scroller mScroller;

    public LaunchLayout(Context context) {
        this(context, null);
    }

    public LaunchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void init() {
        /**
         * 获得屏幕的高度
         */
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;

        mScroller = new Scroller(mContext);

        // 初始化3个 LinearLayout控件
        LinearLayout oneLL = new LinearLayout(mContext);
        oneLL.setBackgroundColor(Color.RED);
        addView(oneLL);

        LinearLayout twoLL = new LinearLayout(mContext);
        twoLL.setBackgroundColor(Color.YELLOW);
        addView(twoLL);

        LinearLayout threeLL = new LinearLayout(mContext);
        threeLL.setBackgroundColor(Color.BLUE);
        addView(threeLL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.measure(mScreenWidth, mScreenHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(left, 0, left + mScreenWidth, mScreenHeight);
            left += mScreenWidth;
        }
    }

    /**
     * 使用scrollTo直接移动到指定位置，不平滑
     *
     * @param page
     */
    public void scrollPage(int page) {
        this.scrollTo(page * mScreenWidth, 0);
    }

    public void scrollerMoveNext(int curScreen) {
        //使用动画控制偏移过程 , 3s内到位
        mScroller.startScroll((curScreen - 1) * getWidth(), 0, getWidth(), 0, 1000);
        //其实点击按钮的时候，系统会自动重新绘制View，我们还是手动加上吧。
        invalidate();
    }

    public void scrollerMovePre(int curScreen){
        //使用动画控制偏移过程 , 3s内到位
        mScroller.startScroll((curScreen) * getWidth(), 0, -getWidth(), 0, 1000);
        //其实点击按钮的时候，系统会自动重新绘制View，我们还是手动加上吧。
        invalidate();
    }

    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        Log.e("Layout", "computeScroll");
        // 如果返回true，表示动画还没有结束
        // 因为前面startScroll，所以只有在startScroll完成时 才会为false
        if (mScroller.computeScrollOffset()) {
            Log.e("Layout", mScroller.getCurrX() + "======" + mScroller.getCurrY());
            // 产生了动画效果，根据当前值 每次滚动一点
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            Log.e("Layout", "### getleft is " + getLeft() + " ### getRight is " + getRight());
            //此时同样也需要刷新View ，否则效果可能有误差
            postInvalidate();
        } else
            Log.i("Layout", "have done the scoller -----");
    }
}
