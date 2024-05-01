package main.ui.views.ingame;

import main.core.Game;
import main.core.state.IngameState;
import main.core.world.World;
import main.core.world.character.Character;
import main.ui.Observer;
import main.ui.views.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class CharactersView extends View implements Observer {
    private JScrollPane tablePanel;
    private JTable table;

    public CharactersView(Game game) {
        super(game, "Characters");
        this.game.addObserver(this);
    }

    public IngameState getState() {
        return (IngameState) this.game.getState();
    }

    public World getWorld() {
        return this.getState().getWorld();
    }

    @Override
    public void init() {
        table = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{ "Id", "Name", "House", "Dynasty" })
        );
        table.setEnabled(false);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    int characterId = (int) table.getModel().getValueAt(row, 0);
                    Character character = getWorld().getCharacters().get(characterId);

                    if(e.getButton() == MouseEvent.BUTTON1) {
                        table.setRowSelectionInterval(row, row);
                        if(e.getClickCount() > 1)
                            getState().openView(new CharacterView(game, character));
                    }
                    else if(e.getButton() == MouseEvent.BUTTON3) {
                        CharacterInteractionPopup interactionPopup = new CharacterInteractionPopup(character);
                        interactionPopup.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        tablePanel = new JScrollPane(table);

        BoxLayout layoutMain = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutMain);
        this.add(tablePanel);

        this.update();
    }

    @Override
    public void update() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Remove all rows from the table.
        model.getDataVector().removeAllElements();

        for(Character character : getWorld().getCharacters().values()) {
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharactersView)) return false;
        return true;
    }
}
