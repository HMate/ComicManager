package bme.aut.comicmanager.ui.browser;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by i7 on 2016.05.21..
 */
public class ComicListPresenter extends Presenter<ComicListScreen> {

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(ComicListScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);

    }

    @Override
    public void detachScreen(){
        super.detachScreen();
    }

    public void showAllComics(){
        List<Comic> comics = comicsInteractor.getComics();
        screen.showComics(comics);
    }

    public void searchByTitle(String titleText){
        List<Comic> comics = comicsInteractor.getComicsByQuery(titleText);
        screen.showComics(comics);
    }

    public void handleComicTouch(long comicId){
        screen.GotoComicIssues(comicId);
    }
}
