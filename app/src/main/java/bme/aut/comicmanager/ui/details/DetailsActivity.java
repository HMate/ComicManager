package bme.aut.comicmanager.ui.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen{

    @Inject
    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ComicManagerApplication.injector.inject(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        detailsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        detailsPresenter.detachScreen();
    }
}
