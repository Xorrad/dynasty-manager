package main.util;

import java.util.HashMap;

public class IdMap<T extends IHasId> extends HashMap<Integer, T> {
    public int lastId;

    public IdMap() {
        super();
        this.lastId = 0;
    }

    public int getLastId() {
        return lastId;
    }

    public int getNewId() {
        return ++lastId;
    }

    public void add(T value) {
        int id = this.getNewId();
        value.setId(id);
        super.put(id, value);
    }

    @Override
    public T put(Integer key, T value) {
        if(key > this.lastId) this.lastId = key;
        return super.put(key, value);
    }

    @Override
    public T remove(Object key) {
        if(key instanceof IHasId) {
            if(((IHasId) key).getId() == this.lastId) this.lastId--;
            return super.remove(((IHasId) key).getId());
        }
        else if(key instanceof Integer && (Integer) key == this.lastId) this.lastId--;
        return super.remove(key);
    }
}
