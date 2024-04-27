package main.core.world.modifier;

import main.core.world.WorldObject;
import main.core.world.character.Character;

public class Modifier {
    private Type type;
    private FactorAmount factorAmount;

    public Modifier(Type type, FactorAmount factorAmount) {
        this.type = type;
        this.factorAmount = factorAmount;
    }

    public Type getType() {
        return type;
    }

    public FactorAmount getNumberModifier() {
        return factorAmount;
    }

    public enum Type {
        MONTHLY_GOLD_INCOME("Amount of gold earned each month.", Character.class)
        ;

        private String description;
        private Class<? extends WorldObject> scope;

        Type(String description, Class<? extends WorldObject> scope) {
            this.description = description;
            this.scope = scope;
        }

        public String getName() {
            return this.name().toLowerCase();
        }

        public String getDescription() {
            return description;
        }

        public Class<? extends WorldObject> getScope() {
            return scope;
        }

        public static Type get(String name) {
            return Type.valueOf(name.toUpperCase());
        }
    }
}
