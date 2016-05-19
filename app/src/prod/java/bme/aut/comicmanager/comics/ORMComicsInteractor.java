package bme.aut.comicmanager.comics;

import java.util.List;

/**
 * Created by mhidvegi on 2016.04.11..
 * Supplements Comics related models
 */
public class ORMComicsInteractor implements ComicsInteractor{

    public void addComic(String title, String editor){
        Comic newComic = new Comic();
        newComic.setTitle(title);
        newComic.save();
    }

    public String getNewestComic(){
        if(getComicCount() > 0) {
            Comic lastComic = Comic.findById(Comic.class, 1);
            return lastComic.getTitle();
        }
        return "No comic!";
    }

    public List<Comic> getComicsDb(){
        List list = Comic.listAll(Comic.class);
        return list;
    }

    public List<Comic> getComics() throws Exception{
        List list = Comic.listAll(Comic.class);
        return list;
    }

    public long getComicCount(){
        long count = Comic.count(Comic.class);
        return count;
    }
}
