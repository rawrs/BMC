package bmc.game.level;

public class Entrance {
    private String  from;
    private String  to;
    private String  direction;
    private int     width;
    
    public Entrance(String from,
                    String to,
                    String direction,
                    int width)
    {
        this.from = from;
        this.to = to;
        this.direction = direction;
        this.width = width;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
