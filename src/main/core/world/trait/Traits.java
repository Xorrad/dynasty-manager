package main.core.world.trait;

import main.core.world.modifier.Amount;
import main.core.world.modifier.Factor;
import main.core.world.modifier.Modifier;
import main.ui.Resources;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public enum Traits implements Trait {
    ATHLETIC(
            "This character like exercising his body.",
            Resources.Images.ATHLETIC_TRAIT,
            new Modifier(Modifier.Type.HEALTH, new Amount(1))
    )
    ;

    private String description;
    private BufferedImage icon;
    private List<Modifier> modifiers;

    Traits(String description, Resources.Images icon, Modifier... modifiers) {
        this.description = description;
        this.icon = icon.get();
        this.modifiers = Arrays.asList(modifiers);
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public BufferedImage getIcon() {
        return icon;
    }

    @Override
    public void addModifier(Modifier modifier) {
        this.modifiers.add(modifier);
    }

    @Override
    public void removeModifier(Modifier modifier) {
        this.modifiers.remove(modifier);
    }

    @Override
    public boolean hasModifier(Modifier modifier) {
        return this.modifiers.contains(modifier);
    }

    public List<Modifier> getModifiers() {
        return this.modifiers;
    }

    public List<Modifier> getModifiers(Modifier.Type type) {
        return this.modifiers.stream()
                .filter(m -> m.getType().equals(type))
                .toList();
    }
}
