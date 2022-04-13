package com.example.jsonobject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubstitutionAdapter extends RecyclerView.Adapter<SubstitutionAdapter.ViewHolder> {

    ArrayList<Substitution> substitutionArrayList;

    public SubstitutionAdapter(ArrayList<Substitution> substitutionArrayList) {
        this.substitutionArrayList = substitutionArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Substitution substitution = substitutionArrayList.get(position);

        // moving substitution value to left or right based on their team
        if (substitution.getTeam().equals("away")) {
            holder.startingLinearLayout.setVisibility(View.VISIBLE);
            holder.endingLinearLayout.setVisibility(View.GONE);
        } else {
            holder.startingLinearLayout.setVisibility(View.GONE);
            holder.endingLinearLayout.setVisibility(View.VISIBLE);
        }

        holder.timesTextView.setText(String.valueOf(substitution.getTime()));
        holder.substitutionTextView.setText(substitution.getSubstitution());
    }

    @Override
    public int getItemCount() {
        return substitutionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout startingLinearLayout;
        LinearLayout endingLinearLayout;

        TextView timesTextView;
        TextView substitutionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startingLinearLayout = itemView.findViewById(R.id.starting_space);
            endingLinearLayout = itemView.findViewById(R.id.ending_space);

            timesTextView = itemView.findViewById(R.id.times_text_view);
            substitutionTextView = itemView.findViewById(R.id.substitution_text_view);
        }
    }
}
