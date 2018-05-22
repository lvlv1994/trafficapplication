package com.e_shop.lin.e_shop.com.e_shop.lin.e_shop.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.e_shop.lin.e_shop.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoSdUtil {

	public static Drawable getPhotoFromSdByUrl(String url) {

		if(!DownloadUtil.ExistSDCard()) return  null;
		String filename = urlToKey(url)+".png";
		File cacheDir = new File(Config.pohto_path);
		File[] cacheFiles = cacheDir.listFiles();
		if(null != cacheFiles){
			for(int i = 0 ;i <cacheFiles.length ; i++){

				if(filename.equals(cacheFiles[i].getName())){
					return Drawable.createFromPath(Config.pohto_path+filename);
				}
			}
		}
		return null;
	}

	public static void savePhotoByUrl(String url, Bitmap bitmap) {
		if(!DownloadUtil.ExistSDCard()) return  ;
		try {
			File file = new File(Config.pohto_path);
			if (!file.exists())
				file.mkdirs();
			File pfile = new File(Config.pohto_path + urlToKey(url)+".png");
			if (!pfile.exists()) {
				pfile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(pfile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String urlToKey(String url){
		return url.hashCode()+"";
	}
}
