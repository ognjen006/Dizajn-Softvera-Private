package rs.raf.model;

import rs.raf.observer.Publisher;

import java.util.List;

public abstract class Node implements Publisher {

    private String name;
    private Folder parent;










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