package ru.gopromo.testappgp.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import ru.gopromo.testappgp.R;
import ru.gopromo.testappgp.data_model.Rss;

/**
 * Utility class for work with Lenta.ru rss.
 */
public final class LentaRu {

    private static final String BASE_LENTA_URL = "https://lenta.ru";

    private static LentaApi sLentaRssAPI;

    /**
     * Api.
     */
    public interface LentaApi {

        /**
         * Return list of science news.
         *
         * @return list of science news.
         */
        @GET("/rss/news/science")
        Call<Rss> getScienceNews();

        /**
         * Return list of russian news.
         *
         * @return list of russian news.
         */
        @GET("/rss/news/russia")
        Call<Rss> getRussianNews();

        /**
         * Return list of world news.
         *
         * @return list of world news.
         */
        @GET("/rss/news/world")
        Call<Rss> getWorldNews();
    }

    /**
     * hide constructor.
     */
    private LentaRu() {

    }

    /**
     * Create new instance API for work with Lenta.ru.
     * @return {@link LentaRu}.
     */
    private static LentaApi getInstance() {
        if (sLentaRssAPI == null) {
            sLentaRssAPI = new Retrofit.Builder()
                    .baseUrl(BASE_LENTA_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build()
                    .create(LentaApi.class);
        }
        return sLentaRssAPI;
    }

    /**
     * Return retrofit call to server by type.
     *
     * @param aNewsType type of news.
     * @return {@link Call < Rss >}.
     */
    public static Call<Rss> getCallToServerByType(int aNewsType) {
        switch (aNewsType) {
            case R.id.russia:
                return getInstance().getRussianNews();
            case R.id.world:
                return getInstance().getWorldNews();
            case R.id.science:
                return getInstance().getScienceNews();
            default:
                return null;
        }
    }
}
