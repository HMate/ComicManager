package bme.aut.comicmanager.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.ui.issueList.IssueListActivity;
import bme.aut.comicmanager.ui.searcher.SearchActivity;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainScreen{

    @BindViews({R.id.main_recent_cover1, R.id.main_recent_cover2, R.id.main_recent_cover3})
    List<ImageView> covers;
    @BindViews({R.id.main_recent_title1, R.id.main_recent_title2, R.id.main_recent_title3})
    List<TextView> coverTitles;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ComicManagerApplication.injector.inject(this);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.main_browser_btn)
    public void handleBrowserButton(){
        mainPresenter.handleBrowserClick();
    }

    @OnClick(R.id.main_searcher_btn)
    public void handleSearchButton(){
        mainPresenter.handleSearcherClick();
    }

    public void GotoComicBrowser(){
        Intent browserIntent = new Intent(this, bme.aut.comicmanager.ui.browser.BrowserActivity.class);
        startActivity(browserIntent);
    }

    public void GotoComicSearcher(){
        Intent searcherIntent = new Intent(this, SearchActivity.class);
        startActivity(searcherIntent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mainPresenter.showNewComic();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mainPresenter.detachScreen();
    }

    public void showRecentComics(List<Comic> recentComics){
        int size = recentComics.size();
        for(int i = 0; i < 3 && i < size; i++){
            final Comic c = recentComics.get(size - i - 1);

            View.OnClickListener click = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainPresenter.showIssuesFor(c.getComicId());
                }
            };

            covers.get(i).setImageResource(R.mipmap.ic_example_img);
            covers.get(i).setOnClickListener(click);
            coverTitles.get(i).setText(c.getTitle());
            coverTitles.get(i).setOnClickListener(click);
        }
    }

    public void GotoIssueList(long comicId){
        Intent issueListIntent = new Intent(this, IssueListActivity.class);
        issueListIntent.putExtra(IssueListActivity.COMIC_ID, comicId);
        startActivity(issueListIntent);
    }
}
