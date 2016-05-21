package bme.aut.comicmanager.ui.searcher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import bme.aut.comicmanager.ComicManagerApplication;
import bme.aut.comicmanager.R;
import bme.aut.comicmanager.ui.browser.ComicListPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchComicFragment extends Fragment  implements SearchScreen{

    @BindView(R.id.search_comic_title_editor)
    EditText etTitle;

    @Inject
    SearchPresenter searchPresenter;
    @Inject
    ComicListPresenter comicListPresenter;

    public SearchComicFragment() {
        ComicManagerApplication.injector.inject(this);
    }

    public static SearchComicFragment newInstance() {
        SearchComicFragment fragment = new SearchComicFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_comic, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        searchPresenter.attachScreen(this);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
        searchPresenter.detachScreen();
    }


    @OnClick(R.id.search_comic_result_button)
    public void search(){
        searchPresenter.startSearch();
    }

    public void showSearchResults(){
        String titleText = etTitle.getText().toString();
        comicListPresenter.searchByTitle(titleText);
    }

}
