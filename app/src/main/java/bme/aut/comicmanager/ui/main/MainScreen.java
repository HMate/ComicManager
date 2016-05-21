package bme.aut.comicmanager.ui.main;

/**
 * Created by mhidvegi on 2016.04.11..
 * Interface for the Main screen functionlities
 */
public interface MainScreen {
    void showComicName(String comicToShow);
    void showComicCount(long comicCount);
    void GotoComicBrowser();
    void GotoComicSearcher();
}
