package main.core.world.dynasty;

import main.core.world.World;
import main.core.world.WorldObject;
import main.core.world.character.Character;

import java.util.ArrayList;
import java.util.List;

public class House extends WorldObject {
    private String name;
    private Dynasty dynasty;
    private ArrayList<Character> members;

    public House(World world, Dynasty dynasty) {
        super(world);
        this.name = "";
        this.dynasty = dynasty;
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dynasty getDynasty() {
        return dynasty;
    }

    public void setDynasty(Dynasty dynasty) {
        this.dynasty = dynasty;
    }

    public void switchDynasty(Dynasty dynasty) {
        this.dynasty.removeHouse(this);
        this.dynasty = dynasty;
        this.dynasty.addHouse(this);
    }

    public List<Character> getMembers() {
        return members.stream().toList();
    }

    public void removeMember(Character character) {
        this.members.remove(character);
    }

    public void addMember(Character character) {
        this.members.add(character);
    }

    public static class Builder {
        private House object;

        public Builder(World world, Dynasty dynasty) {
            this.object = new House(world, dynasty);
            world.addObject(this.object);
        }

        public House.Builder name(String name) {
            this.object.name = name;
            return this;
        }

        public House.Builder addMember(Character character) {
            this.object.addMember(character);
            character.switchHouse(this.object);
            return this;
        }

        public House get() {
            return this.object;
        }
    }
}
