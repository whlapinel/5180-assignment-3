package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditProfileActivity extends AppCompatActivity {
    Button submitBtn;
    EditText nameEditInput;

    EditText emailEditInput;

    RadioGroup roleEditInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User user = getIntent().getParcelableExtra("user");
        if (user == null) {
            finish();
            return;
        }
        submitBtn = findViewById(R.id.submitBtn);
        nameEditInput = findViewById(R.id.nameEditInput);
        emailEditInput = findViewById(R.id.emailEditInput);
        roleEditInput = findViewById(R.id.roleEditInput);
        nameEditInput.setText(user.name);
        emailEditInput.setText(user.email);

        switch (user.role) {
            case "student":
                roleEditInput.check(R.id.studentEditRadioBtn);
                break;
            case "employee":
                roleEditInput.check(R.id.employeeEditRadioBtn);
                break;
            case "other":
                roleEditInput.check(R.id.otherEditRadioBtn);
                break;
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditInput.getText().toString();
                String email = emailEditInput.getText().toString();
                int selectedRadioId = roleEditInput.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioId);
                String role = selectedRadioButton.getText().toString().toLowerCase();
                User updatedUser = new User(name, email, role);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("user", updatedUser);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });


    }
}