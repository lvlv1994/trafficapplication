package trafficcomm.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.trafficcomm.CatalogFragment;
import com.example.trafficcomm.CatalogItem;
import com.example.trafficcomm.R;
import com.example.trafficcomm.R.id;
import com.example.trafficcomm.R.layout;

import trafficcomm.getdb.SQLHelper;
import trafficcomm.model.PageNumber;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/19.
 */
public class CatalogAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<CatalogItem> dealList = new ArrayList<CatalogItem>();
    SQLHelper sqlhelper;
    File file;
    HashMap<String, Object> map;
    Map<String, Object> deal;
    ItemHolder itemHolder;
    CatalogItem item;
    int number;

    public CatalogAdapter(Context context, ArrayList<CatalogItem> dealList,int number) {
        super();
        mContext = context;
        this.dealList =  dealList;
        this.number=number;
    }

    @Override
    public int getCount() {
        if(dealList!=null && dealList.size()>0){
            return dealList.size();}
     
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    	
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.catalog_second, null);
            itemHolder = new ItemHolder();
            itemHolder.logol = (ImageView) convertView.findViewById(R.id.app_logol);
            itemHolder.name = (TextView) convertView.findViewById(R.id.catalog_name);    
            convertView.setTag(itemHolder);
           // Log.e("arrive here1","arriver here1");
        }else{
            itemHolder = (ItemHolder) convertView.getTag();
           // Log.e("arrive here2","arriver here2");
        }
        Drawable img = dealList.get(position).getImage();
        //Log.e("deal",deal.get("name").toString());
        itemHolder.name.setText(dealList.get(position).getTitle());
        //Log.e("arrive here9","arriver here9");     
        itemHolder.logol.setImageDrawable(img);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i =  new Intent(mContext,CatalogFragment.class);
            	i.putExtra("name", dealList.get(position).getTag());
            	Log.e("number",number+"");
            	i.putExtra("number", number);
                mContext.startActivity(i);

            }
        });
 

        return convertView;
    }
 
 

    public class ItemHolder {
        public TextView name;
        public ImageView logol;
        public Button install;

    }

}
