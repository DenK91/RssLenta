package ru.gopromo.testappgp;

import java.util.List;

import ru.gopromo.testappgp.model.Item;

public interface LentaPresenter {

    void onDestroy();

    void updateListNews(int aNewsType);

    void onListNewsUpdated(List<Item> aNews);

}
