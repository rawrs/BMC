package bmc.game.gameobjects;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import bmc.game.R;

public class Sprite {
    private double mSpeed;
    private double mIndex;
    private int mHeight,mWidth;
    private int numIndexes,currentIndex;
    private Rect mSource =  new Rect();
    private Rect mDestination = new Rect();
 
    private Bitmap mBitmap;
    
    public Sprite(Bitmap res, int height, int width,double speed,int X,int Y)
    {
    	this(res,height,width,speed);
    	mDestination.left = X;
    	mDestination.top = Y;
    	mDestination.bottom = Y+height;
    	mDestination.right = X+width;
    }
 
    public Sprite(Bitmap res, int height, int width,double speed) 
    {
        mBitmap = res;
        mIndex = 0;
        currentIndex = 0;
        numIndexes = (int) Math.floor(mBitmap.getWidth()/width);
        mSpeed = speed;
        mHeight = height;
        mWidth = width;
        setSourceRect();
    }
 
    public void animate(long elapsedTime) {
        //mX += mSpeedX * (elapsedTime / 20f);
        //mY += mSpeedY * (elapsedTime / 20f);
    	mIndex += mSpeed;
    	if(mIndex >= numIndexes)
    		mIndex = 0;
    	if(Math.floor(mIndex) != currentIndex)
    	{
    		currentIndex =(int)Math.floor(mIndex); 
    		setSourceRect();
    	}
    }
    private void setSourceRect()
    {
    	mSource.left = mWidth*currentIndex;
    	mSource.top = 0;
    	mSource.bottom = mHeight;
    	mSource.right = mWidth*(currentIndex+1);
    }
    public void doDraw(Canvas canvas) {
        //canvas.drawBitmap(mBitmap, new Rect(0,0,50,50), new Rect(10,10,50,50), null);
        canvas.drawBitmap(mBitmap, mSource, mDestination, null);
    }
    public void addX(int X)
    {
    	mDestination.left = mDestination.left+X;
    	mDestination.right = mDestination.left+mWidth;
    }
    public void addY(int Y)
    {
    	mDestination.top = mDestination.top+Y;
    	mDestination.bottom = mDestination.top+mHeight;
    }

	public int getmX() {
		return mDestination.left;
	}

	public void setmX(int mX) {
		this.mDestination.left = mX;
	}

	public int getmY() {
		return mDestination.top;
	}

	public void setmY(int mY) {
		this.mDestination.top = mY;
	}

	public double getmSpeed() {
		return mSpeed;
	}

	public void setmSpeed(double mSpeed) {
		this.mSpeed = mSpeed;
	}

	public int getmIndex() {
		return currentIndex;
	}
}
