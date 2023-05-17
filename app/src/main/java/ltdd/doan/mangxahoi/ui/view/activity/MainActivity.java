package ltdd.doan.mangxahoi.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.databinding.ActivityMainBinding;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.viewmodel.MainViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private Context context;
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.toolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.logout) {
                Session.ACTIVE_USER = null;
                mainViewModel.removeSessionUser();

                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();

            }

            return true;
        });

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        NavController navController = navHostFragment.getNavController();
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();
            if (id == R.id.feedFragment) navController.navigate(R.id.feedFragment);
            else if (id == R.id.friendsFragment) navController.navigate(R.id.friendsFragment);
            else if (id == R.id.newPostFragment) navController.navigate(R.id.newPostFragment);
            else if (id == R.id.conversationFragment) navController.navigate(R.id.conversationFragment);
            else if (id == R.id.notificationsFragment) navController.navigate(R.id.notificationsFragment);
            else if (id == R.id.profileFragment) navController.navigate(R.id.profileFragment);
            else return false;

            return true;
        });
    }

}