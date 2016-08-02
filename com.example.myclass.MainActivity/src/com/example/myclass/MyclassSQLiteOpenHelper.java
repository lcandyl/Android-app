package com.example.myclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyclassSQLiteOpenHelper extends SQLiteOpenHelper{
	public final static int VERSION = 1;// 版本号
	 public final static String TABLE_NAME = "classes";// 表名
	 public final static String Day = "day";// 后面ContentProvider使用
	// public final static String Startterm = "startterm"; 
	 public final static String Id = "id";
	 public final static String Name = "name";
	 public final static String Address="address";
	 public static final String DATABASE_NAME = "schedule.db";
	 public  static SQLiteDatabase db;
	 private static final  String 	 str_sql = "CREATE TABLE " + TABLE_NAME 
			 + "("  + Id +" integer primary key autoincrement,"
			 + Day + " text,"
			 + Name +" text," 
			 + Address +  " text );";
	 
	 public MyclassSQLiteOpenHelper(Context context) {
	  super(context, DATABASE_NAME, null, VERSION); 
	 } 
	 
	 @Override
	 public void onCreate(SQLiteDatabase db) { 
			 db.execSQL(str_sql);	
			  System.out.println("建课程表成功");		
			db.execSQL("INSERT INTO classes VALUES (0,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (1,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (2,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (3,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (4,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (5,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (6,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (7,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (8,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (9,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (10,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (11,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (12,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (13,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (14,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (15,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (16,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (17,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (18,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (19,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (20,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (21,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (22,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (23,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (24,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (25,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (26,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (27,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (28,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (29,6,'','');");
		       //insert();
		       System.out.println("插入数据成功");		
		 }
	 
	 public void updateCourse(int id, String text) 
	  { 
		// System.out.println("走到更新课程里面");	
		SQLiteDatabase db = this.getWritableDatabase(); 
	    String where = Id + " = ?";
	    String[] whereValue = { Integer.toString(id) }; 
	    /* 将修改的值放入ContentValues */ 
	    ContentValues cv = new ContentValues(); 
	    cv.put(Name, text); 
	    db.update(TABLE_NAME, cv, where, whereValue); 
	    }
	 
	 public void updateaddress(int id, String text) 
	  { 
		  //System.out.println("走到更新上课地点里面");	
	    SQLiteDatabase db = this.getWritableDatabase(); 
	    String where = Id + " = ?";
	    String[] whereValue = { Integer.toString(id) }; 
	  //  System.out.println("whereValue"+whereValue);
	  //  System.out.println("Idwhere"+where);
	    /* 将修改的值放入ContentValues */ 
	    ContentValues cv = new ContentValues(); 
	    cv.put(Address, text); 
	    db.update(TABLE_NAME, cv, where, whereValue); 
	    }
	 public void clearclasses(){
		  String delete = "DELETE FROM " + TABLE_NAME +";";
		  System.out.println (delete);
		   SQLiteDatabase db = this.getWritableDatabase();
		  db.execSQL(delete);
		  db.execSQL("INSERT INTO classes VALUES (0,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (1,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (2,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (3,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (4,1,'','');");
			db.execSQL("INSERT INTO classes VALUES (5,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (6,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (7,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (8,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (9,2,'','');");
			db.execSQL("INSERT INTO classes VALUES (10,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (11,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (12,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (13,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (14,3,'','');");
			db.execSQL("INSERT INTO classes VALUES (15,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (16,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (17,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (18,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (19,4,'','');");
			db.execSQL("INSERT INTO classes VALUES (20,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (21,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (22,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (23,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (24,5,'','');");
			db.execSQL("INSERT INTO classes VALUES (25,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (26,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (27,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (28,6,'','');");
			db.execSQL("INSERT INTO classes VALUES (29,6,'','');");
		       //insert();
		       System.out.println("插入数据成功");		
		 }
	  
	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		 onCreate(db);
	     Log.v("schedule", "onUpgrade");
	 } 
	}