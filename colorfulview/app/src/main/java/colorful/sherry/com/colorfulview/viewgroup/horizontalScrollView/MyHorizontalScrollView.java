package colorful.sherry.com.colorfulview.viewgroup.horizontalScrollView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sinye on 15/8/18.
 */
public class MyHorizontalScrollView extends HorizontalScrollView implements View.OnClickListener {
    private CurrentImageChangeListener mListener;
    private OnItemClickListener mOnClickListener;
    /**
     * HorizontalListView中的LinearLayout
     */
    private LinearLayout mContainer;
    /**
     * 子元素的宽度
     */
    private int mChildWidth;
    /**
     * 子元素的高度
     */
    private int mChildHeight;
    /**
     * 当前最后一张图片的index
     */
    private int mCurrentIndex;
    /**
     * 当前第一张图片的下标
     */
    private int mFirstIndex;
    /**
     * 当前第一个View
     */
    private View mFirstView;
    /**
     * 数据适配器
     */
    private HorizontalScrollViewAdapter mAdapter;
    /**
     * 每屏幕最多显示的个数
     */
    private int mCountOneScreen;
    /**
     * 屏幕的宽度
     */
    private int mScreenWitdh;
    /**
     * 保存View与位置的键值对
     */
    private Map<View, Integer> mViewPos = new HashMap<View, Integer>();

    public MyHorizontalScrollView(Context context) {
        this(context, null);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获得屏幕宽度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWitdh = outMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContainer = (LinearLayout) this.getChildAt(0);
    }

    /**
     * 加载下一张图片
     */
    protected void loadNextImg() {
        if (mCurrentIndex == mAdapter.getCount() - 1) {
            return;
        }
        //移除第一张图片，且将水平滚动位置置0
        scrollTo(0, 0);
        mViewPos.remove(mContainer.getChildAt(0));
        mContainer.removeViewAt(0);
        //获取下一张图片，并且设置onClick事件，且加入容器中
        View view = mAdapter.getView(++mCurrentIndex, null, mContainer);
        view.setOnClickListener(this);
        mContainer.addView(view);
        mViewPos.put(view, mCurrentIndex);
        //当前第一张图片下标
        mFirstIndex++;
        if (mListener != null) {
            notifyCurrentImgChanged();
        }
    }

    protected void loadPreImg() {
        if (mFirstIndex == 0) {
            return;
        }
        //获得当前应该显示为第一张图片的下标
        int index = mCurrentIndex - mCountOneScreen;
        if (index > 0) {
            //移除最后一张
            int oldViewPos = mContainer.getChildCount() - 1;
            mViewPos.remove(mContainer.getChildAt(oldViewPos));
            mContainer.removeViewAt(oldViewPos);

            //将此View放入第一个位置
            View view = mAdapter.getView(index, null, mContainer);
            mViewPos.put(view, index);
            mContainer.addView(view, 0);
            view.setOnClickListener(this);
            //水平滚动位置向左移动view的宽度
            scrollTo(mChildWidth, 0);
            //当前位置--，当前第一个显示的下标--
            mCurrentIndex--;
            mFirstIndex--;
            //回调
            if (mListener != null) {
                notifyCurrentImgChanged();
            }
        }
    }

    /**
     * 滑动时回调
     */
    public void notifyCurrentImgChanged() {
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            mContainer.getChildAt(i).setBackgroundColor(Color.WHITE);
        }
        mListener.onCurrentImgChanged(mFirstIndex, mContainer.getChildAt(0));
    }

    public void initDatas(HorizontalScrollViewAdapter adapter) {
        this.mAdapter = adapter;
        mContainer = (LinearLayout) getChildAt(0);
        //获取适配器中第一个View
        final View view = mAdapter.getView(0, null, mContainer);
        mContainer.addView(view);

        //强制计算当前View的宽和高
        if (mChildWidth == 0 && mChildHeight == 0) {
            int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            view.measure(w, h);
            mChildHeight = view.getMeasuredHeight();
            mChildWidth = view.getMeasuredWidth();
            Log.e("TAG", "--view width=" + mChildWidth + "  height=" + mChildHeight);
            //计算每次加载多少个View
            mCountOneScreen = mScreenWitdh / mChildWidth + 2;
            Log.e("TAG", "--mCountOneScreen=" + mCountOneScreen + "  mChildWidth=" + mChildWidth);

        }
        initFirstScreenChildren(mCountOneScreen);
    }

    public void initFirstScreenChildren(int mCount) {
        mContainer = (LinearLayout) getChildAt(0);
        mContainer.removeAllViews();
        mViewPos.clear();

        for (int i = 0; i < mCountOneScreen; i++) {
            View view = mAdapter.getView(i, null, mContainer);
            view.setOnClickListener(this);
            mContainer.addView(view);
            mViewPos.put(view, i);
            mCurrentIndex = i;
        }

        if (mListener != null) {
            notifyCurrentImgChanged();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int scrollX = getScrollX();
                //如果当前scrollX为view的宽度，加载下一张，移除前一张
                if (scrollX >= mChildWidth) {
                    loadNextImg();
                }
                //如果当前scrollX = 0,往前设置一张，移除后一张
                if (scrollX == 0) {
                    loadPreImg();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        if (mOnClickListener != null) {
            for (int i = 0; i < mContainer.getChildCount(); i++) {
                mContainer.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            mOnClickListener.onClick(v, mViewPos.get(v));
        }
    }

    public interface CurrentImageChangeListener {
        void onCurrentImgChanged(int position, View viewIndicator);
    }

    public void setOnItemClickListener(OnItemClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public void setCurrentImageChangeListener(
            CurrentImageChangeListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 条目点击时的回调
     */
    public interface OnItemClickListener {
        void onClick(View view, int pos);
    }

}
