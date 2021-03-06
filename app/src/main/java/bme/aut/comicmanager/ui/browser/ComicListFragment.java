package bme.aut.comicmanager.ui.browser;


import android.content.Context;
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
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.ui.comicUploader.ComicUploaderActivity;
import bme.aut.comicmanager.ui.issueList.IssueListActivity;
import bme.aut.comicmanager.ui.util.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComicListFragment extends Fragment implements ComicListScreen {
    private static String TAG = "comic_list_frag";

    List<Comic> comicsSource;
    RecyclerView listView;
    ComicListAdapter comicListAdapter;
    TextView tvNoComic;

    @Inject
    ComicListPresenter comicListPresenter;

    public ComicListFragment() {
        ComicManagerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_comic_list, container, false);

        tvNoComic = (TextView) view.findViewById(R.id.comic_list_empty_text);
        listView = (RecyclerView)view.findViewById(R.id.comic_list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);

        comicsSource = new ArrayList<>();
        comicListAdapter = new ComicListAdapter(getContext(), comicsSource);

        listView.setAdapter(comicListAdapter);
        listView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Comic c = comicsSource.get((int) position);
                comicListPresenter.handleComicTouch(c.getComicId());
            }
        }));


        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        comicListPresenter.attachScreen(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        comicListPresenter.showAllComics();
    }

    @Override
    public void onDetach(){
        comicListPresenter.detachScreen();
        super.onDetach();
    }

    public void showComics(List<Comic> comicsToShow){

        comicsSource.clear();
        comicsSource.addAll(comicsToShow);
        comicListAdapter.notifyDataSetChanged();


        if(comicsToShow.size() == 0){
            listView.setVisibility(View.GONE);
            tvNoComic.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            tvNoComic.setVisibility(View.GONE);
        }
    }

    public void GotoComicIssues(long comicId){
        Log.d(TAG, "goto issues");
        Intent issueListIntent = new Intent(getContext(), IssueListActivity.class);
        issueListIntent.putExtra(IssueListActivity.COMIC_ID, comicId);
        startActivity(issueListIntent);
    }

}
