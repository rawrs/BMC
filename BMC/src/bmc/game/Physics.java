package bmc.game;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import bmc.game.gameobjects.GameObject;
import bmc.game.gameobjects.Player;
import bmc.game.gameobjects.Sprite;
import bmc.game.level.Level;

public class Physics {
	private Level mLevel;
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private Player mPlayer;
	private Sprite[] mSprites;
	public Physics(Sprite[] sprites)
	{
		mSprites = sprites;
		mPlayer = new Player(sprites);
		mPlayer.setX(100);
		mPlayer.setY(100);
	}
	public void logic()
	{
		
	}
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		logic();
	    synchronized (gameObjects) {
	        for (GameObject gameObject : gameObjects) {
	        	gameObject.animate(elapsedTime);
	        }
	    }
		mPlayer.animate(elapsedTime);
	}

	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub

	    synchronized (gameObjects) {
	        for (GameObject gameObject : gameObjects) {
	        	gameObject. doDraw(canvas);
	        }
	    }
	    mPlayer.doDraw(canvas);
	}

	public Level getmLevel() 
	{
		return mLevel;
	}

	public void setmLevel(Level mLevel) 
	{
		this.mLevel = mLevel;
	}
}
