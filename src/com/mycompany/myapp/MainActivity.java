package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.util.*;
import android.view.View.*;

public class MainActivity extends Activity
{
	List<Button> btns = new ArrayList();
	Button btn2 = null;
	Yama yama = new Yama();
	Player player = new Player();
	int iBtn=0;
	TextView txt1 = null;
	
	CameraController camera=null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		LinearLayout layout =new LinearLayout(this);
		// layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setOrientation(LinearLayout.VERTICAL);
		HorizontalScrollView scrollView = new HorizontalScrollView(this);
		setContentView(scrollView);
		
		LinearLayout child1 = new LinearLayout(this);
		child1.setOrientation(LinearLayout.HORIZONTAL);
		
		for(iBtn=0;iBtn<Tehai.TEHAI_MAX_CNT;iBtn++){
			Button btn = new Button(this);
			btn.setText("b"+iBtn);
			btn.setOnClickListener(new OnClickListener(){
				int index = iBtn;
				public void onClick(View v){
					Log.d("jong","iBtn="+index);// 13
					player.tehai.set(index, yama.get());
					ri_hai();
					ShantenInfo info = player.tehai.getShanten();
					String str;
					str = "@@@@@@@@ shanten=" + info.shanten + " mentu=" + info.mentu + " taatu=" + info.taatu + " toitu=" + info.toitu + " ";
					// Log.d("jong","@@@@@@@@ shanten=" + player.tehai.getShanten()+"");
					txt1.setText(str);
				}
			});
			child1.addView(btn,
				new LinearLayout.LayoutParams(
					150,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			btns.add(btn);
		}
		
		LinearLayout child2 = new LinearLayout(this);
		child2.setOrientation(LinearLayout.HORIZONTAL);	
		
		btn2 = new Button(this);
		btn2.setText("tsumo");
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				// Tsumo
				player.tehai.tsumo = yama.get();
				// Log.d("jong","@@@@ tsumo @@@@ shanten=" + player.tehai.getShanten(player.tehai.tsumo)+"");
			}
		});
		child2.addView(btn2,
					   new LinearLayout.LayoutParams(
						   LinearLayout.LayoutParams.WRAP_CONTENT,
						   LinearLayout.LayoutParams.WRAP_CONTENT));
			
		LinearLayout child3 = new LinearLayout(this);
		child3.setOrientation(LinearLayout.HORIZONTAL);
		
		txt1 = new TextView(this);
		txt1.setText("hoge");
		child3.addView(txt1,
					   new LinearLayout.LayoutParams(
						   LinearLayout.LayoutParams.WRAP_CONTENT,
						   LinearLayout.LayoutParams.WRAP_CONTENT));
		
		layout.addView(child1);
		layout.addView(child2);
		layout.addView(child3);
		// camera = new CameraController();
		// FrameLayout flayout = camera.init(this);
		// setContentView(flayout);
		scrollView.addView(layout);
		
		hai_pai();
		ri_hai();
    }

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		
		if(camera!=null){
			camera.release();
		}
	}
	
	void hai_pai(){
		yama.set();
		for(int i=0;i<Tehai.TEHAI_MAX_CNT;i++){
			player.tehai.add(yama.get());
		}
	}
	
	void ri_hai(){
		Collections.sort(player.tehai.tehai,new TehaiSorter());
		int index=0;
		for(Pi pi :player.tehai.tehai){
			btns.get(index++).setText(pi.getNameString2());
		}
	}
}
