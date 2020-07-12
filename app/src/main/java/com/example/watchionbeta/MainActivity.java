package com.example.watchionbeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

RecyclerView movieRecyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initialize();
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<movies> options=new FirebaseRecyclerOptions.Builder<movies>().setQuery(databaseReference,movies.class).build();
        FirebaseRecyclerAdapter<movies,movieViewHolder> adapter=new FirebaseRecyclerAdapter<movies, movieViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull movieViewHolder holder, int position, @NonNull movies model) {
                holder.titletextView.setText(model.getMovieName());
                Picasso.get().load(model.getImageUrl()).into(holder.movieImageView);
                final String movieLink=model.getUrl();
                holder.movielinkTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goWatchMovie=new Intent(MainActivity.this,watchMovies.class);
                        goWatchMovie.putExtra("movieUrl",movieLink);
                        startActivity(goWatchMovie);

                    }
                });
            }

            @NonNull
            @Override
            public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view,parent,false);
                movieViewHolder viewHolder=new movieViewHolder(view);
                return  viewHolder;
            }
        };
        movieRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    class movieViewHolder extends RecyclerView.ViewHolder{
        TextView titletextView,movielinkTextView;
        ImageView movieImageView;
        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            titletextView=itemView.findViewById(R.id.movieNameTextView);
            movielinkTextView=itemView.findViewById(R.id.watchNowTextView);
            movieImageView=itemView.findViewById(R.id.movieImageView);
        }
    }

    private void initialize() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Movies");
        movieRecyclerView=(RecyclerView)findViewById(R.id.movieListRecyclerView);
    }


}
