package bme.aut.comicmanager;

import android.content.Context;

import javax.inject.Singleton;

import bme.aut.comicmanager.ui.UIModule;
import bme.aut.comicmanager.ui.browser.BrowserPresenter;
import bme.aut.comicmanager.ui.browser.ComicListPresenter;
import bme.aut.comicmanager.ui.comicUploader.ComicUploaderPresenter;
import bme.aut.comicmanager.ui.details.DetailsPresenter;
import bme.aut.comicmanager.ui.issueList.IssueListPresenter;
import bme.aut.comicmanager.ui.issueList.IssuePresenter;
import bme.aut.comicmanager.ui.issueUploader.IssueUploaderPresenter;
import bme.aut.comicmanager.ui.main.MainPresenter;
import bme.aut.comicmanager.ui.searcher.SearchComicPresenter;
import bme.aut.comicmanager.ui.searcher.SearchIssuePresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by i7 on 2016.05.22..
 */
@Module
public class TestModule {

    Context context;

    public TestModule(Context context){
        this.context = context;
    }

    @Provides
    public Context providesContext(){
        return context;
    }

    @Singleton
    @Provides
    public MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }

    @Singleton
    @Provides
    public DetailsPresenter provideDetailsPresenter(){
        return new DetailsPresenter();
    }

    @Singleton
    @Provides
    public BrowserPresenter provideBrowserPresenter(){
        return new BrowserPresenter();
    }

    @Singleton
    @Provides
    public IssuePresenter provideIssuePresenter(){
        return new IssuePresenter();
    }

    @Singleton
    @Provides
    public ComicListPresenter provideComicListPresenter(){
        return new ComicListPresenter();
    }

    @Singleton
    @Provides
    public IssueListPresenter provideIssueListPresenter(){
        return new IssueListPresenter();
    }

    @Singleton
    @Provides
    public SearchComicPresenter provideSearchComicPresenter(){
        return new SearchComicPresenter();
    }

    @Singleton
    @Provides
    public SearchIssuePresenter provideSearchIssuePresenter(){
        return new SearchIssuePresenter();
    }

    @Singleton
    @Provides
    public ComicUploaderPresenter provideComicUploaderPresenter(){ return new ComicUploaderPresenter(); }

    @Singleton
    @Provides
    public IssueUploaderPresenter provideIssueUploaderPresenter(){ return new IssueUploaderPresenter(); }
}
