package com.example.jsonobject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView homeTeamTextView = findViewById(R.id.team_one_text_view);
        TextView awayTeamTextView = findViewById(R.id.team_two_text_view);

        homeTeamTextView.setText(QueryUtils.getHomeTeamName());
        awayTeamTextView.setText(QueryUtils.getAwayTeamName());

        ArrayList<Substitution> substitutionArrayList = QueryUtils.getFirstTeamSubstitution();
        RecyclerView recyclerView = findViewById(R.id.substitution_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        SubstitutionAdapter adapter = new SubstitutionAdapter(substitutionArrayList);
        recyclerView.setAdapter(adapter);

    }
}