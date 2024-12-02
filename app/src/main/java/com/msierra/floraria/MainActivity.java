package com.msierra.floraria;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private CardView flor2;
    private FrameLayout frame2;
    Boolean visible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        flor2 = findViewById(R.id.carta2);
        frame2 = findViewById(R.id.frame);
        frame2.setVisibility(View.INVISIBLE);
        flor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction =  fragmentManager.beginTransaction();
                FlorFragment2 flor  = new FlorFragment2();
                transaction.add(R.id.frame,flor);
                transaction.commit();

                if(!visible){
                            frame2.setVisibility(View.VISIBLE);



                             visible=true;
                        }else{
                            frame2.setVisibility(View.INVISIBLE);
                            visible=false;
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