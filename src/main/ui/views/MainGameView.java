package main.ui.views;

import main.core.Game;
import main.core.state.IngameState;
import main.core.world.World;
import main.core.world.character.Character;
import main.ui.controllers.BackController;
import main.ui.components.JTabbedPaneClosable;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainGameView extends View implements Observer {
    private IngameState state;

    private JTabbedPaneClosable viewsPane;
    private JButton quitButton;

    public MainGameView(Game game, IngameState state) {
        super(game, "Main Game Menu", false);
        this.state = state;
        this.init();
    }

    public IngameState getState() {
        return this.state;
    }

    public World getWorld() {
        return this.state.getWorld();
    }

    public JTabbedPaneClosable getViewsPane() {
        return viewsPane;
    }

    @Override
    public void init() {
        viewsPane = new JTabbedPaneClosable();
        viewsPane.setCloseTabConsumer(index -> {
            state.closeView(index);
        });

//        characterTab = new JPanel();
//        viewsPane.insertTab(this.state.getPlayerCharacter().getName(), null, characterTab, "", 0);
//        JLabel characterFirstnameLabel = new JLabel("Name: ");
//        characterTab.add(characterFirstnameLabel);

        quitButton = new JButton("Back to main menu");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Character characterTest = new Character.Builder(getWorld()).name("Karl").get();
                state.openView(new CharacterView(game, characterTest));
            }
        });

        BoxLayout layoutMain = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutMain);
        this.add(viewsPane);
        this.add(quitButton);

        this.update();
    }

    @Override
    public void update() {
        ArrayList<View> views = this.getState().getViews();
        for(int i = 0; i < views.size(); i++) {
            if(viewsPane.getTabCount() > i && !viewsPane.getTitleAt(i).equalsIgnoreCase(views.get(i).getTitle())) {
                viewsPane.remove(i);
            }
            else if(viewsPane.getTabCount() <= i) {
                if (i == 0) viewsPane.addUnclosableTab(views.get(i).getTitle(), views.get(i));
                else viewsPane.addTab(views.get(i).getTitle(), views.get(i));
            }
        }
        for(int i = views.size(); i < viewsPane.getTabCount(); i++)
            viewsPane.remove(i);

        this.revalidate();
        this.repaint();
    }
}
