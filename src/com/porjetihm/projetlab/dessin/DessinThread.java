package com.porjetihm.projetlab.dessin;

import android.graphics.Canvas;

public class DessinThread extends Thread 
{
	private LabyritheView labview;
	private boolean dessine=false;

	public DessinThread(LabyritheView lab)
	{
		this.labview=lab;
	}

	public boolean getDessin()
	{
		return this.dessine;
	}

	public void setDessine(Boolean dessine)
	{
		this.dessine=dessine;
	}
	
	public void run()
	{
		Canvas c=null;
		while (dessine)
		{
			
			
			try
			{
				c=labview.getHolder().lockCanvas();
				synchronized(labview.getHolder())
				{
				//	labview.update();
					labview.draw(c);
				}
			}
			finally
			{
				if (c != null)
					labview.getHolder().unlockCanvasAndPost(c);
			}
			//pour redessiner
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {}
		}
	}

}
