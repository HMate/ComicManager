package bme.aut.comicmanager.ui.searcher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bme.aut.comicmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchIssueFragment extends Fragment {


    public SearchIssueFragment() {
        // Required empty public constructor
    }

    public static SearchIssueFragment newInstance() {
        SearchIssueFragment fragment = new SearchIssueFragment();
//        Bundle args = new Bundle();
//        args.putLong(COMIC_ID, comicId);
//        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_issue, container, false);
    }

}
