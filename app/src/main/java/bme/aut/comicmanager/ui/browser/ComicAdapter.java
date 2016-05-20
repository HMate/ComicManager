package bme.aut.comicmanager.ui.browser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bme.aut.comicmanager.R;
import bme.aut.comicmanager.comics.Comic;

/**
 * Created by i7 on 2016.05.20..
 */
public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder>  {

    private Context context;
    private List<Comic> comicsList;

    public ComicAdapter(Context context, List<Comic> artistsList) {
        this.context = context;
        this.comicsList = artistsList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.browser_item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comic comic = comicsList.get(position);
        holder.tvComicTitle.setText(comic.getTitle());
    }

    @Override
    public int getItemCount() {
        return comicsList.size();
    }

    public Comic getItem(int position) {
        return comicsList.get(position);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvComicTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.cover);
            tvComicTitle = (TextView) itemView.findViewById(R.id.comic_title);
        }
    }
}
