package bme.aut.comicmanager.ui.issueList;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.ComicIssue;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class IssueListPresenter extends Presenter<IssueListScreen> {

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(IssueListScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){super.detachScreen();}

    public void addNewIssue(){
        screen.GotoIssueUploader(screen.getComicId());
    }

    public void refreshIssues(){
        List<ComicIssue> comics = comicsInteractor.getIssuesForComic(screen.getComicId());
        screen.showIssues(comics);
    }

    public void handleIssueTouch(long issueId){
        screen.GotoIssueDetails(issueId);
    }
}
