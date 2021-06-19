package boyangwan.tk.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LayerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layer_list);

    }

    public void nike(View view){
        ImageView imageView = findViewById(R.id.cat);
        imageView.setImageResource(R.drawable.nike);
    }

    public void adidas(View view){

    }
}