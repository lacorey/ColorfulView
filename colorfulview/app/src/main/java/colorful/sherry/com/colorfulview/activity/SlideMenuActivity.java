package colorful.sherry.com.colorfulview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import colorful.sherry.com.colorfulview.R;
import colorful.sherry.com.colorfulview.viewgroup.scrollerlaunch.LaunchLayout;

/**
 * Created by sinye on 15/8/18.
 */
public class SlideMenuActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slidemenu);

    }

}
