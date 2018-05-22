package com.example.trafficcomm;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;


public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private ActionBar actionBar;
	Context context = this;
	List<Map<String, Object>> data;
	Map<String, Object> item;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = new ListView(context);
	    data = new ArrayList<Map<String, Object>>();
	    AVOSCloud.initialize(context, "DF61lE7BS6GlhEpUso46MYGK-gzGzoHsz", "7vVIxywbHXk2eKeAcG5pOpeW");
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		listPackages();
		Fragment fragment1 = new ThirdFragment();
		Fragment fragment2 = new FirstFragment();
		Fragment fragment3 = new SecondFragment();
		Fragment fragment4 = new FourthFragment();
		
		Fragment[] fragmentArray = new Fragment[] { fragment1, fragment2,fragment3,fragment4 };
		LFFragmentPagerAdapter adapter = new LFFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentArray);
		AVAnalytics.enableCrashReport(this, true);
        new Thread(new Runnable() {
       	 
            @Override
            public void run() {
		
		 if (!(new File(Environment.getExternalStorageDirectory().getPath() + "/appstore7.db")).exists()) { 
 	         
	            FileOutputStream fos;
				try {
					fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/appstore7.db");
					Log.e("databaseoper","databaseopen");
				
	            byte[] buffer = new byte[8192]; 
	            int count = 0; 
	            InputStream is = getResources().openRawResource( 
	                    R.raw.appstore7); 
	            while ((count = is.read(buffer)) > 0) { 
	                fos.write(buffer, 0, count); 
	            }

	            fos.close(); 
	            is.close(); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        }
		 if (!(new File(Environment.getExternalStorageDirectory().getPath() + "/appstore6.db")).exists()) { 
 	         
	            FileOutputStream fos;
				try {
					fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/appstore6.db");
					Log.e("databaseoper","databaseopen");
				
	            byte[] buffer = new byte[8192]; 
	            int count = 0; 
	            InputStream is = getResources().openRawResource( 
	                    R.raw.appstore6); 
	            while ((count = is.read(buffer)) > 0) { 
	                fos.write(buffer, 0, count); 
	            }

	            fos.close(); 
	            is.close(); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        }
		 if (!(new File(Environment.getExternalStorageDirectory().getPath() + "/appstore4.db")).exists()) { 
 	         
	            FileOutputStream fos;
				try {
					fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/appstore4.db");
					Log.e("databaseoper","databaseopen");
				
	            byte[] buffer = new byte[8192]; 
	            int count = 0; 
	            InputStream is = getResources().openRawResource( 
	                    R.raw.appstore4); 
	            while ((count = is.read(buffer)) > 0) { 
	                fos.write(buffer, 0, count); 
	            }

	            fos.close(); 
	            is.close(); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        }
		 if (!(new File(Environment.getExternalStorageDirectory().getPath() + "/appstore2.db")).exists()) { 
 	         
	            FileOutputStream fos;
				try {
					fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/appstore2.db");
					Log.e("databaseoper","databaseopen");
				
	            byte[] buffer = new byte[8192]; 
	            int count = 0; 
	            InputStream is = getResources().openRawResource( 
	                    R.raw.appstore2); 
	            while ((count = is.read(buffer)) > 0) { 
	                fos.write(buffer, 0, count); 
	            }

	            fos.close(); 
	            is.close(); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        }
		      
		      
		      
            }
        }).start();
		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(3);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				System.out.println("arg0:" + arg0);
				actionBar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}
			

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		actionBar = getActionBar();
		// 设置ActionBar 的导航方式: Tab导航
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// actionBar.get

		Tab tab1 = actionBar.newTab().setText("traffic based")
				.setIcon(android.R.drawable.ic_menu_agenda)
				.setTabListener(new ActionTabListener(fragment1));

		Tab tab2 = actionBar.newTab().setText("popularity based")
				.setIcon(android.R.drawable.ic_menu_agenda)
				.setTabListener(new ActionTabListener(fragment2));

		Tab tab3 = actionBar.newTab().setText("hybird")
				.setIcon(android.R.drawable.ic_menu_agenda)
				.setTabListener(new ActionTabListener(fragment3));
		Tab tab4 = actionBar.newTab().setText("appstore")
				.setIcon(android.R.drawable.ic_menu_agenda)
				.setTabListener(new ActionTabListener(fragment4));

		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		actionBar.addTab(tab4);
		

	}

	class ActionTabListener implements ActionBar.TabListener {

		// 声明Fragment

		private Fragment fragment = new Fragment();

		// 通过构造引用对应的Fragment

		public ActionTabListener(Fragment fragment) {
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			// ft.add(android.R.id.content, fragment, null);
			mType = tab.getPosition();
			System.out.println("tab.getPosition():" + tab.getPosition());
			viewPager.setCurrentItem(tab.getPosition());
			invalidateOptionsMenu();
		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.style1, menu);
		return true;
	}

	private int mType;

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		System.out.println("当前mType:" + mType);
		menu.clear();
		MenuInflater inflater = this.getMenuInflater();
		switch (mType) {
		case 0:
			inflater.inflate(R.menu.style1, menu);
			break;

		case 1:
			inflater.inflate(R.menu.style1, menu);
			break;

		case 2:
			inflater.inflate(R.menu.style1, menu);
			break;
		}
		return super.onPrepareOptionsMenu(menu);
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
