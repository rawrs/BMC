package bmc.game.gameobjects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
//game objects
abstract public class GameObject{
    protected Rect mDestination = new Rect();
    protected RectF mRect = new RectF();
    protected Rect mapPosition = new Rect();

	protected int mWidth;
	protected int mHeight;
	protected Sprite[] mSprites;
	protected float mVelocityX;
	protected float maxVelocityX = 200,minVelocityX = -200;
	protected float mVelocityY;
	protected float maxVelocityY = 20,minVelocityY = -200;
	protected int index = 0;
	protected boolean killHuman = false;
	
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
    public boolean shouldDraw(Rect rectangle)
    {
    	return Rect.intersects(mapPosition,rectangle);
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
	public void setX(float mX,float width) {
		this.mRect.left = mX;
		mRect.right = mRect.left+width;
		
		this.mDestination.left = (int)mX;
		mDestination.right = (int) (mDestination.left+width);
	}


	public float getY() {
		return mRect.top;
	}

	public void setY(float mY,float height) {
		this.mRect.top = mY;
		mRect.bottom = mRect.top+height;
		
		this.mDestination.top = (int)mY;
		mDestination.bottom = (int) (mDestination.top+height);
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
	public RectF getRect() {
		return mRect;
	}
	public void setRect(RectF mRect) {
		this.mRect = mRect;
	}
	public Rect getMapPostion() 
	{
		return mapPosition;
	}
	
}
