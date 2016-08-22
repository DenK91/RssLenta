package ru.gopromo.testappgp.presenter;

import android.app.FragmentManager;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.model.DataKeeper;
import ru.gopromo.testappgp.model.LentaModel;
import ru.gopromo.testappgp.view.LentaView;

/**
 * Presenter.
 */
public class LentaPresenterImpl implements LentaPresenter {

    private LentaModel mDataKeeper;
    private WeakReference<LentaView> mLentaView;

    /**
     * Constructor.
     *
     * @param aFragmentManager {@link FragmentManager}.
     */
    public LentaPresenterImpl(FragmentManager aFragmentManager, LentaView aView) {
        mDataKeeper = DataKeeper.getInstance(aFragmentManager);
        mLentaView = new WeakReference<>(aView);
    }

    @Override
    public void newsSelected(int aNewsType) {
        mDataKeeper.newsRequest(this, aNewsType);
    }

    @Override
    public void onNewsUpdated(List<Item> aNews) {
        LentaView view = mLentaView.get();
        if (view != null) {
            view.onLentaNewsUpdated(aNews);
        }
    }

    @Override
    public void onItemClicked(Item aItem) {
        Log.d("denk", "cleck:" + aItem.getLink());
    }
}
