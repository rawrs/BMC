package bmc.game.gameobjects;

import android.graphics.Canvas;
import bmc.game.PlayerState;
//player class
public class Player extends GameObject{
	private float velocityJump = 0; 
	public Player(Sprite[] sprites)
	{
		super(sprites);
		// TODO Auto-generated constructor stub
	}
	private PlayerState mPlayerState = PlayerState.Falling;
	@Override
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		//mSprites[mPlayerState.getmState()].animate(elapsedTime);
		this.addX(mVelocityX);
		this.addY(mVelocityY+velocityJump);
		if(mVelocityY < 0)
			mPlayerState = PlayerState.Jumping;
		else if (mVelocityX != 0)
			mPlayerState = PlayerState.Running;
		else 
			mPlayerState = PlayerState.Falling;
		mSprites[mPlayerState.getmState()].animate(elapsedTime);
	}
	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//mSprites.get(mPlayerState.getmState()).doDraw(canvas,mDestination);
		mSprites[mPlayerState.getmState()].doDraw(canvas,mDestination);
	}
	@Override
	public void move(int X, int Y) {
		// TODO Auto-generated method stub
		this.addX(X);
		this.addY(Y);
		
	}
	public PlayerState getmPlayerState() {
		return mPlayerState;
	}
	public void setmPlayerState(PlayerState mPlayerState) 
	{
		if(this.mPlayerState != mPlayerState)
		{
			//TODO add stuff for changing animation 
		}
		this.mPlayerState = mPlayerState;
	}
	public float getVelocityJump() {
		return velocityJump;
	}
	public void setVelocityJump(float velocityJump) {
		this.velocityJump = velocityJump;
	}
	
	
}
