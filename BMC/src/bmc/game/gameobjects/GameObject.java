package bmc.game.gameobjects;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;
import android.graphics.Rect;


abstract public class GameObject{
    protected Rect mDestination = new Rect();
    protected int mWidth;
	protected int mHeight;
	protected Sprite[] mSprites;
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
    public void addX(int X)
    {
    	setX(mDestination.left+X);
    }
    public void addY(int Y)
    {
    	setX(mDestination.top+Y);
    }

	public int getX() {
		return mDestination.left;
	}

	public void setX(int mX) {
		this.mDestination.left = mX;
    	mDestination.right = mDestination.left+mWidth;
	}

	public int getY() {
		return mDestination.top;
	}

	public void setY(int mY) {
		this.mDestination.top = mY;
    	mDestination.bottom = mDestination.top+mHeight;
	}
}
