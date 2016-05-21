package bme.aut.comicmanager.ui.issueUploader;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IssueUploaderActivity extends AppCompatActivity implements IssueUploaderScreen{

    @BindView(R.id.issue_uploader_comic_title_editor)
    TextView tvComicTitle;
    @BindView(R.id.issue_uploader_issue_number_editor)
    EditText etIssueNumber;
    @BindView(R.id.issue_uploader_issue_title_editor)
    EditText etIssueTitle;
    @BindView(R.id.issue_uploader_published_editor)
    EditText etPublished;
    @BindView(R.id.issue_uploader_editor_editor)
    EditText etEditorName;
    @BindView(R.id.issue_uploader_penciler_editor)
    EditText etPencilerName;
    @BindView(R.id.issue_uploader_writer_editor)
    EditText etWriterName;

    public static final String COMIC_ID = "IssueUploader_Comic_ID";
    long comicId;

    public long getComicId(){
        return comicId;
    }

    @Inject
    IssueUploaderPresenter issueUploaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_uploader);
        ComicManagerApplication.injector.inject(this);

        comicId = getIntent().getLongExtra(COMIC_ID, 0);
        ButterKnife.bind(this);

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        issueUploaderPresenter.attachScreen(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        issueUploaderPresenter.refreshScreen();
    }

    @Override
    protected void onStop(){
        super.onStop();
        issueUploaderPresenter.detachScreen();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void FillTextFields(String comicTitle){
        tvComicTitle.setText(comicTitle);
    }

    @OnClick(R.id.issue_uploader_save_button)
    public void saveIssue(){
        String issueNumber = etIssueNumber.getText().toString();
        String issueTitle = etIssueTitle.getText().toString();
        String published = etPublished.getText().toString();
        String editorName = etEditorName.getText().toString();
        String writerName = etWriterName.getText().toString();
        String pencilerName = etPencilerName.getText().toString();
        issueUploaderPresenter.addNewIssue(comicId, issueNumber, issueTitle, published, editorName, writerName, pencilerName);
    }

    public void GoBackToParentScreen(){
        finish();
    }
}
