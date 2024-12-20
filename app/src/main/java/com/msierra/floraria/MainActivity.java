package com.msierra.floraria;

import static androidx.core.app.PendingIntentCompat.getActivity;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.transition.AutoTransition;
import android.transition.TransitionManager;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

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
    CardView carta3, flor2, cardView1;
    FrameLayout frame3, frame1, frame2;
    boolean visible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
      
        activity=findViewById(R.id.main);

      
        flor2 = findViewById(R.id.carta2);
        frame2 = findViewById(R.id.frame);
        frame2.setVisibility(View.INVISIBLE);
        flor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FlorFragment2 flor  = new FlorFragment2();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, flor)
                        .commit();

                if(!visible){
                    frame2.setVisibility(View.VISIBLE);
                     visible=true;
                }else{
                    frame2.setVisibility(View.INVISIBLE);
                    visible=false;
                }

            }
        });

        carta3=findViewById(R.id.carta3);
        frame3=findViewById(R.id.flor3);

        carta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!visible){
                    Animation loadTobToBottom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.expand_description);
                    frame3.setVisibility(View.VISIBLE);
                    frame3.setAnimation(loadTobToBottom);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flor3, new FlorFragment3())
                            .commit();
                    visible=true;
                }else{
                    Animation loadTobToBottom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.contract_description);
                    frame3.setAnimation(loadTobToBottom);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flor3, new FlorFragment3())
                            .commit();
                    frame3.setVisibility(View.INVISIBLE);
                    visible=false;
                }

            }
        });

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
                // Esto inicia una nueva transacción de fragmento a través del FragmentManager.
                getSupportFragmentManager().beginTransaction()

                        // Permite al sistema reorganizar las operaciones de transacción para optimizar el rendimiento y reducir posibles errores.
                        .setReorderingAllowed(true)

                        //Agrega el fragmento flor1_fragment a un contenedor con el ID R.id.fragment_container_view.
                        .add(R.id.fragment_container_view, flor1_fragment.class, null)

                        // aplica la transacción.
                        .commit();
            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el FragmentManager asociado a la actividad
                FragmentManager fragmentManager = getSupportFragmentManager();

                //busco mi elemento fragment con el FragmentManager y lo asocio a la variable
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_view);

                // compruebo que no sea  nulo
                if (fragment != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction(); // empieza la transacción
                    transaction.remove(fragment); // elimita el fragmento indicado
                    transaction.commit(); // aplica los cambios.
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}