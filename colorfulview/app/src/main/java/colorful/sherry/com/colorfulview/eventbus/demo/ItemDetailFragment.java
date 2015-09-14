package colorful.sherry.com.colorfulview.eventbus.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import colorful.sherry.com.colorfulview.R;
import colorful.sherry.com.colorfulview.eventbus.EventBus;


public class ItemDetailFragment extends Fragment
{

	private TextView tvDetail;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// register
		EventBus.getInstatnce().register(this);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// Unregister
		EventBus.getInstatnce().unregister(this);
	}

	/** List点击时会发送些事件，接收到事件后更新详情 */
	public void onEventUI(Item item)
	{
		if (item != null)
			tvDetail.setText(item.content);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		tvDetail = (TextView) rootView.findViewById(R.id.item_detail);
		return rootView;
	}
}
