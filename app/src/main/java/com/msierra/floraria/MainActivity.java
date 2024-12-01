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
        Animation loadTobToBottom = AnimationUtils.loadAnimation(this, R.anim.expand_description);

        carta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean visible = false;
                if(!visible){
                    frame3.setVisibility(View.VISIBLE);
                    frame3.setAnimation(loadTobToBottom);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new FlorFragment3())
                            .commit();
                }else{
                    frame3.setVisibility(View.INVISIBLE);
                }

            }
        });
    }
}