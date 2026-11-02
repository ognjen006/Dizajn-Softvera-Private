package rs.raf.model;

import rs.raf.observer.Publisher;
import rs.raf.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class Node implements Publisher {

    private String name;
    private Folder parent;





    public List<Node> getNodes() {
        return List.of();
    }

    public boolean isLeaf() {
        return true;
    }

    //region Observer

    private final List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if (subscriber != null && !subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : new ArrayList<>(subscribers)) {
            subscriber.update();
        }

        if (parent != null) {
            parent.notifySubscribers();
        }
    }

//endregion


    //region Geteri i Seteri

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    //endregion

    @Override
    public String toString() {
        return name;
    }
}