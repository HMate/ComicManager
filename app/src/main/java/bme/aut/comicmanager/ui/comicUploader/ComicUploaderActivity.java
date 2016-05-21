package bme.aut.comicmanager.ui.comicUploader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicUploaderActivity extends AppCompatActivity implements ComicUploaderScreen {

    @Inject
    ComicUploaderPresenter comicUploaderPresenter;

    @BindView(R.id.comic_uploader_title_editor)
    TextView tvComicTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_uploader);

        ComicManagerApplication.injector.inject(this);
        ButterKnife.bind(this);

        Button saveButton = (Button)findViewById(R.id.comic_uploader_save_button);
        if(saveButton != null) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = tvComicTitle.getText().toString();
                    comicUploaderPresenter.saveComic(title);
                }
            });
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        comicUploaderPresenter.attachScreen(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        comicUploaderPresenter.detachScreen();
    }

    public void GoBackToParentScreen(){
        finish();
    }
}
