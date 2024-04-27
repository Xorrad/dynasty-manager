package main.core.world.modifier;

public class FactorAmount {
    private double multiplier;
    private double amount;

    public FactorAmount(double multiplier, double amount) {
        this.multiplier = multiplier;
        this.amount = amount;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public void addMultiplier(double multiplier) {
        this.multiplier += multiplier;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }

    public void add(FactorAmount factorAmount) {
        this.addMultiplier(factorAmount.getMultiplier());
        this.addAmount(factorAmount.getAmount());
    }

    public double calculate(double value) {
        return (1 + this.multiplier) * (value + this.amount);
    }
}
