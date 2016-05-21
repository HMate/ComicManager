package bme.aut.comicmanager.comics;

import java.util.List;

/**
 * Created by mhidvegi on 2016. 04. 25..
 */
public interface ComicsInteractor {
    long getComicCount();

    Comic getComic(long comicId);
    List<Comic> getComics();
    List<Comic> getComicsDb();
    void addNewComic(String title);

    List<ComicIssue> getIssuesForComic(long id);
    void addNewIssue(long comicId, int issueNumber, String issueTitle, String published, String editorName, String writerName, String pencilerName);

    ComicIssueDetails getIssueDetails(long issueId);
}
