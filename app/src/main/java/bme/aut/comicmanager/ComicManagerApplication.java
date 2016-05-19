package bme.aut.comicmanager;

import android.app.Application;

import com.orm.SugarContext;

import bme.aut.comicmanager.ui.UIModule;

/**
 * Created by i7 on 2016.05.19..
 */
public class ComicManagerApplication extends Application {

    public static ComicManagerComponent injector;

    @Override
    public void onCreate(){
        super.onCreate();
        SugarContext.init(this);

        injector = DaggerComicManagerComponent.builder().
                uIModule(new UIModule(this)).build();
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        SugarContext.terminate();
    }
}