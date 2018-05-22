package com.example.trafficcomm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class PhotoSdUtil {

	public static Drawable getPhotoFromSdByUrl(String url) {

		String filename = urlToKey(url)+".png";
		File cacheDir = new File("/lv/AppApk/");
		File[] cacheFiles = cacheDir.listFiles();
		if(null != cacheFiles){
			for(int i = 0 ;i <cacheFiles.length ; i++){
				if(filename.equals(cacheFiles[i].getName())){
					return Drawable.createFromPath("/lv/AppApk/"+filename);
				}
			}
		}
		return null;
	}

	public static void savePhotoByUrl(String url, Bitmap bitmap) {
		try {
			File file = new File("/lv/AppApk/");
			if (!file.exists())
				file.mkdirs();
			File pfile = new File("/lv/AppApk/" + urlToKey(url)+".png");
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
