package com.example.myclass;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast; 
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;

public class MainActivity extends Activity {
	// private Cursor mCursor;
  //  private Uri mUri;
	// ViewPager是google SDk中自带的一个附加包的一个类，可以用来实现屏幕间的切换。
    // 包名为 android-support-v4.jar
	private ViewPager viewPager;//页卡内容,即主要显示内容的画面
	private ImageView imageView;// 动画图片	
	private TextView textView1,textView2,textView3,textView4,textView5,textView6;  
	private List<View> views; // Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private View view1,view2,view3,view4,view5,view6;
	//private String WEEK="0";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	   // setContentView(R.layout.app_schedule_show);	    
	    LinearLayout locallinear =(LinearLayout)getLayoutInflater().inflate(R.layout.app_schedule_show, null);
		setContentView(this.deallayoiut(locallinear));
		InitTextView();		
		InitImageView();
		InitViewPager();
	}
	
	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		viewPager=(ViewPager) findViewById(R.id.vPager);  
		views = new ArrayList<View>();
        GetSchedule getSchedule=new GetSchedule(this);
		View monView=getSchedule.getScheduleView(1);//1为星期一
		View tueView=getSchedule.getScheduleView(2);
		View wedView=getSchedule.getScheduleView(3);
		View thuView=getSchedule.getScheduleView(4);
		View friView=getSchedule.getScheduleView(5);
		View thaView=getSchedule.getScheduleView(6);
		views.add(monView);
		views.add(tueView);
		views.add(wedView);
		views.add(thuView);
		views.add(friView);
		views.add(thaView);
		
		viewPager.setAdapter(new MyPagerAdapter(views));
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());//为mPage设置了另一个监听
		viewPager.setCurrentItem(0);		
	}
	
	/**
	 * 初始化头标	 */
	private void InitTextView() {
		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		textView3 = (TextView) findViewById(R.id.text3);
		textView4 = (TextView) findViewById(R.id.text4);
		textView5 = (TextView) findViewById(R.id.text5);
		textView6 = (TextView) findViewById(R.id.text6);

		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
		textView4.setOnClickListener(new MyOnClickListener(3));
		textView5.setOnClickListener(new MyOnClickListener(4));
		textView6.setOnClickListener(new MyOnClickListener(5));
	}

	/**
	 * 初始化动画	 */
	private void InitImageView() {
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.line_small)
				.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 6 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是返回键，并且没有重复
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
			return false;
		}
		return false;
	}
	
	/**
	 * ViewPager适配器	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
       
		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量
		int three = one * 3;
		int four = one * 4;
		int five = one * 5;
		 
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			imageView.startAnimation(animation); 
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
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
   public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.option_menu, menu);
			return true;
    }
	 
	 public boolean onOptionsItemSelected(MenuItem item)
		{
			switch (item.getItemId()) {
			case R.id.item1:
				startActivityForResult(new Intent(this, ScheduleInsert.class), 0);
				MainActivity.this.finish(); 
				break;
			case R.id.item2:
				Intent intent = new Intent(this, PreferenceSetting.class);
				this.startActivity(intent);
				MainActivity.this.finish(); 
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				break;
			case R.id.item3:
				new AlertDialog.Builder(this).setTitle(R.string.about).setMessage(R.string.help_text).setIcon(android.R.drawable.ic_menu_help).show();
				break;
			case R.id.item4:
				Intent intent1 = new Intent(this, TelephonyDemo.class);
				this.startActivity(intent1);
				MainActivity.this.finish(); 
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				break;
			case R.id.item5:
				new AlertDialog.Builder(this)
						.setCancelable(false)
						.setIcon(android.R.drawable.btn_star)
						.setTitle("退出")
						.setMessage("您确认要退出程序吗？")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										MainActivity.this.finish(); // 关闭程序的核心方法
										System.exit(0);
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();		
									}
								}).show();
				break;
			default:
				break;
			}
			return super.onOptionsItemSelected(item);
		}
     
	
}
