package main.core.world.map;

import main.core.world.World;
import main.util.Direction;
import main.util.Location;
import main.util.Vector2;

import java.util.HashMap;

public class Tile extends Land {
    private Location location;

    public Tile(World world, Location location) {
        super(world);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public Tile getNeighbour(Direction dir) {
        Location neighbourLocation = this.location.getNeighbour(dir);
        if(!this.getWorld().getMap().containsKey(neighbourLocation))
            return null;
        return this.getWorld().getMap().get(neighbourLocation);
    }

    public HashMap<Direction, Tile> getNeighbours() {
        HashMap<Direction, Tile> neighbours = new HashMap<>();
        HashMap<Direction, Location> neighboursLocation = this.location.getNeighbours();

        for(Direction dir : neighboursLocation.keySet()) {
            if(!this.getWorld().getMap().containsKey(neighboursLocation.get(dir)))
                continue;
            neighbours.put(dir, this.getWorld().getMap().get(neighboursLocation.get(dir)));
        }

        return neighbours;
    }

    @Override
    public boolean hasLand(Land land, boolean deep) {
        return this.equals(land);
    }

    @Override
    public boolean isGroup() {
        return false;
    }

    @Override
    public String getName() {
        return "tile " + this.location;
    }
}
