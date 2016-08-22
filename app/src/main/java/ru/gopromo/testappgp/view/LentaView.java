package ru.gopromo.testappgp.view;

import java.util.List;

import ru.gopromo.testappgp.data_model.Item;

public interface LentaView {

    void onLentaNewsUpdated(List<Item> aNews);
}
