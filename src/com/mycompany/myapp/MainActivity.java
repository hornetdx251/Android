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
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		LinearLayout layout =new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		HorizontalScrollView scrollView = new HorizontalScrollView(this);
		setContentView(scrollView);
		
		for(iBtn=0;iBtn<Tehai.TEHAI_MAX_CNT;iBtn++){
			Button btn = new Button(this);
			btn.setText("b"+iBtn);
			btn.setOnClickListener(new OnClickListener(){
				int index = iBtn;
				public void onClick(View v){
					Log.d("jong","iBtn="+index);// 13
					player.tehai.set(index, yama.get());
					ri_hai();
					Log.d("jong","@@@@@@@@ shanten=" + player.tehai.getShanten()+"");
				}
			});
			layout.addView(btn,
				new LinearLayout.LayoutParams(
					150,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			btns.add(btn);
		}
		
		btn2 = new Button(this);
		btn2.setText("tsumo");
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				// Tsumo
				player.tehai.tsumo = yama.get();
				Log.d("jong","@@@@ tsumo @@@@ shanten=" + player.tehai.getShanten(player.tehai.tsumo)+"");
			}
		});
		
		layout.addView(btn2,
					   new LinearLayout.LayoutParams(
						   LinearLayout.LayoutParams.WRAP_CONTENT,
						   LinearLayout.LayoutParams.WRAP_CONTENT));
		scrollView.addView(layout);
		
		hai_pai();
		ri_hai();
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
