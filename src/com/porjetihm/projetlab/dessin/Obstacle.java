package com.porjetihm.projetlab.dessin;

import android.graphics.Bitmap;

public class Obstacle extends Objet {

	public Obstacle(Bitmap b) {
		super(b);
	
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getImage() {
		// TODO Auto-generated method stub
		return Parametre.OBSTACLE;
	}

}
