package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
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

public class CreateUserActivity extends AppCompatActivity {

    Button nextBtn;
    EditText nameInput;
    EditText emailInput;
    RadioGroup role;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nextBtn = findViewById(R.id.nextBtn);
        nameInput = findViewById(R.id.nameCreateInput);
        emailInput = findViewById(R.id.emailCreateInput);
        role = findViewById(R.id.roleCreateInput);
        int roleInputId = R.id.roleCreateInput;

        nextBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();
            int selectedId = role.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);
            String role = selectedRadioButton.getText().toString().toLowerCase();

            if (name.isEmpty() || email.isEmpty() || role.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            user = new User(name, email, role);
            Intent intent = new Intent(this, ProfileViewActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        });
    }
}