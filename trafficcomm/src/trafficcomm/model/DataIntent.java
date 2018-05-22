package trafficcomm.model;

/**
 * Created by Administrator on 2016/3/20.
 */

import android.content.Intent;
import android.text.TextUtils;

import java.util.Hashtable;

//activity之间传递不支持的数据帮助类

public class DataIntent {
    //将要在activity之间传输的数据保存在此
    public static Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
    //用于hashtable的key
    private static long key = 0;


    //根据时间产生零时key
    public static String creatKey() {
        key = (System.currentTimeMillis() + key);
        return key + "";
    }

    //删除零时数据
    public static void deleteObjectByKey(String key) {
        hashtable.remove(key);
    }

    //这里只作为传递activity数据使用 不能作为保存缓存数据使用
    public static Object get(String key) {
        if(isContainsKey(key)){
            Object obj = hashtable.get(key);
            deleteObjectByKey(key);//取出数据后需要删除
            return obj;
        }
        return null;
    }

    public static boolean isContainsKey(String key) {
        return hashtable.containsKey(key);
    }
    //保存数据
    public static void put(String key, Object value) {
        hashtable.put(key, value);
    }

    //将数据保存到hashtable中，然后在intent中传递的是零时的key
    public static void put(Intent intent,String KEY, Object value) {
        String key=DataIntent.creatKey();//根据时间生成key
        DataIntent.put(key, value);//将数据存储到全局map
        intent.putExtra(KEY, key);//intent传递的是key
//hashtable.put(key, value);
    }

    //根据key取得零时key，然后用map的临时key取出真正传递的数据，然后就删除
    public static Object get(Intent intent,String KEY) {
        String key = intent.getStringExtra(KEY);
        Object obj = null;
        if(!TextUtils.isEmpty(key)&&isContainsKey(key)){
            obj = get(key);
        }
        return obj;
    }
}
