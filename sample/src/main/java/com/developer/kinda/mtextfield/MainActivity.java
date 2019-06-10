package com.developer.kinda.mtextfield;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.developer.kinda.ExtendedEditText;
import com.developer.kinda.TextFieldBoxes;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = this.getSharedPreferences("theme", Context.MODE_PRIVATE);
        final boolean dark = sharedPreferences.getBoolean("dark", false);
        setTheme(dark ? R.style.AppThemeDark : R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final TextFieldBoxes tfb = findViewById(R.id.text_field_boxes2);
        final ExtendedEditText t = findViewById(R.id.extendedEditText);

        final String[] aWords = {"Apple", "Android", "Alternative"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, aWords);
        t.setThreshold(1);
        t.setAdapter(adapter);

        setupDarkThemeButton(dark);
        setupErrorButton();
        setupPasswordField();
    }

    private void setupPasswordField() {
        final Button addClearIconButton = findViewById(R.id.clear_icon_button);
        final Button addEndIconButton = findViewById(R.id.end_icon_button);
        final TextFieldBoxes passwordField = findViewById(R.id.text_field_boxes6);

        addClearIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordField.setHasClearButton(!passwordField.getHasClearButton());
            }
        });

        addEndIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordField.getEndIconImageButton().getDrawable() == null) {
                    passwordField.setEndIcon(R.drawable.ic_visibility_black_24dp);
                } else {
                    passwordField.removeEndIcon();
                }
            }
        });
    }

    private void setupDarkThemeButton(final boolean dark) {

        final Button darkButton = findViewById(R.id.dark_button);

        darkButton.setText(dark ? "LIGHT SIDE" : "DARK SIDE");
        darkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean("dark", !dark).apply();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                Objects.requireNonNull(i).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    private void setupErrorButton() {
        final Button errorButton = findViewById(R.id.error_button);
        final TextFieldBoxes errorField = findViewById(R.id.text_field_boxes5);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorField.setError("Invalid coupon code.", false);
            }
        });
    }
}
