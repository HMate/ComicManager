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
    MockComicsDb comicsLocalDb;

    public MockComicsInteractor(){
        ComicManagerApplication.injector.inject(this);
    }

    public void addComic(String title){
        try{
            addComicNetwork(title);
        } catch (Exception e) {
            comicsLocalDb.addComic(title);
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
        List list = comicsLocalDb.getComics();
        return list;
    }

    public List<ComicIssue> getIssuesForComic(long id){
        List<ComicIssue> issues;
        try{
            issues = getIssuesForComicNetwork(id);
        } catch (Exception e) {
            issues = getIssuesForComicDb(id);
        }
        return issues;
    }

    public List<ComicIssue> getIssuesForComicNetwork(long Id) throws Exception{
        Response<InlineResponse2001> response;
        Call<InlineResponse2001> call = comicsApi.comicsComicIdGet(Id);

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

    public List<ComicIssue> getIssuesForComicDb(long Id){
        List list = comicsLocalDb.getIssuesForId(Id);
        return list;
    }

    public void addNewIssue(long id){
        // TODO
    }

    public long getComicCount(){
        long count = 1;
        return count;
    }
}
