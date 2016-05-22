package bme.aut.comicmanager.ui.main;

import java.util.List;

import bme.aut.comicmanager.comics.Comic;

/**
 * Created by mhidvegi on 2016.04.11..
 * Interface for the Main screen functionlities
 */
public interface MainScreen {
    void showRecentComics(List<Comic> recentComics);
    void GotoComicBrowser();
    void GotoComicSearcher();
}
