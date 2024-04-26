
package main.core.world.map;

import main.core.world.World;
import main.core.world.WorldObject;

public abstract class Land extends WorldObject {
    public Land(World world) {
        super(world);
    }

    public abstract boolean hasLand(Land land, boolean deep);
    public abstract boolean isGroup();
    public abstract String getName();
}
