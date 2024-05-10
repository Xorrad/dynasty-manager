package main.ui.components;

import main.core.world.character.Character;
import main.core.world.interaction.character.CharacterInteraction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JCharacterInteractionItem extends JMenuItem {
    private CharacterInteraction interaction;
    private Character character;

    public JCharacterInteractionItem(CharacterInteraction interaction, Character character) {
        this.interaction = interaction;
        this.character = character;

        this.setText(interaction.getText(character));
        this.setEnabled(interaction.isAppliable(character));
        this.addActionListener(new Listener());
    }

    public CharacterInteraction getInteraction() {
        return interaction;
    }

    public Character getCharacter() {
        return character;
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!interaction.isAppliable(character))
                return;
            interaction.apply(character);
        }
    }
}
