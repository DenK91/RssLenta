package ru.gopromo.testappgp.data_model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "channel", strict = false)
public class Channel  {

    @ElementList(name = "item", inline = true)
    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }
}
