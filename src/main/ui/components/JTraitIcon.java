package main.ui.components;

import main.core.world.trait.Trait;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JTraitIcon extends JLabel {
    private Trait trait;
    private JTraitDescription descriptionComponent;

    public JTraitIcon(Trait trait) {
        this.trait = trait;
        this.setIcon(new ImageIcon(trait.getIcon()));
        //this.setToolTipText(trait.getName() + "\n" + trait.getDescription());
        this.addMouseListener(new Listener());

        descriptionComponent = new JTraitDescription(trait);
        descriptionComponent.setVisible(false);
    }

    private class Listener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            descriptionComponent.show(e.getComponent(), e.getX(), e.getY());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            descriptionComponent.setVisible(false);
        }
    }
}
