package com.example.trafficcomm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

public class DrawableUtil {

	public  static HashMap<String, SoftReference<Drawable>> cache = new HashMap<String, SoftReference<Drawable>>();
	public  static HashMap<String, SoftReference<Drawable>> cache2 = new HashMap<String, SoftReference<Drawable>>();
	// set uid and image view
	public static void AyncSetDrawableByUrl(Context context, final String url,
			final ImageView imageView) {
		imageView.setTag(url);
		// Asynchronous download pictures
		final Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				//judge weather imageview url is right
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					imageView.setImageDrawable((Drawable) msg.obj);
				}
			}
		};
		
		//weather url source is stored in cache
		if (cache.containsKey(url)) {
			Drawable drawable = cache.get(url).get();
			// check weather is released
			if (drawable != null) {
				// if not, set directly
				Log.e("before problem","before problem");
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					Log.e("here is pic1","here is pic1");
					imageView.setImageDrawable(drawable);
				}
			} else {
				//if released, download
				imageView.setImageDrawable(context.getResources().getDrawable(
						R.drawable.pic_default));
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// find weather resource is stored in sd card
						Log.e("here is pic","here is pic");
						Drawable drawable = getDrawableByUrl(url);
						Log.e("here is pic2","here is pic2");
						BitmapDrawable bd = (BitmapDrawable) drawable;
						Log.e("here is pic3","here is pic3");
						PhotoSdUtil.savePhotoByUrl(url, bd.getBitmap());
					
						// 将资源存储到缓存
						cache.put(url,
								new SoftReference<Drawable>(drawable));
						Message msg = mHandler.obtainMessage();
						msg.obj = drawable;
						mHandler.sendMessage(msg);
					}
				}).start();
			}
		} else {
			// 缓存中没有对应URL的资源，需要下载
			Log.e("no url","no url");
			imageView.setImageDrawable(context.getResources().getDrawable(
					R.drawable.pic_default));
			Log.e("no url1","no url1");
			new Thread(new Runnable() {

				@Override
				public void run() {
					Log.e("no url2","no url2");
					// TODO Auto-generated method stub
					// 根据u_id返回Drawable资源,先访问sd卡，不存在则访问网络
					Drawable drawable = getDrawableByUrl(url);
					Log.e("no url3","no url3");
					// 将资源存储到cache
					BitmapDrawable bd = (BitmapDrawable) drawable;
					PhotoSdUtil.savePhotoByUrl(url, bd.getBitmap());
					// 将资源存储到缓存
					cache.put(url,
							new SoftReference<Drawable>(drawable));
					Message msg = mHandler.obtainMessage();
					msg.obj = drawable;
					mHandler.sendMessage(msg);
					Log.e("url",url);
				}
			}).start();
		}
	}

	public static void AyncSetDrawableByUrl2(Context context, final String url,
											final ImageView imageView) {
		imageView.setTag(url);
		// 异步加载图片完成后设置图片
		final Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// 必须判断当前imageView控件标志的URL是否与当前URL对应，对应才设置
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					imageView.setImageDrawable((Drawable) msg.obj);
				}
			}
		};
		// 看缓存中是否有对应URL的资源，有则直接设置
		if (cache2.containsKey(url)) {
			Drawable drawable = cache2.get(url).get();
			// 判断软引用资源是否因内存不足而释放
			if (drawable != null) {
				// 如果没有释放直接设置
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					imageView.setImageDrawable(drawable);
				}
			} else {
				// 如果被释放，开启线程异步下载
				// 必须重新设置为默认的图片，否则在图片加载的过程中将显示被重用的项目的图片，出现错位闪烁
				imageView.setImageDrawable(context.getResources().getDrawable(
						R.drawable.pic_default));
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 根据u_id返回Drawable资源,先访问sd卡，不存在则访问网络
						Drawable drawable =getDrawableByUrl(url);
						// 将资源存储到Sd卡
						BitmapDrawable bd = (BitmapDrawable) drawable;
						PhotoSdUtil.savePhotoByUrl(url, bd.getBitmap());
						// 将资源存储到缓存
						cache2.put(url,
								new SoftReference<Drawable>(drawable));
						Message msg = mHandler.obtainMessage();
						msg.obj = drawable;
						mHandler.sendMessage(msg);
					}
				}).start();
			}
		} else {
			// 缓存中没有对应URL的资源，需要下载
			imageView.setImageDrawable(context.getResources().getDrawable(
					R.drawable.pic_default));
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// 根据u_id返回Drawable资源,先访问sd卡，不存在则访问网络
					Drawable drawable = getDrawableByUrl(url);
					// 将资源存储到Sd卡
					BitmapDrawable bd = (BitmapDrawable) drawable;
					PhotoSdUtil.savePhotoByUrl(url, bd.getBitmap());
					// 将资源存储到缓存
					cache2.put(url,
							new SoftReference<Drawable>(drawable));
					Message msg = mHandler.obtainMessage();
					msg.obj = drawable;
					mHandler.sendMessage(msg);
					Log.e("url",url);
				}
			}).start();
		}
	}

	//这个方法没有缓存
	public static void AyncDrawableByUrlNoCache(Context context, final String url,
			final ImageView imageView) {
		imageView.setTag(url);
		// 异步加载图片完成后设置图片
		final Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// 必须判断当前imageView控件标志的URL是否与当前URL对应，对应才设置
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					imageView.setImageDrawable((Drawable) msg.obj);
				}
			}
		};
		// 必须重新设置为默认的图片，否则在图片加载的过程中将显示被重用的项目的图片，出现错位闪烁
		imageView.setImageDrawable(context.getResources().getDrawable(
				R.drawable.pic_default));
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 根据u_id返回Drawable资源,先访问sd卡，不存在则访问网络
				Drawable drawable = getDrawableByUrl(url);
				Message msg = mHandler.obtainMessage();
				msg.obj = drawable;
				mHandler.sendMessage(msg);
				Log.e("url",url);
			}
		}).start();
	}

	// download url by drawable
	public static Drawable getDrawableByUrl(String url) {
		URLConnection urls;
		try {
			urls = new URL(url).openConnection();
			return Drawable.createFromStream(urls.getInputStream(), "image");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getByteArrayFromDrawable(Drawable drawable) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BitmapDrawable bd = (BitmapDrawable) drawable;
		bd.getBitmap().compress(CompressFormat.PNG, 100, os);
		return os.toByteArray();
	}

	public static Drawable getDrawableFromByte(byte[] byteArrays) {
		ByteArrayInputStream is = new ByteArrayInputStream(byteArrays);
		return Drawable.createFromStream(is, "image");
	}

	public static String EncodeBitmap2String(Bitmap bitmap) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = out.toByteArray();
		byte[] encode = Base64.encode(buffer, Base64.DEFAULT);

		return new String(encode);

	}

	public static Bitmap DecodeString2bitmap(String messString) {
		Bitmap bitmap = null;
		byte[] bytes = Base64.decode(messString, Base64.DEFAULT);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return bitmap;
	}

	public static byte[] compressBitmap(Bitmap bitmap, float size) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, baos);// 如果签名是png的话，则不管quality是多少，都不会进行质量的压缩
		int quality = 100;
		while (baos.toByteArray().length / 1024f > size) {
			quality = quality - 4;// 每次都减少4
			baos.reset();// 重置baos即清空baos
			if (quality <= 0) {
				break;
			}
			bitmap.compress(CompressFormat.JPEG, quality, baos);
		}
		return baos.toByteArray();
	}
}
