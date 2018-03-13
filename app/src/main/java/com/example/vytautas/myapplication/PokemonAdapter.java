package com.example.vytautas.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Vytautas on 2/28/2018.
 */

public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Pokemon> pokemons = Collections.emptyList();

    public PokemonAdapter(Context context, List<Pokemon> pokemons) {
        this.context = context;
        this.pokemons = pokemons;
        inflater = LayoutInflater.from(context);
    }

    static final String ENTRY_ID = "com.example.vytautas.myapplication";

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //get current position of item in
        // recyclerview to bind data and assign value from list
        MyHolder myHolder = (MyHolder) holder;
        Pokemon currentPokemon = pokemons.get(position);
        myHolder.tvName.setText(currentPokemon.getName());
        myHolder.tvHeight.setText(new StringBuilder().append("Height: ").append(currentPokemon.getHeight()).toString());
        myHolder.tvWeight.setText(new StringBuilder().append("Weigth: ").append(currentPokemon.getWeight()).toString());
        myHolder.tvType.setText(new StringBuilder().append("Type: ").append(currentPokemon.getType()).toString());
        myHolder.tvCp.setText(new StringBuilder().append("Power: ").append(currentPokemon.getCp()).toString());
        myHolder.tvId.setText(new StringBuilder().append("ID: ").append(currentPokemon.getId()).toString());
        myHolder.tvAbilities.setText(new StringBuilder().append("Abilities: ").append(currentPokemon.getAbilities()).toString());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName, tvType, tvCp, tvAbilities, tvId, tvWeight, tvHeight;

        public MyHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvType = (TextView) itemView.findViewById(R.id.type);
            tvCp = (TextView) itemView.findViewById(R.id.cp);
            tvAbilities = (TextView) itemView.findViewById(R.id.abilities);
            tvId = (TextView) itemView.findViewById(R.id.id);
            tvWeight = (TextView) itemView.findViewById(R.id.weight);
            tvHeight = (TextView) itemView.findViewById(R.id.height);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getAdapterPosition();
            int pokemonaiID = pokemons.get(itemPosition).getId();

            Pokemon pokemon = pokemons.get(itemPosition);

            Intent intent = new Intent(context, RegisterNewEntry.class);

            intent.putExtra(ENTRY_ID, pokemonaiID);
            context.startActivity(intent);
        }
    }
}