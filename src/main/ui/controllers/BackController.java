package main.ui.controllers;

import main.core.Game;

import java.awt.event.ActionEvent;

public class BackController extends Controller {
    public BackController(Game game) {
        super(game);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.game.getState().closeView();
    }
}
