package bme.aut.comicmanager.comics;

import android.util.Log;

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

    @Override
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

    @Override
    public Comic getComic(long comicId){
        List<Comic> comics;
        try{
            comics = getComicsNetwork();
        } catch (Exception e) {
            comics = getComicsDb();
        }

        for(Comic c : comics){
            if(c.getComicId() == comicId){
                return c;
            }
        }
        Log.d("comic interactor", "Couldn't find comic with id: " + comicId);
        return comics.get(0);
    }

    @Override
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

    @Override
    public List<ComicIssue> getIssuesForComic(long comicId){
        List<ComicIssue> issues;
        try{
            issues = getIssuesForComicNetwork(comicId);
        } catch (Exception e) {
            issues = getIssuesForComicDb(comicId);
        }
        return issues;
    }

    public List<ComicIssue> getIssuesForComicNetwork(long comicId) throws Exception{
        Response<InlineResponse2001> response;
        Call<InlineResponse2001> call = comicsApi.comicsComicIdGet(comicId);

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

    public List<ComicIssue> getIssuesForComicDb(long comicId){
        List list = comicsLocalDb.getIssuesForId(comicId);
        return list;
    }

    @Override
    public void addNewIssue(long placeholder){
        // TODO
    }

    @Override
    public ComicIssueDetails getIssueDetails(long issueId) {
        ComicIssueDetails details;
        try{
            details = getIssueDetailsNetwork(issueId);
        } catch (Exception e) {
            details = getIssueDetailsDb(issueId);
        }
        return details;
    }

    public ComicIssueDetails getIssueDetailsNetwork(long issueId) throws Exception{
        Response<InlineResponse2002> response;
        Call<InlineResponse2002> call = comicsApi.issuesIssueIdGet(issueId);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
        return response.body().getData().get(0);
    }

    public ComicIssueDetails getIssueDetailsDb(long issueId){
        ComicIssueDetails details = comicsLocalDb.getIssueDetails(issueId);
        return details;
    }

    public long getComicCount(){
        long count = 1;
        return count;
    }
}
