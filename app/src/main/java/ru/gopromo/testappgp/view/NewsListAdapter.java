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

public class NewsListAdapter extends ArrayAdapter<Item> {

    private LayoutInflater mInflater;
    private int mLayoutRes;
    private LentaPresenter mPresenter;

    public NewsListAdapter(Context aContext, List<Item> aData,
                           int aLayoutRes, LentaPresenter aPresenter) {
        super(aContext, aLayoutRes, aData);
        mPresenter = aPresenter;
        mLayoutRes = aLayoutRes;
        mInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(mLayoutRes, parent, false);

            TrackViewHolder newsViewHolder = new TrackViewHolder();

            newsViewHolder.titleNews = (TextView) view.findViewById(R.id.news_title);
            newsViewHolder.descriptionNews = (TextView) view.findViewById(R.id.news_description);

            view.setTag(newsViewHolder);
        }

        TrackViewHolder viewHolder = (TrackViewHolder) view.getTag();

        final Item currentNews = getItem(position);

        viewHolder.titleNews.setText(currentNews.getTitle());
        viewHolder.descriptionNews.setText(currentNews.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onItemClicked(currentNews);
            }
        });

        return view;
    }

    static class TrackViewHolder {
        TextView titleNews;
        TextView descriptionNews;
    }
}
