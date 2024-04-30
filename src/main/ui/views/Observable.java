package main.ui.views;

import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        this.observers.forEach(o -> o.update());
    }
}