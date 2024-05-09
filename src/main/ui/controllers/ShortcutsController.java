package main.ui.controllers;

import main.core.Game;
import main.core.state.IngameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ShortcutsController implements KeyEventDispatcher {
    private Game game;

    public ShortcutsController(Game game) {
        this.game = game;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
//        System.out.println(e.getKeyCode() + "\t" + e.isControlDown());
        if(e.getID() != KeyEvent.KEY_PRESSED)
            return false;

        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (!(this.game.getState() instanceof IngameState))
                    break;
                if (!e.isControlDown())
                    break;
                IngameState state = (IngameState) this.game.getState();
                // Cannot close main character tab.
                if(state.getViewIndex() == 0)
                    break;
                state.closeView();
                break;
        }

        return false;
    }
}
