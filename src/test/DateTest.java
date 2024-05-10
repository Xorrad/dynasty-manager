package test;

import main.core.world.World;
import main.core.world.map.Tile;
import main.core.world.map.TileGroup;
import main.util.Date;
import main.util.Direction;
import main.util.Location;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    @Test
    public void testDate() {
        Date d1 = new Date(10, 1, 2);
        Date d2 = new Date(10, 5, 1);
        Date d3 = new Date(11, 3, 1);

        assertEquals(10, d1.getYear());
        assertEquals(1, d1.getMonth());
        assertEquals(2, d1.getDay());

        assertEquals(0, d1.getMonthDiff(d1));
        assertEquals(4, d1.getMonthDiff(d2));
        assertEquals(14, d1.getMonthDiff(d3));

        assertEquals(0, d1.getYearDiff(d2));
        assertEquals(1, d1.getYearDiff(d3));
    }
}
