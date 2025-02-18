package main;

import main.core.Game;
import main.ui.Frame;
import main.ui.Resources;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Resources.initImages();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
            }
        });

//        World world = new World();
//
//        Dynasty dyn1 = new Dynasty.Builder(world).name("Meyers").get();
//        House house1 = new House.Builder(world, dyn1).name("Petit-Meyers").get();
//
//        Character c1 = new Character.Builder(world).name("Adam").house(house1).get();
//        Character c2 = new Character.Builder(world).name("Antonin").get();
//        FatherRelation r1 = new FatherRelation(c1, c2);
//        c1.addRelation(r1);
//        c1.addTrait(Traits.ATHLETIC);
//
//        //world.<Character>getObjects(WorldObject.Type.CHARACTER).forEach((id, c) -> System.out.println(c.getId() + " " + c.getName()));
//        world.<House>getObjects(WorldObject.Type.HOUSE).forEach((id, h) -> System.out.println(id + " " + h.getId() + " " + h.getName() + " " + h.getMembers().size()));
//        world.getCharacters().forEach((id, c) -> System.out.println(c.getId() + " " + c.getName()));
//        c1.getHouse().getMembers().forEach(c -> System.out.println(c.getId() + " " + c.getName() + " " + c1.getHouse().getName()));
//
//        c1.getRelations().forEach(r -> System.out.println(r.getFirstCharacter().getName() + " (" + r.getFirstLabel() + ") -> " + r.getSecondCharacter().getName() + " (" + r.getSecondLabel() + ")"));
    }
}