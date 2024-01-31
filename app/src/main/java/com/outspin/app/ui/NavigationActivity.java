package com.outspin.app.ui;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.outspin.app.R;
import com.outspin.app.databinding.ActivityNavigationBinding;
import com.outspin.app.network.NetworkManager;

public class NavigationActivity extends AppCompatActivity {

    private ActivityNavigationBinding navigationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigationBinding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(navigationBinding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_navigation);
        NavigationUI.setupWithNavController(navigationBinding.navView, navController);
    }

}