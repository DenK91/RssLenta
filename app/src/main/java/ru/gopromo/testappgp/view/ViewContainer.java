package ru.gopromo.testappgp.view;

import android.widget.ListView;
import android.widget.TextView;

public class ViewContainer {

    private ListView mListView;
    private TextView mErrorTextView;

    public ViewContainer(ListView aListView, TextView aErrorTextView) {
        mListView = aListView;
        mErrorTextView = aErrorTextView;
    }

    public ListView getListView() {
        return mListView;
    }

    public void setListView(ListView aListView) {
        mListView = aListView;
    }

    public TextView getErrorTextView() {
        return mErrorTextView;
    }

    public void setErrorTextView(TextView aErrorTextView) {
        this.mErrorTextView = aErrorTextView;
    }
}
