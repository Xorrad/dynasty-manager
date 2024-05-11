package main.ui.views.ingame;

import main.core.Game;
import main.core.world.character.Character;
import main.core.world.trait.Trait;
import main.ui.Observer;
import main.ui.components.JTraitIcon;
import main.ui.views.View;

import javax.swing.*;
import java.util.Objects;

public class CharacterView extends View implements Observer {
    private Character character;

    private JPanel infoPanel;
    private JLabel firstnameLabel;
    private JLabel dynastyLabel;
    private JLabel houseLabel;

    private JPanel traitsPanel;

    public CharacterView(Game game, Character character) {
        super(game, character.getName(), false);
        this.character = character;
        this.game.addObserver(this);
        this.init();
    }

    @Override
    public void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Information panel.
        infoPanel = new JPanel();
        firstnameLabel = new JLabel("Name: ");
        dynastyLabel = new JLabel("Dynasty: ");
        houseLabel = new JLabel("House: ");

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(firstnameLabel);
        infoPanel.add(dynastyLabel);
        infoPanel.add(houseLabel);
        this.add(infoPanel);

        // Traits panel.
        traitsPanel = new JPanel();
        this.add(traitsPanel);


        this.update();
    }

    @Override
    public void update() {
        firstnameLabel.setText("Name: " + character.getName());
        dynastyLabel.setText("Dynasty: " + character.getDynasty().getName());
        houseLabel.setText("House: " + character.getHouse().getName());

        traitsPanel.removeAll();
        for(Trait trait : character.getTraits()) {
//            JLabel traitLabel = new JLabel(trait.getName());
            JTraitIcon traitIcon = new JTraitIcon(trait);
            traitsPanel.add(traitIcon);
        }
        traitsPanel.repaint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterView that)) return false;
        return Objects.equals(character, that.character);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(character);
    }
}
