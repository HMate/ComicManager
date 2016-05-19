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
        return new ORMComicsInteractor();
    }
}
