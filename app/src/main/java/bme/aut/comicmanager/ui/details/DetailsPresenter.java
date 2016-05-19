package bme.aut.comicmanager.ui.details;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public class DetailsPresenter extends Presenter<DetailsScreen> {

    @Override
    public void attachScreen(DetailsScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){
        super.detachScreen();
    }
}
