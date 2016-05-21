package bme.aut.comicmanager.ui.browser;

import java.util.List;

import bme.aut.comicmanager.comics.Comic;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public interface ComicListScreen {

    void showComics(List<Comic> comicsToShow);
    void GotoComicIssues(long comicId);
    void GotoComicUploader();
}
