package test;

import main.util.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    @Test
    public void testVector2() {
        int x = (int) (System.currentTimeMillis() % 10000);
        int y = (int) (System.currentTimeMillis() % 10000);
        Vector2<Integer> v1 = new Vector2<>(x, y);
        assertEquals(v1.getX(), x);
        assertEquals(v1.getY(), y);
        v1.setX(x-1);
        v1.setY(y-1);
        assertEquals(v1.getX(), x-1);
        assertEquals(v1.getY(), y-1);

        double x2 = Math.random();
        double y2 = Math.random();
        Vector2<Double> v2 = new Vector2<>(x2, y2);
        assertEquals(v2.getX(), x2);
        assertEquals(v2.getY(), y2);
        v2.setX(x2-0.5);
        v2.setY(y2-0.5);
        assertEquals(v2.getX(), x2-0.5);
        assertEquals(v2.getY(), y2-0.5);
    }

    @Test
    public void testLocation() {
        Location l1 = new Location(0, 0);
        Location l2 = new Location(10, 0);
        Location l3 = new Location(10, 10);

        assertEquals(l1.distance(l2), 10);
        assertEquals(l1.distance(l2), l2.distance(l1));
        assertEquals(l1.distance(l3), Math.sqrt(200));

        HashMap<Direction, Location> neighbours1 = l1.getNeighbours();
        assertEquals(neighbours1.size(), Direction.values().length);
        neighbours1.forEach((dir, l) -> {
            assertEquals(l, new Location(l1.getX()+dir.getXOffset(), l1.getY()+dir.getYOffset()));
        });
    }

    @Test
    public void testIdMap() {
        IdMap<IHasId> map = new IdMap<>();
        for(int i = 0; i < 5; i++) {
            IHasId v = new IHasId() {
                private int id;
                @Override
                public int getId() { return this.id; }
                @Override
                public void setId(int id) { this.id = id; }
            };

            map.add(v);
            assertEquals(map.size(), i+1);
            assertEquals(map.get(i+1), v);
            assertEquals(v.getId(), i+1);
        }

        IHasId v1 = new IHasId() {
            private int id;
            @Override
            public int getId() { return this.id; }
            @Override
            public void setId(int id) { this.id = id; }
        };
        assertEquals(map.getLastId(), 5);
        map.add(v1);
        assertEquals(map.getLastId(), 6);
        map.remove(v1);
        assertEquals(map.getLastId(), 5);
        assertEquals(map.size(), 5);
        assertFalse(map.containsKey(v1.getId()));
    }

}
