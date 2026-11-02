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
                throw new IllegalArgumentException(
                        "Fascikla vec sadrzi cvor sa imenom: " + node.getName()
                );
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



    private static int folderCounter = 0;

    public static String nextDefaultName() {
        return "Folder" + (++folderCounter);
    }


    @Override
    public boolean isLeaf() {
        return false;
    }



    //region Geteri i Seteri

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    //endregion
}
