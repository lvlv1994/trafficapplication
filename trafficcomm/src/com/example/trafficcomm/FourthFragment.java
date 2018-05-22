package com.example.trafficcomm;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import trafficcomm.adapter.ListAdapter;
import trafficcomm.getdb.AccessData;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class FourthFragment extends Fragment{
	    File file;
	    Context context;
	    ListView listview;	
	    LocalBroadcastManager broadcastManager;
	    ArrayList<Map<String, Object>> list;
	    String number;
	    HashMap<String, Object> map;
	    //SQLHelper sqlhelper = new SQLHelper();
	    ListAdapter adapter;

    public void onAttach(Activity activity)   
    {   
        super.onAttach(activity);   
    }   
     
    @Override   
    public void onCreate(Bundle savedInstanceState)   
    {   
        super.onCreate(savedInstanceState);
        Log.e("1","1");
     	context=this.getActivity();
     	broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        AccessData data= new AccessData(context,1);
        list = new ArrayList<Map<String, Object>>();
        for(int i=1;i<=300;i++){      	       
        String selection = "id=?" ;  
        String[] selectionArgs = new  String[]{String.valueOf(i)};  
        list.add(data.QueryAPP(selection, selectionArgs));
        //Log.e("dataQuery",data.QueryAPP(selection, selectionArgs).toString());
        }
         adapter = new ListAdapter(this.getActivity(),list);
    }   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		    Log.e("piccc","picc");		
		    View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);
		    listview=(ListView)rootView.findViewById(R.id.listv);	
		    //Log.e("piccc2","picc2");
	        listview.setAdapter(adapter); 
		     
		return rootView;
	}
	 	 
}
