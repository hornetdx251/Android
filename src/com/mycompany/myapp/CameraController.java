package com.mycompany.myapp;
import android.hardware.Camera;
import android.content.Context;
import android.text.*;
import android.widget.*;
import android.util.Log;

public class CameraController
{
	private Camera camera=null;
	private CameraPreview preview;
	
	public FrameLayout init(Context context){
		
		try{
			// open default camera.
			camera = Camera.open();
		}catch(Exception e){
			Log.d("jong","Error : fail to open camera.");
			return null;
		}
		
		preview = new CameraPreview(context,camera);
		FrameLayout flayout = new FrameLayout(context);
		flayout.addView(preview,
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		return flayout;
	}
	
	
	void release(){
		if(camera!=null){
			camera.release();
			camera=null;
		}
		
	}
}
