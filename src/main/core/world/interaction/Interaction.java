package main.core.world.interaction;

public abstract class Interaction<T> {
    public abstract String getName();
    public abstract String getText(T t);
    public abstract boolean isShowable(T t);
    public abstract boolean isAppliable(T t);
    public abstract void apply(T t);
}
