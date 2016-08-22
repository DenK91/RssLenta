package ru.gopromo.testappgp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import ru.gopromo.testappgp.model.Item;
import ru.gopromo.testappgp.model.Rss;

public class DataKeeper extends Fragment implements LentaModel {

    private static final String TAG_DATA_KEEPER_FRAGMENT = "data_keeper_fragment";

    private interface LentaApi {
        @GET("/rss/news/science")
        Call<Rss> getScienceNews();

        @GET("/rss/news/russia")
        Call<Rss> getRussianNews();

        @GET("/rss/news/world")
        Call<Rss> getWorldNews();
    }

    private static final String BASE_LENTA_URL = "https://lenta.ru";

    private LentaApi mLentaRssAPI;

    public static DataKeeper getInstance(FragmentManager aFragmentManager) {
        DataKeeper dataKeeper = (DataKeeper) aFragmentManager.findFragmentByTag(TAG_DATA_KEEPER_FRAGMENT);
        if (dataKeeper == null) {
            dataKeeper = new DataKeeper();
            aFragmentManager.beginTransaction().add(dataKeeper, TAG_DATA_KEEPER_FRAGMENT).commit();
        }
        return dataKeeper;
    }

    private List<Item> mNewsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setRetainInstance(true);

        mLentaRssAPI = new Retrofit.Builder()
                .baseUrl(BASE_LENTA_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
                .create(LentaApi.class);
    }

    @Override
    public void newsRequest(final LentaPresenter aPresenter, int aNewsType) {
        Call<Rss> apiCall = null;
        switch (aNewsType) {
            case R.id.russia:
                apiCall = mLentaRssAPI.getRussianNews();
                break;
            case R.id.world:
                apiCall = mLentaRssAPI.getWorldNews();
                break;
            case R.id.science:
                apiCall = mLentaRssAPI.getScienceNews();
                break;
        }

        if (apiCall == null) {
            return;
        }

        apiCall.enqueue(new Callback<Rss>() {
                    @Override
                    public void onResponse(Call<Rss> aCall, Response<Rss> aResponse) {
                        if (aPresenter != null) {
                            aPresenter.onListNewsUpdated(aResponse.body().getChannel().getItems());
                        }
                    }

                    @Override
                    public void onFailure(Call<Rss> aCall, Throwable aT) {
                        if (aPresenter != null) {
                            aPresenter.onListNewsUpdated(null);
                        }
                    }
                });
    }

    public void setTrackList(List<Item> aTrackList) {
        Collections.copy(mNewsList, aTrackList);
    }

    public List<Item> getTrackList() {
        return mNewsList;
    }

}
