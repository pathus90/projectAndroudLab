package com.porjetihm.projetlab.dessin;

public interface Parametre {
	int MUR=0;
	int BALLE = 1;
	int OBSTACLE=3;
	int ARRIVE=4;

	int LIGNE=20;
	int COLONE=32;
	
	int LARGEUR_BG = 1280;
	int HAUTEUR_BG = 800;
	
	int LARGEUR_MUR = 40;
	int HAUTEUR_MUR = 50;
	
	int LARGEUR_ARRIVE = 40;
	int HAUTEUR_ARRIVE = 50;
	
	int LARGEUR_OBSTACLE = 50;
	int HAUTEUR_OBSTACLE = 50;
	
	int LARGEUR_BALLE = 50;
	int HAUTEUR_BALLE = 50;
	
	int DIRECTION_DROITE= 1;
	int DIRECTION_GAUCHE= -1;
	int DIRECTION_HAUT	= -1;
	int DIRECTION_BAS	= 1;
	
}
