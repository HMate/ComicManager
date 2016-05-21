package bme.aut.comicmanager.ui.browser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
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
import bme.aut.comicmanager.ui.util.RecyclerItemClickListener;

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
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);

        comicsSource = new ArrayList<>();
        comicAdapter = new ComicAdapter(this, comicsSource);

        listView.setAdapter(comicAdapter);
        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Comic c = comicsSource.get((int) position);
                browserPresenter.handleComicTouch(c.getComicId());
            }
        }));
    }

    @Override
    protected void onStart(){
        super.onStart();
        browserPresenter.attachScreen(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_issue_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_comic) {
            browserPresenter.addComic();
            browserPresenter.refreshComics();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showComics(List<Comic> comicsToShow){

        comicsSource.clear();
        comicsSource.addAll(comicsToShow);
        comicAdapter.notifyDataSetChanged();
    }

    public void GotoComicIssues(long comicId){
        Log.d("browser", "goto issues");
        Intent issueListIntent = new Intent(this, IssueListActivity.class);
        issueListIntent.putExtra(IssueListActivity.COMIC_ID, comicId);
        startActivity(issueListIntent);
    }

    public void GotoComicUploader(){
        Log.d("browser", "goto comic uploader");
        Intent uploaderIntent = new Intent(this, ComicUploaderActivity.class);
        startActivity(uploaderIntent);
    }
}
