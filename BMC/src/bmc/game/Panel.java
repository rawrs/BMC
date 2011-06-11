package bmc.game;


import bmc.game.R;
import bmc.game.gameobjects.Sprite;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class Panel extends SurfaceView implements SurfaceHolder.Callback{
	
	private ViewThread mThread;
	public static float mWidth;
	public static float mHeight;
	private Paint mPaint = new Paint();
	private Sprite[] mSprites = new Sprite[SpriteLocations.values().length];
	private Physics mPhysics;
	 
	public Panel(Context context) {
	    super(context);
	    getHolder().addCallback(this);
	    mThread = new ViewThread(this);
	    
	    
	    mPaint.setColor(Color.WHITE);
	    getSprites();
	    mPhysics = new Physics(mSprites);
	    
	}
	public void getSprites()
	{
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.run);
	    Sprite sprite =new Sprite( mBitmap,46, 42,1); 
	    mSprites[SpriteLocations.Player.getLocation()] = sprite;
	}
	public void doDraw(long elapsed,Canvas canvas) 
	{
		canvas.drawColor(Color.BLACK);
	    mPhysics.doDraw(canvas);
	    canvas.drawText("FPS: " + Math.round(1000f / elapsed), 10, 10, mPaint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{

	    return super.onTouchEvent(event);
	}
	public void animate(long elapsedTime) 
	{
	    mPhysics.animate(elapsedTime);
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
}
