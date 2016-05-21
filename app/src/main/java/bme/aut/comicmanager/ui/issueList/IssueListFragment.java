package bme.aut.comicmanager.ui.issueList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import bme.aut.comicmanager.comics.ComicIssue;
import bme.aut.comicmanager.ui.details.DetailsActivity;
import bme.aut.comicmanager.ui.issueUploader.IssueUploaderActivity;
import bme.aut.comicmanager.ui.util.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class IssueListFragment extends Fragment implements IssueListScreen{

    List<ComicIssue> issueSource;
    IssueListAdapter issueListAdapter;
    RecyclerView listView;
    TextView tvNoIssue;

    public static final String COMIC_ID = "IssueList_Comic_ID";
    long comicId;

    public long getComicId(){
        return comicId;
    }

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

        comicId = getActivity().getIntent().getLongExtra(COMIC_ID, 0);

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
                issueListPresenter.handleIssueTouch(c.getIssueId());
            }
        }));

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        issueListPresenter.attachScreen(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        issueListPresenter.refreshIssues();
    }

    @Override
    public void onStop(){
        super.onStop();
        issueListPresenter.detachScreen();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            comicId = bundle.getLong(COMIC_ID);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null)
        {
            comicId = bundle.getLong(COMIC_ID);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_issue_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add_comic) {
            issueListPresenter.addNewIssue();
            issueListPresenter.refreshIssues();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Log.d("issue_list_frag", "goto details");
        Intent issueListIntent = new Intent(getContext(), DetailsActivity.class);
        issueListIntent.putExtra(DetailsActivity.ISSUE_ID, issueId);
        startActivity(issueListIntent);
    }

    public void GotoIssueUploader(long comicId){
        Log.d("issue_list_frag", "goto issue uploader");
        Intent issueUploaderIntent = new Intent(getContext(), IssueUploaderActivity.class);
        issueUploaderIntent.putExtra(IssueUploaderActivity.COMIC_ID, comicId);
        startActivity(issueUploaderIntent);
    }

}
