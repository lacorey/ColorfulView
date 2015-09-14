package colorful.sherry.com.colorfulview.eventbus.demo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import colorful.sherry.com.colorfulview.eventbus.EventBus;


public class ItemListFragment extends ListFragment
{
	public static final String TAG = "ItemListFragment";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// Register
		EventBus.getInstatnce().register(this);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// Unregister
		EventBus.getInstatnce().unregister(this);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		// 开启线程加载列表
		new Thread()
		{
			public void run()
			{
				try
				{
					Log.e(TAG,"onViewCreated");
					Log.e(TAG,"--threadid="+currentThread().getId());
					Thread.sleep(2000); // 模拟延时
					// 发布事件，在后台线程发的事件
					EventBus.getInstatnce().post(new Event.ItemListEvent(Item.ITEMS));
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			};
		}.start();
	}

	public void onEventUI(Event.ItemListEvent event)
	{
		Log.e(TAG,"onEventUI");
		setListAdapter(new ArrayAdapter<Item>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, event.getItems()));
	}

	public void onEventThread(Event.ItemListEvent event){
		Log.e(TAG,"onEventThread");
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id)
	{
		super.onListItemClick(listView, view, position, id);
		EventBus.getInstatnce().post(getListView().getItemAtPosition(position));
	}

}
