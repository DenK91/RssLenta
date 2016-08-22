package ru.gopromo.testappgp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import ru.gopromo.testappgp.model.Item;
import ru.gopromo.testappgp.model.Rss;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "denk";

    private interface LentaApi {
        @GET("/rss/news/science")
        Call<Rss> getScienceNews();
    }

    private static final String BASE_URL = "https://lenta.ru";
    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();

    private Callback mResponseCallback = new Callback<Rss>() {
        @Override
        public void onResponse(Call<Rss> aCall, Response<Rss> aResponse) {
            List<Item> items = aResponse.body().getChannel().getItems();
            Log.d(TAG, "items.size=" + items.size());
            for (Item item : items) {
                Log.d(TAG, item.getTitle());
            }
            String url = items.get(0).getLink();
            Log.d(TAG, "url: " + url);
        }

        @Override
        public void onFailure(Call<Rss> aCall, Throwable t) {
            Log.d(TAG, "onFailure: " + t.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRetrofit.create(LentaApi.class)
                .getScienceNews()
                .enqueue(mResponseCallback);
    }

}
