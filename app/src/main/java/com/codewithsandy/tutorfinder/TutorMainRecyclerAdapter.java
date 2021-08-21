package com.codewithsandy.tutorfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TutorMainRecyclerAdapter extends RecyclerView.Adapter<TutorMainRecyclerAdapter.TutMainViewHolder> {

    private ArrayList<Student> studentList;

    public TutorMainRecyclerAdapter(ArrayList<Student> tutorList) {
        this.studentList = tutorList;
    }

    @NonNull
    @Override
    public TutMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TutMainViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.student_card,
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TutMainViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.dummyimageda);
        holder.name.setText(studentList.get(position).getName());
        holder.grade.setText(studentList.get(position).getGrade());
        holder.loc.setText(studentList.get(position).getLocatin());
        holder.contact.setText(studentList.get(position).getContact());
        holder.clgName.setText(studentList.get(position).getClg_name());
        holder.email.setText(studentList.get(position).getEmail());



    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class TutMainViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        TextView grade;
        TextView loc,contact,clgName,email;

        public TutMainViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.student_image);
            name = itemView.findViewById(R.id.student_name);
            grade = itemView.findViewById(R.id.student_grade);
            loc=itemView.findViewById(R.id.student_loc);
            contact=itemView.findViewById(R.id.student_contact);
            clgName=itemView.findViewById(R.id.student_clg);
            email=itemView.findViewById(R.id.student_email) ;
        }
    }

}