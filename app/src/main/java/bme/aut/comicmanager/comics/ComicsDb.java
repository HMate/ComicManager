package bme.aut.comicmanager.comics;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i7 on 2016.05.22..
 */
public interface ComicsDb {
    void clearDb();
    List<Comic> getComics();
    List<Comic> getComicsByQuery(String title);
    void addComic(String title);
    void editComic(long comicId, String title);
    void deleteComic(long comicId);
    List<ComicIssue> getIssuesForId(long comicId);
    List<ComicIssue> getIssuesByQuery(String title, String creator, String published);
    ComicIssueDetails getIssueDetails(long issueId);

    List<ComicIssueDetails> getComicIssues();
    void addNewIssue(ComicIssueDetails details);
    void addNewIssue(long comicId, int issueNumber, String issueTitle,
                     String published, String editorName,
                     String writerName, String pencilerName);

    void editIssue(ComicIssueDetails details);
    void deleteIssue(long issueId);
    void initializeMockComicServer();
}
