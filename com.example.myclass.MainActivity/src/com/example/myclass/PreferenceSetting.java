package com.example.myclass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class PreferenceSetting extends PreferenceActivity implements 
OnPreferenceChangeListener{
	private MyclassSQLiteOpenHelper  toDoDB;
    private static  int year1=0,month=0,day=0,count=0;
	private int bgnum=0;
    private static int mYear =0;
    private static  int mMonth =0;
    private static  int mDay = 0;
    private static String nowdate,weekcount;
    public static boolean isnew=false;
    ListPreference bglp;
    PreferenceSetting preferenceSetting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		toDoDB=new MyclassSQLiteOpenHelper(this);
		addPreferencesFromResource(R.xml.preference);
		 bglp = (ListPreference)findPreference("Background");
		 bglp.setOnPreferenceChangeListener(this);
	}
	
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference.getKey().equals("Delete")) {
			Dialog dialog = new AlertDialog.Builder(this)
			.setCancelable(false)
			.setIcon(android.R.drawable.ic_menu_revert)
			.setTitle("清空课表")
			.setMessage("您确认要清空所有数据吗？")
			.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							dialog.cancel();
						}
					})	.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							if ( toDoDB != null)
								{toDoDB.clearclasses();
								Toast.makeText(PreferenceSetting.this, "清空课表成功！", Toast.LENGTH_SHORT).show();
								}
						}
					}).create();
			 dialog.show();
		} 
	else if (preference.getKey().equals("Setdate")) {
		showDialog(1);
	}
	else if (preference.getKey().equals("clock")) {
		Intent intent = new Intent(this, ClockActivity.class);
		this.startActivity(intent);
		PreferenceSetting.this.finish(); 
		overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
	}return false;
	}
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		switch (id) {
		case 1:
			Log.i("已进入onCreateDialog", "已进入onCreateDialog");
			SharedPreferences share = getSharedPreferences("INIT",
					MODE_WORLD_WRITEABLE);
			 //取当前日期
			  Calendar cal = Calendar.getInstance();
			    Date curDate = new Date(System.currentTimeMillis());
			    cal.setTime(curDate);
			   if( mYear ==0){
			    	System.out.println("等于0");
			    mYear =cal.get(Calendar.YEAR);
		        mMonth=cal.get(Calendar.MONTH);
		        mDay=cal.get(Calendar.DAY_OF_MONTH);
			    }else 
			    {   System.out.println("不等于0");
			    	mYear =year1;
		             mDay=day;
		             mMonth=month;
		             }			
			    //取当前日期到字符串nowdate中
			    nowdate = Integer.toString(cal.get(Calendar.YEAR)) + "-"
						+ Integer.toString(cal.get(Calendar.MONTH)) + "-"  //差一个月 5月显示4月
						+ Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
			    System.out.println(nowdate);			    
				DatePickerDialog dpd = new DatePickerDialog(this,
						onDateSetListener, mYear,
						mMonth, mDay);
			         	dpd.show();		       
		default:
			break;
		}
		return super.onCreateDialog(id);
		
	}

	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			  year1=year;
	         month=monthOfYear;
	         day=dayOfMonth;
			String dateString = Integer.toString(year) + "-"
					+ Integer.toString(monthOfYear) + "-"  //差一个月 5月显示4月
					+ Integer.toString(dayOfMonth);

		SharedPreferences share = getSharedPreferences("INIT",
				Context.MODE_WORLD_WRITEABLE);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//输入日期的格式 
			Date date1 = null;
			try {
			date1 = simpleDateFormat.parse(dateString);
			} catch (ParseException e) {
			e.printStackTrace();
			}  
			Date date2 = null;
			try {
			date2 = simpleDateFormat.parse(nowdate);
			} catch (ParseException e) {
			e.printStackTrace();
			}  
			GregorianCalendar cal1 = new GregorianCalendar();  
			GregorianCalendar cal2 = new GregorianCalendar();  
			cal1.setTime(date1);  
			cal2.setTime(date2);  			
			double dayCount = ((cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24))/7+1;//从间隔毫秒变成间隔天数
			count = (int)dayCount;
			Toast.makeText(PreferenceSetting.this, "设置开学日期成功！", Toast.LENGTH_SHORT).show();
		}
	};
	
	 private String updateCount()
     {
		 if (count==0)
		 {
			 weekcount="请设置开学日期！"; 
		 }else { 
			 weekcount ="第"+Integer.toString(+count)+"周";		
		 }
		 return weekcount;
     }
	 public  String getWeekandDate() {
		    String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		    Calendar cal = Calendar.getInstance();
		    Date curDate = new Date(System.currentTimeMillis());
		    cal.setTime(curDate);
		    int mYear=cal.get(Calendar.YEAR);
	        int mMonth=cal.get(Calendar.MONTH);
	        int mDay=cal.get(Calendar.DAY_OF_MONTH);
	        int Month=mMonth+1;
		    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		    if (w < 0)
		        w = 0;
		    String string= ("当前日期为："+mYear+"/"+Month+"/"+mDay+"/"+weekDays[w]+"/"+"开学"+updateCount());
		    return  string;    
		}
	 
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			 if(preference.getKey().equals("Background"))
				{
				     System.out.println("进入到背景更改的监听里面");
					 if(((String)newValue).trim().equals("蓝色"))bgnum=1;
					 else if(((String) newValue).trim().equals("绿色"))bgnum=2;
					 else if(((String) newValue).trim().equals("粉色"))bgnum=3;
					 else if(((String) newValue).trim().equals("橙色"))bgnum=4;
					 preference.setSummary(((String) newValue).trim());
					  isnew=true;
				       SharedPreferences share = getSharedPreferences("INIT",Context.MODE_PRIVATE);  
				       Editor editor = share.edit();
				       editor.putInt("bgnum", bgnum);
				       editor.commit();  
				       Toast.makeText(PreferenceSetting.this, "背景更改", Toast.LENGTH_LONG).show();
				}
			else return false;
			return true;
		}
	 @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			//如果按下的是返回键，并且没有重复
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				Intent intent=new Intent(PreferenceSetting.this,MainActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				return false;
			}
			return false;
		}

	 public static Calendar getTimeAfterInSecs (int secs) { 
		 Calendar cal 	= Calendar.getInstance();
		 cal.add(Calendar.SECOND,secs);
		 return cal;
		 }
	
}
