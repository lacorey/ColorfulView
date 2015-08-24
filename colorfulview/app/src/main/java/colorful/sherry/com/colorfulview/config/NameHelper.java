package colorful.sherry.com.colorfulview.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinye on 15/7/27.
 */
public class NameHelper {
    public static List<String> getNameList(){
        List<String> list = new ArrayList<String>();
        list.add("Viewgroup exacly (match all)");
        list.add("Viewgroup exacly (200 * 200)");
        list.add("Viewgroup at most (wrap content)");
        list.add("View one (verify code)");
        list.add("view two (display image)");
        list.add("view three (progressbar)");
        list.add("view four (volumn controller)");
        list.add("FlowLayout");
        list.add("HorizontalScrollView");
        list.add("竖向滚动的ViewPager");
        list.add("launch及scroller学习");
        list.add("SlideMenu");
        return list;
    }

}
