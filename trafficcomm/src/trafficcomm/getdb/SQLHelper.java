package trafficcomm.getdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class SQLHelper {
	public static String appdown_TABLE;
	public static String DB_NAME;
	public SQLHelper(int number){
		if(number==1){
			appdown_TABLE = "appdownload";
			DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/appstore7.db";
			
		}
		if(number==2){
			appdown_TABLE = "appdownload";
			DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/appstore6.db";
		}
		if(number==3){
			appdown_TABLE = "appdownload";
			DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/appstore4.db";
		}
		if(number==4){
			appdown_TABLE = "appdownload";
			DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/appstore2.db";
			Log.e("here","here4");
		}
	}
	
	
	


	public void Insert(Context context,String table, ContentValues values){
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(DB_NAME, null); 
		try{
			db.insert(table, null, values);
			Log.i("wordroid=", "Insert");
			}
			catch(Exception e){
				e.getStackTrace();
			}
		
		}
	
	public void CreateTable(Context context,String table){
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(DB_NAME, null); 
		String sql="CREATE TABLE " + table + " ( ID text not null, appname text not null , url text not null, pic text not null, dex text not null" + ");";
		try{
			db.execSQL(sql);
			Log.i("wordroid=", sql);
			}
			catch(Exception e){
				e.getStackTrace();
			}
		db.close();
	}
	
	public void Update(Context context,String table, ContentValues values, String whereClause, String[] whereArgs){
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(DB_NAME, null); 
		try{
			db.update(table, values, whereClause, whereArgs); 
			Log.i("wordroid=", "Update");
			}
			catch(Exception e){
				e.getStackTrace();
			}
		db.close();
	}
	
	public Cursor Query(Context context,String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(DB_NAME, null); 
		Cursor cursor = null ;
		try{
			cursor=db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
			
			}
			catch(Exception e){
				e.getStackTrace();
			}
		//db.close();
		return cursor;
		
	}
	
	public void Delete(Context context,String table, String whereClause, String[] whereArgs){
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(DB_NAME, null); 
		try{
			db.delete(table, whereClause, whereArgs);
			Log.i("wordroid=", "delete");
			}
			catch(Exception e){
				e.getStackTrace();
			}
		db.close();
	}
	
	public void DeleteTable(Context context,String table){

		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(DB_NAME, null); 
		String sql="drop table " + table;
		try{
		db.execSQL(sql);
		Log.i("wordroid=", sql);
		}
		catch(Exception e){
			e.getStackTrace();
		}
		db.close();
	}

}
