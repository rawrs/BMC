package bmc.game.gameobjects;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;

public class Missile extends GameObject {
	final float speed = 4;
	private float rotation;
	private Player player;
	
	public Missile(Sprite[] sprites, float x, float y, Player player)
	{
		super(sprites, SpriteLocations.Missile.getLocation());
		float playerX = player.getRect().centerX();
		float playerY = player.getRect().centerY();
		double angle = Math.atan( (playerY-y) / (playerX-x) );
		if( playerX - x < 0 )
			angle += Math.PI;
		rotation = (float)(angle * 180 / Math.PI) + 90f;
		setX(x - mWidth/2f);
		setY(y - mHeight/2f);
		mVelocityX = (float)Math.cos(angle) * speed;
		mVelocityY = (float)Math.sin(angle) * speed;
		this.player = player;
		killHuman = true;
	}
	
	public void animate(long elapsedTime)
	{
		float x = getRect().centerX();
		float y = getRect().centerY();
		float playerX = player.getRect().centerX();
		float playerY = player.getRect().centerY();
		double angle = Math.atan( (playerY-y) / (playerX-x) );
		if( playerX - x < 0 )
			angle += Math.PI;
		rotation = (float)(angle * 180 / Math.PI) + 90f;
		mVelocityX = (float)Math.cos(angle) * speed;
		mVelocityY = (float)Math.sin(angle) * speed;
		addX(mVelocityX);
		addY(mVelocityY);
		mSprites[index].animate(elapsedTime);
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
