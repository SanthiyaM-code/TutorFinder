package com.codewithsandy.tutorfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FavoriteRecyclerAdapter extends RecyclerView.Adapter<FavoriteRecyclerAdapter.FavViewHolder> {

    private ArrayList<Tutor> tutorList;
    Context context;

    public FavoriteRecyclerAdapter(ArrayList<Tutor> tutorList, Context context) {
        this.tutorList = tutorList;
        this.context=context;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_main_card,
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.dummyimageda);
        holder.name.setText(tutorList.get(position).getName());
        holder.rating.setText(String.valueOf(tutorList.get(position).getRating()));
        holder.studCount.setText(String.valueOf(tutorList.get(position).getStudentsCount()));
    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }

    static class FavViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        ImageView imageView;
        TextView name;
        TextView rating;
        TextView studCount;
        ImageView options;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_name);
            rating = itemView.findViewById(R.id.tv_rating);
            studCount = itemView.findViewById(R.id.tv_studentscount);
            options = itemView.findViewById(R.id.card_options);

            options.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            showPopUpMenu(view);
        }

        private void showPopUpMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu_fav);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.popup_fav_rem:

                    //TODO( Delete Tutor from Favorites )

                    Snackbar.make(imageView.getContext(),imageView,"Removed from Favorite",Snackbar.LENGTH_SHORT).show();
                    return true;

                case R.id.popup_showprof:

                    //TODO(" Go to Profile Activity ");

                    return true;
                default:
                    return true;
            }
        }
    }

}