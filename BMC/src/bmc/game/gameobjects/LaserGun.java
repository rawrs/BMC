package bmc.game.gameobjects;

import java.util.List;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;

public class LaserGun extends GameObject {
	private float rotation;
	private Player player;
	private long lastShot = 0;
	private List<GameObject> gameObjects;
	Sprite[] sprites;
	
	public LaserGun(Sprite[] sprites, float x, float y, Player player, List<GameObject> gameObjects)
	{
		super(sprites, SpriteLocations.Laser.getLocation());
		mVelocityX = 0;
		mVelocityY = 0;
		float playerX = player.getDestination().centerX();
		float playerY = player.getDestination().centerY();
		double angle = Math.atan( (playerY-y) / (playerX-x) );
		if( playerX - x < 0 )
			angle += Math.PI;
		rotation = (float)(angle * 180 / Math.PI);
		setX(x - mWidth/2f);
		setY(y - mHeight/2f);
		this.player = player;
		this.gameObjects = gameObjects;
		this.sprites = sprites;
	}
	
	public void doDraw(Canvas canvas)
	{
		mSprites[index].doDraw(canvas, mDestination, rotation);
	}
	
	public void animate(long elapsedTime)
	{
		lastShot += elapsedTime;
		if(lastShot > 3000)
		{
			synchronized (gameObjects)
			{
				gameObjects.add(new Laser(sprites, getDestination().centerX(), getDestination().centerY(), player.getDestination().centerX(), player.getDestination().centerY()));
				lastShot = 0;
			}
		}
		float x = getX();
		float y = getY();
		float playerX = player.getDestination().centerX();
		float playerY = player.getDestination().centerY();
		double angle = Math.atan( (playerY-y) / (playerX-x) );
		if( playerX - x < 0 )
			angle += Math.PI;
		rotation = (float)(angle * 180 / Math.PI);
		mSprites[index].animate(elapsedTime);
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
