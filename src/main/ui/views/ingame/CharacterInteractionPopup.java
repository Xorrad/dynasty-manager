package main.ui.views.ingame;

import main.core.Game;
import main.core.world.World;
import main.core.world.character.Character;

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

        nameLabel = new JLabel(character.getName() + " " + character.getHouse().getName());
        this.add(nameLabel);
        this.add(new JSeparator());

        removeItem = new JMenuItem("Remove Me");
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.removeObject(character);
            }
        });

        this.add(removeItem);
    }
}
