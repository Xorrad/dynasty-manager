package main;

import main.core.world.World;
import main.core.world.WorldObject;
import main.core.world.character.Character;
import main.core.world.character.relations.FatherRelation;

public class Main {
    public static void main(String[] args) {

        World world = new World();
        Character c1 = new Character.Builder(world).name("Adam").get();
        Character c2 = new Character.Builder(world).name("Antonin").get();
        FatherRelation r1 = new FatherRelation(c1, c2);
        c1.addRelation(r1);

        //world.<Character>getObjects(WorldObject.Type.CHARACTER).forEach((id, c) -> System.out.println(c.getId() + " " + c.getName()));
        world.getCharacters().forEach((id, c) -> System.out.println(c.getId() + " " + c.getName()));

        c1.getRelations().forEach(r -> System.out.println(r.getFirstCharacter().getName() + " (" + r.getFirstLabel() + ") -> " + r.getSecondCharacter().getName() + " (" + r.getSecondLabel() + ")"));
    }
}