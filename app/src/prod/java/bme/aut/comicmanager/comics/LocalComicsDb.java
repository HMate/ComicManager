package bme.aut.comicmanager.comics;

import java.util.List;

/**
 * Created by i7 on 2016.05.22..
 */
public class LocalComicsDb implements ComicsDb {
    public void clearDb(){

    }
    public List<Comic> getComics(){
        return null;
    }
    public List<Comic> getComicsByQuery(String title){
        return null;
    }
    public void addComic(String title){}
    public void editComic(long comicId, String title){}
    public void deleteComic(long comicId){}
    public List<ComicIssue> getIssuesForId(long comicId){
        return null;
    }
    public List<ComicIssue> getIssuesByQuery(String title, String creator, String published){
        return null;
    }
    public ComicIssueDetails getIssueDetails(long issueId){
        return null;
    }

    public List<ComicIssueDetails> getComicIssues(){
        return null;
    }
    public void addNewIssue(ComicIssueDetails details){}
    public void addNewIssue(long comicId, int issueNumber, String issueTitle,
                     String published, String editorName,
                     String writerName, String pencilerName){}

    public void editIssue(ComicIssueDetails details){}
    public void deleteIssue(long issueId){}
    public void initializeMockComicServer(){}
}
