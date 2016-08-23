package ru.gopromo.testappgp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import ru.gopromo.testappgp.presenter.LentaPresenter;
import ru.gopromo.testappgp.presenter.LentaPresenterImpl;
import ru.gopromo.testappgp.view.LentaView;
import ru.gopromo.testappgp.view.ViewContainer;

/**
 * Main activity.
 */
public class MainActivity extends AppCompatActivity implements LentaView {

    private LentaPresenter mPresenter;
    private ViewContainer mMainActivityView;

    @Override
    protected void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        mMainActivityView = new ViewContainer(
                (ListView) findViewById(R.id.listNews),
                (TextView) findViewById(R.id.errorTextView));
        mPresenter = new LentaPresenterImpl(this, getFragmentManager(), this);
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
    public ViewContainer getViewContainer() {
        return mMainActivityView;
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy(this);
        super.onDestroy();
    }
}
