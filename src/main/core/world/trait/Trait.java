package main.core.world.trait;

import main.core.world.modifier.Modifier;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Trait {
    String getName();
    String getDescription();
    void addModifier(Modifier modifier);
    void removeModifier(Modifier modifier);
    boolean hasModifier(Modifier modifier);
    List<Modifier> getModifiers();
    List<Modifier> getModifiers(Modifier.Type type);
    BufferedImage getIcon();
}
