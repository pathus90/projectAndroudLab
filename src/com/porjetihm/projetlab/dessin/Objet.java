package com.porjetihm.projetlab.dessin;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Objet 
{
	protected Bitmap bitmap;
	protected int pos_x,pos_y;
	protected int largeur,hauteur;
	protected int timage;
	
	public Objet()
	{

	}
	public Objet(Bitmap b)
	{
		bitmap =b;
		largeur=bitmap.getWidth();
		hauteur=bitmap.getHeight();
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(bitmap, getPos_x(), getPos_y(),null);
	}
	public abstract int getImage();

}
