package ru.gopromo.testappgp.presenter;

import java.util.List;

import ru.gopromo.testappgp.data_model.Item;

public interface LentaPresenter {

    void newsSelected(int aNewsType);

    void onNewsUpdated(List<Item> aNews);

    void onItemClicked(Item aItem);

}
