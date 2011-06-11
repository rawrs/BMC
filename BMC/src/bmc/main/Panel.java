package bmc.main;

import java.util.ArrayList;

import bmc.main.R;
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
	private ArrayList<Element> mElements = new ArrayList<Element>();
	private ViewThread mThread;
	public static float mWidth;
	public static float mHeight;
	private int mElementNumber = 0;
	private Paint mPaint = new Paint();
	 
	public Panel(Context context) {
	    super(context);
	    //mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
	    getHolder().addCallback(this);
	    mThread = new ViewThread(this);
	    mPaint.setColor(Color.WHITE);
	}
 
	public void doDraw(long elapsed,Canvas canvas) {
		canvas.drawColor(Color.BLACK);
	    synchronized (mElements) {
	        for (Element element : mElements) {
	            element.doDraw(canvas);
	        }
	    }
	    canvas.drawText("FPS: " + Math.round(1000f / elapsed) + " Elements: " + mElementNumber, 10, 10, mPaint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		synchronized (mElements) {
	        mElements.add(new Element(getResources(), (int) event.getX(), (int) event.getY()));
	        mElementNumber = mElements.size();
	    }
	    return super.onTouchEvent(event);
	}
	public void animate(long elapsedTime) {
	    synchronized (mElements) {
	        for (Element element : mElements) {
	            element.animate(elapsedTime);
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
