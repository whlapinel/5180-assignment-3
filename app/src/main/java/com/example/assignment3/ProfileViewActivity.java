package com.example.assignment3;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Output;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileViewActivity extends AppCompatActivity {
    User user;
    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        user = getIntent().getParcelableExtra("user");
        if (user == null) {
            finish();
            return;
        }
        TextView name = findViewById(R.id.nameValue);
        name.setText(user.name);
        TextView email = findViewById(R.id.emailValue);
        email.setText(user.email);
        TextView role = findViewById(R.id.roleValue);
        role.setText(user.role);
        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        User updatedUser = data.getParcelableExtra("user");
                        if (updatedUser != null) {
                            user = updatedUser;
                            name.setText(user.name);
                            email.setText(user.email);
                            role.setText(user.role);
                        }
                    }
                }
            }
        });
        updateBtn = findViewById(R.id.profileUpdateBtn);
        updateBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            intent.putExtra("user", user);
            startForResult.launch(intent);
        });
    }
}