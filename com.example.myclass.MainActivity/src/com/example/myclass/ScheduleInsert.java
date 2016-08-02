package com.example.myclass;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ScheduleInsert extends Activity  {
	
	private String WEEK[]={"星期一","星期二","星期三","星期四","星期五","星期六"};
	private String []name=new String[5];
	private String []address=new String[5];
	private ArrayList<EditText> et_2EditTexts = new ArrayList<EditText>();
	private ArrayList<EditText> et_3EditTexts = new ArrayList<EditText>();
	private String week="1";
	private MyclassSQLiteOpenHelper  toDoDB;
	private Cursor mCursor;
	private SQLiteDatabase db;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    LinearLayout locallinear =(LinearLayout)getLayoutInflater().inflate(R.layout.app_schedule_insert, null);
			setContentView(this.deallayoiut(locallinear));
	   // setContentView(R.layout.app_schedule_insert);
	    //设置spinner的显示
	    Spinner spinner1=(Spinner)findViewById(R.id.spinner1);
	    ArrayAdapter<String>adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, WEEK);
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter1);
	    //设置按钮
	    ImageButton bt1=(ImageButton)findViewById(R.id.schedule_insert_bt1);
	    ImageButton bt2=(ImageButton)findViewById(R.id.schedule_insert_bt2);
	    //设置edittext
	    et_2EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et1_2));
	    et_3EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et1_3));
	    et_2EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et2_2));
	    et_3EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et2_3));
	    et_2EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et3_2));
	    et_3EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et3_3));
	    et_2EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et4_2));
	    et_3EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et4_3));
	    et_2EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et5_2));
	    et_3EditTexts.add((EditText)findViewById(R.id.scheduleInsert_et5_3));
	    
	    //创建数据库
	   toDoDB=new MyclassSQLiteOpenHelper(this);
	   db=toDoDB.getReadableDatabase();
	
	spinner1.setOnItemSelectedListener (new OnItemSelectedListener () 
            {
                public void onItemSelected(
                        AdapterView<?> parent, View view, int position, long id)
                {
                	week=Integer.toString(position+1);         	
           		     String sql="select * from classes where day="+week;
           		     mCursor=db.rawQuery(sql, null);
           		     if(mCursor!=null ){  
        			 int i=0,n=mCursor.getCount();
        			 mCursor.moveToFirst();
        			// Log.i("", "mCursor!=null");
        			// Log.i("n=?", n+"");
        			 
                     //遍历游标  11.       
        			  while (!mCursor.isAfterLast()) {  
        				  name[i]=mCursor.getString(2); 
        				  address[i]=mCursor.getString(3);
        				  i++;
        				  mCursor.moveToNext();
        			   } 
        		 }
                 //给edittext赋初值
        		 for (int i = 0; i < 5; i++) {
        			 et_2EditTexts.get(i).setHint("课程名称") ;
        			 et_3EditTexts.get(i).setHint("地点、任课教师") ; 
					et_2EditTexts.get(i).setText(name[i]);
					et_3EditTexts.get(i).setText(address[i]);
				}               	
                }

                public void onNothingSelected(AdapterView<?> parent)
                {              
                }
            });
	
	 bt1.setOnClickListener(new OnClickListener(){
		 public void onClick(View v) {	
					editTodo();
					Toast.makeText(ScheduleInsert.this, "更新课程成功！", Toast.LENGTH_SHORT).show();
				    finish();
					Intent intent=new Intent(ScheduleInsert.this,MainActivity.class);
					startActivity(intent);
			} 
	 }); 
	bt2.setOnClickListener(new OnClickListener(){
		 public void onClick(View v) {				
				mCursor.close();
				toDoDB.close();
				show();
				finish();
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);		
		}	 
});
	}
	private void editTodo()
	  { 	 
		mCursor.moveToFirst();	
		 id=mCursor.getInt(0);
	    /* 修改数据 */ 
	   for (int i=0; i < 5; i++) {
		   System.out.println("Test"+getEditText_2(i)+"ID"+id);
		   toDoDB.updateCourse(id,getEditText_2(i));
		   toDoDB.updateaddress(id, getEditText_3(i));
		   Log.i("et_2EditTexts[i]","");//正常
		   id++;
	         } 	  
	    } 
	 public void show(){
			Intent intent=new Intent(this,MainActivity.class);
			startActivity(intent);
		 
	 }
	  private String getEditText_2(int index)
		{
			EditText etEditText=et_2EditTexts.get(index);
			String string=etEditText.getText().toString();
		  return string;
		}
	  private String getEditText_3(int index)
		{
		    EditText etEditText=et_3EditTexts.get(index);
			String string=etEditText.getText().toString();
		    return string;
		}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mCursor.close();
		toDoDB.close();
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
				Intent intent=new Intent(ScheduleInsert.this,MainActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				return false;
		}
		return false;
	}

}

