package bme.aut.comicmanager.ui.details;

import bme.aut.comicmanager.comics.ComicIssueDetails;

/**
 * Created by mhidvegi on 2016. 04. 25..
 */
public interface DetailsScreen {
    void showIssueDetails(ComicIssueDetails details);
    long getIssueId();
}
