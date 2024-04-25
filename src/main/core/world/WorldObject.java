package main.core.world;

import main.core.world.character.Character;
import main.util.IHasId;

import java.util.HashMap;

public abstract class WorldObject implements IHasId {

    // Check https://stackoverflow.com/a/12222656 to support modding.
    public enum Type {
        CHARACTER(Character.class),
        /*LAND,
        CLAIM,
        SECRET,
        TITLE*/;

        private Class<? extends WorldObject> targetClass;

        Type(Class<? extends WorldObject> targetClass) {
            this.targetClass = targetClass;
        }

        public Class<? extends WorldObject> getTargetClass() {
            return targetClass;
        }
    }

    private World world;
    private int id;

    public WorldObject(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
