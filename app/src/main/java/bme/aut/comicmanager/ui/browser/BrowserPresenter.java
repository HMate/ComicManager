package bme.aut.comicmanager.ui.browser;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by i7 on 2016.05.21..
 */
public class BrowserPresenter extends Presenter<BrowserScreen> {

    @Override
    public void attachScreen(BrowserScreen screen){
        super.attachScreen(screen);
//        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){
        super.detachScreen();
    }

    public void addComic(){
        screen.GotoComicUploader();
    }

}
