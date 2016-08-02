package com.example.myclass;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;


public class AlarmAlert extends Activity
{
	 MediaPlayer  mMediaPlayer = new MediaPlayer();
	 private Vibrator mVibrator01;  //声明一个振动器对象  
  @Override
  protected void onCreate(Bundle savedInstanceState) 
  {
      super.onCreate(savedInstanceState);
      if (ClockActivity.vibrator == 1)
      {
    	  try {
 		palyLocalAudio_UsingDescriptor();
   		} catch (Exception e) {
   			// TODO Auto-generated catch block  		
   			e.printStackTrace();
  		}
    	  }
      else{
				    try {
				    	  mVibrator01 = ( Vibrator ) getApplication().getSystemService(Service.VIBRATOR_SERVICE);  
				          mVibrator01.vibrate( new long[]{100,100,100,1000}, 0);  
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
  }
	
   new AlertDialog.Builder(AlarmAlert.this)
        .setIcon(R.drawable.clock)
        .setTitle("闹钟响了!!")
        .setMessage("快去上课吧，不要迟到哦亲!!!")
        .setPositiveButton("关掉它",
         new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int whichButton)
          {
        	   if (ClockActivity.vibrator == 1){
        	  mMediaPlayer.release();  }
        	   else 
        	   {
        		  mVibrator01.cancel();
        	  } 		 
            AlarmAlert.this.finish();
          }
        })
        .show();
  } 

  //闹钟里面添加音乐文件
 private void palyLocalAudio_UsingDescriptor() throws Exception{
	 AssetFileDescriptor fileDesc = getResources().openRawResourceFd(R.raw.yinyue );
	 if(fileDesc != null){
		 mMediaPlayer.setDataSource(fileDesc.getFileDescriptor(),fileDesc.getStartOffset(),fileDesc.getLength());
		 fileDesc.close();
		 mMediaPlayer.prepare();
		 mMediaPlayer.start();
	 }
 }
}

