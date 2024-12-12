package com.msierra.floraria;

import static androidx.core.app.PendingIntentCompat.getActivity;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    View activity;
    CardView carta3;
    FrameLayout frame3, frame1;
    boolean visible = false;
    CardView cardView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        activity=findViewById(R.id.main);
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
        frame1=findViewById(R.id.fragment_container_view);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragment_container_view, flor1_fragment.class, null)
                        .commit();
            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_view);
                if (fragment != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.remove(fragment);
                    transaction.commit();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        carta3=findViewById(R.id.carta3);
        frame3=findViewById(R.id.frame);

        carta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!visible){
                    Animation loadTobToBottom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.expand_description);
                    frame3.setVisibility(View.VISIBLE);
                    frame3.setAnimation(loadTobToBottom);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new FlorFragment3())
                            .commit();
                    visible=true;
                }else{
                    Animation loadTobToBottom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.contract_description);
                    frame3.setAnimation(loadTobToBottom);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new FlorFragment3())
                            .commit();
                    frame3.setVisibility(View.INVISIBLE);
                    visible=false;
                }

            }
        });
    }
}