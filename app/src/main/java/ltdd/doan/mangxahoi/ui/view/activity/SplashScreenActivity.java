package ltdd.doan.mangxahoi.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;

@AndroidEntryPoint
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        DataBindingUtil.setContentView(this,R.layout.activity_splashscreen);

        Thread time = new Thread(() -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        time.start();

    }
}