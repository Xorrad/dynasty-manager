package main.core.world;

import main.core.world.character.Character;
import main.core.world.dynasty.Dynasty;
import main.core.world.dynasty.House;
import main.core.world.map.Land;
import main.core.world.map.Tile;
import main.core.world.map.TileGroup;
import main.core.world.title.Title;
import main.util.IHasId;

import java.util.HashSet;

public abstract class WorldObject implements IHasId {

    // Check https://stackoverflow.com/a/12222656 to support modding.
    public enum Type {
        CHARACTER(Character.class),
        DYNASTY(Dynasty.class),
        HOUSE(House.class),
        LAND(Land.class, Tile.class, TileGroup.class),
        TITLE(Title.class)
        /*LAND,
        CLAIM,
        SECRET,
        TITLE*/;

        private HashSet<Class<? extends WorldObject>> targetClasses;

        Type(Class<? extends WorldObject>... targetClasses) {
            this.targetClasses = new HashSet<>();
            for(Class<? extends WorldObject> targetClass : targetClasses)
                this.targetClasses.add(targetClass);
        }

        public HashSet<Class<? extends WorldObject>> getTargetClasses() {
            return this.targetClasses;
        }

        public static Type get(Class<? extends WorldObject> clazz) {
            for(Type type : Type.values()) {
                if(type.getTargetClasses().contains(clazz))
                    return type;
            }
            return null;
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
