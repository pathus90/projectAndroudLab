package com.porjetihm.projetlab;

import com.porjetihm.projetlab.dessin.LabyritheView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public class Level1Activity extends Activity {

	public static final int DEFEAT_DIALOG = 0;
	private LabyritheView labyrithe;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Remove title bar
		//notre surface de dessin
		MediaPlayer player = MediaPlayer.create(this,R.raw.bang);
		player.start();
		labyrithe=new LabyritheView(getApplicationContext(),this);
		setContentView(labyrithe);

	}
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case DEFEAT_DIALOG:
			builder.setCancelable(false)
			.setMessage("La Terre a été détruite à cause de vos erreurs.")
			.setTitle("Bah bravo !")
			.setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Level1Activity.this.finish();
				}
			});
		}
		return builder.create();

	}

}
