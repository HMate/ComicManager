package bme.aut.comicmanager.comics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhidvegi on 2016.05.19..
 */
public class MockComicsDb {

    List<Comic> comics = new ArrayList<Comic>();

    List<Comic> getComics(){
        return comics;
    }
}
