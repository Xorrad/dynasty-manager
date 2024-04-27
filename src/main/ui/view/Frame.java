package main.ui.view;

import main.core.Game;

import javax.swing.*;

public class Frame extends JFrame {
    private Game game;

    public Frame(Game game) {
        this.game = game;

        this.setTitle("Dynasty Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.add(new MainView(game));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
