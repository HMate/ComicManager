package bme.aut.comicmanager;

import javax.inject.Singleton;

import bme.aut.comicmanager.comics.ComicsModule;
import bme.aut.comicmanager.network.NetworkModule;
import dagger.Component;

/**
 * Created by i7 on 2016.05.22..
 */
@Singleton
@Component(modules = {TestModule.class, ComicsModule.class, NetworkModule.class})
public interface TestComponent extends ComicManagerComponent {
    void inject(ComicInteractorUnitTest ut);
}
