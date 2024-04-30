package main.core.state;

import main.core.Game;
import main.ui.views.View;

public class MainMenuState extends GameState {
    public MainMenuState(Game game) {
        super(game, State.IN_MAIN_MENU);
    }

    @Override
    public View getView() {
        return this.views.get(this.views.size()-1);
    }

    @Override
    public int getViewIndex() {
        return this.views.size()-1;
    }
}
