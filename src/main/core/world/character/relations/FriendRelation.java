package main.core.world.character.relations;

import main.core.world.character.Character;

public class FriendRelation extends Relation {
    // First character: friend
    // Second character: friend
    public FriendRelation(Character firstCharacter, Character secondCharacter) {
        super(firstCharacter, secondCharacter);
    }

    @Override
    public String getFirstLabel() {
        return "friend";
    }

    @Override
    public String getSecondLabel() {
        return "friend";
    }
}
