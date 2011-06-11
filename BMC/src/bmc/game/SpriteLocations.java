package bmc.game;

public enum SpriteLocations 
{
	Player(0);
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
