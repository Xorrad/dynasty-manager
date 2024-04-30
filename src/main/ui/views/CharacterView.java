package main.ui.views;

import main.core.Game;
import main.core.world.character.Character;
import main.ui.controllers.BackController;
import main.ui.controllers.NewGameController;

import javax.swing.*;

import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;

public class CharacterView extends View implements Observer {
    private Character character;

    private JPanel infoPanel;
    private JLabel firstnameLabel;
    private JLabel dynastyLabel;
    private JLabel houseLabel;

    public CharacterView(Game game, Character character) {
        super(game, character.getName(), false);
        this.character = character;
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
}
