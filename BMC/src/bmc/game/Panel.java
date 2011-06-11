package bmc.game;

import java.util.ArrayList;

import bmc.game.R;
import bmc.game.gameobjects.Sprite;
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



public class Panel extends SurfaceView implements SurfaceHolder.Callback{
	private ArrayList<Element> mElements = new ArrayList<Element>();
	private ViewThread mThread;
	public static float mWidth;
	public static float mHeight;
	private int mElementNumber = 0;
	private Paint mPaint = new Paint();
	private ArrayList<Sprite> mSprites = new ArrayList<Sprite>();
	 
	public Panel(Context context) {
	    super(context);
	    Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.run);
	    getHolder().addCallback(this);
	    mThread = new ViewThread(this);
	    mPaint.setColor(Color.WHITE);
	    Sprite sprite =new Sprite( mBitmap,46, 42,1,100,100); 
	    mSprites.add(sprite);
	}
 
	public void doDraw(long elapsed,Canvas canvas) {
		canvas.drawColor(Color.BLACK);
	    synchronized (mSprites) {
	        for (Sprite sprite : mSprites) {
	        	sprite.doDraw(canvas);
	        }
	    }
	    canvas.drawText("FPS: " + Math.round(1000f / elapsed) + " Sprite: " + mSprites.size(), 10, 10, mPaint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {

	    return super.onTouchEvent(event);
	}
	public void animate(long elapsedTime) {
	    synchronized (mSprites) {
	        for (Sprite Sprite : mSprites) {
	        	Sprite.animate(elapsedTime);
	        }
	    }
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
	    mWidth = width;
	    mHeight = height;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!mThread.isAlive()) {
	        mThread = new ViewThread(this);
	        mThread.setRunning(true);
	        mThread.start();
	    }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mThread.isAlive()) {
	        mThread.setRunning(false);
	    }
		
	}
}
