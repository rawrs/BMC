package bmc.game.level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import bmc.game.R;
import bmc.game.SpriteLocations;
import bmc.game.gameobjects.GameObject;
import bmc.game.gameobjects.LaserGun;
import bmc.game.gameobjects.Player;
import bmc.game.gameobjects.Sprite;

///////////////////////////////////////////////////////////////////////////////////////////////////
// Class LevelManager
//
// This class takes the XML file and manages the level objects. It then serves up the level
// requested by the player.
///////////////////////////////////////////////////////////////////////////////////////////////////
public class LevelManager {
    private ArrayList<Level> levels;
    private Resources resources;
    private Sprite[] sprites;
    
    public LevelManager(Resources res,Sprite[] sprite) 
    {
        resources = res;
        levels = new ArrayList<Level>();
        sprites = sprite;
    }
    public Level getLevel(int id)
    {
    	return levels.get(id);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Function LoadLevels
    // This opens the file and loads the levels
    // 
    // Input:   None
    // Output:  0 on successful reading of at least one level
    //          -1 on improper XML
    //          -2 on no levels properly loaded
    //
    // TODO: Refactor later. This is awful
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public int LoadLevels()
    {
        try
        {
            XmlResourceParser parser = resources.getXml(R.xml.levels);
            
            // Stores the current state of the parsing
            parser.next();
            int event = parser.getEventType();
            
            // TODO: Include check for XML tag. that's what it's on now. Although there is the problem
            // of parser.getName seemingly not grabbing anything...
            
            parser.next();
            event = parser.getEventType();
            
            // Make sure our first tag is a start 'game' tag. If not, it is bad XML
            if (event == XmlPullParser.START_TAG && !parser.getName().contentEquals("game"))
            {
                Log.e("LevelManager.LoadLevels", "Improperly formed XML, missing <game> tag!");
                return -1;
            }
            
            // Move to next
            parser.next();
            event = parser.getEventType();
            
            // Read until end of document
            while (event != XmlPullParser.END_TAG || !parser.getName().contentEquals("game"))
            {
                // See if we have a level. If we do, begin processing.
                if (event == XmlPullParser.START_TAG && parser.getName().contentEquals("level"))
                {
                    
                    ArrayList<Path> paths = new ArrayList<Path>();
                    ArrayList<Entrance> entrances = new ArrayList<Entrance>();
                    
                    // Next line...
                    parser.next();
                    event = parser.getEventType();
                    
                    // Now we're grabbing the data. Algorithm:
                    // 1. Grab next line and event.
                    // 2. Make sure it's a start tag. If so, set the data and look for its end tag.
                    // 3. Rinse and repeat until we find the level end tag
                    while (event != XmlPullParser.END_TAG || !parser.getName().contentEquals("level"))
                    {
                        
                        
                        if (event == XmlPullParser.START_TAG)
                        {
                            // We should look for path, entrance, or object tags
                            if (parser.getName().contentEquals("path"))
                            {
                                // Next line...
                                parser.next();
                                event = parser.getEventType();
                                
                                // Required variables to set up a path
                                int distance = 0;
                                String name = "";
                                boolean isVertical = false;
                                ArrayList<Block> blocks = new ArrayList<Block>();
                                ArrayList<GameObject> objects = new ArrayList<GameObject>();
                                boolean hasStartPoint = false;
                                int startX = 0, startY = 0;
                                
                                // Look for items in the path tag until we find the end tag for it.
                                while (event != XmlPullParser.END_TAG || !parser.getName().contentEquals("path"))
                                {
                                    if (parser.getName().contentEquals("start"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        hasStartPoint = true;
                                        
                                        // Now we have to look for the x and y position
                                        while (event != XmlPullParser.END_TAG || !parser.getName().contentEquals("start"))
                                        {
                                            if (parser.getName().contentEquals("xpos"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                startX = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("xpos") || event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed xpos tag for " +
                                                            "start point. Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("ypos"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                startY = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("ypos") || event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed ypos tag for " +
                                                            "start point. Exiting!");
                                                    return -1;
                                                }
                                            }
                                            
                                            parser.next();
                                            event = parser.getEventType();
                                        }
                                    }
                                    else if (parser.getName().contentEquals("distance"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        distance = Integer.parseInt(parser.getText());
                                        
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        if (!parser.getName().contentEquals("distance") || event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed distance tag. Exiting!");
                                            return -1;
                                        }
                                    }
                                    else if (parser.getName().contentEquals("name"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        name = parser.getText();
                                        
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        if (!parser.getName().contentEquals("name") || event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed name tag. Exiting!");
                                            return -1;
                                        }
                                    }
                                    else if (parser.getName().contentEquals("isvertical"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        isVertical = Boolean.parseBoolean(parser.getText());
                                        
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        if (!parser.getName().contentEquals("isvertical") || event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed isVertical tag. Exiting!");
                                            return -1;
                                        }
                                    }
                                    else if (parser.getName().contentEquals("object"))
                                    {
                                        // Type, path, width, height, xpos, ypos
                                        // Next line...
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        String type = "";
                                        int width = 0, height = 0, xpos = 0, ypos = 0;
                                        
                                        // Look for items in the object tag until we find the end tag for it.
                                        while (event != XmlPullParser.END_TAG || !parser.getName().contentEquals("object"))
                                        {
                                            if (parser.getName().contentEquals("width"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                width = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("width") || 
                                                        event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed width tag in object. " +
                                                            "Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("height"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                height = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("height") || 
                                                        event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed height tag in object. " +
                                                            "Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("xpos"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                xpos = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("xpos") || 
                                                        event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed xpos tag in object. " +
                                                            "Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("ypos"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                ypos = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("ypos") || 
                                                        event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed ypos tag in object. " +
                                                            "Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("type"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                type = parser.getText();
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("type") || 
                                                        event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed type tag in object. " +
                                                            "Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else
                                            {
                                                Log.e("LevelManager.LoadLevels", "Error: unknown tag in object element. " +
                                                        "Exiting!");
                                                return -1;
                                            }
                                            
                                            parser.next();
                                            event = parser.getEventType();
                                        }
                                        
                                        // Add the object
                                        // TODO: Add the rest of the types
                                        if (type.contentEquals("Platform"))
                                        {
                                            Block platform = new Block(width, height, xpos, ypos, sprites, 
                                                    SpriteLocations.ground.getLocation());
                                            
                                            blocks.add(platform);
                                        }
                                        else if (type.contentEquals("LaserGun"))
                                        {
                                            // Create a temp player and list of game objects. These will be
                                            // set later in physics
                                            Player tempPlayer = new Player(sprites);
                                            List<GameObject> tempObjects = new ArrayList<GameObject>();
                                            LaserGun laser = new LaserGun(sprites, xpos, ypos, tempPlayer, tempObjects);
                                            objects.add(laser);
                                        }
                                    }
                                    else if (parser.getName().contentEquals("block"))
                                    {
                                        // Go to next tag. We need to parse the block now.
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        // Now check the inner block tags. Looking for width, height, xpos, and ypos
                                        int width = 0, height = 0, xpos = 0, ypos = 0;
                                        while (event != XmlPullParser.END_TAG || !parser.getName().contentEquals("block"))
                                        {
                                            if (parser.getName().contentEquals("width"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                width = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("width") || event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed width tag. Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("height"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                height = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("height") || event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed height tag. Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("xpos"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                xpos = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("xpos") || event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed xpos tag. Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else if (parser.getName().contentEquals("ypos"))
                                            {
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                ypos = Integer.parseInt(parser.getText());
                                                
                                                parser.next();
                                                event = parser.getEventType();
                                                
                                                if (!parser.getName().contentEquals("ypos") || event != XmlPullParser.END_TAG)
                                                {
                                                    Log.e("LevelManager.LoadLevels", "Error: malformed ypos tag. Exiting!");
                                                    return -1;
                                                }
                                            }
                                            else
                                            {
                                                Log.e("LevelManager.LoadLevels", "Error: unknown tag in block element. " +
                                                        "Exiting!");
                                                return -1;
                                            }
                                            
                                            parser.next();
                                            event = parser.getEventType();
                                        }
                                        
                                        if (!parser.getName().contentEquals("block") || event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed block tag. Exiting!");
                                            return -1;
                                        }
                                        
                                        // Add the block to the array list
                                        Block newBlock = new Block(width, height, xpos, ypos,sprites,SpriteLocations.ground.getLocation());
                                        blocks.add(newBlock);
                                    }
                                    else
                                    {
                                        Log.e("LevelManager.LoadLevels", "Error: unknown tag in level element. Exiting!");
                                        return -1;
                                    }

                                    // Next line...
                                    parser.next();
                                    event = parser.getEventType();
                                }
                                
                                // Create the path
                                Path newPath = new Path(name, distance, isVertical);
                                for (Block b : blocks)
                                {
                                    newPath.AddBlock(b);
                                }
                                
                                // Set start stuff
                                newPath.setHasStartPoint(hasStartPoint);
                                newPath.setStartX(startX);
                                newPath.setStartY(startY);
                                
                                // Add the path
                                paths.add(newPath);
                                
                                // Next line...
                                parser.next();
                                event = parser.getEventType();
                            }
                            if (parser.getName().contentEquals("entrance"))
                            {
                                // Next line...
                                parser.next();
                                event = parser.getEventType();
                                
                                String from = "", to = "", direction = "";
                                int width = 0;
                                
                                // Look for items in the entrance tag until we find the end tag for it.
                                while (event != XmlPullParser.END_TAG || !parser.getName().contentEquals("entrance"))
                                {
                                    if (parser.getName().contentEquals("from"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        from = parser.getText();
                                        
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        if (!parser.getName().contentEquals("from") || event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed from tag. Exiting!");
                                            return -1;
                                        }
                                    }
                                    else if (parser.getName().contentEquals("to"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        to = parser.getText();
                                        
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        if (!parser.getName().contentEquals("to") || event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed to tag. Exiting!");
                                            return -1;
                                        }
                                    }
                                    else if (parser.getName().contentEquals("direction"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        direction = parser.getText();
                                        
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        if (!parser.getName().contentEquals("direction") || 
                                                event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed direction tag. " +
                                                    "Exiting!");
                                            return -1;
                                        }
                                    }
                                    else if (parser.getName().contentEquals("width"))
                                    {
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        width = Integer.parseInt(parser.getText());
                                        
                                        parser.next();
                                        event = parser.getEventType();
                                        
                                        if (!parser.getName().contentEquals("width") || 
                                                event != XmlPullParser.END_TAG)
                                        {
                                            Log.e("LevelManager.LoadLevels", "Error: malformed width tag. " +
                                                    "Exiting!");
                                            return -1;
                                        }
                                    }
                                    else
                                    {
                                        Log.e("LevelManager.LoadLevels", "Error: unknown tag in entrance element. " +
                                                "Exiting!");
                                        return -1;
                                    }
                                    
                                    parser.next();
                                    event = parser.getEventType();
                                }
                                
                                // Add the entrance
                                Entrance entrance = new Entrance(from, to, direction, width);
                                entrances.add(entrance);
                                
                                // Next line...
                                parser.next();
                                event = parser.getEventType();
                            }
                            
                           
                        }
                        
                        // If it isn't a start tag, we don't need to worry, so we can continue
                    }
                    
                    // Add the level
                    Level newLevel = new Level();
                    
                    for (Path p : paths)
                    {
                        newLevel.AddPath(p);
                    }
                    
                    for (Entrance e : entrances)
                    {
                        newLevel.AddEntrance(e);
                    }
                    
                    levels.add(newLevel);
                }
                
                // Otherwise, we can just move on. Nothing needs to happen, it's bad
                // XML, and we can not load anything.
                parser.next();
                event = parser.getEventType();
            }
        }
        catch (XmlPullParserException xppe)
        {
            Log.e("LevelManager.LoadLevels", "XmlPullParserException reading levels XML file! Error: " +
                    xppe.getMessage() + ", " + xppe.getLineNumber());
        }
        catch (IOException ioe)
        {
            Log.e("LevelManager.LoadLevels", "IO exception reading levels XML file! Error: " + ioe.getMessage());
        }
        catch(Exception e)
        {
        	Log.e("LevelManager.LoadLevels",e.getMessage());
        }
        
        return 0;
    }
}
