package colorful.sherry.com.colorfulview.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import colorful.sherry.com.colorfulview.R;
import colorful.sherry.com.colorfulview.viewgroup.horizontalScrollView.HorizontalScrollViewAdapter;
import colorful.sherry.com.colorfulview.viewgroup.horizontalScrollView.MyHorizontalScrollView;

/**
 * Created by sinye on 15/8/18.
 */
public class HorizontalScrollViewActivity extends Activity{
    private MyHorizontalScrollView mHorizontalScrollView;
    private HorizontalScrollViewAdapter mAdapter;
    private ImageView mImg;
    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
            R.drawable.l));

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_horizontal_scrollview);

        mImg = (ImageView) findViewById(R.id.id_content);

        mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
        mAdapter = new HorizontalScrollViewAdapter(this, mDatas);
        //添加滚动回调
        mHorizontalScrollView
                .setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener()
                {
                    @Override
                    public void onCurrentImgChanged(int position,
                                                    View viewIndicator)
                    {
                        mImg.setImageResource(mDatas.get(position));
                        viewIndicator.setBackgroundColor(Color
                                .parseColor("#AA024DA4"));
                    }
                });
        //添加点击回调
        mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener()
        {

            @Override
            public void onClick(View view, int position)
            {
                mImg.setImageResource(mDatas.get(position));
                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        //设置适配器
        mHorizontalScrollView.initDatas(mAdapter);
    }
}
