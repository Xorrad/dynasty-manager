package main.core.world.map;

import main.core.world.World;
import main.util.IdMap;

import java.util.HashMap;
import java.util.List;

public class TileGroup extends Land {
    private HashMap<Integer, Land> contents;

    public TileGroup(World world) {
        super(world);
        this.contents = new HashMap<>();
    }

    public HashMap<Integer, Land> getLands() {
        return this.contents;
    }

    public void addLand(Land land) {
        this.contents.put(land.getId(), land);
    }

    // Returns true if the land has been removed, false if it wasn't in the group.
    public boolean removeLand(Land land) {
        // If direct child, then remove from hashmap.
        if(this.contents.containsKey(land.getId())) {
            this.contents.remove(land);
            return true;
        }

        // If undirect child, then remove recursively.
        for(Land l : this.contents.values()) {
            if(!l.isGroup())
                continue;
            TileGroup tileGroup = (TileGroup) l;
            if(tileGroup.removeLand(land))
                return true;
        }
        return false;
    }

    @Override
    public boolean hasLand(Land land, boolean deep) {
        if(this.contents.containsKey(land.getId()))
            return true;
        if(!deep)
            return false;
        for(Land l : this.contents.values()) {
            if(l.hasLand(l, true))
                return true;
        }
        return false;
    }

    @Override
    public boolean isGroup() {
        return true;
    }

    @Override
    public String getName() {
        return "tilegroup " + this.getId();
    }
}
