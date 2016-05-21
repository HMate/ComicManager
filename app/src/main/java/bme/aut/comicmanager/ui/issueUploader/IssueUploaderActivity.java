package bme.aut.comicmanager.ui.issueUploader;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public static final String ISSUE_ID = "IssueUploader_Issue_ID";
    long comicId;
    long issueId;

    @Inject
    IssueUploaderPresenter issueUploaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_uploader);
        ComicManagerApplication.injector.inject(this);

        comicId = getIntent().getLongExtra(COMIC_ID, -1);
        issueId = getIntent().getLongExtra(ISSUE_ID, -1);
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
        issueUploaderPresenter.refreshScreen(comicId, issueId);
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

    public void FillComicFields(String comicTitle){
        tvComicTitle.setText(comicTitle);
    }

    public void FillIssueFields(String issueTitle, String issueNumber, String published, String editor, String writer, String penciler){
        etIssueTitle.setText(issueTitle);
        etIssueNumber.setText(issueNumber);
        etPublished.setText(published);
        etEditorName.setText(editor);
        etWriterName.setText(writer);
        etPencilerName.setText(penciler);
    }

    public void ShowError(){
        Toast.makeText(getApplicationContext(), "Error in save", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.issue_uploader_save_button)
    public void saveIssue(){
        String issueNumber = etIssueNumber.getText().toString();
        String issueTitle = etIssueTitle.getText().toString();
        String published = etPublished.getText().toString();
        String editorName = etEditorName.getText().toString();
        String writerName = etWriterName.getText().toString();
        String pencilerName = etPencilerName.getText().toString();
        issueUploaderPresenter.saveIssue(comicId, issueId, issueNumber, issueTitle, published, editorName, writerName, pencilerName);
    }

    public void GoBackToParentScreen(){
        finish();
    }
}
