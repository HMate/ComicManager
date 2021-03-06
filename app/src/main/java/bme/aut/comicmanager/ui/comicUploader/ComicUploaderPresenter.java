package bme.aut.comicmanager.ui.comicUploader;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class ComicUploaderPresenter extends Presenter<ComicUploaderScreen>{

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(ComicUploaderScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){super.detachScreen();}

    public void saveComic(long comicId, String title){
        if(title == null || title.isEmpty()){
            screen.ShowError();
            return;
        }

        if(comicId < 0) {
            comicsInteractor.addNewComic(title);
        }else {
            comicsInteractor.editComic(comicId, title);
        }
        screen.GoBackToParentScreen();
    }
}
