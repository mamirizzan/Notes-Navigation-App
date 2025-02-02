package com.example.notesnavigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup the toolbar
        Toolbar toolbar = findViewById(R.id.ToolBarMain);
        setSupportActionBar(toolbar);

        // Set up Navigation Controller
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.NavHostMain);
        NavController navController = host.getNavController();

        // Setup the Bottom Navigation
        // Link Bottom Navigation with Navigation Controller
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        // Bottom Navigation will update ui here
        NavigationUI.setupWithNavController(bottomNav, navController);

    }

    // Inflate the layout for toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        try {

            if(id == R.id.test){
                Toast.makeText(this, "Test was clicked", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        catch (Exception ex)
        {
            return super.onOptionsItemSelected(item);
        }
    }

    // Back Navigation
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.NavHostMain).navigateUp();
    }

}