package ru.gopromo.testappgp.view;

import android.util.Log;
import android.widget.ListView;

import java.util.List;

import ru.gopromo.testappgp.view.LentaView;
import ru.gopromo.testappgp.data_model.Item;

public class LentaViewImpl implements LentaView {

    private static final String TAG = "denk";

    private ListView mNewsList;

    public LentaViewImpl(ListView aNewsList) {
        mNewsList = aNewsList;
    }

    @Override
    public void onLentaNewsUpdated(List<Item> aNews) {
        if (aNews != null) {
            Log.d(TAG, "items.size=" + aNews.size());
            for (Item item : aNews) {
                Log.d(TAG, item.getTitle());
            }
            //String url = aNews.get(0).getLink();
            //Log.d(TAG, "url: " + url);
        }
    }
}
