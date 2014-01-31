package com.porjetihm.projetlab;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivityLevels extends Activity {

	private ImageView Niveau1;
	private ImageView Quitter;
	private ImageView Apropos;
	private MediaPlayer player;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main_activity_levels);
		player=MediaPlayer.create(this,R.raw.drum);
		player.setLooping(true);
		player.start();
		Niveau1=(ImageView)findViewById(R.id.image1);
		Niveau1.setClickable(true);
		Niveau1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivityLevels.this,Level1Activity.class);
				startActivity(i);

			}
		});

		Apropos=(ImageView)findViewById(R.id.image4);
		Apropos.setClickable(true);
		Apropos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivityLevels.this,Apropos.class);
				startActivity(i);

			}
		});

		Quitter=(ImageView)findViewById(R.id.image5);
		Quitter.setClickable(true);
		Quitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				moveTaskToBack(true); 
				MainActivityLevels.this.finish();

			}
		});
		

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		player.release();
	}
	

}
