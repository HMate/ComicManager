package bme.aut.comicmanager;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by i7 on 2016.05.22..
 */
public class TestHelper {

    public static void setTestInjector(){
        ShadowLog.stream = System.out;
        ComicManagerApplication app = (ComicManagerApplication)RuntimeEnvironment.application;
        ComicManagerComponent component = DaggerTestComponent.builder().testModule(new TestModule(app.getApplicationContext())).build();
        app.injector = component;
    }
}
