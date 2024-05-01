package main.core;

import main.core.state.GameState;
import main.core.state.MainMenuState;
import main.ui.Frame;
import main.ui.views.MainMenuView;
import main.ui.Observable;

public class Game extends Observable {
    private Frame frame;
    private GameState state;

    public Game() {
        this.frame = new Frame(this);
        this.state = new MainMenuState(this);
        this.state.openView(new MainMenuView(this));
    }

    public String getVersion() {
        return "0.1";
    }

    public Frame getFrame() {
        return frame;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
        this.notifyObservers();
    }
}