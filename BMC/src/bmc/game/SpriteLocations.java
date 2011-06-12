package bmc.game;

public enum SpriteLocations 
{
	PlayerRun(0),PlayerJump(1),PlayerFall(2);
	private int mLocation;
	SpriteLocations(int location)
	{
		mLocation = location;
	}
	public int getLocation() {
		return mLocation;
	}
	public void setLocation(int mLocation) {
		this.mLocation = mLocation;
	}
}
