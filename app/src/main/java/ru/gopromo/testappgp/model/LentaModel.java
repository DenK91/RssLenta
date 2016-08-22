package ru.gopromo.testappgp.model;

import java.util.List;

import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.presenter.LentaPresenter;

/**
 * Interface for model.
 */
public interface LentaModel {

    /**
     * Do request for load news.
     *
     * @param aPresenter {@link LentaPresenter}.
     * @param aNewsType type of news.
     */
    void newsRequest(LentaPresenter aPresenter, int aNewsType);

    /**
     * Saves data in the kepper.
     *
     * @param aTrackList data to saves.
     * @param aNewsType type of news.
     */
    void saveDataNewsModel(List<Item> aTrackList, int aNewsType);
}
