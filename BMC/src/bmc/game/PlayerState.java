package bmc.game;

public enum PlayerState {
	Start(0),Running(1),Falling(2),Dead(3);
	private int mState;
	PlayerState(int state)
	{
		this.mState = state;
	}
	public int getmState() {
		return mState;
	}
	public void setmState(int mState) {
		this.mState = mState;
	}
	
}
