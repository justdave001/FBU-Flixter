package com.example.fbu_flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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
        Log.d("MovieAdapter","onBindWorks");
        View movieView =  LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);

    }
    //populating data into item thru holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter","onBindWorks" + position);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView overview;
        ImageView poster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleView);
            overview = itemView.findViewById(R.id.overview);
            poster = itemView.findViewById(R.id.poster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            titleView.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            String imageUrl;

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();

            } else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.try_again)
                    .into(poster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent (context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));

                    context.startActivity(i);
                }
            });
        }

    }

}
