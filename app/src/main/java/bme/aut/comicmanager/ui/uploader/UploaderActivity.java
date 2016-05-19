package bme.aut.comicmanager.ui.uploader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;

public class UploaderActivity extends AppCompatActivity implements UploaderScreen{

    @Inject
    UploaderPresenter uploaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploader);

        ComicManagerApplication.injector.inject(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        uploaderPresenter.attachScreen(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        uploaderPresenter.detachScreen();
    }
}
