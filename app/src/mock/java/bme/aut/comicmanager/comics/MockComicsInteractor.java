package bme.aut.comicmanager.comics;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.network.ComicsApi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by i7 on 2016.04.11..
 * Supplemnets Comics related models
 */
public class MockComicsInteractor implements ComicsInteractor {

    @Inject
    ComicsApi comicsApi;
    @Inject
    MockComicsDb comicsDb;

    public MockComicsInteractor(){
        ComicManagerApplication.injector.inject(this);
    }

    public void addComic(String title){
        try{
            addComicNetwork(title);
        } catch (Exception e) {
            comicsDb.addComic(title);
        }
    }

    public void addComicNetwork(String title) throws Exception{

        Comic c = new Comic();
        c.setTitle(title);

        Response<InlineResponse200> response;
        Call call = comicsApi.comicsNewPost(c);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
    }

    public List<Comic> getComics(){
        List<Comic> comics;
        try{
            comics = getComicsNetwork();
        } catch (Exception e) {
            comics = getComicsDb();
        }
        return comics;
    }

    public List<Comic> getComicsNetwork() throws Exception{
        Response<InlineResponse200> response;
        Call<InlineResponse200> call = comicsApi.comicsGet(null);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
        return response.body().getData();
    }

    public List<Comic> getComicsDb(){
        List list = comicsDb.getComics();
        return list;
    }

    public long getComicCount(){
        long count = 1;
        return count;
    }
}
