package com.example.fbu_flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fbu_flixter.DetailActivity;
import com.example.fbu_flixter.R;
import com.example.fbu_flixter.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }
    //inflates layout from XML and returns holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    
        View movieView =  LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);

    }
    //populating data into item thru holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get movie at the passed in posiiton
        Movie movie = movies.get(position);
        //bind movie to view
        holder.bind(movie);
    }
//returns count of item in list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleView;
        TextView overview;
        ImageView poster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView =  itemView.findViewById(R.id.titleView);
            overview = itemView.findViewById(R.id.overview);
            poster = itemView.findViewById(R.id.poster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            titleView.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            String imageUrl;

            imageUrl=movie.getPosterPath();
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.try_again)
                    .into(poster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent (context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));

                    context.startActivity(i);
                }
            });
        }

        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Movie movie = movies.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, DetailActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                // show the activity
                context.startActivity(intent);
        }
    }

}}
