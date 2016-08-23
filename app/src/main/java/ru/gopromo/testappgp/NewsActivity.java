package ru.gopromo.testappgp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ru.gopromo.testappgp.data_model.Item;

/**
 * Activity for showing news item.
 */
public class NewsActivity extends AppCompatActivity {

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

        WebView webView = (WebView) findViewById(R.id.webView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView errorTextView = (TextView) findViewById(R.id.errorTextView);
        progressBar.setVisibility(View.VISIBLE);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView aView, int aNewProgress) {
                progressBar.setProgress(aNewProgress);
                super.onProgressChanged(aView, aNewProgress);
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView aView, String aUrl, Bitmap aFavicon) {
                super.onPageStarted(aView, aUrl, aFavicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView aView, String aUrl) {
                super.onPageFinished(aView, aUrl);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedHttpError(WebView aView,
                                            WebResourceRequest aRequest,
                                            WebResourceResponse aErrorResponse) {
                super.onReceivedHttpError(aView, aRequest, aErrorResponse);
                progressBar.setVisibility(View.GONE);
                errorTextView.setText(aErrorResponse.toString());
                errorTextView.setVisibility(View.VISIBLE);
            }
        });

        String link = Item.getLinkFromBundle(getIntent().getBundleExtra(KEY_ITEM_BUNDLE));
        webView.loadUrl(link);
    }
}
