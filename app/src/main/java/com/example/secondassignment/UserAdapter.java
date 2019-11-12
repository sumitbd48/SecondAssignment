package com.example.secondassignment;

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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> userData = new ArrayList<>();

    public UserAdapter(List<User> userList){
        this.userData = userList ;
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
