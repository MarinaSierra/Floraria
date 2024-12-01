package com.msierra.floraria;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CardView carta3;
    FrameLayout frame3;

    Boolean visible = true;



    CardView cardView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        frame3=findViewById(R.id.frame);
        frame3.setVisibility(View.INVISIBLE);
        setContentView(R.layout.activity_main);


        CardView cv4 = findViewById(R.id.carta4);

        cv4.setOnClickListener(v -> {

                    if (visible) {
                        visible = false;
                        // Iterar sobre todos los hijos del CardView y ocultarlos
                        for (int i = 0; i < cv4.getChildCount(); i++) {
                            View child = cv4.getChildAt(i);
                            child.setVisibility(View.GONE);
                        }
                        findViewById(R.id.frg4).setVisibility(View.VISIBLE);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frg4, new Fragment4())
                                .commit();
                    } else {
                        visible = true;
                        for (int i = 0; i < cv4.getChildCount(); i++) {
                            View child = cv4.getChildAt(i);
                            child.setVisibility(View.VISIBLE);
                        }
                        findViewById(R.id.frg4).setVisibility(View.INVISIBLE);
                    }
        });

        cardView1 = findViewById(R.id.carta1);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.zoom_in, R.anim.zoom_out)
                            .setReorderingAllowed(true)
                            .add(R.id.fragment_container_view, flor1_fragment.class, null)
                            .commit();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame3.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, new FlorFragment3())
                        .commit();
            }
        });

    }
}