package trafficcomm.model;

/**
 * Created by Administrator on 2016/3/20.
 */

import android.content.Intent;
import android.text.TextUtils;

import java.util.Hashtable;

//activity֮�䴫�ݲ�֧�ֵ����ݰ�����

public class DataIntent {
    //��Ҫ��activity֮�䴫������ݱ����ڴ�
    public static Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
    //����hashtable��key
    private static long key = 0;


    //����ʱ�������ʱkey
    public static String creatKey() {
        key = (System.currentTimeMillis() + key);
        return key + "";
    }

    //ɾ����ʱ����
    public static void deleteObjectByKey(String key) {
        hashtable.remove(key);
    }

    //����ֻ��Ϊ����activity����ʹ�� ������Ϊ���滺������ʹ��
    public static Object get(String key) {
        if(isContainsKey(key)){
            Object obj = hashtable.get(key);
            deleteObjectByKey(key);//ȡ�����ݺ���Ҫɾ��
            return obj;
        }
        return null;
    }

    public static boolean isContainsKey(String key) {
        return hashtable.containsKey(key);
    }
    //��������
    public static void put(String key, Object value) {
        hashtable.put(key, value);
    }

    //�����ݱ��浽hashtable�У�Ȼ����intent�д��ݵ�����ʱ��key
    public static void put(Intent intent,String KEY, Object value) {
        String key=DataIntent.creatKey();//����ʱ������key
        DataIntent.put(key, value);//�����ݴ洢��ȫ��map
        intent.putExtra(KEY, key);//intent���ݵ���key
//hashtable.put(key, value);
    }

    //����keyȡ����ʱkey��Ȼ����map����ʱkeyȡ���������ݵ����ݣ�Ȼ���ɾ��
    public static Object get(Intent intent,String KEY) {
        String key = intent.getStringExtra(KEY);
        Object obj = null;
        if(!TextUtils.isEmpty(key)&&isContainsKey(key)){
            obj = get(key);
        }
        return obj;
    }
}
