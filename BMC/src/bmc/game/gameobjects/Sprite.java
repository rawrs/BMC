package bmc.game.gameobjects;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import bmc.game.R;

public class Sprite {
    private int mSpeed;
    private double mIndex;
    private int mHeight,mWidth;
    private int numIndexes,currentIndex;
    private Rect mSource;
    private Rect mDestination;
 
    private Bitmap mBitmap;
    
    public Sprite(Resources res, int height, int width,int speed,int X,int Y)
    {
    	this(res,height,width,speed);
    	mDestination.left = X;
    	mDestination.top = Y;
    	mDestination.bottom = Y-height;
    	mDestination.right = X+width;
    }
 
    public Sprite(Resources res, int height, int width,int speed) 
    {
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.icon);
        mIndex = 0;
        currentIndex = 0;
        setSourceRect();
        numIndexes = (int) Math.floor(mBitmap.getWidth()/width);
        mSpeed = speed;
        mHeight = height;
        mWidth = width;
    }
 
    public void animate(long elapsedTime) {
        //mX += mSpeedX * (elapsedTime / 20f);
        //mY += mSpeedY * (elapsedTime / 20f);
    	mIndex += mSpeed;
    	if(mIndex > numIndexes)
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
    	mDestination.bottom = mDestination.top-mHeight;
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

	public int getmSpeed() {
		return mSpeed;
	}

	public void setmSpeed(int mSpeed) {
		this.mSpeed = mSpeed;
	}

	public int getmIndex() {
		return currentIndex;
	}
}
