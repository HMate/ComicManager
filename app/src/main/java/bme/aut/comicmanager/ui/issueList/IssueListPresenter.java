package bme.aut.comicmanager.ui.issueList;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class IssueListPresenter extends Presenter<IssueListScreen> {
    @Override
    public void attachScreen(IssueListScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){super.detachScreen();}
}
