package com.codewithsandy.tutorfinder;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentMainRecyclerAdapter extends RecyclerView.Adapter<StudentMainRecyclerAdapter.MainViewHolder> {

    private ArrayList<Tutor> tutorList;

    public StudentMainRecyclerAdapter(ArrayList<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.dummyimageda);
        holder.name.setText(tutorList.get(position).getName());
        holder.rating.setText(String.valueOf(tutorList.get(position).getRating()));
        holder.studCount.setText(String.valueOf(tutorList.get(position).getStudentsCount()));
        holder.options.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(holder.options.getContext(),holder.options);
            popupMenu.inflate(R.menu.popup_menu_main);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.popup_fav:

                        //TODO( Add data to Favorites FireBase )
                        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                        firestore.collection("Student").document(uid).collection("Favorites").add(tutorList.get(position))
                                .addOnSuccessListener(documentReference -> Snackbar.make(holder.imageView.getContext(), holder.imageView, "Added to Favorite", Snackbar.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> Snackbar.make(holder.imageView.getContext(), holder.imageView, ""+e.getMessage(), Snackbar.LENGTH_SHORT).show());

                        return true;

                    case R.id.popup_showprof:

                        //TODO(" Go to Profile Activity ");

                        return true;
                    default:
                        return true;
                }
            });
            popupMenu.show();

        });
    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }

    static class MainViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        TextView rating;
        TextView studCount;
        ImageView options;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_name);
            rating = itemView.findViewById(R.id.tv_rating);
            studCount = itemView.findViewById(R.id.tv_studentscount);
            options = itemView.findViewById(R.id.card_options);

        }
    }

}