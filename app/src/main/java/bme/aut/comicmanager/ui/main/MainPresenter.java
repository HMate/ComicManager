package bme.aut.comicmanager.ui.main;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.ui.Presenter;

/**
 * Created by mhidvegi on 2016.04.11..
 * The MVP presenter class, which implements the functionality for the Main Screen
 */
public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    ComicsInteractor comicsInteractor;

    @Override
    public void attachScreen(MainScreen screen){
        super.attachScreen(screen);
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen(){
        super.detachScreen();
    }

    public void addNewComic(String title, String editor){
        comicsInteractor.addComic(title,editor);
        screen.showComicCount(comicsInteractor.getComicCount());
    }

    public void showNewComic(){
        screen.showComicCount(comicsInteractor.getComicCount());

        List<Comic> comics;
        try{
            comics = comicsInteractor.getComics();
        } catch (Exception e) {
            comics = comicsInteractor.getComicsDb();
        }

        if(comics.size() > 0) {
            String title = comics.get(0).getTitle();
            screen.showComicName(title);
        }else{
            screen.showComicName("No comics :(");
        }
    }
}
