package bme.aut.comicmanager.ui.issueList;

import java.util.ArrayList;
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

    public void searchById(long comicId){
        List<ComicIssue> comics;
        if(comicId > -1) {
             comics = comicsInteractor.getIssuesForComic(comicId);
        }else {
            comics = new ArrayList<>();
        }
        screen.showIssues(comics);
    }

    public void searchByArguments(String titleText, String creatorString, String publishedString){

        List<ComicIssue> comics = comicsInteractor.getIssuesByQuery(titleText, creatorString, publishedString);
        screen.showIssues(comics);
    }

    public void handleIssueTouch(ComicIssue issue){
        screen.GotoIssueDetails(issue.getIssueId());
    }
}
