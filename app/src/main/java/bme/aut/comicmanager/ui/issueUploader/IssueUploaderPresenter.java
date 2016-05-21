package bme.aut.comicmanager.ui.issueUploader;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicIssueDetails;
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

    public void refreshScreen(long comicId, long issueId){
        if(comicId > -1) {
            Comic c = comicsInteractor.getComic(comicId);
            String comicTitle = c.getTitle();
            screen.FillComicFields(comicTitle);
        }
        if(issueId > -1) {
            ComicIssueDetails c = comicsInteractor.getIssueDetails(issueId);
            String issueTitle = c.getTitle();
            Integer num = c.getIssueNumber();
            String issueNumber;
            if(num == null)
                issueNumber = "";
            else
                issueNumber = num.toString();
            String published = c.getPublished();
            String editor = c.getEditor();
            String writer = c.getWriter();
            String penciler = c.getPenciler();
            screen.FillIssueFields(issueTitle, issueNumber, published, editor, writer, penciler);
        }
    }

    public void saveIssue(long comicId, long issueId, String issueNumberString, String issueTitle, String published, String editorName, String writerName, String pencilerName){
        if(issueTitle == null || issueTitle.isEmpty()){
            screen.ShowError();
            return;
        }
        int issueNumber;
        try {
            issueNumber = Integer.valueOf(issueNumberString);
        }catch (NumberFormatException ne){
            screen.ShowError();
            return;
        }
        if(issueId == -1) {
            comicsInteractor.addNewIssue(comicId, issueNumber, issueTitle, published, editorName, writerName, pencilerName);
            screen.GoBackToParentScreen();
            return;
        }
        if(issueId > -1){
            comicsInteractor.editIssue(comicId, issueId, issueNumber, issueTitle, published, editorName, writerName, pencilerName);
            screen.GoBackToParentScreen();
            return;
        }
        screen.ShowError();
    }

}
