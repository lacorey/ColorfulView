package colorful.sherry.com.colorfulview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import colorful.sherry.com.colorfulview.config.NameHelper;

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

        }
    }
}
