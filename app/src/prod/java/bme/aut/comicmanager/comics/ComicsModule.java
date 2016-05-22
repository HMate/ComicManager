package bme.aut.comicmanager.comics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mhidvegi on 2016.04.11..
 * Provides comics package classes for Dagger
 */
@Module
public class ComicsModule {

    @Singleton
    @Provides
    public ComicsInteractor provideComicsInteractor(){
        return new ComicsInteractor();
    }

    @Singleton
    @Provides
    public LocalComicsDb provideLocalComicsDb(){
        return new LocalComicsDb();
    }

    @Singleton
    @Provides
    public ComicsDb provideComicsDb(){
        return new LocalComicsDb();
    }
}
