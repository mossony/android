package boyangwan.tk.layouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import boyangwan.tk.layouts.R;

public class ListViewFragment extends Fragment {

    private int img[] = {R.id.image1, R.id.image2, R.id.image3, R.id.image4, R.id.image5};

    public interface ListViewEvent{
        void onImageClickListener(ImageView view);
    }

    ListViewEvent listViewEvent;

    public void setListViewEvent(ListViewEvent listViewEvent){
        this.listViewEvent = listViewEvent;
    }



    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        for (int i=0; i<img.length; i++){
            ImageView iv = (ImageView) this.getActivity().findViewById(img[i]);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listViewEvent.onImageClickListener((ImageView) v);
                }
            });

        }

        super.onViewCreated(view, savedInstanceState);
    }
}
