package bme.aut.comicmanager.ui.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.Comic;

public class BrowserActivity extends AppCompatActivity implements BrowserScreen {

    List<Comic> comicsSource;
    RecyclerView listView;
    ComicAdapter comicAdapter;

    @Inject
    BrowserPresenter browserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        ComicManagerApplication.injector.inject(this);


        listView = (RecyclerView)findViewById(R.id.browser_list);
        comicsSource = new ArrayList<>();
        comicAdapter = new ComicAdapter(this, comicsSource);

        listView.setAdapter(comicAdapter);
        listView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d("browser", "onInterceptTouchEvent");
                GotoItemDetails();
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d("browser", "onTouchEvent");
                GotoItemDetails();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    protected void GotoItemDetails(){
        // TODO
        Log.d("browser", "goto details");
    }

    @Override
    protected void onStart(){
        super.onStart();
        browserPresenter.attachScreen(this);
        browserPresenter.refreshComics();
    }

    @Override
    protected void onResume(){
        super.onResume();
        browserPresenter.refreshComics();
    }

    @Override
    protected void onStop(){
        browserPresenter.detachScreen();
        super.onStop();
    }

    public void showComics(List<Comic> comicsToShow){

        comicsSource.clear();
        comicsSource.addAll(comicsToShow);
        comicAdapter.notifyDataSetChanged();
    }
}
