package main.core.world.character.relations;

import main.core.world.character.Character;

public class FatherRelation extends Relation {
    // First character: father
    // Second character: child
    public FatherRelation(Character father, Character child) {
        super(father, child);
    }

    @Override
    public String getFirstLabel() {
        return "father";
    }

    @Override
    public String getSecondLabel() {
        return "child";
    }
}
