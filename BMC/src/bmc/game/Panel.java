package bmc.game;


import bmc.game.R;
import bmc.game.gameobjects.Sprite;
import bmc.game.level.LevelManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


//panel class
public class Panel extends SurfaceView implements SurfaceHolder.Callback{
	public enum GameState
	{
		start,running,end;
	}
	private static ViewThread mThread;
	public static float mWidth;
	public static float mHeight;
	private static Paint mPaint = new Paint();
	private static Sprite[] mSprites = new Sprite[SpriteLocations.values().length];
	private static Physics mPhysics;
	public static float points = 0;
	private static Boolean paused = false;
	private static GameState gameState = GameState.start;
	private static float sinceEnd = 0;
	 
	public Panel(Context context) {
	    super(context);
	    getHolder().addCallback(this);
	    mThread = new ViewThread(this);
	    
	    if(mPhysics == null)
	    {
		    mPaint.setColor(Color.WHITE);
		    getSprites();
		    Resources res = getResources();
		    LevelManager level = new LevelManager(getResources(),mSprites);
		    mPhysics = new Physics(mSprites,level);
	    }
	    else
	    {
	    	ViewThread.mStartTime = System.currentTimeMillis();
	    }
	    
	}
	public void getSprites()
	{
		
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.run);
	    Sprite sprite =new Sprite( mBitmap,47, 40,1); 
	    mSprites[SpriteLocations.PlayerRun.getLocation()] = sprite;
	    
	    Bitmap mBitmapPlayerFall = BitmapFactory.decodeResource(getResources(), R.drawable.fall);
	    Sprite playerFall =new Sprite( mBitmapPlayerFall,45, 40,1); 
	    mSprites[SpriteLocations.PlayerFall.getLocation()] = playerFall;
	    
	    Bitmap mBitmapPlayerJump = BitmapFactory.decodeResource(getResources(), R.drawable.jumps);
	    Sprite playerJump =new Sprite( mBitmapPlayerJump,59, 38,1); 
	    mSprites[SpriteLocations.PlayerJump.getLocation()] = playerJump;
	    
	    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.debris);
	    sprite =new Sprite( mBitmap,40, 40,1); 
	    mSprites[SpriteLocations.Debris.getLocation()] = sprite;
	    
	    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.laser);
	    sprite =new Sprite( mBitmap,5, 40,1); 
	    mSprites[SpriteLocations.Laser.getLocation()] = sprite;
	    
	    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
	    sprite =new Sprite( mBitmap,5, 40,1); 
	    mSprites[SpriteLocations.ground.getLocation()] = sprite;
	    
	    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.missile);
	    sprite =new Sprite( mBitmap,97, 24,1); 
	    mSprites[SpriteLocations.Missile.getLocation()] = sprite;
	}
	public void doDraw(long elapsed,Canvas canvas) 
	{
		canvas.drawColor(Color.BLACK);
		switch(gameState)
		{
		case start:
			canvas.drawText("Click to start", mWidth /2-mWidth/4, mHeight/2, mPaint);
			break;
		case end:
			canvas.drawText("You scored "+points+" points", mWidth /2-mWidth/4, mHeight/2, mPaint);
			break;
		case running:
			canvas.drawColor(Color.BLACK);
		    mPhysics.doDraw(canvas);
		    points += elapsed*10;
		    canvas.drawText("Points: " + Math.round(points), 10, 10, mPaint);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		switch(gameState)
		{
		case start:
			gameState = gameState.running;
			break;
		case end:
			switchFromEndToStart();
			break;
		case running:
			if(!paused)
			{
				mPhysics.jump();
			}
		}
	    return super.onTouchEvent(event);
	}
	public void animate(long elapsedTime) 
	{
		switch(gameState)
		{
		case start:
			break;
		case end:
			sinceEnd += elapsedTime;
			if(sinceEnd > 5000)
				switchFromEndToStart();
			break;
		case running:
			if(!paused)
			{
				mPhysics.animate(elapsedTime);
			}
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
	{
		// TODO Auto-generated method stub
	    mWidth = width;
	    mHeight = height;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		if (!mThread.isAlive()) 
		{
	        mThread = new ViewThread(this);
	        mThread.setRunning(true);
	        mThread.start();
	    }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		if (mThread.isAlive()) {
	        mThread.setRunning(false);
	    }
		
	}
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		//add pause timer
		gameState = GameState.start;
		super.onWindowFocusChanged(hasWindowFocus);
	}
	public static void end()
	{
		gameState = GameState.end;
		sinceEnd = 0;
	}
	private void switchFromEndToStart()
	{
		gameState = GameState.start;
		points = 0;
	}
	
}
