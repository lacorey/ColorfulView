package colorful.sherry.com.colorfulview.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinye on 15/7/27.
 */
public class NameHelper {
    public static List<String> getNameList(){
        List<String> list = new ArrayList<String>();
        list.add("exacly (match all)");
        list.add("exacly (200 * 200)");
        list.add("at most (wrap content)");
        return list;
    }

}
