package main.ui.views.ingame;

import main.core.Game;
import main.core.world.character.Character;
import main.ui.Observer;
import main.ui.views.View;

import javax.swing.*;
import java.util.Objects;

public class CharacterView extends View implements Observer {
    private Character character;

    private JPanel infoPanel;
    private JLabel firstnameLabel;
    private JLabel dynastyLabel;
    private JLabel houseLabel;

    public CharacterView(Game game, Character character) {
        super(game, character.getName(), false);
        this.character = character;
        this.game.addObserver(this);
        this.init();
    }

    @Override
    public void init() {
        infoPanel = new JPanel();

        firstnameLabel = new JLabel("Name: ");
        dynastyLabel = new JLabel("Dynasty: ");
        houseLabel = new JLabel("House: ");

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(firstnameLabel);
        infoPanel.add(dynastyLabel);
        infoPanel.add(houseLabel);

        BoxLayout layoutMain = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutMain);
        this.add(infoPanel);

        this.update();
    }

    @Override
    public void update() {
        firstnameLabel.setText("Name: " + character.getName());
        dynastyLabel.setText("Dynasty: " + character.getDynasty().getName());
        houseLabel.setText("House: " + character.getHouse().getName());
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
