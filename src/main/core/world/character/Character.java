package main.core.world.character;

import main.core.world.WorldObject;
import main.core.world.World;
import main.core.world.character.relations.Relation;
import main.core.world.dynasty.Dynasty;
import main.core.world.dynasty.House;
import main.core.world.title.Title;
import main.util.Location;

import java.util.ArrayList;
import java.util.List;

public class Character extends WorldObject {
    private String name;
    private House house;
    private ArrayList<Relation> relations;
    private ArrayList<Title> titles;
    private Location location;

    public Character(World world) {
        super(world);
        this.house = world.getDefaultHouse();
        this.relations = new ArrayList<>();
        this.titles = new ArrayList<>();
        this.location = new Location(0, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public House getHouse() {
        return house;
    }

    public Dynasty getDynasty() {
        return this.house.getDynasty();
    }

    public void setHouse(House house) {
        // Character house can never be null, it will always, at least, be the lowborn house.
        this.house = (house == null) ? this.getWorld().getDefaultHouse() : house;
    }

    public void switchHouse(House house) {
        this.house.removeMember(this);
        this.setHouse(house);
        this.house.addMember(this);
    }

    public void leaveHouse() {
        // Switch to lowborn/default house of the world.
        this.switchHouse(null);
    }

    public void addRelation(Relation relation) {
        this.relations.add(relation);
    }

    public boolean hasRelation(Class<? extends Relation> clazz) {
        return this.relations.stream()
                .filter(r -> r.getClass() == clazz)
                .findFirst()
                .isPresent();
    }

    public boolean hasRelation(Class<? extends Relation> clazz, Character character) {
        return this.relations.stream()
                .filter(r -> (r.getClass() == clazz && r.isPartOf(character)))
                .findFirst()
                .isPresent();
    }

    public Relation getRelation(Class<? extends Relation> clazz) {
        return this.relations.stream()
                .filter(r -> r.getClass() == clazz)
                .findFirst()
                .orElseGet(() -> null);
    }

    public List<Relation> getCharacterRelations(Character character) {
        return this.relations.stream().filter(r -> r.isPartOf(character)).toList();
    }

    public List<Relation> getRelations() {
        return this.relations.stream().toList();
    }

    public void addTitle(Title title) {
        this.titles.add(title);
    }

    public void removeTitle(Title title) {
        this.titles.remove(title);
    }

    public boolean hasTitle(Title title) {
        return this.titles.contains(title);
    }

    public List<Title> getTitles() {
        return this.titles.stream().toList();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static class Builder {
        private Character character;

        public Builder(World world) {
            this.character = new Character(world);
            world.addObject(this.character);
        }

        public Character.Builder name(String name) {
            this.character.name = name;
            return this;
        }

        public Character.Builder house(House house) {
            this.character.switchHouse(house);
            return this;
        }

        public Character.Builder location(Location location) {
            this.character.location = location;
            return this;
        }

        public Character get() {
            return this.character;
        }
    }
}