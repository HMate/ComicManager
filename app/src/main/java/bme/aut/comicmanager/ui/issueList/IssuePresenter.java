package bme.aut.comicmanager.ui.issueList;

import bme.aut.comicmanager.ui.Presenter;
import bme.aut.comicmanager.ui.browser.BrowserScreen;

/**
 * Created by i7 on 2016.05.21..
 */
public class IssuePresenter extends Presenter<IssueScreen> {

    @Override
    public void attachScreen(IssueScreen screen) {
        super.attachScreen(screen);
//        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void addNewIssue(long comicId){
        if(comicId > -1) {
            screen.GotoIssueUploader(comicId);
        }
    }
}
