package colorful.sherry.com.colorfulview.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import colorful.sherry.com.colorfulview.R;
import colorful.sherry.com.colorfulview.viewgroup.horizontalScrollView.HorizontalScrollViewAdapter;
import colorful.sherry.com.colorfulview.viewgroup.horizontalScrollView.MyHorizontalScrollView;
import colorful.sherry.com.colorfulview.viewgroup.verticallinearlayout.VerticalLinearLayout;

/**
 * Created by sinye on 15/8/18.
 */
public class VerticalLinearLayoutActivity extends Activity{
    private VerticalLinearLayout mMianLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_verticalinearlayout);


        mMianLayout = (VerticalLinearLayout) findViewById(R.id.id_main_ly);
        mMianLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener()
        {
            @Override
            public void onPageChange(int currentPage)
            {
//              mMianLayout.getChildAt(currentPage);
                Toast.makeText(VerticalLinearLayoutActivity.this, "第" + (currentPage + 1) + "页", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
