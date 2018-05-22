package com.example.trafficcomm;

import java.util.ArrayList;

import trafficcomm.adapter.CatalogAdapter;
import trafficcomm.adapter.CatalogAdapter.ItemHolder;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ThirdFragment extends Fragment{
	ListView listview;	
	ItemHolder itemHolder = null;
	CatalogItem item;
	ArrayList<CatalogItem> data;
	Context context;
	CatalogAdapter ca;
	@Override   
    public void onAttach(Activity activity)   
    {   
        super.onAttach(activity);   
    } 

     
    @Override   
    public void onCreate(Bundle savedInstanceState)   
    {   
        super.onCreate(savedInstanceState);  
        context = this.getActivity();
        data = new ArrayList<CatalogItem>();
        Resources res = this.getResources(); 
        item = new CatalogItem();
        item.setTag("wifi");
        item.setTitle("wifi");
        item.setImage(res.getDrawable(R.drawable.wifi));
        data.add(item);
        item = new CatalogItem();
        item.setTag("safegard");
        item.setTitle("��ȫ����");
        item.setImage(res.getDrawable(R.drawable.safegarde));
        data.add(item);
        item = new CatalogItem();
        item.setTag("navigation");
        item.setTitle("���е���");
        item.setImage(res.getDrawable(R.drawable.navigation));
        data.add(item);
        item = new CatalogItem();
        item.setTag("onlineshow");
        item.setTitle("����ֱ��");
        item.setImage(res.getDrawable(R.drawable.onlineplayer));
        data.add(item);
        item = new CatalogItem();
        item.setTag("onlinevedio");
        item.setTitle("��̨");
        item.setImage(res.getDrawable(R.drawable.listenvideo));
        data.add(item);
        item = new CatalogItem();
        item.setTag("shopping");
        item.setTitle("����");
        item.setImage(res.getDrawable(R.drawable.shopping));
        data.add(item);
        item = new CatalogItem();
        item.setTag("browser");
        item.setTitle("�����");
        item.setImage(res.getDrawable(R.drawable.browser));
        data.add(item);
        item = new CatalogItem();
        item.setTag("cars");
        item.setTitle("����");
        item.setImage(res.getDrawable(R.drawable.cars));
        data.add(item);
        item = new CatalogItem();
        item.setTag("socialcomm");
        item.setTitle("�罻����");
        item.setImage(res.getDrawable(R.drawable.socialcommunicate));
        data.add(item);
        item = new CatalogItem();
        item.setTag("videoplay");
        item.setTitle("��Ƶ����");
        item.setImage(res.getDrawable(R.drawable.vedioplay));
        data.add(item);
        item = new CatalogItem();
        item.setTag("search");
        item.setTitle("����");
        item.setImage(res.getDrawable(R.drawable.search));
        data.add(item);
        item = new CatalogItem();
        item.setTag("weather");
        item.setTitle("��������");
        item.setImage(res.getDrawable(R.drawable.weather));
        data.add(item);
        item = new CatalogItem();
        item.setTag("picture");
        item.setTitle("ͼƬ���");
        item.setImage(res.getDrawable(R.drawable.camera));
        data.add(item);
        item = new CatalogItem();
        item.setTag("newspaper");
        item.setTitle("���ű�ֽ");
        item.setImage(res.getDrawable(R.drawable.newspaper));
        data.add(item);
        item = new CatalogItem();
        item.setTag("musicplay");
        item.setTitle("���ֲ���");
        item.setImage(res.getDrawable(R.drawable.musicplayer));
        data.add(item);
        item = new CatalogItem();
        item.setTag("bank");
        item.setTitle("����");
        item.setImage(res.getDrawable(R.drawable.bank));
        data.add(item);
        item = new CatalogItem();
        item.setTag("email");
        item.setTitle("����");
        item.setImage(res.getDrawable(R.drawable.email));
        data.add(item);
        item = new CatalogItem();
        item.setTag("game");
        item.setTitle("��Ϸ");
        item.setImage(res.getDrawable(R.drawable.game));
        data.add(item);
        item = new CatalogItem();
        item.setTag("comic");
        item.setTitle("������Ĭ");
        item.setImage(res.getDrawable(R.drawable.comic));
        data.add(item);
        item = new CatalogItem();
        item.setTag("calling");
        item.setTitle("����ͨ��");
        item.setImage(res.getDrawable(R.drawable.calling));
        data.add(item);
        item = new CatalogItem();
        item.setTag("tools");
        item.setTitle("���ֹ���");
        item.setImage(res.getDrawable(R.drawable.helper));
        data.add(item);
        item = new CatalogItem();
        item.setTag("locktable");
        item.setTitle("��������");
        item.setImage(res.getDrawable(R.drawable.locktable));
        data.add(item);
        ca = new CatalogAdapter(context,data,3);        
    }   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_second, container,false);
		listview=(ListView)rootView.findViewById(R.id.listv);	
		
		listview.setAdapter(ca);
		
	        
		
		return rootView;
	}
}
