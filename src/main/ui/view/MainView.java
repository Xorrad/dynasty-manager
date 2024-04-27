package main.ui.view;

import main.core.Game;
import main.ui.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class MainView extends View {
    private JPanel buttonsPanel;
    private JButton newGameButton;
    private JButton quitButton;

    private JPanel creditsPanel;
    private JLabel versionLabel;
    private JLabel creditsLabel;

    public MainView(Game game) {
        super(game);
    }

    @Override
    public void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new MainController.NewGameController(game));

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new MainController.QuitController(game));

        buttonsPanel = new JPanel(new GridLayout(2, 1));
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(quitButton);

        JPanel west = new JPanel(new GridBagLayout());
        west.add(buttonsPanel);

        versionLabel = new JLabel("v" + game.getVersion(), SwingConstants.LEFT);
        creditsLabel = new JLabel("Made by Xorrad", SwingConstants.RIGHT);

        creditsPanel = new JPanel(new BorderLayout());
        creditsPanel.add(versionLabel, BorderLayout.WEST);
        creditsPanel.add(Box.createHorizontalStrut(10));
        creditsPanel.add(creditsLabel, BorderLayout.EAST);

        this.add(west, BorderLayout.WEST);
        this.add(creditsPanel);
    }
}
