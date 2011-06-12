package bmc.game.gameobjects;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;

public class Laser extends GameObject {
	public Laser(Sprite[] sprites)
	{
		super(sprites, SpriteLocations.Laser.getLocation());
		mVelocityX = 3;
		mVelocityY = 3;
	}
	
	public void doDraw(Canvas canvas)
	{
		mSprites[index].doDraw(canvas,mDestination,45);
	}

	public void addVelocityX(double vel)
	{
		return;
	}
	
	public void addVelocityY(double vel)
	{
		return;
	}
}
