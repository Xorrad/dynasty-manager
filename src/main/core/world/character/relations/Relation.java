package main.core.world.character.relations;

import main.core.world.character.Character;

public abstract class Relation {
    protected Character firstCharacter;
    protected Character secondCharacter;
    private boolean known;

    public Relation(Character firstCharacter, Character secondCharacter) {
        this(firstCharacter, secondCharacter, true);
    }

    public Relation(Character firstCharacter, Character secondCharacter, boolean known) {
        this.firstCharacter = firstCharacter;
        this.secondCharacter = secondCharacter;
        this.known = known;
    }

    public Character getFirstCharacter() {
        return firstCharacter;
    }

    public Character getSecondCharacter() {
        return secondCharacter;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    // Override to apply effects when the relation is revealed.
    public void reveal() {
        this.setKnown(true);
    }

    // Override to apply effects when relation is hidden.
    public void hide() {
        this.setKnown(false);
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
