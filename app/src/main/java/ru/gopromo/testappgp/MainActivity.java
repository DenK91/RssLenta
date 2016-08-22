package ru.gopromo.testappgp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import ru.gopromo.testappgp.presenter.LentaPresenter;
import ru.gopromo.testappgp.presenter.LentaPresenterImpl;
import ru.gopromo.testappgp.view.LentaViewImpl;

/**
 * Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private LentaPresenter mPresenter;

    @Override
    protected void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new LentaPresenterImpl(getFragmentManager(), new LentaViewImpl(
                (ListView) findViewById(R.id.listNews))
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu aMenu) {
        getMenuInflater().inflate(R.menu.main_menu, aMenu);
        MenuItem table = aMenu.findItem(R.id.russia);
        table.setChecked(true);
        mPresenter.updateListNews(R.id.russia);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem aItem) {
        switch (aItem.getItemId()) {
            case R.id.russia:
            case R.id.world:
            case R.id.science:
                aItem.setChecked(!aItem.isChecked());
                mPresenter.updateListNews(aItem.getItemId());
                return true;
            default:
                return super.onOptionsItemSelected(aItem);
        }
    }
}
