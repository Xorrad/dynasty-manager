package test;

import main.core.world.World;
import main.core.world.WorldObject;
import main.core.world.character.Character;
import main.core.world.map.Tile;
import main.core.world.map.TileGroup;
import main.util.Direction;
import main.util.IdMap;
import main.util.Location;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {
    @Test
    public void testWorldObject() {
        World world = new World(null);
        Character c1 = new Character(world);
        assertEquals(world, c1.getWorld());
        assertEquals(0, world.getObjects(WorldObject.Type.CHARACTER).size());
        world.addObject(c1);
        IdMap<Character> characters = world.getObjects(WorldObject.Type.CHARACTER);
        assertTrue(characters.containsValue(c1));
        assertEquals(c1, characters.get(c1.getId()));
        world.removeObject(c1);
        assertEquals(0, world.getObjects(WorldObject.Type.CHARACTER).size());
    }
}
