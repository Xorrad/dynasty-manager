package main.core.world.trait;

import main.core.world.modifier.Amount;
import main.core.world.modifier.Modifier;

import java.util.Arrays;
import java.util.List;

public enum Traits implements Trait {
    ATHLETIC(
            "This character like exercising his body.",
            new Modifier(Modifier.Type.HEALTH, new Amount(1))
    )
    ;

    private String description;
    private List<Modifier> modifiers;

    Traits(String description, Modifier... modifiers) {
        this.description = description;
        this.modifiers = Arrays.asList(modifiers);
    }

    public String getName() {
        return this.name().toLowerCase();
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
}
