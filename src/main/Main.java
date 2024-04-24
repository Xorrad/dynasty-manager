package main;

import main.core.world.World;
import main.core.world.WorldObject;
import main.core.world.character.Character;

public class Main {
    public static void main(String[] args) {

        World world = new World();
        Character c1 = new Character.Builder(world).name("Adam").get();
        Character c2 = new Character.Builder(world).name("Antonin").get();

        System.out.println(c1.getName());
        System.out.println(c1.getId());

        System.out.println(c2.getName());
        System.out.println(c2.getId());

        world.<Character>getObjects(WorldObject.Type.CHARACTER).forEach((id, c) -> System.out.println(c.getId() + " " + c.getName()));
    }
}