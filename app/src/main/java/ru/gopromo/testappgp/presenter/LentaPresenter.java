package ru.gopromo.testappgp.presenter;

import java.util.List;

import ru.gopromo.testappgp.data_model.Item;

/**
 * Interfece for presenter.
 */
public interface LentaPresenter {

    /**
     * Callback will be called when new type of news was selected.
     *
     * @param aNewsType type of news.
     */
    void newsSelected(int aNewsType);

    /**
     * Callback will be called when news in model was updated.
     *
     * @param aNews list which contains new data.
     */
    void onNewsUpdated(List<Item> aNews);

    /**
     * Callback will be called when item in view was clicked.
     *
     * @param aItem item on which the clicked
     */
    void onItemClicked(Item aItem);

}
