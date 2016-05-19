package bme.aut.comicmanager.ui;

/**
 * Created by mhidvegi on 2016.04.11..
 * Presenter template for MVP architecture
 * Every functionality should be implemented through Presenter classes
 */
public abstract class Presenter<S> {
    protected S screen;

    public void attachScreen(S screen){
        this.screen = screen;
    }

    public void detachScreen(){
        this.screen = null;
    }
}
