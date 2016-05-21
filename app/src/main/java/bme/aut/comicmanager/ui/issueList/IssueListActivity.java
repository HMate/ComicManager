package bme.aut.comicmanager.ui.issueList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicIssue;
import bme.aut.comicmanager.ui.details.DetailsActivity;
import bme.aut.comicmanager.ui.issueUploader.IssueUploaderActivity;
import bme.aut.comicmanager.ui.util.RecyclerItemClickListener;

public class IssueListActivity extends AppCompatActivity implements IssueListScreen{

    public static final String COMIC_ID = "IssueList_Comic_ID";

    List<ComicIssue> issueSource;
    IssueListAdapter issueListAdapter;
    RecyclerView listView;
    TextView tvNoIssue;
    long comicId;

    @Inject
    IssueListPresenter issueListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_list);

        ComicManagerApplication.injector.inject(this);

        comicId = getIntent().getLongExtra(COMIC_ID, 0);

        tvNoIssue = (TextView) findViewById(R.id.issue_list_no_issue_label);
        listView = (RecyclerView)findViewById(R.id.issue_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);

        issueSource = new ArrayList<>();
        issueListAdapter = new IssueListAdapter(this, issueSource);

        listView.setAdapter(issueListAdapter);
        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ComicIssue c = issueSource.get((int) position);
                issueListPresenter.handleIssueTouch(c.getIssueId());
            }
        }));
    }

    @Override
    protected void onStart(){
        super.onStart();
        issueListPresenter.attachScreen(this);
        issueListPresenter.setComicId(comicId);
    }

    @Override
    protected void onResume(){
        super.onResume();
        issueListPresenter.refreshIssues();
    }

    @Override
    protected void onStop(){
        super.onStop();
        issueListPresenter.detachScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add_comic) {
            issueListPresenter.addNewIssue();
            issueListPresenter.refreshIssues();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showIssues(List<ComicIssue> issuesToShow){

        issueSource.clear();
        issueSource.addAll(issuesToShow);
        issueListAdapter.notifyDataSetChanged();

        if(issuesToShow.size() == 0){
            listView.setVisibility(View.GONE);
            tvNoIssue.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            tvNoIssue.setVisibility(View.GONE);
        }
    }

    public void GotoIssueDetails(long issueId){
        Log.d("issue_list", "goto details");
        Intent issueListIntent = new Intent(this, DetailsActivity.class);
        issueListIntent.putExtra(DetailsActivity.ISSUE_ID, issueId);
        startActivity(issueListIntent);
    }

    public void GotoIssueUploader(long comicId){
        Log.d("issue_list", "goto issue uploader");
        Intent issueUploaderIntent = new Intent(this, IssueUploaderActivity.class);
        issueUploaderIntent.putExtra(IssueUploaderActivity.COMIC_ID, comicId);
        startActivity(issueUploaderIntent);
    }
}
