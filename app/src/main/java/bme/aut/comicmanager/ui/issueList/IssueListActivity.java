package bme.aut.comicmanager.ui.issueList;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import bme.aut.comicmanager.R;

public class IssueListActivity extends AppCompatActivity {

    public static final String COMIC_ID = "IssueList_Comic_ID";

    long comicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        comicId = getIntent().getLongExtra(COMIC_ID, 0);


        setContentView(R.layout.activity_issue_list);
    }
}
