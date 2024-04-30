package main.ui.views;

import main.core.Game;

import javax.swing.*;

public abstract class View extends JPanel {
    protected Game game;
    protected String title;

    public View(Game game, String title) {
        this(game, title, true);
    }

    public View(Game game, String title, boolean init) {
        this.game = game;
        this.title = title;
        if(init) this.init();
    }

    public Game getGame() {
        return game;
    }

    public String getTitle() {
        return title;
    }

    public abstract void init();
}
