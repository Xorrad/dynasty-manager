package main.core.state;

import main.core.Game;
import main.ui.views.View;

import java.util.ArrayList;

public abstract class GameState {
    protected Game game;
    protected State state;
    protected ArrayList<View> views;

    public GameState(Game game, State state) {
        this.game = game;
        this.state = state;
        this.views = new ArrayList<>();
    }

    public Game getGame() {
        return game;
    }

    public State getState() {
        return state;
    }

    public ArrayList<View> getViews() {
        return views;
    }

    public abstract int getViewIndex();

    public View getView() {
        return this.views.get(this.getViewIndex());
    }

    public void openView(View view) {
        this.openView(view, false);
    }

    public void openView(View view, boolean closePrevious) {
        if(closePrevious)
            this.views.remove(this.views.size()-1);
        this.views.add(view);
        this.game.notifyObservers();
    }

    public void closeView() {
        this.closeView(this.views.size()-1);
    }

    public void closeView(int index) {
        this.views.remove(index);
        this.game.notifyObservers();
    }

    enum State {
        IN_MAIN_MENU,
        IN_GAME,
    }
}
