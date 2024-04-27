package main.ui.view;

import main.core.Game;

import javax.swing.*;

public abstract class View extends JPanel {
    protected Game game;

    public View(Game game) {
        this.game = game;
        this.init();
    }

    public Game getGame() {
        return game;
    }

    public abstract void init();
}
