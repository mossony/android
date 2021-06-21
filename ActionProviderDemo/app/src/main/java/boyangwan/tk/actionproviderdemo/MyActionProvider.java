package boyangwan.tk.actionproviderdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.view.ActionProvider;

public class MyActionProvider extends ActionProvider {
    Context context;
    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public MyActionProvider(Context context) {
        super(context);
        this.context = context;
    }


    // Action performed when the view is on the menu bar
    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(context).inflate(R.layout.my_action_provider, null, false);
        Button button = view.findViewById(R.id.hello);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Customized menu", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // Action performed when the view is collapsed in the menu bar
    @Override
    public boolean onPerformDefaultAction() {
        Toast.makeText(context, "Customized menu 2", Toast.LENGTH_SHORT).show();
        return super.onPerformDefaultAction();
    }
}
