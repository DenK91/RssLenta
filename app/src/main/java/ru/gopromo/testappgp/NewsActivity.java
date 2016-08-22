package ru.gopromo.testappgp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ru.gopromo.testappgp.data_model.Item;

public class NewsActivity extends AppCompatActivity {

    private static final String TAG = "denk";
    public static String KEY_ITEM_BUNDLE = "itemBundleKey";

    public static void startNewsView(Context aContext, Item aItem) {
        Intent startNewsIntent = new Intent(aContext, NewsActivity.class);
        startNewsIntent.putExtra(NewsActivity.KEY_ITEM_BUNDLE, aItem.linkToBundle());
        aContext.startActivity(startNewsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String link = Item.getLinkFromBundle(getIntent().getBundleExtra(KEY_ITEM_BUNDLE));
        Log.d(TAG, "onCreate: " + link);
    }
}
