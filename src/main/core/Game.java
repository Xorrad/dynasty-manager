package main.core;

import main.core.world.World;

public class Game {
    private World activeWorld;

    public Game() {
        this.activeWorld = null;
    }

    public World getActiveWorld() {
        return this.activeWorld;
    }

    public String getVersion() {
        return "0.1";
    }
}
