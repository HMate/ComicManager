package bme.aut.comicmanager.comics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhidvegi on 2016.05.19..
 */
public class MockComicsDb {

    public List<Comic> comics = new ArrayList<Comic>();
    private boolean isInitialized = false;

    public void addComic(String title){
        Comic c = new Comic();
        c.setTitle(title);
        c.setComicId((long) comics.size());
        comics.add(c);
    }

    public List<Comic> getComics(){
        if(!isInitialized){
            addComic("Spider-Man");
            addComic("Captain America");
            addComic("Super Man");
            isInitialized = true;
        }

        return comics;
    }
}
