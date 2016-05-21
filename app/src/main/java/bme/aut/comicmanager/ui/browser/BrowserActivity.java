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

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // ComicManagerApplication.injector.inject(this);

        setContentView(R.layout.activity_browser);
    }
}
