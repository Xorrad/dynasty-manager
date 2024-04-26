package main.util;

public class Vector2<T> {
    private T x;
    private T y;

    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vector2))
            return false;
        Vector2 v2 = (Vector2) obj;
        return v2.getX() == this.x && v2.getY() == this.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
