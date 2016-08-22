package ru.gopromo.testappgp;

import java.util.List;

import ru.gopromo.testappgp.model.Item;

public interface LentaView {

    void onLentaNewsUpdated(List<Item> aNews);
}
