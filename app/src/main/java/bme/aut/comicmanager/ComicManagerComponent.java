package bme.aut.comicmanager;

import javax.inject.Singleton;

import bme.aut.comicmanager.comics.ComicsModule;
import bme.aut.comicmanager.comics.MockComicsInteractor;
import bme.aut.comicmanager.network.NetworkModule;
import bme.aut.comicmanager.ui.UIModule;
import bme.aut.comicmanager.ui.browser.BrowserActivity;
import bme.aut.comicmanager.ui.browser.BrowserPresenter;
import bme.aut.comicmanager.ui.comicUploader.ComicUploaderActivity;
import bme.aut.comicmanager.ui.comicUploader.ComicUploaderPresenter;
import bme.aut.comicmanager.ui.details.DetailsActivity;
import bme.aut.comicmanager.ui.details.DetailsPresenter;
import bme.aut.comicmanager.ui.issueList.IssueListFragment;
import bme.aut.comicmanager.ui.issueList.IssueListPresenter;
import bme.aut.comicmanager.ui.issueUploader.IssueUploaderActivity;
import bme.aut.comicmanager.ui.issueUploader.IssueUploaderPresenter;
import bme.aut.comicmanager.ui.main.MainActivity;
import bme.aut.comicmanager.ui.main.MainPresenter;
import bme.aut.comicmanager.ui.searcher.SearchActivity;
import bme.aut.comicmanager.ui.searcher.SearchPresenter;
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
    void inject(IssueListFragment issueListFragment);
    void inject(SearchPresenter searchPresenter);
    void inject(SearchActivity searchActivity);
    void inject(ComicUploaderPresenter comicUploaderPresenter);
    void inject(ComicUploaderActivity comicUploaderActivity);
    void inject(IssueUploaderPresenter issueUploaderPresenter);
    void inject(IssueUploaderActivity issueUploaderActivity);

    void inject(MockComicsInteractor comicsInteractor);
}