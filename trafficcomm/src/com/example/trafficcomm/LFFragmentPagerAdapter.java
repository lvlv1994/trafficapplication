package com.example.trafficcomm;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LFFragmentPagerAdapter extends FragmentPagerAdapter {
	Fragment[] fragmentArray;
	
	public LFFragmentPagerAdapter(FragmentManager fm,Fragment[] fragmentArray2) {
		super(fm);
		// TODO Auto-generated constructor stub
		if (null == fragmentArray2) {
			this.fragmentArray = new Fragment[] {};
		} else {
			this.fragmentArray = fragmentArray2;
		}
	}

	
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragmentArray[arg0];
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentArray.length;
	}

}
