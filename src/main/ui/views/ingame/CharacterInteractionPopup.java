package main.ui.views.ingame;

import main.core.state.IngameState;
import main.core.world.World;
import main.core.world.character.Character;
import main.core.world.interaction.character.CharacterInteraction;
import main.ui.components.JCharacterInteractionItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterInteractionPopup extends JPopupMenu {
    private Character character;
    private JLabel nameLabel;
    private JMenuItem removeItem;

    public CharacterInteractionPopup(Character character) {
        this.character = character;
        World world = character.getWorld();
        IngameState state = (IngameState) world.getGame().getState();

        nameLabel = new JLabel(character.getName() + " " + character.getHouse().getName());
        this.add(nameLabel);
        this.add(new JSeparator());

        for(CharacterInteraction interaction : state.<CharacterInteraction>getInteractions(CharacterInteraction.class)) {
            if(!interaction.isShowable(character))
                continue;
            JCharacterInteractionItem item = new JCharacterInteractionItem(interaction, character);
            this.add(item);
        }

//        removeItem = new JMenuItem("Remove Me");
//        removeItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                world.removeObject(character);
//            }
//        });
//        this.add(removeItem);
    }
}
