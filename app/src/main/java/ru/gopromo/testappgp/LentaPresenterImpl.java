package ru.gopromo.testappgp;

import android.app.FragmentManager;

import java.util.List;

import ru.gopromo.testappgp.model.Item;

public class LentaPresenterImpl implements LentaPresenter {

    private LentaModel mDataKeeper;
    private LentaView mLentaView;

    public LentaPresenterImpl(FragmentManager aFragmentManager, LentaView aView) {
        mDataKeeper = DataKeeper.getInstance(aFragmentManager);
        mLentaView = aView;
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void updateListNews(int aNewsType) {
        mDataKeeper.newsRequest(this, aNewsType);
    }

    @Override
    public void onListNewsUpdated(List<Item> aNews) {
        mLentaView.onLentaNewsUpdated(aNews);
    }
}
