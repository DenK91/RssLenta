package ru.gopromo.testappgp.presenter;

import android.app.FragmentManager;

import java.util.List;

import ru.gopromo.testappgp.model.DataKeeper;
import ru.gopromo.testappgp.model.LentaModel;
import ru.gopromo.testappgp.view.LentaView;
import ru.gopromo.testappgp.data_model.Item;

/**
 * Presenter.
 */
public class LentaPresenterImpl implements LentaPresenter {

    private LentaModel mDataKeeper;
    private LentaView mLentaView;

    /**
     * Constructor.
     *
     * @param aFragmentManager {@link FragmentManager}.
     * @param aView {@link LentaView}.
     */
    public LentaPresenterImpl(FragmentManager aFragmentManager, LentaView aView) {
        mDataKeeper = DataKeeper.getInstance(aFragmentManager);
        mLentaView = aView;
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
