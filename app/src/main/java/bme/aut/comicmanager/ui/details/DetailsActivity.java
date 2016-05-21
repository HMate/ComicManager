package bme.aut.comicmanager.ui.details;

import android.support.v7.app.ActionBar;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicIssueDetails;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen{

    public static final String ISSUE_ID = "Details_Issue_ID";

    long issueId;

    @Inject
    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ComicManagerApplication.injector.inject(this);
        issueId = getIntent().getLongExtra(ISSUE_ID, 0);

    }

    @Override
    protected void onStart(){
        super.onStart();
        detailsPresenter.attachScreen(this);
        detailsPresenter.showIssueDetails();
    }

    @Override
    protected void onStop(){
        super.onStop();
        detailsPresenter.detachScreen();
    }

    public long getIssueId(){
        return issueId;
    }

    public void showIssueDetails(ComicIssueDetails details){
        if(details == null)
            return;

        Comic comic = detailsPresenter.getComic(details.getComicId());
        ActionBar ab = getSupportActionBar();
        if(ab != null)
            ab.setTitle(details.getTitle());

        setTextViewText(R.id.detail_comic_title_text, comic.getTitle());
        setTextViewText(R.id.detail_issue_title_text, details.getTitle());
        setTextViewText(R.id.detail_published_text, details.getPublished());
        setTextViewText(R.id.detail_editor_text, details.getEditor());
        setTextViewText(R.id.detail_penciler_text, details.getPenciler());
        setTextViewText(R.id.detail_writer_text, details.getWriter());
        setTextViewText(R.id.detail_summary_text, details.getSummary());

        if(details.getCover() == null){
            ImageView cover = (ImageView) findViewById(R.id.detail_cover_img);
            if(cover != null) {
                cover.setImageResource(R.mipmap.ic_example_img);
            }
        }
    }

    private void setTextViewText(int resID, String text){
        TextView tv = (TextView) findViewById(resID);
        if(tv != null){
            tv.setText(text);
        }
    }
}
