package bme.aut.comicmanager.ui.issueUploader;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.Comic;
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

    public void refreshScreen(){
        long comicId = screen.getComicId();
        Comic c = comicsInteractor.getComic(comicId);
        String comicTitle = c.getTitle();
        screen.FillTextFields(comicTitle);
    }

    public void addNewIssue(long comicId, String issueNumberString, String issueTitle, String published, String editorName, String writerName, String pencilerName){
        int issueNumber = Integer.valueOf(issueNumberString);
        comicsInteractor.addNewIssue(comicId, issueNumber, issueTitle, published, editorName, writerName, pencilerName);
        screen.GoBackToParentScreen();
    }

}
