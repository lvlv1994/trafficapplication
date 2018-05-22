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
					
						// ����Դ�洢������
						cache.put(url,
								new SoftReference<Drawable>(drawable));
						Message msg = mHandler.obtainMessage();
						msg.obj = drawable;
						mHandler.sendMessage(msg);
					}
				}).start();
			}
		} else {
			// ������û�ж�ӦURL����Դ����Ҫ����
			Log.e("no url","no url");
			imageView.setImageDrawable(context.getResources().getDrawable(
					R.drawable.pic_default));
			Log.e("no url1","no url1");
			new Thread(new Runnable() {

				@Override
				public void run() {
					Log.e("no url2","no url2");
					// TODO Auto-generated method stub
					// ����u_id����Drawable��Դ,�ȷ���sd�������������������
					Drawable drawable = getDrawableByUrl(url);
					Log.e("no url3","no url3");
					// ����Դ�洢��cache
					BitmapDrawable bd = (BitmapDrawable) drawable;
					PhotoSdUtil.savePhotoByUrl(url, bd.getBitmap());
					// ����Դ�洢������
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
		// �첽����ͼƬ��ɺ�����ͼƬ
		final Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// �����жϵ�ǰimageView�ؼ���־��URL�Ƿ��뵱ǰURL��Ӧ����Ӧ������
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					imageView.setImageDrawable((Drawable) msg.obj);
				}
			}
		};
		// ���������Ƿ��ж�ӦURL����Դ������ֱ������
		if (cache2.containsKey(url)) {
			Drawable drawable = cache2.get(url).get();
			// �ж���������Դ�Ƿ����ڴ治����ͷ�
			if (drawable != null) {
				// ���û���ͷ�ֱ������
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					imageView.setImageDrawable(drawable);
				}
			} else {
				// ������ͷţ������߳��첽����
				// ������������ΪĬ�ϵ�ͼƬ��������ͼƬ���صĹ����н���ʾ�����õ���Ŀ��ͼƬ�����ִ�λ��˸
				imageView.setImageDrawable(context.getResources().getDrawable(
						R.drawable.pic_default));
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// ����u_id����Drawable��Դ,�ȷ���sd�������������������
						Drawable drawable =getDrawableByUrl(url);
						// ����Դ�洢��Sd��
						BitmapDrawable bd = (BitmapDrawable) drawable;
						PhotoSdUtil.savePhotoByUrl(url, bd.getBitmap());
						// ����Դ�洢������
						cache2.put(url,
								new SoftReference<Drawable>(drawable));
						Message msg = mHandler.obtainMessage();
						msg.obj = drawable;
						mHandler.sendMessage(msg);
					}
				}).start();
			}
		} else {
			// ������û�ж�ӦURL����Դ����Ҫ����
			imageView.setImageDrawable(context.getResources().getDrawable(
					R.drawable.pic_default));
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// ����u_id����Drawable��Դ,�ȷ���sd�������������������
					Drawable drawable = getDrawableByUrl(url);
					// ����Դ�洢��Sd��
					BitmapDrawable bd = (BitmapDrawable) drawable;
					PhotoSdUtil.savePhotoByUrl(url, bd.getBitmap());
					// ����Դ�洢������
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

	//�������û�л���
	public static void AyncDrawableByUrlNoCache(Context context, final String url,
			final ImageView imageView) {
		imageView.setTag(url);
		// �첽����ͼƬ��ɺ�����ͼƬ
		final Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// �����жϵ�ǰimageView�ؼ���־��URL�Ƿ��뵱ǰURL��Ӧ����Ӧ������
				if (imageView.getTag() != null
						&& imageView.getTag().equals(url)) {
					imageView.setImageDrawable((Drawable) msg.obj);
				}
			}
		};
		// ������������ΪĬ�ϵ�ͼƬ��������ͼƬ���صĹ����н���ʾ�����õ���Ŀ��ͼƬ�����ִ�λ��˸
		imageView.setImageDrawable(context.getResources().getDrawable(
				R.drawable.pic_default));
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// ����u_id����Drawable��Դ,�ȷ���sd�������������������
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
			if (bytes[i] < 0) {// �����쳣����
				bytes[i] += 256;
			}
		}
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return bitmap;
	}

	public static byte[] compressBitmap(Bitmap bitmap, float size) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, baos);// ���ǩ����png�Ļ����򲻹�quality�Ƕ��٣����������������ѹ��
		int quality = 100;
		while (baos.toByteArray().length / 1024f > size) {
			quality = quality - 4;// ÿ�ζ�����4
			baos.reset();// ����baos�����baos
			if (quality <= 0) {
				break;
			}
			bitmap.compress(CompressFormat.JPEG, quality, baos);
		}
		return baos.toByteArray();
	}
}
