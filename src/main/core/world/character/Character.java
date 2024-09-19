package main.core.world.character;

import main.core.world.WorldObject;
import main.core.world.World;
import main.core.world.character.relations.Relation;
import main.core.world.dynasty.Dynasty;
import main.core.world.dynasty.House;
import main.core.world.modifier.FactorAmount;
import main.core.world.modifier.Modifier;
import main.core.world.title.Title;
import main.core.world.trait.PregnantTrait;
import main.core.world.trait.Trait;
import main.util.Date;
import main.util.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Character extends WorldObject {
    private String name;
    private House house;
    private ArrayList<Relation> relations;
    private ArrayList<Title> titles;
    private ArrayList<Trait> traits;
    private Location location;
    private Date birthday;
    private Gender gender;

    public Character(World world) {
        super(world);
        this.house = world.getDefaultHouse();
        this.relations = new ArrayList<>();
        this.titles = new ArrayList<>();
        this.traits = new ArrayList<>();
        this.location = new Location(0, 0);
        this.birthday = world.getDate();
        this.gender = Gender.MALE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public House getHouse() {
        return house;
    }

    public Dynasty getDynasty() {
        return this.house.getDynasty();
    }

    public void setHouse(House house) {
        // Character house can never be null, it will always, at least, be the lowborn house.
        this.house = (house == null) ? this.getWorld().getDefaultHouse() : house;
    }

    public void switchHouse(House house) {
        this.house.removeMember(this);
        this.setHouse(house);
        this.house.addMember(this);
    }

    public void leaveHouse() {
        // Switch to lowborn/default house of the world.
        this.switchHouse(null);
    }

    public void addRelation(Relation relation) {
        this.relations.add(relation);
    }

    public boolean hasRelation(Class<? extends Relation> clazz) {
        return this.relations.stream()
                .filter(r -> r.getClass() == clazz)
                .findFirst()
                .isPresent();
    }

    public boolean hasRelation(Class<? extends Relation> clazz, Character character) {
        return this.relations.stream()
                .filter(r -> (r.getClass() == clazz && r.isPartOf(character)))
                .findFirst()
                .isPresent();
    }

    public Relation getRelation(Class<? extends Relation> clazz) {
        return this.relations.stream()
                .filter(r -> r.getClass() == clazz)
                .findFirst()
                .orElseGet(() -> null);
    }

    public List<Relation> getCharacterRelations(Character character) {
        return this.relations.stream().filter(r -> r.isPartOf(character)).toList();
    }

    public List<Relation> getRelations() {
        return this.relations.stream().toList();
    }

    public void addTitle(Title title) {
        this.titles.add(title);
    }

    public void removeTitle(Title title) {
        this.titles.remove(title);
    }

    public boolean hasTitle(Title title) {
        return this.titles.contains(title);
    }

    public List<Title> getTitles() {
        return this.titles.stream().toList();
    }

    public void addTrait(Trait title) {
        this.traits.add(title);
    }

    public void removeTrait(Trait title) {
        this.traits.remove(title);
    }

    public boolean hasTrait(Class<? extends Trait> clazz) {
        for(Trait t : this.traits) {
            if(clazz.isAssignableFrom(t.getClass()))
                return true;
        }
        return false;
    }

    public boolean hasTrait(Trait title) {
        return this.traits.contains(title);
    }

    public List<Trait> getTraits() {
        return this.traits.stream().toList();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public FactorAmount getTotalModifier(Modifier.Type type) {
        // Initialize à default value and increment it with all the modifiers in traits, items...
        FactorAmount factorAmount = new FactorAmount(1.0, 0);

        for(Trait trait : this.traits) {
            for(Modifier m : trait.getModifiers(type)) {
                factorAmount.add(m.getNumberModifier());
            }
        }

        return factorAmount;
    }

    public double applyModifier(Modifier.Type type, double value) {
        return this.getTotalModifier(type).calculate(value);
    }

    public int applyModifier(Modifier.Type type, int value) {
        return (int) this.applyModifier(type, (double) value);
    }

    public int getAge() {
        return this.getWorld().getDate().getYear() - birthday.getYear();
    }

    public double getFertilityModifier() {
        double fertility = 1.0;

        // Add age modifier.
        fertility = Math.exp(-0.05*this.getAge());

        // Apply modifiers.
        fertility = getTotalModifier(Modifier.Type.FERTILITY).calculate(fertility);

        return fertility;
    }

    public void layWith(Character target) {
        Random ran = new Random();

        // TODO: Add a secret.

        Character mother = this.gender == Gender.FEMALE ? this : (target.gender == Gender.FEMALE ? target : null);
        Character father = this.gender == Gender.MALE ? this : (target.gender == Gender.MALE ? target : null);
        if(mother != null && father != null && !mother.hasTrait(PregnantTrait.class)) {
            double motherFertility = mother.getFertilityModifier();
            double fatherFertility = father.getFertilityModifier();
            double fertility = (motherFertility + fatherFertility)/2.0;
            if (ran.nextDouble() >= fertility) {
                mother.addTrait(new PregnantTrait(mother, father, getWorld().getDate()));
            }
        }
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static class Builder {
        private Character character;

        public Builder(World world) {
            this.character = new Character(world);
            world.addObject(this.character);
        }

        public Character.Builder name(String name) {
            this.character.name = name;
            return this;
        }

        public Character.Builder house(House house) {
            this.character.switchHouse(house);
            return this;
        }

        public Character.Builder location(Location location) {
            this.character.location = location;
            return this;
        }

        public Character.Builder birthday(Date birthday) {
            this.character.birthday = birthday;
            return this;
        }

        public Character.Builder gender(Gender gender) {
            this.character.gender = gender;
            return this;
        }

        public Character.Builder trait(Trait trait) {
            this.character.addTrait(trait);
            return this;
        }

        public Character get() {
            return this.character;
        }
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER;
    }
}