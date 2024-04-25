package main.core.world.character;

import main.core.world.WorldObject;
import main.core.world.World;
import main.core.world.character.relations.Relation;

import java.util.ArrayList;
import java.util.List;

public class Character extends WorldObject {
    private String name;
    private ArrayList<Relation> relations;

    public Character(World world) {
        super(world);
        this.relations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRelation(Relation relation) {
        this.relations.add(relation);
    }

    public boolean hasRelation(Class<? extends Relation> clazz) {
        return relations.stream()
                .filter(r -> r.getClass() == clazz)
                .findFirst()
                .isPresent();
    }

    public boolean hasRelation(Class<? extends Relation> clazz, Character character) {
        return relations.stream()
                .filter(r -> (r.getClass() == clazz && r.isPartOf(character)))
                .findFirst()
                .isPresent();
    }

    public Relation getRelation(Class<? extends Relation> clazz) {
        return relations.stream()
                .filter(r -> r.getClass() == clazz)
                .findFirst()
                .orElseGet(() -> null);
    }

    public List<Relation> getCharacterRelations(Character character) {
        return relations.stream().filter(r -> r.isPartOf(character)).toList();
    }

    public List<Relation> getRelations() {
        return this.relations.stream().toList();
    }

    public static class Builder {
        private Character character;

        public Builder(World world) {
            this.character = new Character(world);
            world.getObjects(Type.CHARACTER).add(this.character);
        }

        public Character.Builder name(String name) {
            this.character.name = name;
            return this;
        }

        public Character get() {
            return this.character;
        }
    }
}