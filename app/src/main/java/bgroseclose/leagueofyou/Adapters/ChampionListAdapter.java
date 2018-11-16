package bgroseclose.leagueofyou.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.ChampionModels.Champion;
import bgroseclose.leagueofyou.R;

public class ChampionListAdapter extends RecyclerView.Adapter<ChampionListAdapter.ViewHolder> {

    private LinkedHashMap<String, String> championList;
    private Picasso picasso;
    private Object[] names;
    private Context context;

    public ChampionListAdapter(Context context, LinkedHashMap<String, String> championList, Picasso picasso) {
        this.championList = championList;
        this.picasso = picasso;
        this.names = championList.keySet().toArray();
        this.context = context;
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
        final String name = String.valueOf(names[i]);
        viewHolder.championNameText.setText(name);
        viewHolder.championFlavorText.setText(championList.get(name));
        picasso.load(LeagueOfYouSingleton.getChampionIcon(name)).into(viewHolder.championImage);

        viewHolder.championWinRateText.setText("50%");
        viewHolder.championLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openChampion(name);
            }
        });

    }

    private void openChampion(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return championList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout championLayout;
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