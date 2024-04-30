package main.ui.controllers;

import main.core.Game;

import javax.swing.*;

public abstract class Controller extends AbstractAction {
    protected Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
