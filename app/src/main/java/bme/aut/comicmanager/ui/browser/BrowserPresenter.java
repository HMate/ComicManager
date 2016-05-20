package bme.aut.comicmanager.ui.browser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class BrowserPresenter extends Presenter<BrowserScreen> {

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(BrowserScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);

    }

    @Override
    public void detachScreen(){
        super.detachScreen();
    }

    public void addComic(){
        // TODO: send to comicUploader screen
        comicsInteractor.addComic("SheHulk");
    }

    public void refreshComics(){
        List<Comic> comics = comicsInteractor.getComics();
        screen.showComics(comics);
    }

    public void handleComicTouch(long comicId){
        screen.GotoComicIssues(comicId);
    }
}
