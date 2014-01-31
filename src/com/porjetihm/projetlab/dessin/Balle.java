package com.porjetihm.projetlab.dessin;

import android.graphics.Bitmap;

public class Balle extends Objet 
{

	public Balle(Bitmap b) {
		super(b);

		// TODO Auto-generated constructor stub
	}
	@Override
	public int getImage() {
		// TODO Auto-generated method stub
		return Parametre.BALLE;
	}

	public boolean Colision(Objet o)
	{
		boolean trouve=true;
		//Les cotes des rectangles
		int GaucheA, leftB;
		int DroiteA, rightB;
		int HautA, topB;
		int BasA, bottomB;

		//Calcul les cotes du rectangle A
		GaucheA = getPos_x();
		DroiteA = getPos_x()+getLargeur();
		HautA = getPos_y();
		BasA = getPos_y() + getHauteur();

		//Calcul les cotes du rectangle B
		leftB = o.getPos_x();
		rightB = o.getPos_x() + o.getLargeur();
		topB = o.getPos_y();
		bottomB = o.getPos_y() + o.getHauteur();

		if (( DroiteA>leftB))
		{
			//Une collision est detectee
			trouve= true;
		}
		if ((GaucheA<rightB))
		{
			//Une collision est detectee
			trouve= true;
		}
		if ((HautA<bottomB))
		{
			trouve=true;
		}
		if ((BasA>topB))
		{
			trouve=true;
		}
		return trouve;
	}
	public void deplacement (Objet o)
	{
		if (Colision(o))
		{
			if (this.getPos_x()+this.getLargeur()>o.getPos_x())
			{
				
			}
		}
	}
}
