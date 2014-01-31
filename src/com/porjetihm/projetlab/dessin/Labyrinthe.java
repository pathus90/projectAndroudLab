package com.porjetihm.projetlab.dessin;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Labyrinthe 
{
	//liste mur
	ArrayList<Objet>Listes_objets;
	//images
	private Bitmap mur;
	private Bitmap obstacle;
	private Bitmap arrive;
	
	static int tab_labyrithe[][];	


	public Labyrinthe(Bitmap mur, Bitmap obstacle, Bitmap arrive)
	{
		super();
		this.mur = mur;
		this.obstacle = obstacle;
		this.arrive = arrive;
		this.Listes_objets=new ArrayList<Objet>();
	}


	/*
	 * methode qui charger le fichier d'interface
	 */
	void Chargement(Activity a)
	{
		BufferedReader fichier = null;

		try {

			fichier =new BufferedReader( new InputStreamReader (a.getResources().getAssets().open("niveau.txt"), "UTF-8")); 
			String chaine;
			int i=0;
			while (((chaine = fichier.readLine()) != null)&&(i<Parametre.LIGNE ))
			{
				String []ligne=chaine.split(",");
				for (int j = 0; j <Parametre.COLONE; j++)
				{
					if(Integer.parseInt(ligne[j]) == Parametre.MUR)
					{
						Mur m=new Mur(mur);
						m.setPos_x(j*m.getLargeur());
						m.setPos_y(i*m.getHauteur());
						Listes_objets.add(m);	
					}
					else if (Integer.parseInt(ligne[j]) == Parametre.OBSTACLE)
					{
						Obstacle o=new Obstacle(obstacle);
						o.setPos_x(j*o.getLargeur());
						o.setPos_y(i*o.getHauteur());
						Listes_objets.add(o);
					}
					else if (Integer.parseInt(ligne[j]) == Parametre.ARRIVE)
					{
						Arrivee arr = new Arrivee(arrive);
						arr.setPos_x(j*arr.getLargeur());
						arr.setPos_y(i*arr.getHauteur());
						Listes_objets.add(arr);
					}
					
				}
				i++;
			}
		} catch (Exception e) {

		} finally {
			closeStream(fichier);
		}

	}

	/*
	 * dessin de l'interface
	 */
	public void DessinInterface(Canvas canvas)
	{
		for (int i=0;i<Listes_objets.size();i++)
		{
			Listes_objets.get(i).draw(canvas);
		}
	}

	private void closeStream(Closeable fichier) {
		if (fichier != null) {
			try {
				fichier.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}


	public ArrayList<Objet> getObjets() {
		// TODO Auto-generated method stub
		return this.Listes_objets;
	}
}
