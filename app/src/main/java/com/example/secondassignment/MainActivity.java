package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.secondassignment.model.User;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private AutoCompleteTextView txtImage;
    private String [] word = {"sumit", "manish", "deepak", "kushal"};
    EditText txtName, txtDOB, txtCountry, txtPhone, txtEmail;
    RadioGroup RG;
    Spinner spinner;
    Button btnSubmit, btnView;
    String name, dob, gender, country, phone, email,image;
    String[] countries = {"--Select Country--","Nepal","India","Srilanka","Bhutan","Maldives","Myanmar","Pakistan","Afghanistan"};

    List<User> userList = new ArrayList<>();

    Calendar calender = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calender.set(Calendar.YEAR,year);
            calender.set(Calendar.MONTH,month);
            calender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            String mydateFormat = "dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            txtDOB.setText(simpleDateFormat.format(calender.getTime()));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtDOB = findViewById(R.id.txtDoB);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        RG = findViewById(R.id.RG);
        spinner = findViewById(R.id.Spinner);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnView = findViewById(R.id.btnView);

        txtImage = findViewById(R.id.txtImage);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,R.layout.activity_word_item,word);
        txtImage.setAdapter(stringArrayAdapter);
        txtImage.setThreshold(1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_values,countries);
        spinner.setAdapter(adapter);

        RG.setOnCheckedChangeListener(this);
        setSpinnerValue();
        txtDOB.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.RBMale){
            gender = "Male";
        }

        if (checkedId == R.id.RBFemale){
            gender = "Female";
        }

        if (checkedId == R.id.RBOther){
            gender = "Other";
        }

    }

    public void setSpinnerValue(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Please select country.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        name = txtName.getText().toString();
        dob = txtDOB.getText().toString();
        phone = txtPhone.getText().toString();
        email = txtEmail.getText().toString();
        image = txtImage.getText().toString();

        String uri = "@drawable/"+image;
        int resId = getResources().getIdentifier(uri,null,getPackageName());

        if (v.getId() == R.id.btnSubmit){
            if (validate()){
                userList.add(new User(name,dob,gender,country,phone,email,resId));
                Toast.makeText(this, "User Added.", Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.txtDoB){
            new DatePickerDialog(this,mydatepicker,
                    calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();
        }

        if (v.getId() == R.id.btnView){

            Intent intent = new Intent(this,RvActivity.class);
            intent.putExtra("allusers",(Serializable) userList);
            startActivity(intent);

        }

    }

    private boolean validate(){

        if (TextUtils.isEmpty(name)){
            txtName.setError("Enter Name");
            txtName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(dob)){
            txtDOB.setError("Select Date");
            txtDOB.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(country)){
            txtCountry.setError("Select Country");
            txtCountry.requestFocus();
            return false;
        }

        if (country.equals("--Select Country--")){
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!TextUtils.isDigitsOnly(phone)){
            txtPhone.setError("Invalid input");
            txtPhone.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email)){
            txtEmail.setError("Enter email");
            txtEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmail.setError("Invalid email");
            txtEmail.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(image)){
            txtImage.setError("Image Name must not be empty!");
            txtImage.requestFocus();
            return false;
        }

        return true;
    }
}
