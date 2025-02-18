package main.core.state;

import main.core.Game;
import main.core.world.World;
import main.core.world.character.Character;
import main.core.world.interaction.Interaction;
import main.core.world.interaction.character.CharacterInteraction;
import main.core.world.interaction.character.MurderInteraction;
import main.ui.GameMenuBar;
import main.ui.views.ingame.CharacterView;
import main.ui.views.ingame.MainGameView;
import main.ui.views.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class IngameState extends GameState {
    private int viewIndex;
    private World world;
    private int playerCharacterId;
    private HashMap<Class<? extends Interaction>, ArrayList<Interaction>> interactions;

    private MainGameView mainView;

    public IngameState(Game game, World world, int playerCharacterId) {
        super(game, State.IN_GAME);
        this.viewIndex = 0;
        this.world = world;
        this.playerCharacterId = playerCharacterId;
        this.initInteractions();

        this.game.getFrame().setJMenuBar(new GameMenuBar(game));
        this.views.add(new CharacterView(this.game, this.getPlayerCharacter()));
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

    public void updateView() {
        this.game.notifyObservers();
        this.mainView.update();
        this.mainView.getViewsPane().setSelectedIndex(this.viewIndex);
    }

    @Override
    public void closeView() {
        this.closeView(this.viewIndex);
    }

    @Override
    public void closeView(int index) {
        this.views.remove(index);
        if(index > this.viewIndex || this.views.size() <= this.viewIndex)
            this.viewIndex--;
        this.updateView();
    }

    @Override
    public void openView(View view) {
        this.openView(view, false);
    }

    @Override
    public void openView(View view, boolean closePrevious) {
        // Check if this view isn't already opened.
        OptionalInt optionalIndex = IntStream.range(0, this.views.size())
            .filter(i-> this.views.get(i).equals(view))
            .findFirst();
        if(!optionalIndex.isPresent())
            super.openView(view, closePrevious);
        int i = optionalIndex.isPresent() ? optionalIndex.getAsInt() : this.views.size()-1;
        this.setViewIndex(i);
        this.updateView();
    }

    private void initInteractions() {
        this.interactions = new HashMap<>();

        // Initialize character interactions.
        this.interactions.put(CharacterInteraction.class, new ArrayList<>());
        this.addInteraction(new MurderInteraction());
    }

    public <T> void addInteraction(Interaction<T> interaction) {
        for(Class<? extends Interaction> c : interactions.keySet()) {
            if(!c.isAssignableFrom(interaction.getClass()))
                continue;
            this.interactions.get(c).add(interaction);
            return;
        }
    }

    public <T> void removeInteraction(Interaction<T> interaction) {
        for(Class<? extends Interaction> c : interactions.keySet()) {
            if(!c.isAssignableFrom(interaction.getClass()))
                continue;
            this.interactions.get(c).remove(interaction);
            return;
        }
    }

    public <T> ArrayList<T> getInteractions(Class<? extends Interaction> clazz) {
        return (ArrayList<T>) this.interactions.get(clazz);
    }
}
