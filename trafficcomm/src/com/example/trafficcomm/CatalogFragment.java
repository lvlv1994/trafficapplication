package com.example.trafficcomm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import trafficcomm.adapter.ListAdapter;
import trafficcomm.getdb.AccessData;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CatalogFragment extends Activity{

	    File file;
	    Context context;
	    ListView listview;	
	    LocalBroadcastManager broadcastManager;
	    ArrayList<Map<String, Object>> list;
	    String number;
	    HashMap<String, Object> map;
	   // SQLHelper sqlhelper = new SQLHelper();
	    ListAdapter adapter;
	
 
 
    @Override   
    public void onCreate(Bundle savedInstanceState)   
    {   
        super.onCreate(savedInstanceState);
        
     	context=this;
     	Intent intent=getIntent();
     	String value1 = intent.getStringExtra("name");
     	int value2 = intent.getExtras().getInt("number");
        AccessData data= new AccessData(context,value2);
       // Log.e("value",value2);
        list = new ArrayList<Map<String, Object>>();
         
        String selection = "catalog=?" ;  
        String[] selectionArgs = new  String[]{value1};  
        list=data.QueryAPP2(selection, selectionArgs);
       // Log.e("list",list.toString());        
        adapter = new ListAdapter(context,list);
	    //Log.e("piccc","picc");
	    setContentView(R.layout.fragment_fourth);
	    listview=(ListView)findViewById(R.id.listv);
	    
	    
	    if(value1.equals("wifi")||value1.equals("cars")||value1.equals("email")){
        listview.setAdapter(adapter);
        Log.e("piccc2","picc2");}
	   
	    else{
	    	SimpleAdapter adapter1 = new SimpleAdapter(context, list,R.layout.listitem, new String[] { "appname"}, new int[] { R.id.listitem_title});
	    	listview.setAdapter(adapter1);
	    }
   
        
    }   
}
