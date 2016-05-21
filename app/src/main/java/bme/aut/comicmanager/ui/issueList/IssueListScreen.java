package bme.aut.comicmanager.ui.issueList;

import java.util.List;

import bme.aut.comicmanager.comics.ComicIssue;

/**
 * Created by mobsoft on 2016. 04. 25..
 */
public interface IssueListScreen {
    void showIssues(List<ComicIssue> issues);
    void GotoIssueDetails(long issueId);
    void GotoIssueUploader(long comicId);
    long getComicId();
}
