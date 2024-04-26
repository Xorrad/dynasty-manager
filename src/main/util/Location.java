package main.util;

import java.util.HashMap;

public class Location extends Vector2<Integer> {
    public Location() {
        super(0, 0);
    }

    public Location(Integer x, Integer y) {
        super(x, y);
    }

    public double distance(Location l2) {
        float xDiff = this.getX() - l2.getX();
        float yDiff = this.getY() - l2.getY();
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }

    public Location getNeighbour(Direction dir) {
        return new Location(this.getX() + dir.getXOffset(), this.getY() + dir.getYOffset());
    }

    public HashMap<Direction, Location> getNeighbours() {
        HashMap<Direction, Location> neighbours = new HashMap<>();
        for(Direction dir : Direction.values()) {
            neighbours.put(dir, this.getNeighbour(dir));
        }
        return neighbours;
    }
}
