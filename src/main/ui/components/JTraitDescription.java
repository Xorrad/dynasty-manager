package main.ui.components;

import main.core.world.modifier.Modifier;
import main.core.world.trait.Trait;

import javax.swing.*;
import java.awt.*;

public class JTraitDescription extends JPopupMenu {
    private Trait trait;
    private JLabel nameLabel;
    private JLabel descriptionLabel;

    public JTraitDescription(Trait trait) {
        this.trait = trait;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nameLabel = new JLabel(trait.getName());
        nameLabel.setFont(nameLabel.getFont().deriveFont(24.0f));
        this.add(nameLabel);

        descriptionLabel = new JLabel(trait.getDescription());
        this.add(descriptionLabel);
        this.add(new JSeparator());

        for(Modifier m : trait.getModifiers()) {
            if(m.getNumberModifier().getAmount() != 0) {
                String value = (m.getNumberModifier().getAmount() < 0 ? "" : "+")
                        + m.getNumberModifier().getAmount();
                JLabel modifierLabel = new JLabel(m.getName() + ": " + value);
                this.add(modifierLabel);
            }
            if(m.getNumberModifier().getMultiplier() != 1.0) {
                String value = (m.getNumberModifier().getMultiplier() < 1.0 ? "" : "+")
                        + (m.getNumberModifier().getMultiplier()*100 - 100) + "%";
                JLabel modifierLabel = new JLabel(m.getName() + ": " + value);
                this.add(modifierLabel);
            }
        }
    }
}
