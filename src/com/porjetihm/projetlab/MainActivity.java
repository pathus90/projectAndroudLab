package com.porjetihm.projetlab;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	 private MediaPlayer player;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		player=MediaPlayer.create(this,R.raw.drum);
		player.start();
		Thread background = new Thread() {
			public void run() {
				try {
					// Thread will sleep for 3 seconds
					sleep(2000);

					// After 3 seconds redirect to another intent
					Intent i=new Intent(MainActivity.this,MainActivityLevels.class);
					startActivity(i);

					//Remove activity
					finish();

				} catch (Exception e) {

				}
			}
		};

		background.start();
	}
	protected void onDestroy() {

		super.onDestroy();

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		player.release();
	}
	
	
	

}
