package bmc.game;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Canvas;
import bmc.game.gameobjects.GameObject;
import bmc.game.gameobjects.Laser;
import bmc.game.gameobjects.Player;
import bmc.game.gameobjects.Sprite;
import bmc.game.level.Level;
import bmc.game.level.Level.CollisionStates;
import bmc.game.level.LevelManager;
//physics class
public class Physics {
	private Level mLevel;
	private LevelManager mLevelManager;
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private List<GameObject> gameObjectsAdd = new ArrayList<GameObject>();
	private Player mPlayer;
	private Sprite[] mSprites;
	private float gameSpeed = 1;
	private float fall = 1f,jump = -200f,run = 3f,stop = -1f;
	
	public Physics(Sprite[] sprites,LevelManager level)
	{
		mSprites = sprites;
		mPlayer = new Player(sprites);
		mPlayer.setX(100);
		mPlayer.setY(100);

		level.LoadLevels();
		mLevel = level.getLevel(0);
		mLevelManager = level;
		
		gameObjects.add(new Laser(sprites, 200, 200, 300, 400));
	}
	public void logic()
	{
		//if(level.onGround(mPlayer.getDestination){
		//else
		
		
        for (GameObject gameObject : gameObjects) {
        	gameObject.addVelocityY(fall);
        }
		mPlayer.addVelocityY(fall);
		
		CollisionStates collision = mLevel.IsCollidingWithLevel(mPlayer.getRect());
		switch(collision)
		{
			case BOTTOMANDLEFT:
			case BOTTOMANDRIGHT:
			case BOTTOM:
				if(mPlayer.getVelocityX() < 0)
					mPlayer.setVelocityX(0);
				break;
			case TOPANDLEFT:
			case TOPANDRIGHT:
			case TOP:
				if(mPlayer.getVelocityX() > 0)
					mPlayer.setVelocityX(0);
	        	break;
			
		}switch(collision)
		{
		case TOPANDLEFT:
		case BOTTOMANDLEFT:
		case LEFT:
			if(mPlayer.getVelocityY() < 0)
				mPlayer.setVelocityY(0);
			break;
		case BOTTOMANDRIGHT:
		case TOPANDRIGHT:
		case RIGHT:
			if(mPlayer.getVelocityY() > 0)
				mPlayer.setVelocityY(0);
        	break;
		
		}
		
	}
	public void jump()
	{
		mPlayer.addVelocityY(jump);
	}
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
	    synchronized (gameObjects) {
	    	logic();
	        for (GameObject gameObject : gameObjects) {
	        	gameObject.animate(elapsedTime);
	        }
	    }
	    mLevel.animate(elapsedTime, 0, 0);
		mPlayer.animate(elapsedTime);
	}

	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		mLevel.doDraw(canvas);
	    synchronized (gameObjects) {
	        for (GameObject gameObject : gameObjects) {
	        	gameObject. doDraw(canvas);
	        }
                synchronized (gameObjectsAdd) {
	        	for (GameObject gameObject : gameObjectsAdd) {
		        	gameObjects.add(gameObject);
		        }
	        	gameObjectsAdd.clear();
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
