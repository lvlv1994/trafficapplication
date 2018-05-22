package trafficcomm.adapter;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.trafficcomm.DrawableUtil;
import com.example.trafficcomm.R;
import com.example.trafficcomm.R.id;
import com.example.trafficcomm.R.layout;

import trafficcomm.getdb.AccessData;
import trafficcomm.getdb.SQLHelper;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/3/19.
 */
public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Map<String, Object>> dealList;
    SQLHelper sqlhelper;
    File file;
    HashMap<String, Object> map;
    Map<String, Object> deal;
    ItemHolder itemHolder = null;  

    public ListAdapter(Context context,ArrayList<Map<String, Object>> dealList ) {
        super();
        mContext = context;
        this.dealList = dealList;
        //sqlhelper = new SQLHelper();
    }

    @Override
    public int getCount() {
        if(dealList!=null && dealList.size()>0){
        	//Log.e("getcount",String.valueOf(dealList.size()));
            return dealList.size();}
        //Log.e("0","0");
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
                    R.layout.list_item, null);
            itemHolder = new ItemHolder();
            itemHolder.logol = (ImageView) convertView.findViewById(R.id.app_logol);
            itemHolder.name = (TextView) convertView.findViewById(R.id.app_name);
            itemHolder.recommend = (TextView) convertView.findViewById(R.id.app_recommend);
            itemHolder.recommend2 = (TextView) convertView.findViewById(R.id.app_recommend2);
            itemHolder.num = (TextView) convertView.findViewById(R.id.num);
            itemHolder.install = (Button) convertView.findViewById(R.id.install);        
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ItemHolder) convertView.getTag();
        }
        
         deal = dealList.get(position);
         if(position<3){        	
            itemHolder.num.setTextColor(Color.RED);
        }else{
 
            itemHolder.num.setTextColor(Color.WHITE);
        }
       
        itemHolder.num.setText(position+1+"");
        if(deal.get("dex").toString()!="NULL"){
        if(deal.get("dex").toString().length()>20){
        	
            itemHolder.recommend.setText(deal.get("dex").toString().substring(44,60));
           
        }else{
       
            itemHolder.recommend.setText(deal.get("dex").toString());
        }
        
      }
        if(deal.get("pic").toString()!="NULL"){
        DrawableUtil.AyncSetDrawableByUrl(mContext,deal.get("pic").toString(),itemHolder.logol);      
        itemHolder.install.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          	downFile(dealList.get(position).get("url").toString(),dealList.get(position).get("appname").toString());

          }
      });}
        itemHolder.name.setText(deal.get("appname").toString());
        Log.e("arrive here9","arriver here9");

        return convertView;
    }
    private File downFile(final String httpUrl, final String name) {
    	
    	final ProgressDialog mProgressDialog = new ProgressDialog(mContext);
    	mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	mProgressDialog.setMessage("DownLoading......");
    	mProgressDialog.show();
 
        new Thread(new Runnable() {
 
            @Override
            public void run() {
            	          	
            	try {
                    URL url = new URL(httpUrl);                   
                    String save_path =Environment.getExternalStorageDirectory().getAbsolutePath() +"/lv/AppApk/";                   
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){  
                  
                    }else{
                    	Log.e("lcy","456");
                       
                    }
                    //open connection
                    HttpURLConnection  Fconnection = (HttpURLConnection)url.openConnection();
                    HttpURLConnection Dconnection = (HttpURLConnection)url.openConnection();
                    if (Fconnection.getReadTimeout() == 5) {
                        return;
                    }
                    long FileLength=Fconnection.getContentLength();
                    //build file storage path
                    File file1 = new File(save_path);
                    if (!file1.exists()) {
                        file1.mkdirs();                     
                    }
                   
                    String savePathString = save_path+name+".apk";
                    file = new File(savePathString);
                    Dconnection.setRequestProperty("User-Agent","NetFox");
                    Dconnection.setRequestProperty("RANGE", "bytes="+ 0 + "-");
                    Dconnection.connect();                    
                    //get download stream
                    InputStream inputStream = Dconnection.getInputStream();
                    //build random access file
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    //set file pointer
                    raf.seek(0);
                    //set buffer
                    byte [] buffer=new byte[256*4];                
                    long DownedFileLength =0;
                    //start download loop
                    while (DownedFileLength<FileLength) {
                        int num = inputStream.read(buffer);
                        if(num>0){
                            DownedFileLength+=num;
                            raf.write(buffer,0,num);
                        } else{break;}
                       
                    }
                    
                    File apk = new File(savePathString);
                    raf.close();
                    if(!apk.exists()){
                        Toast.makeText(mContext,"安装包不存在！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try{
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
                    mContext.startActivity(intent);
                    
                    }catch (Exception e){
                        Toast.makeText(mContext,"install package analyse error！",Toast.LENGTH_SHORT).show();
                    }
                    mProgressDialog.hide();
                    Log.e("lyj", "7");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally {
                   
                }
            }
        }).start();
        return file;
    }
    public class ItemHolder {
        public TextView recommend;
        public TextView recommend2;
        public TextView name;
        public ImageView logol;
        public TextView num;
        public Button install;

    }
}
