package com.porjetihm.projetlab.dessin;


import java.util.ArrayList;

import com.porjetihm.projetlab.Level1Activity;
import com.porjetihm.projetlab.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ViewConstructor")
public class LabyritheView extends SurfaceView implements SurfaceHolder.Callback
{

	private MediaPlayer player;
	private SurfaceHolder holder;
	private DessinThread Thdessin;
	Bitmap Backgroud;
	Bitmap mure_jeu;
	Bitmap ball_arrivee,ball_depart;
	Bitmap obstacle;
	private Labyrinthe labyrinthe;
	private Balle departBalle;
	Level1Activity activity;
	/**/
	private SensorEventListener mySensorListener;
	private SensorManager sm;
	private Sensor sensor;
	float x=0,y=0,z=0;
	int mx;
	int my;
	private ArrayList<Objet> objets;
	public LabyritheView(Context context,Level1Activity a) 
	{
		super(context);

		activity=a;
		player=MediaPlayer.create(context, R.raw.drum);
		//Arriere plan
		Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		Backgroud=getResizedBitmap(b, Parametre.HAUTEUR_BG, Parametre.LARGEUR_BG);
		//mur
		mure_jeu = BitmapFactory.decodeResource(getResources(), R.drawable.mur);
		mure_jeu=getResizedBitmap(mure_jeu, Parametre.HAUTEUR_MUR, Parametre.LARGEUR_MUR);
		//arrivée
		ball_arrivee= BitmapFactory.decodeResource(getResources(), R.drawable.cible);
		ball_arrivee=getResizedBitmap(ball_arrivee, Parametre.HAUTEUR_ARRIVE, Parametre.LARGEUR_ARRIVE);
		//obstacle
		obstacle=BitmapFactory.decodeResource(getResources(), R.drawable.danger);
		obstacle=getResizedBitmap(obstacle, Parametre.HAUTEUR_OBSTACLE, Parametre.LARGEUR_OBSTACLE);
		labyrinthe=new Labyrinthe(mure_jeu, obstacle, ball_arrivee);
		labyrinthe.Chargement(activity);
		//balle depart
		ball_depart= BitmapFactory.decodeResource(getResources(), R.drawable.balle);
		ball_depart=getResizedBitmap(ball_depart, Parametre.HAUTEUR_BALLE, Parametre.LARGEUR_BALLE);
		departBalle=new Balle(ball_depart);

		holder = getHolder();
		holder.addCallback(this);
		Thdessin=new DessinThread(this);
		objets =new ArrayList<Objet>();
		objets=labyrinthe.getObjets();
		sm = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
		sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//
		mx=60;
		my=60;
		mySensorListener=new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event)
			{
				// TODO Auto-generated method stub
				x=event.values[0];
				y=event.values[1];
				z=event.values[2];
				//balle
				mx-=(int)x;
				my+=(int)y;
				if (mx<40)
				{
					mx=40;
				}
				if (mx>1190)
				{
					mx=1190;
				}
				if (my<50)
				{
					my=50;
				}
				if (my>700)
				{
					my=700;
				}
	
					//departBalle
	
				departBalle.setPos_x(mx);
				departBalle.setPos_y(my);
			System.out.println(""+mx+":"+my);
				


			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub

			}

		};
		sm.registerListener(mySensorListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) 
	{


	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		Thdessin.setDessine(true);
		Thdessin.start();
		departBalle.setPos_x(160);
		departBalle.setPos_y(my);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		Thdessin.setDessine(false);
		boolean ressai = true;
		while (ressai) {
			try {
				Thdessin.join();
				ressai = false;
			} catch (InterruptedException e) {}
		}
	}

	@Override
	public void draw(Canvas canvas)
	{
		// TODO Auto-generated method stub
		super.draw(canvas);
		canvas.drawBitmap(Backgroud,null,new Rect(0, 0, Backgroud.getWidth(), Backgroud.getHeight()),null);
		labyrinthe.DessinInterface(canvas);
		departBalle.draw(canvas);

	}

	//fonction qui creée une bitmap
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}

	public void update() 
	{
		for(int i=0;i<objets.size();i++)
		{
			if (objets.get(i).getImage()==Parametre.MUR)
			{
				//departBalle.deplacement(objets.get(i));
			}
		}

	}
	// Arrête le capteur
	public void stop() {
		sm.unregisterListener(mySensorListener, sensor);
	}

	// Redémarre le capteur
	public void resume() {
		sm.registerListener(mySensorListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
	}

}

