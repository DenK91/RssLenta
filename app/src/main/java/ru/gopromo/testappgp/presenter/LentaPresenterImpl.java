package ru.gopromo.testappgp.presenter;

import android.app.FragmentManager;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.gopromo.testappgp.NewsActivity;
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
    private Context mContext;

    /**
     * Constructor.
     *
     * @param aContext {@link Context}.
     * @param aFragmentManager {@link FragmentManager}.
     * @param aView {@link LentaView}.
     */
    public LentaPresenterImpl(Context aContext, FragmentManager aFragmentManager, LentaView aView) {
        mContext = aContext;
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
        NewsActivity.startNewsView(mContext, aItem);
    }
}
