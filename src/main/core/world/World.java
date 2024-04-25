package main.core.world;

import main.core.world.character.Character;
import main.util.IdMap;

import java.util.HashMap;

public class World {

    public HashMap<WorldObject.Type, IdMap<WorldObject>> objects;

    public World() {
        this.initObjects();
    }

    public <T extends WorldObject> IdMap<T> getObjects(WorldObject.Type type) {
        return (IdMap<T>) this.objects.get(type);
    }

    public IdMap<Character> getCharacters() {
        return this.<Character>getObjects(WorldObject.Type.CHARACTER);
    }

    private void initObjects() {
        this.objects = new HashMap<>();
        for(WorldObject.Type type : WorldObject.Type.values()) {
            this.objects.put(type, new IdMap<>());
        }
    }
}
