package bme.aut.comicmanager.ui.details;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicIssueDetails;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class DetailsPresenter extends Presenter<DetailsScreen> {

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(DetailsScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){
        super.detachScreen();
    }

    public void showIssueDetails(){
        ComicIssueDetails details = comicsInteractor.getIssueDetails(screen.getIssueId());
        screen.showIssueDetails(details);
    }

    public Comic getComic(long comicId){
        Comic comic = comicsInteractor.getComic(comicId);
        return comic;
    }
}
