package bmc.game.gameobjects;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

abstract public class GameObject{
    protected Rect mDestination = new Rect();
    protected RectF mRect = new RectF();

	protected int mWidth;
	protected int mHeight;
	protected Sprite[] mSprites;
	protected float mVelocityX;
	protected float maxVelocityX = 2,minVelocityX = -2;
	protected float mVelocityY;
	protected float maxVelocityY = 2,minVelocityY = -2;
	public GameObject(Sprite[] sprites)
	{
		mSprites = sprites;
		Sprite current = sprites[SpriteLocations.Player.getLocation()];
		mHeight = (current.getHeight());
		mWidth = (current.getWidth());
	}
	public GameObject(Sprite[] sprites,int location)
	{
		mSprites = sprites;
		Sprite current = sprites[location];
		mHeight = (current.getHeight());
		mWidth = (current.getWidth());
	}
	abstract public void animate(long elapsedTime);
    abstract public void doDraw(Canvas canvas);
    abstract public void move(int X,int Y);
    
    public void addVelocityX(double vel)
    {
    	mVelocityX += vel;
    	if(mVelocityX < maxVelocityX)
    		mVelocityX = maxVelocityX;
    	if(mVelocityX > maxVelocityX)
    		mVelocityX = maxVelocityX;
    }
    public double getVelocity()
    {
    	return mVelocityX;
    }
    public void addVelocityY(double vel)
    {
    	mVelocityY += vel;
    	if(mVelocityY < minVelocityY)
    		mVelocityY = minVelocityY;
    	if(mVelocityY > maxVelocityY)
    		mVelocityY = maxVelocityY;
    }
    public double getVelocityY()
    {
    	return mVelocityY;
    }
    
    public void addX(float X)
    {
    	setX(mRect.left+X);
    }
    public void addY(float Y)
    {
    	setY(mRect.top+Y);
    }

	public float getX() {
		return mRect.left;
	}

	public void setX(float mX) {
		this.mRect.left = mX;
		mRect.right = mRect.left+mWidth;
		
		this.mDestination.left = (int)mX;
		mDestination.right = mDestination.left+mWidth;
	}

	public float getY() {
		return mRect.top;
	}

	public void setY(float mY) {
		this.mRect.top = mY;
		mRect.bottom = mRect.top+mHeight;
		
		this.mDestination.top = (int)mY;
		mDestination.bottom = mDestination.top+mHeight;
	}
    public Rect getDestination() {
		return mDestination;
	}
	public void setDestination(Rect mDestination) {
		this.mDestination = mDestination;
	}
	
}
