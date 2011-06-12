package bmc.game.level;
    
import java.util.ArrayList;

import android.graphics.RectF;

public class Level {
    private ArrayList<Path>         paths;
    private ArrayList<Entrance>     entrances;
    
    public enum CollisionStates
    {
        NONE,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        TOPANDLEFT,
        TOPANDRIGHT,
        BOTTOMANDLEFT,
        BOTTOMANDRIGHT
    }
    public Level()
    {
        paths = new ArrayList<Path>();
        entrances = new ArrayList<Entrance>();
    }
    
    public void AddPath (Path path)
    {
        paths.add(path);
    }
    
    public void AddEntrance (Entrance entrance)
    {
        entrances.add(entrance);
    }
    
    public CollisionStates IsCollidingWithLevel(RectF rect)
    {
        // We have the bounding rectangle for the object in question. Now
        // we should check all of the blocks in our paths to see if they intersect in any direction.
        CollisionStates state = CollisionStates.NONE;
        boolean top = false, bottom = false, left = false, right = false;
        for (Path p : paths)
        {
            ArrayList<Block> blocks = p.getBlocks();
            
            for (Block b : blocks)
            {
                // Bounds for top and bottom
                if ((rect.left >= b.getXpos() || rect.right <= b.getXpos() + b.getWidth()))
                {
                    // Top check
                    if (rect.top < b.getYpos() + b.getHeight())
                    {
                        top = true;
                    }
                    
                    if (rect.bottom > b.getYpos())
                    {
                        bottom = true;
                    }
                }
                
                // Bounds for left and right
                if ((rect.top >= b.getYpos() || rect.bottom <= b.getYpos() + b.getHeight()))
                {
                    // Left check
                    if (rect.left <= b.getXpos() + b.getWidth())
                    {
                        left = true;
                    }
                    // Right check
                    if (rect.right >= b.getXpos())
                    {
                        right = true;
                    }
                }
            }
        }
        
        // Now, merge the booleans
        if (top)
        {
            if (left) { state = CollisionStates.TOPANDLEFT; }
            else if (right) { state = CollisionStates.TOPANDRIGHT; }
            else { state = CollisionStates.TOP; }
        }
        
        else if (bottom)
        {
            if (left) { state = CollisionStates.BOTTOMANDLEFT; }
            else if (right) { state = CollisionStates.BOTTOMANDRIGHT; }
            else { state = CollisionStates.BOTTOM; }
        }
        
        // We already checked for the combined ones, so we can safely use else if's to check for left and right
        else if (left) { state = CollisionStates.LEFT; }
        else if (right) { state = CollisionStates.RIGHT; }
        
        // If it didn't hit any of those, it's already set to NONE.
        return state;
    }
}
