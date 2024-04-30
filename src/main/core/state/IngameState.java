package main.core.state;

import main.core.Game;
import main.core.world.World;
import main.core.world.character.Character;
import main.ui.views.CharacterView;
import main.ui.views.MainGameView;
import main.ui.views.Observer;
import main.ui.views.View;

public class IngameState extends GameState {
    private int viewIndex;
    private World world;
    private int playerCharacterId;

    private MainGameView mainView;

    public IngameState(Game game, World world, int playerCharacterId) {
        super(game, State.IN_GAME);
        this.viewIndex = 0;
        this.world = world;
        this.playerCharacterId = playerCharacterId;
        this.views.add(new CharacterView(this.game, this.getPlayerCharacter()));

        Character characterTest = new Character.Builder(world).name("Charles").get();
        this.views.add(new CharacterView(game, characterTest));

        this.mainView = new MainGameView(game, this);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getPlayerCharacterId() {
        return playerCharacterId;
    }

    public void setPlayerCharacterId(int playerCharacterId) {
        this.playerCharacterId = playerCharacterId;
    }

    public Character getPlayerCharacter() {
        return this.world.getCharacters().get(this.playerCharacterId);
    }

    @Override
    public int getViewIndex() {
        return this.viewIndex;
    }

    @Override
    public View getView() {
        return this.mainView;
    }

    public View getSubView() {
        return this.views.get(this.viewIndex);
    }

    public void setViewIndex(int viewIndex) {
        this.viewIndex = viewIndex;
    }

    @Override
    public void openView(View view) {
        this.openView(view, false);
    }

    @Override
    public void openView(View view, boolean closePrevious) {
        super.openView(view, closePrevious);
        this.setViewIndex(this.views.size()-1);
        this.mainView.update();
        this.mainView.getViewsPane().setSelectedIndex(this.views.size()-1);
    }
}
