package main.ui;

import main.core.Game;
import main.ui.controllers.ShortcutsController;
import main.ui.views.menu.MainMenuView;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Observer {
    private Game game;

    public Frame(Game game) {
        this.game = game;
        this.game.addObserver(this);

        this.setTitle("Dynasty Manager");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

//        this.addKeyListener(new ShortcutsController(game));
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new ShortcutsController(game));


        this.add(new MainMenuView(game));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void update() {
        this.getContentPane().removeAll();
        this.add(game.getState().getView());
        this.revalidate();
        this.repaint();
    }
}
