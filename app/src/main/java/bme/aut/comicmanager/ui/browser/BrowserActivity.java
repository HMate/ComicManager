package bme.aut.comicmanager.ui.browser;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.ui.comicUploader.ComicUploaderActivity;
import bme.aut.comicmanager.ui.issueList.IssueListActivity;
import bme.aut.comicmanager.ui.main.MainActivity;
import bme.aut.comicmanager.ui.util.RecyclerItemClickListener;

public class BrowserActivity extends AppCompatActivity implements BrowserScreen{
    private static String TAG = "browser";

    @Inject
    BrowserPresenter browserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComicManagerApplication.injector.inject(this);
        setContentView(R.layout.activity_browser);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        browserPresenter.attachScreen(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        browserPresenter.detachScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_issue_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_comic) {
            browserPresenter.addComic();
            return true;
        }

        if(id == android.R.id.home){
            Intent homeIntent = new Intent(this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void GotoComicUploader(){
        Log.d(TAG, "goto comic uploader");
        Intent uploaderIntent = new Intent(this, ComicUploaderActivity.class);
        startActivity(uploaderIntent);
    }
}
