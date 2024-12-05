package com.msierra.floraria;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    CardView carta3;
    FrameLayout frame3;
    boolean visible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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