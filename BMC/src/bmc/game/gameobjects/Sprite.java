package bmc.game.gameobjects;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Sprite {
    private double mSpeed;
    private double mIndex;
    private int mHeight,mWidth;
    private int numIndexes,currentIndex;
    private Rect mSource =  new Rect();
 
    private Bitmap mBitmap;
     
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
 
    public void animate(long elapsedTime) 
    {
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
    public void doDraw(Canvas canvas,Rect destination) 
    {
        canvas.drawBitmap(mBitmap, mSource, destination, null);
    }
    
    public void doDraw(Canvas canvas, Rect destination, int degrees)
    {
    	Matrix rotate = new Matrix();
    	rotate.postRotate(degrees);
    	rotate.postTranslate(destination.left, destination.top);
    	canvas.drawBitmap(mBitmap, rotate, null);
    }

	public double getSpeed() {
		return mSpeed;
	}

	public void setSpeed(double mSpeed) {
		this.mSpeed = mSpeed;
	}

	public int getIndex() {
		return currentIndex;
	}

	public int getHeight()
	{
		return mHeight;
	}

	public void setHeight(int mHeight)
	{
		this.mHeight = mHeight;
	}

	public int getWidth()
	{
		return mWidth;
	}

	public void setWidth(int mWidth)
	{
		this.mWidth = mWidth;
	}
	
}
