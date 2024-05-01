package main.ui.controllers;

import main.core.Game;
import main.ui.views.menu.NewGameView;

import java.awt.event.ActionEvent;

public class MainController {
    public static class NewGameController extends Controller {
        public NewGameController(Game game) {
            super(game);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.game.getState().openView(new NewGameView(this.game));
        }
    }

    public static class QuitController extends Controller {
        public QuitController(Game game) {
            super(game);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
