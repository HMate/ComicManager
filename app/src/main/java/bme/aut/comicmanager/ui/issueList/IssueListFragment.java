package bme.aut.comicmanager.ui.issueList;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class IssueListFragment extends Fragment implements IssueListScreen{
    private static String TAG = "issue_list_frag";

    List<ComicIssue> issueSource;
    IssueListAdapter issueListAdapter;
    RecyclerView listView;
    TextView tvNoIssue;

    public static final String COMIC_ID = "IssueList_Comic_ID";
    Long comicId;

    @Inject
    IssueListPresenter issueListPresenter;

    public IssueListFragment() {
        // Required empty public constructor
        ComicManagerApplication.injector.inject(this);
    }

    public static IssueListFragment newInstance(long comicId) {
        IssueListFragment fragment = new IssueListFragment();
        Bundle args = new Bundle();
        args.putLong(COMIC_ID, comicId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue_list, container, false);

        comicId = getActivity().getIntent().getLongExtra(COMIC_ID, -1);

        tvNoIssue = (TextView) view.findViewById(R.id.issue_list_no_issue_label);
        listView = (RecyclerView) view.findViewById(R.id.issue_list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);

        issueSource = new ArrayList<>();
        issueListAdapter = new IssueListAdapter(getContext(), issueSource);

        listView.setAdapter(issueListAdapter);
        listView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ComicIssue c = issueSource.get((int) position);
                issueListPresenter.handleIssueTouch(c);
            }
        }));

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        issueListPresenter.attachScreen(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        issueListPresenter.searchById(comicId);
    }

    @Override
    public void onDetach(){
        issueListPresenter.detachScreen();
        super.onDetach();
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
        Log.d(TAG, "goto details");
        Intent issueListIntent = new Intent(getContext(), DetailsActivity.class);
        issueListIntent.putExtra(DetailsActivity.ISSUE_ID, issueId);
        startActivity(issueListIntent);
    }

}
