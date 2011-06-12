package bmc.game.gameobjects;

import bmc.game.SpriteLocations;
import android.graphics.Canvas;

public class Debris extends GameObject {

	public Debris(Sprite[] sprites)
	{
		super(sprites, SpriteLocations.Debris.getLocation());
	}
	
}
