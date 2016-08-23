package ru.gopromo.testappgp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ru.gopromo.testappgp.data_model.Item;

/**
 * Activity for showing news item.
 */
public class NewsActivity extends AppCompatActivity {

    private static final String TAG = "denk";
    private static final String KEY_ITEM_BUNDLE = "itemBundleKey";

    /**
     * Start activity for show news item.
     *
     * @param aContext {@link Context}.
     * @param aItem {@link Item} to show.
     */
    public static void startNewsView(Context aContext, Item aItem) {
        Intent startNewsIntent = new Intent(aContext, NewsActivity.class);
        startNewsIntent.putExtra(NewsActivity.KEY_ITEM_BUNDLE, aItem.linkToBundle());
        aContext.startActivity(startNewsIntent);
    }

    @Override
    protected void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_news);

        String link = Item.getLinkFromBundle(getIntent().getBundleExtra(KEY_ITEM_BUNDLE));
        Log.d(TAG, "onCreate: " + link);
    }
}
