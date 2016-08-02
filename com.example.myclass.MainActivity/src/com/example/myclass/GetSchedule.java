package com.example.myclass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * *给viewPager上不同数据,周一到周五
 * @author Jenny
 *
 */
public class GetSchedule {
	private static String[] days1 = {"星期一", "星期二", "星期三",
        "星期四", "星期五", "星期六"};
	private TextView tv0,tv1_2,tv1_3,tv2_2,tv2_3,tv3_2,tv3_3,tv4_2,tv4_3,tv5_2,tv5_3;
	private String []name=new String[5];
	private String []address=new String[5];
	private MyclassSQLiteOpenHelper toDoDB;
	private Cursor mCursor ;
	private Context context ;
	
	public GetSchedule(Context context) {
		this.context=context;
	}
    public View getScheduleView(int week) {
		LayoutInflater mInflater = LayoutInflater.from(context);
		View myView = mInflater.inflate(R.layout.app_schedule_show_page, null);
	    tv0=(TextView) myView.findViewById(R.id.show_tv0);
	    tv1_2=(TextView) myView.findViewById(R.id.show_tv1_2);
	    tv1_3=(TextView)  myView.findViewById(R.id.show_tv1_3);    
	    tv2_2=(TextView)  myView.findViewById(R.id.show_tv2_2);
	    tv2_3=(TextView)  myView.findViewById(R.id.show_tv2_3);	    
	    tv3_2=(TextView)  myView.findViewById(R.id.show_tv3_2);
	    tv3_3=(TextView)  myView.findViewById(R.id.show_tv3_3);	    
	    tv4_2=(TextView)  myView.findViewById(R.id.show_tv4_2);
	    tv4_3=(TextView)  myView.findViewById(R.id.show_tv4_3);   
	    tv5_2=(TextView)  myView.findViewById(R.id.show_tv5_2);
	    tv5_3=(TextView)  myView.findViewById(R.id.show_tv5_3);
	    TextView tv=(TextView)myView.findViewById(R.id.day_tv);
	    //取当前时间
	    PreferenceSetting preferenceSetting = new PreferenceSetting();
	   String value = preferenceSetting.getWeekandDate(); 
	   tv.setText( value);
	     SQLiteDatabase db;
	     toDoDB=new MyclassSQLiteOpenHelper(context); 
		 db=toDoDB.getReadableDatabase();
		 String sql="select * from classes where day="+week;//1为星期一
		 mCursor =db.rawQuery(sql, null);
		 Log.i("", sql);
		//判断游标是否为空  
		 if(mCursor !=null ){  
			 int i=0,n=mCursor .getCount();
			 mCursor .moveToFirst();
			 Log.i("", "mCursor !=null");
			 Log.i("n=?", n+"");
			
             //遍历游标  11.       
			  while (!mCursor .isAfterLast()) {   
				  //获得ID            
				  int id = mCursor .getInt(0);     
				  //System.out.println("mcursor"+id);
				  name[i]=mCursor .getString(2); 
				  //System.out.println("mcursor"+mCursor .getString(2));
				  Log.i("name", mCursor .getString(2));    
				  address[i]=mCursor .getString(3);
				  Log.i("address", mCursor .getString(3));    
				  i++;
				  mCursor .moveToNext();
		}
}		 
		 tv0.setText(days1[week-1]);	 
		 tv1_2.setText(name[0]);
		 tv1_3.setText(address[0]);		 
		 tv2_2.setText(name[1]);
		 tv2_3.setText(address[1]);	 
	     tv3_2.setText(name[2]);
	     tv3_3.setText(address[2]);		 
		 tv4_2.setText(name[3]);
		 tv4_3.setText(address[3]);		 
		 tv5_2.setText(name[4]);
		 tv5_3.setText(address[4]);	 
		Log.i("tv", "已设置tv");
		mCursor .close();
		 toDoDB.close();
		 return myView;
	}
}

