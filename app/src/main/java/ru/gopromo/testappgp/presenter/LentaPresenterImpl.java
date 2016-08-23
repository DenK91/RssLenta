package ru.gopromo.testappgp.presenter;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ru.gopromo.testappgp.NewsActivity;
import ru.gopromo.testappgp.R;
import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.model.DataKeeper;
import ru.gopromo.testappgp.model.LentaModel;
import ru.gopromo.testappgp.view.LentaView;
import ru.gopromo.testappgp.view.NewsListAdapter;

/**
 * Presenter.
 */
public class LentaPresenterImpl implements LentaPresenter {

    private LentaModel mDataKeeper;
    private WeakReference<LentaView> mLentaView;
    private Context mContext;
    private List<Item> mNewsListData = new ArrayList<>();
    private NewsListAdapter mNewsListAdapter;
    private int mLastNewsRequested = -1;

    private BroadcastReceiver mInetConnectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context aContext, Intent aIntent) {
            ConnectivityManager cm =
                    (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()
                    && mDataKeeper != null
                    && mLastNewsRequested != -1) {
                mDataKeeper.newsRequest(LentaPresenterImpl.this, mLastNewsRequested);
            }
        }
    };

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
        mNewsListAdapter = new NewsListAdapter(mContext, mNewsListData, R.layout.newslist_item_list, this);
        aView.getViewContainer().getListView().setAdapter(mNewsListAdapter);

        mContext.registerReceiver(mInetConnectionReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onDestroy(Context aContext) {
        aContext.unregisterReceiver(mInetConnectionReceiver);
    }

    @Override
    public void newsSelected(int aNewsType) {
        mLastNewsRequested = aNewsType;
        mDataKeeper.newsRequest(this, aNewsType);
    }

    @Override
    public void onNewsUpdated(List<Item> aNews) {
        LentaView view = mLentaView.get();

        if (view != null) {
            TextView text = view.getViewContainer().getErrorTextView();
            ListView list = view.getViewContainer().getListView();
            if (aNews != null) {
                mNewsListData.clear();
                if (!aNews.isEmpty()) {
                    mNewsListData.addAll(aNews);
                    mNewsListAdapter.notifyDataSetChanged();
                    text.setVisibility(View.GONE);
                    list.setVisibility(View.VISIBLE);
                    return;
                }
            }
            text.setText(mContext.getString(R.string.error_list_is_empty));
            text.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }

    }

    @Override
    public void onItemClicked(int aItemPosition) {
        NewsActivity.startNewsView(mContext, mNewsListData.get(aItemPosition));
    }
}
