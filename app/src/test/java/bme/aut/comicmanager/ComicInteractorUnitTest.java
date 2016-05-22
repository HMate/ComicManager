package bme.aut.comicmanager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.network.MockServerComicsDb;

import static org.junit.Assert.*;

/**
 * Created by i7 on 2016.05.22..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ComicInteractorUnitTest {

    @Inject
    ComicsInteractor comicsInteractor;
    MockServerComicsDb comicsDb;

    @Before
    public void setup() throws Exception{
        TestHelper.setTestInjector();
        TestHelper.getInjector().inject(this);
        comicsDb = new MockServerComicsDb();
    }

    @Test
    public void getComicsNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> comics = comicsInteractor.getComics();
            assertEquals(comics.get(0), comicsDb.comics.get(0));
        }
    }
}
