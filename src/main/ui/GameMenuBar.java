package main.ui;

import main.core.Game;
import main.ui.views.ingame.CharactersView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameMenuBar extends JMenuBar {
    private Game game;

    public GameMenuBar(Game game) {
        JMenu viewsMenu = new JMenu("Views");

        JMenuItem charactersMenu = new JMenuItem("Characters");
        charactersMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getState().openView(new CharactersView(game));
            }
        });
        charactersMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        viewsMenu.add(charactersMenu);

        this.add(viewsMenu);
    }
}
