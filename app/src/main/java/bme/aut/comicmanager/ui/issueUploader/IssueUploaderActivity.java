package bme.aut.comicmanager.ui.issueUploader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;

public class IssueUploaderActivity extends AppCompatActivity implements IssueUploaderScreen{

    @Inject
    IssueUploaderPresenter issueUploaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_uploader);
        ComicManagerApplication.injector.inject(this);
    }
}
