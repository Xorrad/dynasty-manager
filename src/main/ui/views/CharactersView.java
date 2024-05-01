package main.ui.views;

import main.core.Game;
import main.core.world.character.Character;
import main.ui.Observer;

import javax.swing.*;

public class CharactersView extends View implements Observer {
    private JPanel infoPanel;
    private JLabel testLabel;

    public CharactersView(Game game) {
        super(game, "Characters");
    }

    @Override
    public void init() {
        infoPanel = new JPanel();

        testLabel = new JLabel("Name: ");

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(testLabel);

        BoxLayout layoutMain = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutMain);
        this.add(infoPanel);

        this.update();
    }

    @Override
    public void update() {

    }
}
