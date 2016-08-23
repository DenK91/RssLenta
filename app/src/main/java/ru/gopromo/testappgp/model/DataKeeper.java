package ru.gopromo.testappgp.model;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import ru.gopromo.testappgp.R;
import ru.gopromo.testappgp.presenter.LentaPresenter;
import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.data_model.Rss;

/**
 * Retain fragment for keep data.
 * Also is a {@link LentaModel}.
 */
public class DataKeeper extends Fragment implements LentaModel {

    private static final String TAG_DATA_KEEPER_FRAGMENT = "data_keeper_fragment";

    /**
     * Return instance of {@link DataKeeper} who also is a {@link LentaModel}.
     *
     * @param aFragmentManager {@link FragmentManager}.
     * @return real instance {@link DataKeeper}. Can't be null.
     */
    public static DataKeeper getInstance(FragmentManager aFragmentManager) {
        DataKeeper dataKeeper = (DataKeeper) aFragmentManager.findFragmentByTag(TAG_DATA_KEEPER_FRAGMENT);
        if (dataKeeper == null) {
            dataKeeper = new DataKeeper();
            aFragmentManager.beginTransaction().add(dataKeeper, TAG_DATA_KEEPER_FRAGMENT).commit();
        }
        return dataKeeper;
    }

    private List<Item> mRussianNewsList = new ArrayList<>();
    private List<Item> mWorldNewsList = new ArrayList<>();
    private List<Item> mScienceNewsList = new ArrayList<>();

    /**
     * for debug only.
     */
    private static boolean DEBUG_MOD = false;
    private static int sRCallCounter = 0;
    private static int sWCallCounter = 0;
    private static int sSCallCounter = 0;

    @Override
    public void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void newsRequest(final LentaPresenter aPresenter, final int aNewsType) {
        if (hasFreshNews(aNewsType)) {
            Call<Rss> call = LentaRu.getCallToServerByType(aNewsType);
            if (call != null) {
                call.enqueue(new ServerCallback(aPresenter, this, aNewsType));
            }
        } else {
            aPresenter.onNewsUpdated(getNewsListByType(aNewsType));
        }
    }

    @Override
    public void saveDataNewsModel(List<Item> aTrackList, int aNewsType) {
        if (aTrackList == null) {
            return;
        }
        switch (aNewsType) {
            case R.id.russia:
                mRussianNewsList = aTrackList;
                break;
            case R.id.world:
                mWorldNewsList = aTrackList;
                break;
            case R.id.science:
                mScienceNewsList = aTrackList;
                break;
            default:
        }
    }

    /**
     * Find and return news container by type.
     *
     * @param aNewsType type of news.
     * @return refs to news list.
     */
    private List<Item> getNewsListByType(int aNewsType) {
        switch (aNewsType) {
            case R.id.russia:
                return mRussianNewsList;
            case R.id.world:
                return mWorldNewsList;
            case R.id.science:
                return mScienceNewsList;
            default:
                return null;
        }
    }

    /**
     * Detect new item on server.
     *
     * @param aNewsType type of news
     * @return true if news on server was updated.
     */
    private boolean hasFreshNews(int aNewsType) {
        if (DEBUG_MOD) {
            switch (aNewsType) {
                case R.id.russia:
                    return sRCallCounter++ < 1;
                case R.id.world:
                    return sWCallCounter++ < 1;
                case R.id.science:
                    return sSCallCounter++ < 1;
                default:
                    return true;
            }
        } else {
            //TODO: implement this method.
            return true;
        }
    }
}
