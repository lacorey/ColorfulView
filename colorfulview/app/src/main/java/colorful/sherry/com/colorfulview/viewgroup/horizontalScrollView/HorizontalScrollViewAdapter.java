package colorful.sherry.com.colorfulview.viewgroup.horizontalScrollView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import colorful.sherry.com.colorfulview.R;

/**
 * Created by sinye on 15/8/18.
 */
public class HorizontalScrollViewAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mDatas;

    public HorizontalScrollViewAdapter(Context context, List<Integer> mDatas)
    {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    public int getCount(){
        return mDatas.size();
    }

    public Object getItem(int position){
        return mDatas.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position,View converView,ViewGroup parent){
        ViewHolder viewHolder = null;
        if(converView == null){
            viewHolder = new ViewHolder();
            converView = mInflater.inflate(R.layout.horizontal_scrollview_item,parent,false);
            viewHolder.mImg = (ImageView) converView.findViewById(R.id.id_index_gallery_item_image);
            viewHolder.mText = (TextView) converView.findViewById(R.id.id_index_gallery_item_text);
            converView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) converView.getTag();
        }
        viewHolder.mImg.setImageResource(mDatas.get(position));
        viewHolder.mText.setText("some info");
        return converView;
    }

    private class ViewHolder
    {
        ImageView mImg;
        TextView mText;
    }
}
