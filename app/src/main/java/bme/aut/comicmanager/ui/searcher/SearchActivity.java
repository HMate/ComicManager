package bme.aut.comicmanager.ui.searcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;

public class SearchActivity extends AppCompatActivity implements SearchScreen {

    @Inject
    SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ComicManagerApplication.injector.inject(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        searchPresenter.attachScreen(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        searchPresenter.detachScreen();
    }
}
