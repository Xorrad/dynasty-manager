package main.ui.views.ingame;

import main.core.Game;
import main.core.state.IngameState;
import main.core.world.World;
import main.core.world.character.Character;
import main.ui.Observer;
import main.ui.views.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CharactersView extends View implements Observer {
    private JScrollPane tablePanel;
    private JTable table;

    public CharactersView(Game game) {
        super(game, "Characters");
        this.game.addObserver(this);
    }

    @Override
    public void init() {
        table = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{ "Id", "Name", "House", "Dynasty" })
        );
        table.setEnabled(false);
        tablePanel = new JScrollPane(table);

        BoxLayout layoutMain = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutMain);
        this.add(tablePanel);

        this.update();
    }

    @Override
    public void update() {
        World world = ((IngameState) game.getState()).getWorld();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Remove all rows from the table.
        model.getDataVector().removeAllElements();

        for(Character character : world.getCharacters().values()) {
            model.addRow(new Object[]{
                    character.getId(),
                    character.getName(),
                    character.getHouse().getName(),
                    character.getDynasty().getName()
            });
        }

        table.revalidate();
        table.repaint();
        this.revalidate();
        this.repaint();
    }
}
