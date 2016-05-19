package bme.aut.comicmanager.ui;

import android.content.Context;

import javax.inject.Singleton;

import bme.aut.comicmanager.ui.browser.BrowserPresenter;
import bme.aut.comicmanager.ui.details.DetailsPresenter;
import bme.aut.comicmanager.ui.issueList.IssueListPresenter;
import bme.aut.comicmanager.ui.main.MainPresenter;
import bme.aut.comicmanager.ui.searcher.SearchPresenter;
import bme.aut.comicmanager.ui.uploader.UploaderPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by i7 on 2016.05.19..
 */
@Module
public class UIModule {
    private Context context;

    public UIModule(Context cont){
        this.context = cont;
    }

    @Provides
    public Context provideContext(){
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
    public IssueListPresenter provideIssueListPresenter(){
        return new IssueListPresenter();
    }

    @Singleton
    @Provides
    public SearchPresenter provideSearchPresenter(){
        return new SearchPresenter();
    }

    @Singleton
    @Provides
    public UploaderPresenter provideUploaderPresenter(){ return new UploaderPresenter(); }
}
