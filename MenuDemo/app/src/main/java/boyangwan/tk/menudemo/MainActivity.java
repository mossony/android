package boyangwan.tk.menudemo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import boyangwan.tk.menudemo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    TextView textview_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        textview_first = findViewById(R.id.textview_first);

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            textview_first.setText("Setting clicked");
//            return true;
//        }
//
//        if (id == R.id.action_help) {
//            textview_first.setText("Help clicked");
//            return true;
//        }
//
//        if (id == R.id.action_file) {
//            textview_first.setText("File clicked");
//            return true;
//        }

        switch (id){
            case R.id.action_settings:
                textview_first.setText("Setting clicked");
                return true;
            case R.id.action_help:
                textview_first.setText("Help clicked");
                return true;
            case R.id.action_file:
                textview_first.setText("File clicked");
                return true;
            case R.id.action_open:
                textview_first.setText("File open clicked");
                return true;
            case R.id.action_close:
                textview_first.setText("File close clicked");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setting(MenuItem item){
        textview_first = findViewById(R.id.textview_first);
        textview_first.setText("setting method called");
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}