package main.core.world.character;

import main.core.world.WorldObject;
import main.core.world.World;

public class Character extends WorldObject {
    private String name;

    public Character(World world) {
        super(world);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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