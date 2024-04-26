package main.core.world.character.relations;

import main.core.world.character.Character;

public class MotherRelation extends Relation {
    // First character: mother
    // Second character: child
    public MotherRelation(Character mother, Character child) {
        super(mother, child);
    }

    @Override
    public String getFirstLabel() {
        return "mother";
    }

    @Override
    public String getSecondLabel() {
        return "child";
    }
}
