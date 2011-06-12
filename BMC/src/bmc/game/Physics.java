package bmc.game;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import bmc.game.gameobjects.GameObject;
import bmc.game.gameobjects.Laser;
import bmc.game.gameobjects.Player;
import bmc.game.gameobjects.Sprite;
import bmc.game.level.Level;
import bmc.game.level.Level.CollisionStates;
//physics class
public class Physics {
	private Level mLevel;
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private Player mPlayer;
	private Sprite[] mSprites;
	private float gameSpeed = 1;
	private float fall = 1f,jump = -200f,run = 3f,stop = -1f;
	
	public Physics(Sprite[] sprites)
	{
		mSprites = sprites;
		mPlayer = new Player(sprites);
		mPlayer.setX(100);
		mPlayer.setY(100);
		
		mLevel = new Level();
		
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
		
		CollisionStates collision = mLevel.IsCollidingWithLevel(mPlayer.getmRect());
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
