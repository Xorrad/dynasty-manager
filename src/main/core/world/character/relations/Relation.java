package main.core.world.character.relations;

import main.core.world.character.Character;

public abstract class Relation {
    protected Character firstCharacter;
    protected Character secondCharacter;

    public Relation(Character firstCharacter, Character secondCharacter) {
        this.firstCharacter = firstCharacter;
        this.secondCharacter = secondCharacter;
    }

    public Character getFirstCharacter() {
        return firstCharacter;
    }

    public Character getSecondCharacter() {
        return secondCharacter;
    }

    public boolean isPartOf(Character character) {
        return this.firstCharacter == character || this.secondCharacter == character;
    }

    public String getLabel(Character character) {
        if(character.equals(this.firstCharacter))
            return this.getFirstLabel();
        if(character.equals(this.secondCharacter))
            return this.getSecondLabel();
        throw new RuntimeException("character not part of relation.");
    }

    public abstract String getFirstLabel();
    public abstract String getSecondLabel();
}
