package rs.raf.model;

import rs.raf.observer.Subscriber;
import java.util.ArrayList;
import java.util.List;

public class Folder extends Node {

    private List<Node> nodes;

    public Folder(String name, Folder parent) {
        this.setName(name);
        this.setParent(parent);
        this.nodes = new ArrayList<>();
    }

    public void AddNode(Node node) {
        for(Node n : nodes) {
            if(n.toString().equals(node.toString())) {
                //error
                return;
            }
        }
        node.setParent(this);
        nodes.add(node);
        notifySubscribers();
    }

    public void DeleteNode(Node node) {
        if(nodes.contains(node))
            nodes.remove(node);
        notifySubscribers();
    }



    //region Subscriber

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

        if (getParent() != null) {
            getParent().notifySubscribers();
        }
    }

//endregion



    //region Geteri i Seteri

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    //endregion
}
