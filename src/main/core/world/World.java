package main.core.world;

import main.core.world.character.Character;
import main.core.world.dynasty.Dynasty;
import main.core.world.dynasty.House;
import main.core.world.map.Tile;
import main.util.IdMap;
import main.util.Location;
import main.util.Vector2;

import java.util.HashMap;

public class World {
    public static final int DEFAULT_MAP_WIDTH = 10;
    public static final int DEFAULT_MAP_HEIGHT = 10;

    public HashMap<WorldObject.Type, IdMap<WorldObject>> objects;
    public HashMap<Location, Tile> map;

    public World() {
        this.initObjects();
        this.initDynasties();
        this.initMap();
    }

    public void addObject(WorldObject object) {
        WorldObject.Type type = WorldObject.Type.get(object.getClass());
        if(type == null)
            throw new RuntimeException("world object does not have registered type.");
        this.objects.get(type).add(object);
    }

    public void removeObject(WorldObject object) {
        WorldObject.Type type = WorldObject.Type.get(object.getClass());
        if(type == null)
            throw new RuntimeException("world object does not have registered type.");
        this.objects.get(type).remove(object);
    }

    public <T extends WorldObject> IdMap<T> getObjects(WorldObject.Type type) {
        return (IdMap<T>) this.objects.get(type);
    }

    public IdMap<Character> getCharacters() {
        return this.<Character>getObjects(WorldObject.Type.CHARACTER);
    }

    public House getDefaultHouse() {
        return (House) this.objects.get(WorldObject.Type.HOUSE).get(1);
    }

    public HashMap<Location, Tile> getMap() {
        return map;
    }

    private void initObjects() {
        this.objects = new HashMap<>();
        for(WorldObject.Type type : WorldObject.Type.values()) {
            this.objects.put(type, new IdMap<>());
        }
    }

    private void initDynasties() {
        // Create the lowborn dynasty and house.
        // NB: WorldObject are already added to world in builder constructor.
        Dynasty lowbornDynasty = new Dynasty.Builder(this).name("Lowborn").get();
        House lowbornHouse = new House.Builder(this, lowbornDynasty).name("Lowborn").get();
    }

    private void initMap() {
        // Initialize the map tiles.
        this.map = new HashMap<>();

        for(int y = 0; y < DEFAULT_MAP_HEIGHT; y++) {
            for(int x = 0; x < DEFAULT_MAP_WIDTH; x++) {
                Location location = new Location(x, y);
                Tile tile = new Tile(this, location);
                this.addObject(tile);
                this.map.put(location, tile);
            }
        }
    }
}
