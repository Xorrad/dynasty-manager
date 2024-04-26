package main.core.world.title;

import main.core.world.World;

public class HonorificTitle extends Title {
    private String name;

    public HonorificTitle(World world, String name) {
        super(world);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Type getType() {
        return Type.HONORIFIC;
    }
}
