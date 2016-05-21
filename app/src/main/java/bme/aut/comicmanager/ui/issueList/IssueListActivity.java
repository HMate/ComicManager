package bme.aut.comicmanager.ui.issueList;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.ui.comicUploader.ComicUploaderActivity;
import bme.aut.comicmanager.ui.issueUploader.IssueUploaderActivity;

public class IssueListActivity extends AppCompatActivity implements IssueScreen{
    private static String TAG = "issue_list";

    public static final String COMIC_ID = "IssueList_Comic_ID";

    long comicId;

    @Inject
    IssuePresenter issuePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        comicId = getIntent().getLongExtra(COMIC_ID, 0);
        ComicManagerApplication.injector.inject(this);
        setContentView(R.layout.activity_issue_list);

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        issuePresenter.attachScreen(this);

        Comic comic = issuePresenter.getComic(comicId);
        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(comic.getTitle());
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        issuePresenter.detachScreen();
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
            issuePresenter.addNewIssue(comicId);
            return true;
        }
        if(id == android.R.id.home){
            onBackPressed();
        }

        if(id == R.id.action_edit){
            issuePresenter.editComic(comicId);
        }
        if(id == R.id.action_delete){
            issuePresenter.deleteComic(comicId);
        }

        return super.onOptionsItemSelected(item);
    }

    public void GotoComicUploader(long comicId){
        Log.d(TAG, "goto comic uploader");
        Intent comicUploaderIntent = new Intent(this, ComicUploaderActivity.class);
        comicUploaderIntent.putExtra(ComicUploaderActivity.COMIC_ID, comicId);
        startActivity(comicUploaderIntent);
    }

    public void GotoIssueUploader(long comicId){
        Log.d(TAG, "goto issue uploader");
        Intent issueUploaderIntent = new Intent(this, IssueUploaderActivity.class);
        issueUploaderIntent.putExtra(IssueUploaderActivity.COMIC_ID, comicId);
        startActivity(issueUploaderIntent);
    }

    public void GoBackToParent(){
        finish();
    }
}
