package ru.gopromo.testappgp.model;

import java.util.List;

import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.presenter.LentaPresenter;

public interface LentaModel {

    void newsRequest(LentaPresenter aPresenter, int aNewsType);

    void saveDataNewsModel(List<Item> aTrackList, int aNewsType);
}
