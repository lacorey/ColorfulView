package colorful.sherry.com.colorfulview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import colorful.sherry.com.colorfulview.activity.CustomerViewFourActivity;
import colorful.sherry.com.colorfulview.activity.CustomerViewOneActivity;
import colorful.sherry.com.colorfulview.activity.CustomerViewThreeActivity;
import colorful.sherry.com.colorfulview.activity.CustomerViewTwoActivity;
import colorful.sherry.com.colorfulview.activity.ExaclyActivity;
import colorful.sherry.com.colorfulview.activity.FlowLayoutActivity;
import colorful.sherry.com.colorfulview.activity.HorizontalScrollViewActivity;
import colorful.sherry.com.colorfulview.activity.LaunchActivity;
import colorful.sherry.com.colorfulview.activity.MatchExaclyActivity;
import colorful.sherry.com.colorfulview.activity.SlideMenuActivity;
import colorful.sherry.com.colorfulview.activity.VerticalLinearLayoutActivity;
import colorful.sherry.com.colorfulview.activity.WrapAtMostActivity;
import colorful.sherry.com.colorfulview.config.NameHelper;
import colorful.sherry.com.colorfulview.eventbus.demo.EventBusActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, NameHelper.getNameList()));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                Intent matchIntent = new Intent();
                matchIntent.setClass(this,MatchExaclyActivity.class);
                startActivity(matchIntent);
                break;
            case 1:
                Intent exaclyIntent = new Intent();
                exaclyIntent.setClass(this,ExaclyActivity.class);
                startActivity(exaclyIntent);
                break;
            case 2:
                Intent wrapIntent = new Intent();
                wrapIntent.setClass(this,WrapAtMostActivity.class);
                startActivity(wrapIntent);
                break;
            case 3:
                Intent customer1 = new Intent();
                customer1.setClass(this,CustomerViewOneActivity.class);
                startActivity(customer1);
                break;
            case 4:
                Intent customer2 = new Intent();
                customer2.setClass(this,CustomerViewTwoActivity.class);
                startActivity(customer2);
                break;
            case 5:
                Intent customer3 = new Intent();
                customer3.setClass(this,CustomerViewThreeActivity.class);
                startActivity(customer3);
                break;
            case 6:
                Intent customer4 = new Intent();
                customer4.setClass(this,CustomerViewFourActivity.class);
                startActivity(customer4);
                break;
            case 7:
                Intent flowlayout = new Intent();
                flowlayout.setClass(this,FlowLayoutActivity.class);
                startActivity(flowlayout);
                break;
            case 8:
                Intent scrollIntent = new Intent();
                scrollIntent.setClass(this,HorizontalScrollViewActivity.class);
                startActivity(scrollIntent);
                break;
            case 9:
                Intent verticalLinearIntent = new Intent();
                verticalLinearIntent.setClass(this,VerticalLinearLayoutActivity.class);
                startActivity(verticalLinearIntent);
                break;
            case 10:
                Intent launchIntent = new Intent();
                launchIntent.setClass(this,LaunchActivity.class);
                startActivity(launchIntent);
                break;
            case 11:
                Intent slideMenuIntent = new Intent();
                slideMenuIntent.setClass(this,SlideMenuActivity.class);
                startActivity(slideMenuIntent);
                break;
            case 12:
                Intent eventbusIntent = new Intent();
                eventbusIntent.setClass(this,EventBusActivity.class);
                startActivity(eventbusIntent);
                break;

        }
    }
}
