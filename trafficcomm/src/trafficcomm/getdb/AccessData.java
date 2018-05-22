package trafficcomm.getdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import trafficcomm.model.appmodel;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class AccessData {
	//List<Map<String, Object>> data;
	private SQLHelper helper;
	public  Context context;
	Map<String, Object> item;
	 ArrayList<Map<String, Object>> data1;
	 int number;
	public  AccessData(Context context,int number){
		helper = new SQLHelper(number);
		this.context = context;
		this.number=number;
	}
	public Map<String, Object> QueryAPP(String selection,String[] selectionArgs){
		
		Cursor cursor =helper.Query(context, "appdownload", null, selection, selectionArgs, null, null, null);
  
        if (cursor.moveToFirst()){    	
        	do{
        		item = new HashMap<String,Object>();
        		appmodel app=new appmodel();
        		app.setId(cursor.getString(0));
        		app.setAppname(cursor.getString(1));
        		app.seturl(cursor.getString(2));
        		app.setpic(cursor.getString(3));
        		app.setdex(cursor.getString(4));
        		item.put("id",app.getId());
        		item.put("appname",app.getAppname());
        		item.put("url", app.geturl()); 
        		item.put("pic", app.getpic());
        		item.put("dex", app.getdex());
        		}
            while(cursor.moveToNext());
        }
        cursor.close();
		return item;
		
	}
public ArrayList<Map<String, Object>>  QueryAPP2(String selection,String[] selectionArgs){
	    Cursor cursor=null;
	    if(number==4){
	    	cursor=helper.Query(context, "appdownload", null, selection, selectionArgs, null, null, "popularrank");
	    	
	    }
	    else{
		cursor =helper.Query(context, "appdownload", null, selection, selectionArgs, null, null, null);}
		
		data1 = new ArrayList<Map<String, Object>>();
  
        if (cursor.moveToFirst()){    	
        	do{
        		item = new HashMap<String,Object>();
        		appmodel app=new appmodel();
        		app.setId(cursor.getString(0));
        		app.setAppname(cursor.getString(1));
        		app.seturl(cursor.getString(2));
        		app.setpic(cursor.getString(3));
        		app.setdex(cursor.getString(4));
        		item.put("id",app.getId());
        		item.put("appname",app.getAppname());
        		item.put("url", app.geturl()); 
        		item.put("pic", app.getpic());
        		item.put("dex", app.getdex());
        		data1.add(item);
         		Log.e("Querying appname:",item.get("appname").toString());
         		Log.e("1",item.get("id").toString());
        		}
            while(cursor.moveToNext());
        }
        Log.e("data1",data1.toString());
        cursor.close();
		return data1;
		
	}
	
	

}
