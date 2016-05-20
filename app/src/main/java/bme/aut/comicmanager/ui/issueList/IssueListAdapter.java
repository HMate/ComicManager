package bme.aut.comicmanager.ui.issueList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.ComicIssue;

/**
 * Created by i7 on 2016.05.20..
 */
public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.ViewHolder>  {

    private Context context;
    private List<ComicIssue> comicsList;

    public IssueListAdapter(Context context, List<ComicIssue> issueList) {
        this.context = context;
        this.comicsList = issueList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.issuelist_item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ComicIssue comic = comicsList.get(position);
        holder.tvIssueTitle.setText(comic.getTitle());
    }

    @Override
    public int getItemCount() {
        return comicsList.size();
    }

    public ComicIssue getItem(int position) {
        return comicsList.get(position);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvIssueTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.cover);
            tvIssueTitle = (TextView) itemView.findViewById(R.id.issue_title);
        }
    }
}
