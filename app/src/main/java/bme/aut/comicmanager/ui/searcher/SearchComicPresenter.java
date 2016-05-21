package bme.aut.comicmanager.ui.searcher;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class SearchComicPresenter extends Presenter<SearchComicScreen>{
    @Override
    public void attachScreen(SearchComicScreen screen){
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen(){super.detachScreen();}

    public void startSearch(){
        screen.showSearchResults();
    }
}
