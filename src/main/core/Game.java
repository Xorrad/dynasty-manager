package main.core;

import main.core.state.GameState;
import main.core.state.MainMenuState;
import main.ui.views.MainMenuView;
import main.ui.views.Observable;

public class Game extends Observable {
    private GameState state;

    public Game() {
        this.state = new MainMenuState(this);
        this.state.openView(new MainMenuView(this));
    }

    public String getVersion() {
        return "0.1";
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
        this.notifyObservers();
    }
}