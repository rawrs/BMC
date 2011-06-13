package bmc.game.gameobjects;

import java.util.List;

import bmc.game.SpriteLocations;

import android.graphics.Canvas;

public class MissileLauncher extends LaserGun {
	public MissileLauncher(Sprite[] sprites, float x, float y, Player player, List<GameObject> gameObjects)
	{
		super(sprites,x,y,player,gameObjects,SpriteLocations.Missile.getLocation());
	}

	public void animate(long elapsedTime)
	{
		lastShot += elapsedTime;
		if(lastShot > 3000)
		{
			synchronized (gameObjects)
			{
				gameObjects.add(new Missile(sprites, getRect().centerX(), getRect().centerY(), player));
				lastShot = 0;
			}
		}
		float x = getX();
		float y = getY();
		float playerX = player.getRect().centerX();
		float playerY = player.getRect().centerY();
		double angle = Math.atan( (playerY-y) / (playerX-x) );
		if( playerX - x < 0 )
			angle += Math.PI;
		rotation = (float)(angle * 180 / Math.PI) + 90f;
		mSprites[index].animate(elapsedTime);
	}
}
