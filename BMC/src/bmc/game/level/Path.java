package bmc.game.level;

import java.util.ArrayList;

import bmc.game.gameobjects.GameObject;

public class Path {
    private String                  name;
    private int                     distance;
    private boolean                 isVertical;
    private ArrayList<Block>        blocks;
    private ArrayList<GameObject>   objects;
    
    public Path(String name,
                 int distance,
                 boolean isVertical) 
    {
        this.name = name;
        this.distance = distance;
        this.isVertical = isVertical;
        blocks = new ArrayList<Block>();
        objects = new ArrayList<GameObject>();
    }
    
    public void AddBlock(Block block)
    {
        blocks.add(block);
    }
    
    public void AddObject(GameObject object)
    {
        objects.add(object);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean isVertical) {
        this.isVertical = isVertical;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
}
