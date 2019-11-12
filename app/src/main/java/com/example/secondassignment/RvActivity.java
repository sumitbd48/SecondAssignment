package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.secondassignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class RvActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        recyclerView = findViewById(R.id.rvUser);

        Intent intent = getIntent();
        users = (List<User>) intent.getSerializableExtra("allusers");

        UserAdapter userAdapter = new UserAdapter(users);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);


    }
}
