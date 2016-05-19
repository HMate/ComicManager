package bme.aut.comicmanager.ui.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;

public class BrowserActivity extends AppCompatActivity implements BrowserScreen {

    @Inject
    BrowserPresenter browserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        ComicManagerApplication.injector.inject(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        browserPresenter.attachScreen(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        browserPresenter.detachScreen();
    }
}
