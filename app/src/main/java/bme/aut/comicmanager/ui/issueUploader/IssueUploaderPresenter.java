package bme.aut.comicmanager.ui.issueUploader;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by i7 on 2016.05.20..
 */
public class IssueUploaderPresenter extends Presenter<IssueUploaderScreen>{

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(IssueUploaderScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){super.detachScreen();}

}
