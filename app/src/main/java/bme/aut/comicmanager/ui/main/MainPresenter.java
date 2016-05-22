package bme.aut.comicmanager.ui.main;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mhidvegi on 2016.04.11..
 * The MVP presenter class, which implements the functionality for the Main Screen
 */
public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(MainScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){
        super.detachScreen();
    }

    public void showNewComic(){
        List<Comic> comics =  comicsInteractor.getComics();
        screen.showRecentComics(comics);
    }

    public void handleBrowserClick(){
        screen.GotoComicBrowser();
    }

    public void handleSearcherClick(){
        screen.GotoComicSearcher();
    }

    public void showIssuesFor(long comicId){
        screen.GotoIssueList(comicId);
    }
}
