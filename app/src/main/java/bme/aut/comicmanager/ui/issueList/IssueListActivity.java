package bme.aut.comicmanager.ui.issueList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;

public class IssueListActivity extends AppCompatActivity implements IssueListScreen{

    @Inject
    IssueListPresenter issueListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_list);

        ComicManagerApplication.injector.inject(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        issueListPresenter.attachScreen(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        issueListPresenter.detachScreen();
    }
}
