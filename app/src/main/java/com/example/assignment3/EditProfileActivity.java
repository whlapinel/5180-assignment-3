package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditProfileActivity extends AppCompatActivity {
    Button submitBtn;
    Button cancelBtn;
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
        cancelBtn = findViewById(R.id.cancelBtn);
        nameEditInput = findViewById(R.id.nameEditInput);
        emailEditInput = findViewById(R.id.emailEditInput);
        roleEditInput = findViewById(R.id.roleEditInput);
        nameEditInput.setText(user.name);
        emailEditInput.setText(user.email);

        switch (user.role) {
            case "Student":
                roleEditInput.check(R.id.studentEditRadioBtn);
                break;
            case "Employee":
                roleEditInput.check(R.id.employeeEditRadioBtn);
                break;
            case "Other":
                roleEditInput.check(R.id.otherEditRadioBtn);
                break;
        }

        cancelBtn.setOnClickListener(v -> {
                finish();
        });

        submitBtn.setOnClickListener(v -> {
                String name = nameEditInput.getText().toString();
                String email = emailEditInput.getText().toString();
                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                int selectedRadioId = roleEditInput.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioId);
                String role = selectedRadioButton.getText().toString();
                User updatedUser = new User(name, email, role);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("user", updatedUser);
                setResult(RESULT_OK, returnIntent);
                finish();
        });
    }
}