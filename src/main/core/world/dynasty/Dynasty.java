package main.core.world.dynasty;

import main.core.world.World;
import main.core.world.WorldObject;

import java.util.ArrayList;

public class Dynasty extends WorldObject {
    private String name;
    private ArrayList<House> houses;

    public Dynasty(World world) {
        super(world);
        this.name = "";
        this.houses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public void addHouse(House house) {
        this.houses.add(house);
    }

    public void removeHouse(House house) {
        this.houses.remove(house);
    }

    public static class Builder {
        private Dynasty object;

        public Builder(World world) {
            this.object = new Dynasty(world);
            world.addObject(this.object);
        }

        public Dynasty.Builder name(String name) {
            this.object.name = name;
            return this;
        }

        public Dynasty.Builder addHouse(House house) {
            this.object.houses.add(house);
            house.setDynasty(this.object);
            return this;
        }

        public Dynasty get() {
            return this.object;
        }
    }
}
