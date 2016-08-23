package ru.gopromo.testappgp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.gopromo.testappgp.R;
import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.presenter.LentaPresenter;

/**
 * List adapter for news.
 */
public class NewsListAdapter extends ArrayAdapter<Item> {

    private LayoutInflater mInflater;
    private LentaPresenter mPresenter;

    /**
     * Constructor.
     *
     * @param aContext {@link Context}.
     * @param aData list items.
     * @param aLayoutRes layout id.
     * @param aPresenter {@link LentaPresenter}.
     */
    public NewsListAdapter(Context aContext, List<Item> aData,
                           int aLayoutRes, LentaPresenter aPresenter) {
        super(aContext, aLayoutRes, aData);
        mPresenter = aPresenter;
        mInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int aPosition, View aConvertView, ViewGroup aParent) {
        View view = aConvertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.newslist_item_list, aParent, false);

            NewsViewHolder newsViewHolder = new NewsViewHolder();

            newsViewHolder.mTitleNews = (TextView) view.findViewById(R.id.news_title);
            newsViewHolder.mDescriptionNews = (TextView) view.findViewById(R.id.news_description);

            view.setTag(newsViewHolder);
        }

        NewsViewHolder viewHolder = (NewsViewHolder) view.getTag();

        Item currentNews = getItem(aPosition);

        viewHolder.mTitleNews.setText(currentNews.getTitle());
        viewHolder.mDescriptionNews.setText(currentNews.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                mPresenter.onItemClicked(aPosition);
            }
        });

        return view;
    }

    /**
     * View holder.
     */
    static class NewsViewHolder {
        TextView mTitleNews;
        TextView mDescriptionNews;
    }
}
