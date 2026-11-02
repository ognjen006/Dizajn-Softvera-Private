package rs.raf.model;

import rs.raf.observer.Subscriber;

public abstract class Document extends Node {

    private String text;




    //region Geteri i Seteri


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //endregion
}
