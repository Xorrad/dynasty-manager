package main.core.world.trait;

import main.core.world.modifier.Modifier;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomTrait implements Trait {
    private String name;
    private String description;
    private BufferedImage icon;
    private List<Modifier> modifiers;

    public CustomTrait(String name, String description, BufferedImage icon, Modifier... modifiers) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.modifiers = Arrays.asList(modifiers);
    }

    public String getName() {
        return this.name.toLowerCase();
    }

    public String getDescription() {
        return this.description;
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

    @Override
    public BufferedImage getIcon() {
        return this.icon;
    }
}
