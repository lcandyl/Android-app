package com.example.myclass;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

	public class ClockActivity extends Activity {
          public 	 static   int  vibrator ;
          AbsoluteLayout locallinear;
		  TextView setTime1;
		  TextView setTime2;
		  TextView setTime3;
		  Button mButton1;
		  Button mButton2;
		  Button mButton3;
		  Button mButton4;
		  Button mButton5;
		  Button mButton6;
		  
		  String time1String = null;
		  String time2String = null;
		  String time3String = null;
		  String defalutString = "目前无设置";
		  
		  AlertDialog builder = null;
		  Calendar c=Calendar.getInstance();

		  @Override
		  public void onCreate(Bundle savedInstanceState)
		  {
		    super.onCreate(savedInstanceState);
		   locallinear =(AbsoluteLayout)getLayoutInflater().inflate(R.layout.activity_main, null);
 			setContentView(this.deallayoiut(locallinear));
		    //取得活动的Preferences对象
		    SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
		    time1String = settings.getString("TIME1", defalutString);
		    time2String = settings.getString("TIME2", defalutString);
		    time3String = settings.getString("TIME3", defalutString);
		    
		    InitButton1();
		    InitButton2();
		    InitButton3();
		    InitButton4();	
		    InitButton5();
		    InitButton6();	
		    
		    setTime1.setText(time1String);
		    setTime2.setText(time2String);
		    setTime3.setText(time3String);
		  
		  }
		  
		  public void InitButton1()
		  {
			    setTime1=(TextView) findViewById(R.id.setTime1);
			    mButton1=(Button)findViewById(R.id.mButton1);
			    mButton1.setOnClickListener(new View.OnClickListener()
			    {
			    	
			      public void onClick(View v)
			      {
			    	  new AlertDialog.Builder(ClockActivity.this)
						.setIcon(android.R.drawable.ic_menu_revert)
						.setTitle("响铃方式")
						.setMessage("请选择响铃方式")
						.setNegativeButton("震动",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									vibrator = 0;
								      c.setTimeInMillis(System.currentTimeMillis());
								        int mHour=c.get(Calendar.HOUR_OF_DAY);
								        int mMinute=c.get(Calendar.MINUTE);
								        
								       
								        new TimePickerDialog(ClockActivity.this,
								          new TimePickerDialog.OnTimeSetListener()
								          {                
								            public void onTimeSet(TimePicker view,int hourOfDay,
								                                  int minute)
								            {
								              c.setTimeInMillis(System.currentTimeMillis());
								              c.set(Calendar.HOUR_OF_DAY,hourOfDay);
								              c.set(Calendar.MINUTE,minute);
								              c.set(Calendar.SECOND,0);
								              c.set(Calendar.MILLISECOND,0);
								              
								              Intent intent = new Intent(ClockActivity.this, CallAlarm.class);
								              PendingIntent sender=PendingIntent.getBroadcast(
								            		  ClockActivity.this,0, intent, 0);
								              AlarmManager am;
								              am = (AlarmManager)getSystemService(ALARM_SERVICE);
								              am.set(AlarmManager.RTC_WAKEUP,
								                     c.getTimeInMillis(),
								                     sender
								                    );
								              String tmpS=format(hourOfDay)+"："+format(minute);
								              setTime1.setText(tmpS);
								              
								              //SharedPreferences保存数据，并提交
								              SharedPreferences time1Share = getPreferences(0);
								              SharedPreferences.Editor editor = time1Share.edit();
								              editor.putString("TIME1", tmpS);
								              editor.commit();
								              
								              Toast.makeText(ClockActivity.this,"设置闹钟时间为"+tmpS,
								                Toast.LENGTH_SHORT)
								                .show();
								              
								            }          
								          },mHour,mMinute,true).show();
									}
								})	.setPositiveButton("闹钟",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										vibrator = 1;
									      c.setTimeInMillis(System.currentTimeMillis());
									        int mHour=c.get(Calendar.HOUR_OF_DAY);
									        int mMinute=c.get(Calendar.MINUTE);
									        
									       
									        new TimePickerDialog(ClockActivity.this,
									          new TimePickerDialog.OnTimeSetListener()
									          {                
									            public void onTimeSet(TimePicker view,int hourOfDay,
									                                  int minute)
									            {
									              c.setTimeInMillis(System.currentTimeMillis());
									              c.set(Calendar.HOUR_OF_DAY,hourOfDay);
									              c.set(Calendar.MINUTE,minute);
									              c.set(Calendar.SECOND,0);
									              c.set(Calendar.MILLISECOND,0);
									              
									              Intent intent = new Intent(ClockActivity.this, CallAlarm.class);
									              PendingIntent sender=PendingIntent.getBroadcast(
									            		  ClockActivity.this,0, intent, 0);
									              AlarmManager am;
									              am = (AlarmManager)getSystemService(ALARM_SERVICE);
									              am.set(AlarmManager.RTC_WAKEUP,
									                     c.getTimeInMillis(),
									                     sender
									                    );
									              String tmpS=format(hourOfDay)+"："+format(minute);
									              setTime1.setText(tmpS);
									              
									              //SharedPreferences保存数据，并提交
									              SharedPreferences time1Share = getPreferences(0);
									              SharedPreferences.Editor editor = time1Share.edit();
									              editor.putString("TIME1", tmpS);
									              editor.commit();
									              
									              Toast.makeText(ClockActivity.this,"设置闹钟时间为"+tmpS,
									                Toast.LENGTH_SHORT)
									                .show();
									              
									            }          
									          },mHour,mMinute,true).show();
									}
								}).show();
		    
			  
			      }
			    });
		  }
 
		  public void InitButton2()
		  {
			    mButton2=(Button) findViewById(R.id.mButton2);
			    mButton2.setOnClickListener(new View.OnClickListener()
			    {
			      public void onClick(View v)
			      {
			        Intent intent = new Intent(ClockActivity.this, CallAlarm.class);
			        PendingIntent sender=PendingIntent.getBroadcast(
			        		ClockActivity.this,0, intent, 0);
			        AlarmManager am;
			        am =(AlarmManager)getSystemService(ALARM_SERVICE);
			        am.cancel(sender);
			        Toast.makeText(ClockActivity.this,"闹钟时间删除",
			                       Toast.LENGTH_SHORT).show();
			        setTime1.setText("目前无设置");
			        
			        SharedPreferences time1Share = getPreferences(0);
		            SharedPreferences.Editor editor = time1Share.edit();
		            editor.putString("TIME1", "目前无设置");
		            editor.commit();
			      }
			    });
		  }
		  
		  public void InitButton3()
		  {
			  
			    setTime2=(TextView) findViewById(R.id.setTime2);
			    LayoutInflater factory = LayoutInflater.from(this);
			    final View setView = factory.inflate(R.layout.timeset,null);
			    final TimePicker tPicker=(TimePicker)setView
			                               .findViewById(R.id.tPicker);
			    tPicker.setIs24HourView(true);
			    
			    final AlertDialog di=new AlertDialog.Builder(ClockActivity.this)
		          .setIcon(R.drawable.clock)
		          .setTitle("设置")
		          .setView(setView)
		          .setPositiveButton("确定",
		            new DialogInterface.OnClickListener()
		           {
		             public void onClick(DialogInterface dialog, int which)
		             {
		            	 new AlertDialog.Builder(ClockActivity.this)
							.setIcon(android.R.drawable.ic_menu_revert)
							.setTitle("响铃方式")
							.setMessage("请选择响铃方式")
							.setNegativeButton("震动",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
										vibrator = 0;	
									    EditText ed=(EditText)setView.findViewById(R.id.mEdit);
							               int times=Integer.parseInt(ed.getText().toString())
							                          *1000*60;
							               c.setTimeInMillis(System.currentTimeMillis());
							               c.set(Calendar.HOUR_OF_DAY,tPicker.getCurrentHour());
							               c.set(Calendar.MINUTE,tPicker.getCurrentMinute());
							               c.set(Calendar.SECOND,0);
							               c.set(Calendar.MILLISECOND,0);
							               
							               Intent intent = new Intent(ClockActivity.this,
							                                          CallAlarm.class);
							               PendingIntent sender = PendingIntent.getBroadcast(
							            		   ClockActivity.this,1, intent, 0);
							               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
							               AlarmManager am;
							               am = (AlarmManager)getSystemService(ALARM_SERVICE);
							               am.setRepeating(AlarmManager.RTC_WAKEUP,
							                         c.getTimeInMillis(),times,sender);
							               String tmpS=format(tPicker.getCurrentHour())+"："+
							                           format(tPicker.getCurrentMinute());
							               String subStr = "设置闹钟时间为"+tmpS+
					                   "开始，重复间隔为"+times/1000/60+"分钟";
							               setTime2.setText("设置闹钟时间为"+tmpS+
							                                "开始，重复间隔为"+times/1000/60+"分钟");
							               
							               //SharedPreferences保存数据，并提交  
							               SharedPreferences time2Share = getPreferences(2);
								           SharedPreferences.Editor editor = time2Share.edit();
								           editor.putString("TIME2", subStr);
								           editor.commit();
							               
							               Toast.makeText(ClockActivity.this,"设置闹钟为"+tmpS+
							                              "开始，重复间隔为"+times/1000/60+"分钟",
							                              Toast.LENGTH_SHORT).show();
										}
									})	.setPositiveButton("闹钟",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
											vibrator = 1;
											  EditText ed=(EditText)setView.findViewById(R.id.mEdit);
								               int times=Integer.parseInt(ed.getText().toString())
								                          *1000*60;
								               c.setTimeInMillis(System.currentTimeMillis());
								               c.set(Calendar.HOUR_OF_DAY,tPicker.getCurrentHour());
								               c.set(Calendar.MINUTE,tPicker.getCurrentMinute());
								               c.set(Calendar.SECOND,0);
								               c.set(Calendar.MILLISECOND,0);
								               
								               Intent intent = new Intent(ClockActivity.this,
								                                          CallAlarm.class);
								               PendingIntent sender = PendingIntent.getBroadcast(
								            		   ClockActivity.this,1, intent, 0);
								               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
								               AlarmManager am;
								               am = (AlarmManager)getSystemService(ALARM_SERVICE);
								               am.setRepeating(AlarmManager.RTC_WAKEUP,
								                         c.getTimeInMillis(),times,sender);
								               String tmpS=format(tPicker.getCurrentHour())+"："+
								                           format(tPicker.getCurrentMinute());
								               String subStr = "设置闹钟时间为"+tmpS+
						                   "开始，重复间隔为"+times/1000/60+"分钟";
								               setTime2.setText("设置闹钟时间为"+tmpS+
								                                "开始，重复间隔为"+times/1000/60+"分钟");
								               
								               //SharedPreferences保存数据，并提交  
								               SharedPreferences time2Share = getPreferences(2);
									           SharedPreferences.Editor editor = time2Share.edit();
									           editor.putString("TIME2", subStr);
									           editor.commit();
								               
								               Toast.makeText(ClockActivity.this,"设置闹钟为"+tmpS+
								                              "开始，重复间隔为"+times/1000/60+"分钟",
								                              Toast.LENGTH_SHORT).show();
										}
									}).show();
		            	 
		           
		             }
		           })
		          .setNegativeButton("取消",
		            new DialogInterface.OnClickListener()
		           {
		             public void onClick(DialogInterface dialog, int which)
		             {
		             }
		           }).create();

			    mButton3=(Button) findViewById(R.id.mButton3);
			    mButton3.setOnClickListener(new View.OnClickListener()
			    {
			      public void onClick(View v)
			      {
			    	    c.setTimeInMillis(System.currentTimeMillis());
				        tPicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
				        tPicker.setCurrentMinute(c.get(Calendar.MINUTE));
				        di.show();
			      }
			    });
		  }
		  
		  public void InitButton4()
		  {
			    mButton4=(Button) findViewById(R.id.mButton4);
			    mButton4.setOnClickListener(new View.OnClickListener()
			    {
			      public void onClick(View v)
			      {
			        Intent intent = new Intent(ClockActivity.this, CallAlarm.class);
			        PendingIntent sender = PendingIntent.getBroadcast(
			        		ClockActivity.this,1, intent, 0);
			        AlarmManager am;
			        am = (AlarmManager)getSystemService(ALARM_SERVICE);
			        am.cancel(sender);
			        Toast.makeText(ClockActivity.this,"闹钟时间删除",
			                       Toast.LENGTH_SHORT).show();
			        setTime2.setText("目前无设置");
			        //SharedPreferences保存数据，并提交  
		            SharedPreferences time2Share = getPreferences(2);
			        SharedPreferences.Editor editor = time2Share.edit();
			        editor.putString("TIME2", "目前无设置");
			        editor.commit();
			      }
			    });
		  }
		  
		  public void InitButton5()
		  {
			    setTime3=(TextView) findViewById(R.id.setTime3);
			    LayoutInflater factory = LayoutInflater.from(this);
			    final View setView = factory.inflate(R.layout.dayset,null);
			    final TimePicker tPicker=(TimePicker)setView
			                               .findViewById(R.id._tPicker);
			    tPicker.setIs24HourView(true);

			    final AlertDialog di=new AlertDialog.Builder(ClockActivity.this)
		          .setIcon(R.drawable.clock)
		          .setTitle("设置")
		          .setView(setView)
		          .setPositiveButton("确定",
		            new DialogInterface.OnClickListener()
		           {
		             public void onClick(DialogInterface dialog, int which)
		             {
		            	  new AlertDialog.Builder(ClockActivity.this)
							.setIcon(android.R.drawable.ic_menu_revert)
							.setTitle("响铃方式")
							.setMessage("请选择响铃方式")
							.setNegativeButton("震动",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
										vibrator = 0;	
										   EditText ed=(EditText)setView.findViewById(R.id._mEdit);
							               int times=Integer.parseInt(ed.getText().toString())
							                          *1000*60*60*24;
							               c.setTimeInMillis(System.currentTimeMillis());
							               c.set(Calendar.HOUR_OF_DAY,tPicker.getCurrentHour());
							               c.set(Calendar.MINUTE,tPicker.getCurrentMinute());
							               c.set(Calendar.SECOND,0);
							               c.set(Calendar.MILLISECOND,0);

							               Intent intent = new Intent(ClockActivity.this,
							                                          CallAlarm.class);
							               PendingIntent sender = PendingIntent.getBroadcast(
							            		   ClockActivity.this,1, intent, 0);
							               AlarmManager am;
							               am = (AlarmManager)getSystemService(ALARM_SERVICE);
							               am.setRepeating(AlarmManager.RTC_WAKEUP,
							                         c.getTimeInMillis(),times,sender);
							               String tmpS=format(tPicker.getCurrentHour())+"："+
							                           format(tPicker.getCurrentMinute());
							               String subStr = "设置闹钟时间为"+tmpS+
					                   "开始，重复间隔为"+times/1000/60/60/24+"天";
							               setTime3.setText("设置闹钟时间为"+tmpS+
							                                "开始，重复间隔为"+times/1000/60/60/24+"天");
							               
							               //SharedPreferences保存数据，并提交  
							               SharedPreferences time3Share = getPreferences(2);
								           SharedPreferences.Editor editor = time3Share.edit();
								           editor.putString("TIME3", subStr);
								           editor.commit();
							               
							               Toast.makeText(ClockActivity.this,"设置闹钟为"+tmpS+
							                              "开始，重复间隔为"+times/1000/60/60/24+"天",
							                              Toast.LENGTH_SHORT).show();
										}
									})	.setPositiveButton("闹钟",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
											vibrator = 1;
										     EditText ed=(EditText)setView.findViewById(R.id._mEdit);
								               int times=Integer.parseInt(ed.getText().toString())
								                          *1000*60*60*24;
								               c.setTimeInMillis(System.currentTimeMillis());
								               c.set(Calendar.HOUR_OF_DAY,tPicker.getCurrentHour());
								               c.set(Calendar.MINUTE,tPicker.getCurrentMinute());
								               c.set(Calendar.SECOND,0);
								               c.set(Calendar.MILLISECOND,0);

								               Intent intent = new Intent(ClockActivity.this,
								                                          CallAlarm.class);
								               PendingIntent sender = PendingIntent.getBroadcast(
								            		   ClockActivity.this,1, intent, 0);
								               AlarmManager am;
								               am = (AlarmManager)getSystemService(ALARM_SERVICE);
								               am.setRepeating(AlarmManager.RTC_WAKEUP,
								                         c.getTimeInMillis(),times,sender);
								               String tmpS=format(tPicker.getCurrentHour())+"："+
								                           format(tPicker.getCurrentMinute());
								               String subStr = "设置闹钟时间为"+tmpS+
						                   "开始，重复间隔为"+times/1000/60/60/24+"天";
								               setTime3.setText("设置闹钟时间为"+tmpS+
								                                "开始，重复间隔为"+times/1000/60/60/24+"天");
								               
								               //SharedPreferences保存数据，并提交  
								               SharedPreferences time3Share = getPreferences(2);
									           SharedPreferences.Editor editor = time3Share.edit();
									           editor.putString("TIME3", subStr);
									           editor.commit();
								               
								               Toast.makeText(ClockActivity.this,"设置闹钟为"+tmpS+
								                              "开始，重复间隔为"+times/1000/60/60/24+"天",
								                              Toast.LENGTH_SHORT).show();
										}
									}).show();
		            	  
		          
		             }
		           })
		          .setNegativeButton("取消",
		            new DialogInterface.OnClickListener()
		           {
		             public void onClick(DialogInterface dialog, int which)
		             {
		             }
		           }).create();

			    mButton5=(Button) findViewById(R.id.mButton5);
			    mButton5.setOnClickListener(new View.OnClickListener()
			    {
			      public void onClick(View v)
			      {
			    	    c.setTimeInMillis(System.currentTimeMillis());
				        tPicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
				        tPicker.setCurrentMinute(c.get(Calendar.MINUTE));
				        di.show();
			      }
			    });
		  }
		  
		  public void InitButton6()
		  {
			    mButton6=(Button) findViewById(R.id.mButton6);
			    mButton6.setOnClickListener(new View.OnClickListener()
			    {
			      public void onClick(View v)
			      {
			        Intent intent = new Intent(ClockActivity.this, CallAlarm.class);
			        PendingIntent sender = PendingIntent.getBroadcast(
			        		ClockActivity.this,1, intent, 0);
			        AlarmManager am;
			        am = (AlarmManager)getSystemService(ALARM_SERVICE);
			        am.cancel(sender);
			        Toast.makeText(ClockActivity.this,"闹钟时间删除",
			                       Toast.LENGTH_SHORT).show();
			        setTime3.setText("目前无设置");
			        //SharedPreferences保存数据，并提交  
		            SharedPreferences time3Share = getPreferences(2);
			        SharedPreferences.Editor editor = time3Share.edit();
			        editor.putString("TIME3", "目前无设置");
			        editor.commit();
			      }
			    });
		  }
      
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			//如果按下的是返回键，并且没有重复
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				Intent intent=new Intent(ClockActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				return false;
			}
			return false;
		}

		private AbsoluteLayout deallayoiut(AbsoluteLayout locallinear){
			   SharedPreferences prs = getSharedPreferences("INIT",Context.MODE_PRIVATE);  
		       int num = prs.getInt("bgnum", 0);
			if(num==0||num==1)
				locallinear.setBackgroundResource(R.drawable.bg1);
				else if(num==2)
					locallinear.setBackgroundResource(R.drawable.bg2);
				else if(num==3)
					locallinear.setBackgroundResource(R.drawable.bg3);
				else if(num==4)
					locallinear.setBackgroundResource(R.drawable.bg4);
			return locallinear;
			
		}
		
		private String format(int x)
		  {
		    String s=""+x;
		    if(s.length()==1) s="0"+s;
		    return s;
		  }
}