package boyangwan.tk.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ListViewFragment.ListViewEvent {

    private int img[] = {R.id.image1, R.id.image2, R.id.image3, R.id.image4, R.id.image5};
    ImageView main_img = findViewById(R.id.image_view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.listFragment);
        if (screenWidth > screenHeight){  // horizontal
            if (frag != null){
                fm.beginTransaction().remove(frag).commit();
            }

        }

        else{  // vertical
            if (frag == null) {
                fm.beginTransaction().add(R.id.listFragment, new ListViewFragment()).commitNow();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
//        ImageView main_img = findViewById(R.id.image_view);
//        for (int i=0; i<img.length; i++){
//            ImageView iv = (ImageView) findViewById(img[i]);
//            if (iv != null) {
//                iv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ImageView v1 = (ImageView) v;
//                        main_img.setImageDrawable(v1.getDrawable());
//                    }
//                });
//            }
//        }

    }

    @Override
    public void onImageClickListener(ImageView view) {
        main_img.setImageDrawable(view.getDrawable());
    }

//    @Override
//    public void onAttachFragment(Fragment fragment) {
//        super.onAttachFragment(fragment);
//        if (fragment instanceof ListViewFragment){
//            ListViewFragment lvf = (ListViewFragment) fragment;
//            lvf.setListViewEvent(this);
//        }
//    }

}