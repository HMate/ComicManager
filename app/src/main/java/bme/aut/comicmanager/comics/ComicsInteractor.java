package bme.aut.comicmanager.comics;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.network.ComicsApi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by mhidvegi on 2016. 04. 25..
 */
public class ComicsInteractor {

    @Inject
    ComicsApi comicsApi;
    @Inject
    ComicsDb comicsLocalDb;

    public ComicsInteractor(){
        ComicManagerApplication.injector.inject(this);
    }

    public void addNewComic(String title){
        try{
            addNewComicNetwork(title);
        } catch (Exception e) {
            comicsLocalDb.addComic(title);
        }
    }

    public void addNewComicNetwork(String title) throws Exception{

        Comic c = new Comic(null, title);

        Response<Void> response;
        Call<Void> call = comicsApi.comicsNewPost(c);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
    }

    public void editComic(long comicId, String title){
        try{
            editComicNetwork(comicId, title);
        } catch (Exception e) {
            comicsLocalDb.editComic(comicId, title);
        }
    }

    public void editComicNetwork(long comicId, String title) throws Exception{

        Comic c = new Comic(comicId, title);
        Response<Void> response;
        Call<Void> call = comicsApi.comicsComicIdPost(comicId, c);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
    }

    public void deleteComic(long comicId){
        try{
            deleteComicNetwork(comicId);
        } catch (Exception e) {
            comicsLocalDb.deleteComic(comicId);
        }
    }

    public void deleteComicNetwork(long comicId) throws Exception{

        Response<Void> response;
        Call<Void> call = comicsApi.comicsComicIdDelete(comicId, new ComicIssue());

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
    }

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

    public List<Comic> getComicsByQuery(String title){
        List<Comic> comics;
        try{
            comics = getComicsByQueryNetwork(title);
        } catch (Exception e) {
            comics = getComicsByQueryDb(title);
        }
        return comics;
    }

    public List<Comic> getComicsByQueryNetwork(String title) throws Exception{
        Response<InlineResponse200> response;
        Call<InlineResponse200> call = comicsApi.comicsGet(title);

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

    public List<Comic> getComicsByQueryDb(String title){
        List list = comicsLocalDb.getComicsByQuery(title);
        return list;
    }

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

    public List<ComicIssue> getIssuesByQuery(String title, String creator, String published){
        List<ComicIssue> issues;
        try{
            issues = getIssuesByQueryNetwork(title, creator, published);
        } catch (Exception e) {
            issues = getIssuesByQueryDb(title, creator, published);
        }
        return issues;
    }

    public List<ComicIssue> getIssuesByQueryNetwork(String title, String creator, String published) throws Exception{
        Response<InlineResponse2001> response;
        Call<InlineResponse2001> call = comicsApi.issuesGet(title, creator, published);

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

    public List<ComicIssue> getIssuesByQueryDb(String title, String creator, String published){
        List list = comicsLocalDb.getIssuesByQuery(title, creator, published);
        return list;
    }

    public void addNewIssue(long comicId, int issueNumber, String issueTitle,
                            String published, String editorName,
                            String writerName, String pencilerName){
        try{
            addNewIssueNetwork(comicId, issueNumber, issueTitle,
                    published, editorName, writerName, pencilerName);
        } catch (Exception e) {
            comicsLocalDb.addNewIssue(comicId, issueNumber, issueTitle,
                    published, editorName, writerName, pencilerName);
        }
    }

    public void addNewIssueNetwork(long comicId, int issueNumber, String issueTitle,
                                   String published, String editorName,
                                   String writerName, String pencilerName) throws Exception{
        ComicIssueDetails details = new ComicIssueDetails();
        details.setComicId(comicId);
        details.setIssueNumber(issueNumber);
        details.setTitle(issueTitle);
        details.setEditor(editorName);
        details.setPenciler(pencilerName);
        details.setPublished(published);
        details.setWriter(writerName);
        details.setSummary("Placeholder");

        Response<Void> response;
        Call<Void> call = comicsApi.issuesNewPost(details);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
    }

    public void editIssue(long comicId, long issueId, int issueNumber, String issueTitle,
                          String published, String editorName,
                          String writerName, String pencilerName){
        ComicIssueDetails details = new ComicIssueDetails();
        details.setComicId(comicId);
        details.setIssueId(issueId);
        details.setIssueNumber(issueNumber);
        details.setTitle(issueTitle);
        details.setEditor(editorName);
        details.setPenciler(pencilerName);
        details.setPublished(published);
        details.setWriter(writerName);
        details.setSummary("Placeholder");
        try{
            editIssueNetwork(details);
        } catch (Exception e) {
            comicsLocalDb.editIssue(details);
        }
    }

    public void editIssueNetwork(ComicIssueDetails details) throws Exception{

        Response<Void> response;
        Call<Void> call = comicsApi.issuesIssueIdPost(details.getIssueId(), details);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
    }

    public void deleteIssue(long issueId){
        try{
            deleteIssueNetwork(issueId);
        } catch (Exception e) {
            comicsLocalDb.deleteIssue(issueId);
        }
    }

    public void deleteIssueNetwork(long issueId) throws Exception{
        Response<Void> response;
        Call<Void> call = comicsApi.issuesIssueIdDelete(issueId, null);

        try{
            response = call.execute();
        }catch(java.io.IOException e){
            throw new Exception("Network error executing GET comics!");
        }
        if(response.code() != 200){
            throw new Exception("Network error with GET!");
        }
    }

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
}
