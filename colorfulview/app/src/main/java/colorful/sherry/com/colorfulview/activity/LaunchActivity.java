package colorful.sherry.com.colorfulview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import colorful.sherry.com.colorfulview.R;
import colorful.sherry.com.colorfulview.viewgroup.scrollerlaunch.LaunchLayout;
import colorful.sherry.com.colorfulview.viewgroup.verticallinearlayout.VerticalLinearLayout;

/**
 * Created by sinye on 15/8/18.
 */
public class LaunchActivity extends Activity implements View.OnClickListener{
    private LaunchLayout mlayout;
    private Button preBtn,nextBtn;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launch);


        mlayout = (LaunchLayout) findViewById(R.id.launch_layout);
        preBtn = (Button) findViewById(R.id.bt_scrollLeft);
        preBtn.setOnClickListener(this);
        nextBtn = (Button) findViewById(R.id.bt_scrollRight);
        nextBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_scrollLeft:
                if(page > 0){
                    //直接移动
//                    mlayout.scrollPage(page);
                    //scroller移动
                    mlayout.scrollerMovePre(page);
                    page --;
                    Log.e("TAG","--pre page="+page);
                }
                break;
            case R.id.bt_scrollRight:
                if(page < 2){
                    page ++;
//                    mlayout.scrollPage(page);
                    //scroller移动
                    mlayout.scrollerMoveNext(page);
                    Log.e("TAG","--next page="+page);
                }
                break;
        }
    }
}
