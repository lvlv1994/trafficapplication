package trafficcomm.getdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

public class readapp extends Activity {
	  List<Map<String, Object>> data;
	    Map<String, Object> item;
	     ListView listView;
	     Context context;
	    public readapp(Context context) {
	    	this.context=context;
	        //super.onCreate(savedInstanceState);
	       // setContentView(R.layout.fragment_second);
	       // listView=(ListView)findViewById(R.id.listv);
	        listView = new ListView(context);
	        data = new ArrayList<Map<String, Object>>();
            
        //SimpleAdapter adapter = new SimpleAdapter(this, data,android.R.layout.simple_list_item_2, new String[] { "appname","pname" }, new int[] { android.R.id.text1,android.R.id.text2, });
//	        listView.setAdapter(adapter);
//	        Log.e("list1",listView.toString());
	        //setContentView(listView);
	    }
	    @Override   
	    public void onCreate(Bundle savedInstanceState)   
	    {   
	        super.onCreate(savedInstanceState);
	        AVOSCloud.initialize(context, "DF61lE7BS6GlhEpUso46MYGK-gzGzoHsz", "7vVIxywbHXk2eKeAcG5pOpeW");
	       // listPackages();
	        ArrayList<PInfo> apps = getInstalledApps(false); 
	        final int max = apps.size();
	        for (int i = 0; i < max; i++) {
	        	AVObject todo = new AVObject("Todo");
	            apps.get(i).prettyPrint();
	            item = new HashMap<String, Object>();
	            item.put("appname", apps.get(i).appname);
	            item.put("pname", apps.get(i).pname);
	            item.put("imei", apps.get(i).imei);
	            todo.put("appname", apps.get(i).appname);
	            todo.put("pname", apps.get(i).pname);
	            todo.put("imei", apps.get(i).imei);
	            data.add(item);	           

	            todo.saveInBackground(new SaveCallback() {
	                @Override
	                public void done(AVException e) {
	                    if (e == null) {
	                        // 存储成功
	                    	Log.e("success","success");
	                    } else {
	                    	Log.e("fall","fall");
	                    }
	                }
	            });
	        }
	    }
	    
	    class PInfo {
	        private String appname = "";
	        private String pname = "";
	        private String versionName = "";
	        private int versionCode = 0;
	        private String imei = "";
	        private void prettyPrint() {
	            Log.i("taskmanger", appname + "\t" + pname + "\t" + versionName
	                    + "\t" + versionCode + "\t");
	        }
	    }
	    public void listPackages() {
	        ArrayList<PInfo> apps = getInstalledApps(false); 
	        final int max = apps.size();
	        for (int i = 0; i < max; i++) {
	        	AVObject todo = new AVObject("Todo");
	            apps.get(i).prettyPrint();
	            item = new HashMap<String, Object>();
	            item.put("appname", apps.get(i).appname);
	            item.put("pname", apps.get(i).pname);
	            item.put("imei", apps.get(i).imei);
	            todo.put("appname", apps.get(i).appname);
	            todo.put("pname", apps.get(i).pname);
	            todo.put("imei", apps.get(i).imei);
	            data.add(item);	           

	            todo.saveInBackground(new SaveCallback() {
	                @Override
	                public void done(AVException e) {
	                    if (e == null) {
	                        // 存储成功
	                    	Log.e("success","success");
	                    } else {
	                    	Log.e("fall","fall");
	                    }
	                }
	            });
	        }
	    }

	    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
	       // ArrayList<PInfo> res = new ArrayList<PInfo>();
	       // List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
	    	ArrayList<PInfo> res = new ArrayList<PInfo>();  
	        PackageManager pManager = context.getPackageManager(); 
	        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
	        String tmnubmer = tm.getDeviceId();
	        //获取手机内所有应用  
	        List<PackageInfo> packs = pManager.getInstalledPackages(0);  
	        for (int i = 0; i < packs.size(); i++) {
	            PackageInfo p = packs.get(i);
	            if ((!getSysPackages) && (p.versionName == null)) {
	                continue;
	            }
	            PInfo newInfo = new PInfo();
	            newInfo.appname = p.applicationInfo.loadLabel(pManager)
	                    .toString();
	            //Log.e("appname",newInfo.appname);
	            newInfo.pname = p.packageName;
	            newInfo.versionName = p.versionName;
	            newInfo.versionCode = p.versionCode;
	            newInfo.imei = tmnubmer;
	            p.applicationInfo.loadIcon(pManager);
	            res.add(newInfo);
	        }
	        return res;
	    }
}
