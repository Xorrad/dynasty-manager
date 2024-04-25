package main.core.world;

import main.core.world.character.Character;
import main.core.world.dynasty.Dynasty;
import main.core.world.dynasty.House;
import main.util.IdMap;

import java.util.HashMap;

public class World {

    public HashMap<WorldObject.Type, IdMap<WorldObject>> objects;

    public World() {
        this.initObjects();
        this.initDynasties();
    }

    public void addObject(WorldObject object) {
        WorldObject.Type type = WorldObject.Type.get(object.getClass());
        if(type == null)
            throw new RuntimeException("world object does not have registered type.");
        this.objects.get(type).add(object);
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
}
