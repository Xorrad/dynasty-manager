package main.core.world.trait;

import main.core.world.character.Character;
import main.core.world.modifier.Modifier;
import main.ui.Resources;
import main.util.Date;

import java.awt.image.BufferedImage;

public class PregnantTrait extends CustomTrait {
    private Character mother;
    private Character father;
    private Date date;

    public PregnantTrait(Character mother, Character father, Date date) {
        super("Pregnant", "", Resources.Images.PREGNANT_TRAIT.get());
        this.mother = mother;
        this.father = father;
        this.date = date;
    }

    public Character getMother() {
        return mother;
    }

    public Character getFather() {
        return father;
    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return this.date.getYearDiff(this.mother.getWorld().getDate());
    }

    @Override
    public String getDescription() {
        return "This character is with child since " + this.getDuration() + " months";
    }
}
