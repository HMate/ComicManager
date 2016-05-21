package bme.aut.comicmanager.ui.searcher;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by i7 on 2016.05.21..
 */
public class SearchIssuePresenter extends Presenter<SearchIssueScreen> {
    @Override
    public void attachScreen(SearchIssueScreen screen){
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen(){super.detachScreen();}

    public void startSearch(){
        screen.showSearchResults();
    }
}
