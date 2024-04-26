package test;

import main.core.world.World;
import main.core.world.map.Land;
import main.core.world.map.Tile;
import main.core.world.map.TileGroup;
import main.util.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testTile() {
        World world = new World();
        Tile t1 = new Tile(world, new Location(10, 1));
        assertFalse(t1.isGroup());
        assertEquals(new Location(10, 1), t1.getLocation());
        HashMap<Direction, Tile> neighbours = t1.getNeighbours();
        assertEquals(3, neighbours.size());
        assertFalse(neighbours.containsKey(Direction.NORTH_EAST));
        assertFalse(neighbours.containsKey(Direction.EAST));
        assertFalse(neighbours.containsKey(Direction.SOUTH_EAST));
        assertFalse(neighbours.containsKey(Direction.SOUTH));
        assertTrue(neighbours.containsKey(Direction.SOUTH_WEST));
        assertTrue(neighbours.containsKey(Direction.WEST));
        assertTrue(neighbours.containsKey(Direction.NORTH_WEST));
        assertFalse(neighbours.containsKey(Direction.NORTH));
        assertEquals(world.getMap().get(new Location(9, 0)), neighbours.get(Direction.NORTH_WEST));
        assertEquals(world.getMap().get(new Location(9, 1)), neighbours.get(Direction.WEST));
        assertEquals(world.getMap().get(new Location(9, 2)), neighbours.get(Direction.SOUTH_WEST));
    }

    @Test
    public void testTileGroup() {
        World world = new World();

        TileGroup tg1 = new TileGroup(world);
        world.addObject(tg1);
        Tile t1 = world.getMap().get(new Location(1, 1));
        assertTrue(tg1.isGroup());
        assertEquals(0, tg1.getSize());
        assertFalse(tg1.hasLand(t1, false));
        tg1.addLand(t1);
        assertEquals(1, tg1.getSize());
        assertTrue(tg1.hasLand(t1, false));
        assertTrue(tg1.hasLand(t1, true));
        tg1.removeLand(t1);
        assertEquals(0, tg1.getSize());
        assertFalse(tg1.hasLand(t1, false));
        tg1.addLand(t1);

        TileGroup tg2 = new TileGroup(world);
        world.addObject(tg2);
        Tile t2 = world.getMap().get(new Location(2, 1));
        tg2.addLand(t2);
        tg1.addLand(tg2);
        assertEquals(2, tg1.getSize());
        assertFalse(tg1.hasLand(t2, false));
        assertTrue(tg1.hasLand(t2, true));
        assertTrue(tg1.hasLand(tg2, true));
    }
}
