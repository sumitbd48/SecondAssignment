package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsUser extends AppCompatActivity {

    ImageView imageView;
    TextView nameView, dobView, genderView, countryView, phoneView, emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);

        imageView = findViewById(R.id.imgView);
        nameView = findViewById(R.id.nameView);
        dobView = findViewById(R.id.dobView);
        genderView = findViewById(R.id.genderView);
        countryView = findViewById(R.id.countryView);
        phoneView = findViewById(R.id.phoneView);
        emailView = findViewById(R.id.emailView);

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        String dob = intent.getStringExtra("DoB");
        String gender = intent.getStringExtra("Gender");
        String country = intent.getStringExtra("Country");
        String phone = intent.getStringExtra("Phone");
        String email = intent.getStringExtra("Email");
        String image = intent.getStringExtra("Image");
        int img = Integer.parseInt(image);

        imageView.setImageResource(img);
        nameView.setText(name);
        dobView.setText(dob);
        genderView.setText(gender);
        countryView.setText(country);
        phoneView.setText(phone);
        emailView.setText(email);
    }
}
