package bmc.game.gameobjects;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;

public class Laser extends GameObject {
	final float speed = 3;
	float rotation;
	
	public Laser(Sprite[] sprites, int x1, int y1, int x2, int y2)
	{
		super(sprites, SpriteLocations.Laser.getLocation());
		double angle = Math.atan( (y2-y1) / (x2-x1) );
		if( x2 - x1 < 0 )
			angle += Math.PI;
		rotation = (float)(angle * 180 / Math.PI);
		setX(x1);
		setY(y1);
		mVelocityX = (float)Math.cos(angle) * speed;
		mVelocityY = (float)Math.sin(angle) * speed;
	}

	@Override
	public void doDraw(Canvas canvas)
	{
		mSprites[index].doDraw(canvas, mDestination, rotation);
	}
	@Override
	public void addVelocityX(double vel)
	{
		return;
	}

	@Override
	public void addVelocityY(double vel)
	{
		return;
	}
}
