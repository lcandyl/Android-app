package com.example.myclass;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TelephonyDemo extends Activity {
	private MyclassSQLiteOpenHelper  toDoDB;
	private SQLiteDatabase db;
	private Cursor mCursor ;
	private String []day=new String[30];
	private String []name=new String[30];
	private String []address=new String[30];
    private String sms ="";
    private EditText addrTxt;
    private  EditText msgTxt;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		 LinearLayout locallinear =(LinearLayout)getLayoutInflater().inflate(R.layout.sms_show, null);
			setContentView(this.deallayoiut(locallinear));
			 toDoDB=new MyclassSQLiteOpenHelper(this);
		     db=toDoDB.getReadableDatabase();
		     addrTxt = (EditText)findViewById(R.id.addrEditText);
			 msgTxt = (EditText)findViewById(R.id.msgEditText);
			 addrTxt.setHint("输入电话号码") ;
			
		   //  msgTxt = toDoDB.read();
		     String sql="select * from classes ";
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
					  day[i]=mCursor .getString(1); 
					
					  name[i]=mCursor .getString(2); 
					  address[i]=mCursor .getString(3);
					  
					  if ( i==0  ){
						  day[i]="星期一";
					  }else
						  if ( i==1 || i==2 || i==3 || i==4 ){
						  day[i]="              ";
						  }else
						  if ( i==5){
						  day[i]="星期二";
					  }else
						  if (  i==6 || i==7 || i==8 || i==9 ){	  
							  day[i]="              ";
							  }else					  
						  if ( i==10 ){
						  day[i]="星期三";
					  }else
                                   if ( i==11 || i==12 || i==13 || i==14 ){
							  day[i]="              ";
							  }else				  
						  if ( i==15 ){
						  day[i]="星期四";
					  }else
                          if ( i==16 || i==17 || i==18 || i==19){					  
					  day[i]="              ";
					  }else		
						  if ( i==20  ){
						  day[i]="星期五";
					  }else
						  if (  i==21 || i==22 || i==23 || i==24){					  
							  day[i]="              ";
							  }else		
						  if ( i==25){
						  day[i]="星期六";
					  }
					  if ( i==26 || i==27 || i==28 || i==29){						  
						  day[i]="              ";
						  }		
					  
					  if(i==0||i==5||i==10||i==15||i==20||i==25){
						  sms += day[i]+"     第一大节   "+name[i]+"   "+address[i]+"\n";
					  } else  
						  if(i==1||i==6||i==11||i==16||i==21||i==26){
						  sms += day[i]+"   第二大节   "+name[i]+"   "+address[i]+"\n";
					  } else 
						  if(i==2||i==7||i==12||i==17||i==22||i==27){
						  sms += day[i]+"   第三大节   "+name[i]+"   "+address[i]+"\n";
					  } else 
						  if(i==3||i==8||i==13||i==18||i==23||i==28){
							  sms += day[i]+"   第四大节   "+name[i]+"   "+address[i]+"\n";
						  } else 
							  if(i==4||i==9||i==14||i==19||i==24||i==29){
							  sms += day[i]+"   选  修   "+name[i]+"   "+address[i]+"\n";
							  } 
					  i++;
					  mCursor .moveToNext();
					
					}
			 }		
				mCursor .close();
				 toDoDB.close();
				msgTxt.setText(sms);
	}		 
			 

	public void doSend (View view)
	{
		
	try {
		System.out.println(addrTxt.getText().toString());
		System.out.println(msgTxt.getText().toString());
		sendSmsMessage (
				addrTxt.getText().toString(),
				msgTxt.getText().toString() ) ;
				Toast.makeText(this, "短信发送成功", Toast.LENGTH_LONG).show();
				finish();
				Intent intent=new Intent(TelephonyDemo.this,MainActivity.class);
				startActivity(intent);
				}
				catch (Exception e){
					Toast.makeText(this, "短信息发送失败，请输入正确的电话号码！", Toast.LENGTH_LONG).show();
					e.printStackTrace();
					}
				}

	@Override
	
	protected  void  onDestroy()  {
		super.onDestroy();
	}
	
	private void sendSmsMessage (String address ,String message) throws Exception {
		
		SmsManager smsMgr = SmsManager.getDefault();
		 if (message.length() > 200) {
	            ArrayList<String> msgs = smsMgr.divideMessage(message);
	            for (String msg : msgs) {
	            	smsMgr.sendTextMessage(address, null, msg, null, null);
	            }
	        } else {
	        	smsMgr.sendTextMessage(address, null, message, null, null);
	        }
	}
	
	private LinearLayout deallayoiut(LinearLayout locallinear){
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是返回键，并且没有重复
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				Intent intent=new Intent(TelephonyDemo.this,MainActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				return false;
		}
		return false;
	}
}
