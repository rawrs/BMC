package bmc.game.level;

import bmc.game.gameobjects.GameObject;
import bmc.game.gameobjects.Sprite;
import android.graphics.Rect;
    
public class Block extends GameObject{
    public Block(int width,
                 int height,
                 int xpos,
                 int ypos,Sprite[] sprites, int location) {
    	super( sprites, location);
        this.mWidth = width;
        this.mHeight = height;
        setX(xpos);
        setY(ypos);
        mapPosition = new Rect(xpos,ypos,xpos+width,ypos+height); 
        
    }
	@Override
	public void addVelocityX(double vel)
	{
		return;
	}

	@Override
	public void addVelocityY(double vel)
	{
		return;
	}
    
    
    public int getWidth() {
        return mWidth;
    }


    public int getHeight() {
        return mHeight;
    }

    public int getXpos() {
        return mDestination.left;
    }

    public int getYpos() {
        return mDestination.top;
    }
}
