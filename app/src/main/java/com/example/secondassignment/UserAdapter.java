package com.example.secondassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondassignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private List<User> userData;
    private Context context;

    public UserAdapter(List<User> userList, Context context){
        this.userData = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_rv,parent,false);
        UserHolder userHolder = new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {
        final User user = userData.get(position);
        holder.imgUser.setImageResource(user.getImage());
        holder.txtUser.setText(user.getName());

        holder.txtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsUser.class);
                intent.putExtra("Name", user.getName());
                intent.putExtra("DoB", user.getDob());
                intent.putExtra("Gender", user.getGender());
                intent.putExtra("Country", user.getCountry());
                intent.putExtra("Phone", user.getPhone());
                intent.putExtra("Email", user.getEmail());
                intent.putExtra("Image", String.valueOf(user.getImage()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder{

        ImageView imgUser;
        TextView txtUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.imgUser);
            txtUser = itemView.findViewById(R.id.txtUser);
        }
    }
}
