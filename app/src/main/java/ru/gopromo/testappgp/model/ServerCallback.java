package ru.gopromo.testappgp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.gopromo.testappgp.presenter.LentaPresenter;
import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.data_model.Rss;

/**
 * Callback for retrofit async request.
 */
public class ServerCallback implements Callback<Rss> {

    private LentaPresenter mPresenter;
    private LentaModel mModel;
    private int mNewsType;

    /**
     * Constructor.
     *
     * @param aPresenter {@link LentaPresenter} for post callback with data.
     * @param aModel {@link LentaModel} for caching data.
     * @param aNewsType type of news.
     */
    public ServerCallback(LentaPresenter aPresenter, LentaModel aModel, int aNewsType) {
        mPresenter = aPresenter;
        mModel = aModel;
        mNewsType = aNewsType;
    }

    @Override
    public void onResponse(Call<Rss> aCall, Response<Rss> aResponse) {
        if (mPresenter != null) {
            List<Item> news = aResponse.body().getChannel().getItems();
            mPresenter.onListNewsUpdated(news);
            mModel.saveDataNewsModel(news, mNewsType);
        }
    }

    @Override
    public void onFailure(Call<Rss> aCall, Throwable aT) {
        mPresenter.onListNewsUpdated(null);
    }
}
