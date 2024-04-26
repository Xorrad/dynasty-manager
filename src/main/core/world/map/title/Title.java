package main.core.world.map.title;

import main.core.world.World;
import main.core.world.WorldObject;

public abstract class Title extends WorldObject {
    public Title(World world) {
        super(world);
    }

    public abstract String getName();
    public abstract Type getType();

    public enum Type {
        LAND, // A title on a land, de jure or not.
        HONORIFIC, // No duties... (i.e Breaker of Chains)
        POSITION, // A rank within a structure or organisation. (i.e Marshall, Diplomat...)
        ;
    }
}
