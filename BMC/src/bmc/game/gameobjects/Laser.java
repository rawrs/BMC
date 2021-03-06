package bmc.game.gameobjects;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;

public class Laser extends GameObject {
	final float speed = 3;
	float rotation;
	
	public Laser(Sprite[] sprites, float x1, float y1, float x2, float y2)
	{
		super(sprites, SpriteLocations.Laser.getLocation());
		double angle = Math.atan( (y2-y1) / (x2-x1) );
		if( x2 - x1 < 0 )
			angle += Math.PI;
		rotation = (float)(angle * 180 / Math.PI);
		setX(x1 - mWidth/2f);
		setY(y1 - mHeight/2f);
		mVelocityX = (float)Math.cos(angle) * speed;
		mVelocityY = (float)Math.sin(angle) * speed;
		killHuman = true;
	}

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
