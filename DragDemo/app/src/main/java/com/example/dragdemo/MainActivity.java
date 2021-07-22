package com.example.dragdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    LinearLayout container;
    RelativeLayout topContainer;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        imageView.setTag("Dragged to icon area");
        container = findViewById(R.id.container);
        topContainer = findViewById(R.id.top_container);
        title = findViewById(R.id.title);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onLongClick(View view) {
                ClipData.Item item = new ClipData.Item((String)view.getTag());
                ClipData data = new ClipData("Dragged to icon area", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                view.startDragAndDrop(data, new View.DragShadowBuilder(view), null, 0);
                return true;
            }
        });

        topContainer.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int action = dragEvent.getAction();
                switch (action){
                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        return true;

                    case DragEvent.ACTION_DROP:
                        imageView.setX(dragEvent.getX()-imageView.getWidth()/2.0f);
                        imageView.setY(dragEvent.getY()-imageView.getHeight()/2.0f);
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;

                }




                return false;
            }
        });

        container.setOnDragListener(new View.OnDragListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int action = dragEvent.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return true;
                        }
                        return false;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        container.setBackgroundColor(Color.YELLOW);
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        container.setBackgroundColor(Color.BLUE);
                        title.setText("");
                        return true;

                    case DragEvent.ACTION_DROP:
                        ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                        String dragData = item.getText().toString();
                        title.setText(dragData + "\n" + dragEvent.getX() + "," + dragEvent.getY());
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;

                }
                return false;
            }
        });

    }
}



