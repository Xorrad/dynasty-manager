package main.core.world.interaction.character;

import main.core.state.IngameState;
import main.core.world.character.Character;

public class MurderInteraction extends CharacterInteraction {
    @Override
    public String getName() {
        return "Murder";
    }

    @Override
    public String getText(Character character) {
        return "Murder " + character.getName();
    }

    @Override
    public boolean isShowable(Character character) {
        if(((IngameState) character.getWorld().getGame().getState()).getPlayerCharacter().equals(character))
            return false;
        return true;
    }

    @Override
    public boolean isAppliable(Character character) {
        if(character.getId() <= 5)
            return false;
        return true;
    }

    @Override
    public void apply(Character character) {
        character.getWorld().removeObject(character);
    }

}
