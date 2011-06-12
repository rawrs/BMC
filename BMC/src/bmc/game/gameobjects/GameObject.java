package bmc.game.gameobjects;

import bmc.game.PlayerState;
import bmc.game.SpriteLocations;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
//game objects
abstract public class GameObject{
    protected Rect mDestination = new Rect();
    protected RectF mRect = new RectF();

	protected int mWidth;
	protected int mHeight;
	protected Sprite[] mSprites;
	protected float mVelocityX;
	protected float maxVelocityX = 2,minVelocityX = -2;
	protected float mVelocityY;
	protected float maxVelocityY = 2,minVelocityY = -20;
	protected int index = 0;
	
	public GameObject(Sprite[] sprites)
	{
		mSprites = sprites;
		Sprite current = sprites[0];
		mHeight = (current.getHeight());
		mWidth = (current.getWidth());
	}
	public GameObject(Sprite[] sprites, int location)
	{
		mSprites = sprites;
		index = location;
		Sprite current = sprites[location];
		mHeight = (current.getHeight());
		mWidth = (current.getWidth());
	}
	public void animate(long elapsedTime)
	{
		this.addX(mVelocityX);
		this.addY(mVelocityY);
		mSprites[index].animate(elapsedTime);
	}
	public void doDraw(Canvas canvas)
	{
		mSprites[index].doDraw(canvas,mDestination);
	}
	
	public void move(int X, int Y)
	{
		this.addX(X);
		this.addY(Y);
	}
    
    public void addVelocityX(double vel)
    {
    	mVelocityX += vel;
    	if(mVelocityX < maxVelocityX)
    		mVelocityX = maxVelocityX;
    	if(mVelocityX > maxVelocityX)
    		mVelocityX = maxVelocityX;
    }
    public double getVelocityX()
    {
    	return mVelocityX;
    }
    public void setVelocityX(float vel)
    {
    	mVelocityX = vel;
    }
    public void addVelocityY(double vel)
    {
    	mVelocityY += vel;
    	if(mVelocityY < minVelocityY)
    		mVelocityY = minVelocityY;
    	if(mVelocityY > maxVelocityY)
    		mVelocityY = maxVelocityY;
    }
    public void setVelocityY(float vel)
    {
    	mVelocityY = vel;
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
	public RectF getmRect() {
		return mRect;
	}
	public void setmRect(RectF mRect) {
		this.mRect = mRect;
	}
	
}
