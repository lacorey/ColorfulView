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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        }
    }
}
