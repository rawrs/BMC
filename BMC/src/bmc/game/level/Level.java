package bmc.game.level;
    
import java.util.ArrayList;

public class Level {
    private ArrayList<Path>         paths;
    private ArrayList<Entrance>     entrances;
    
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
}
