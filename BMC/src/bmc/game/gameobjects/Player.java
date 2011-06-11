package bmc.game.gameobjects;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import bmc.game.PlayerState;

public class Player extends GameObject{
	public Player(Sprite[] sprites)
	{
		super(sprites);
		// TODO Auto-generated constructor stub
	}
	private PlayerState mPlayerState = PlayerState.Start;
	@Override
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		//mSprites[mPlayerState.getmState()].animate(elapsedTime);
		mSprites[0].animate(elapsedTime);
	}
	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//mSprites.get(mPlayerState.getmState()).doDraw(canvas,mDestination);
		mSprites[0].doDraw(canvas,mDestination);
	}
	@Override
	public void move(int X, int Y) {
		// TODO Auto-generated method stub
		addX(X);
		addY(Y);
		
	}
	public PlayerState getmPlayerState() {
		return mPlayerState;
	}
	public void setmPlayerState(PlayerState mPlayerState) 
	{
		if(this.mPlayerState != mPlayerState)
		{
			
		}
		this.mPlayerState = mPlayerState;
	}
	
}
