package bme.aut.comicmanager.comics;

import java.util.List;

/**
 * Created by mhidvegi on 2016. 04. 25..
 */
public interface ComicsInteractor {
    long getComicCount();
    void addComic(String title, String editor);

    List<Comic> getComicsDb();
    List<Comic> getComics() throws Exception;
}
