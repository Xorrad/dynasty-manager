package main.ui.controller;

import main.core.Game;

import java.awt.event.ActionEvent;

public class MainController {
    public static class NewGameController extends Controller {
        public NewGameController(Game game) {
            super(game);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked play");
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
