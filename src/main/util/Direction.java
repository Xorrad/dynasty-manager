package main.util;

public enum Direction {
    NORTH_WEST(-1, -1),
    NORTH(0, -1),
    NORTH_EAST(1, -1),
    EAST(1, 0),
    SOUTH_EAST(1, 1),
    SOUTH(0, 1),
    SOUTH_WEST(-1, 1),
    WEST(-1, 0);

    private int xOffset;
    private int yOffset;

    Direction(int x, int y) {
        this.xOffset = x;
        this.yOffset = y;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
