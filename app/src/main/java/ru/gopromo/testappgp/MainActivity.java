package ru.gopromo.testappgp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.gopromo.testappgp.data_model.Item;
import ru.gopromo.testappgp.presenter.LentaPresenter;
import ru.gopromo.testappgp.presenter.LentaPresenterImpl;
import ru.gopromo.testappgp.view.LentaView;
import ru.gopromo.testappgp.view.NewsListAdapter;

/**
 * Main activity.
 */
public class MainActivity extends AppCompatActivity implements LentaView {

    private LentaPresenter mPresenter;
    private List<Item> mNewsListData = new ArrayList<>();
    private NewsListAdapter mNewsListAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listNews);
        mPresenter = new LentaPresenterImpl(getFragmentManager(), this);
        mNewsListAdapter = new NewsListAdapter(this, mNewsListData,
                R.layout.newslist_item_list, mPresenter);
        mListView.setAdapter(mNewsListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu aMenu) {
        getMenuInflater().inflate(R.menu.main_menu, aMenu);
        aMenu.findItem(R.id.russia).setChecked(true);
        mPresenter.newsSelected(R.id.russia);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem aItem) {
        switch (aItem.getItemId()) {
            case R.id.russia:
            case R.id.world:
            case R.id.science:
                aItem.setChecked(!aItem.isChecked());
                mPresenter.newsSelected(aItem.getItemId());
                return true;
            default:
                return super.onOptionsItemSelected(aItem);
        }
    }

    @Override
    public void onLentaNewsUpdated(List<Item> aNews) {
        if (aNews != null) {
            if (aNews.size() > 0) {
                mNewsListData.clear();
                mNewsListData.addAll(aNews);
                mNewsListAdapter.notifyDataSetChanged();
            }
        }
    }
}
