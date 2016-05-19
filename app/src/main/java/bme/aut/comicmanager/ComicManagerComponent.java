package bme.aut.comicmanager;

import javax.inject.Singleton;

import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.comics.ComicsModule;
import bme.aut.comicmanager.comics.MockComicsInteractor;
import bme.aut.comicmanager.network.NetworkModule;
import bme.aut.comicmanager.ui.UIModule;
import bme.aut.comicmanager.ui.browser.BrowserActivity;
import bme.aut.comicmanager.ui.browser.BrowserPresenter;
import bme.aut.comicmanager.ui.details.DetailsActivity;
import bme.aut.comicmanager.ui.details.DetailsPresenter;
import bme.aut.comicmanager.ui.issueList.IssueListActivity;
import bme.aut.comicmanager.ui.issueList.IssueListPresenter;
import bme.aut.comicmanager.ui.main.MainActivity;
import bme.aut.comicmanager.ui.main.MainPresenter;
import bme.aut.comicmanager.ui.searcher.SearchActivity;
import bme.aut.comicmanager.ui.searcher.SearchPresenter;
import bme.aut.comicmanager.ui.uploader.UploaderActivity;
import bme.aut.comicmanager.ui.uploader.UploaderPresenter;
import dagger.Component;

/**
 * Created by mhidvegi on 2016. 04. 11..
 * Injects to Comic manager classes with Dagger
 */
@Singleton
@Component(modules = {UIModule.class, ComicsModule.class, NetworkModule.class})
public interface ComicManagerComponent {
    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
    void inject(BrowserPresenter browserPresenter);
    void inject(BrowserActivity browserActivity);
    void inject(DetailsPresenter detailsPresenter);
    void inject(DetailsActivity detailsActivity);
    void inject(IssueListPresenter issueListPresenter);
    void inject(IssueListActivity issueListActivity);
    void inject(SearchPresenter searchPresenter);
    void inject(SearchActivity searchActivity);
    void inject(UploaderPresenter uploaderPresenter);
    void inject(UploaderActivity uploaderActivity);


    void inject(MockComicsInteractor comicsInteractor);
}