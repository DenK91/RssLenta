package ru.gopromo.testappgp.presenter;

import java.util.List;

import ru.gopromo.testappgp.data_model.Item;

public interface LentaPresenter {

    void updateListNews(int aNewsType);

    void onListNewsUpdated(List<Item> aNews);

}
