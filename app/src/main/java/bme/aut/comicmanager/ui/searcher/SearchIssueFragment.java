package bme.aut.comicmanager.ui.searcher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.ui.issueList.IssueListPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchIssueFragment extends Fragment implements SearchIssueScreen {
    private static String TAG = "search_issue_frag";

    @BindView(R.id.search_issue_title_editor)
    EditText etTitle;
    @BindView(R.id.search_issue_creator_editor)
    EditText etCreator;
    @BindView(R.id.search_issue_published_editor)
    EditText etPublished;

    @Inject
    SearchIssuePresenter searchPresenter;
    @Inject
    IssueListPresenter issueListPresenter;

    public SearchIssueFragment() {
        ComicManagerApplication.injector.inject(this);
    }

    public static SearchIssueFragment newInstance() {
        SearchIssueFragment fragment = new SearchIssueFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_issue, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
        searchPresenter.attachScreen(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onStop(){
        Log.d(TAG, "onStop");
        searchPresenter.detachScreen();
        super.onStop();
    }

    @OnClick(R.id.search_issue_result_button)
    public void search(){
        searchPresenter.startSearch();
    }

    public void showSearchResults(){
        String titleText = etTitle.getText().toString();
        String creatorString = etCreator.getText().toString();
        String publishedString = etPublished.getText().toString();

        issueListPresenter.searchByArguments(titleText, creatorString, publishedString);
    }

}
