package main.core.world.map.title;

import main.core.world.World;
import main.core.world.map.Land;

public class LandTitle extends Title {
    private Land land;

    public LandTitle(World world, Land land) {
        super(world);
        this.land = land;
    }

    public Land getLand() {
        return land;
    }

    @Override
    public String getName() {
        return "Title of " + this.land.getName();
    }

    @Override
    public Type getType() {
        return Title.Type.LAND;
    }
}
