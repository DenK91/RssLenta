package ru.gopromo.testappgp.view;

import java.util.List;

import ru.gopromo.testappgp.data_model.Item;

/**
 * Interface for view.
 */
public interface LentaView {

    /**
     * Callback will be called when news was updated.
     *
     * @param aNews list which contains new data.
     */
    void onLentaNewsUpdated(List<Item> aNews);
}
