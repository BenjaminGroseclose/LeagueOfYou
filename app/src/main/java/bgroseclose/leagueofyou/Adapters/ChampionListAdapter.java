package bgroseclose.leagueofyou.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bgroseclose.leagueofyou.Models.ChampionModels.Champion;
import bgroseclose.leagueofyou.R;

public class ChampionListAdapter extends RecyclerView.Adapter<ChampionListAdapter.ViewHolder> {

    private ArrayList<Champion> championsList;
    private Picasso picasso;

    public ChampionListAdapter(ArrayList<Champion> championList, Picasso picasso) {
        this.championsList = championList;
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View gameListView = inflater.inflate(R.layout.champion_list_item_view, viewGroup, false);
        return new ViewHolder(gameListView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Champion champion = championsList.get(i);
        viewHolder.championNameText.setText(champion.getName());
        viewHolder.championFlavorText.setText(champion.getTitle());
        picasso.load(champion.getChampionSquare()).into(viewHolder.championImage);

        viewHolder.championLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChampion(champion);
            }
        });

    }

    private void openChampion(Champion champion) {

    }

    @Override
    public int getItemCount() {
        return championsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout championLayout;
        ImageView championImage;
        TextView championNameText;
        TextView championFlavorText;
        TextView championWinRateText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            championLayout = itemView.findViewById(R.id.champion_list_item_layout);
            championImage = itemView.findViewById(R.id.champion_list_image);
            championNameText = itemView.findViewById(R.id.champion_list_name);
            championFlavorText = itemView.findViewById(R.id.champion_list_name_flavor);
            championWinRateText = itemView.findViewById(R.id.champion_list_win_rate);
        }
    }
}
