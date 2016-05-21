package bme.aut.comicmanager.ui.searcher;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class SearchPresenter extends Presenter<SearchScreen>{
    @Override
    public void attachScreen(SearchScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){super.detachScreen();}

    public void startSearch(){
        screen.showSearchResults();
    }
}
