package bme.aut.comicmanager.ui.comicUploader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;

public class ComicUploaderActivity extends AppCompatActivity implements ComicUploaderScreen {

    @Inject
    ComicUploaderPresenter comicUploaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_uploader);

        ComicManagerApplication.injector.inject(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        comicUploaderPresenter.attachScreen(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        comicUploaderPresenter.detachScreen();
    }
}
