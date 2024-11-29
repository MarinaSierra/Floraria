package com.msierra.floraria;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Boolean visible = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CardView cv4 = findViewById(R.id.carta4);


        cv4.setOnClickListener(v ->{

            if(visible){
                visible = false;
                // Iterar sobre todos los hijos del CardView y ocultarlos
                for (int i = 0; i < cv4.getChildCount(); i++) {
                    View child = cv4.getChildAt(i);
                    child.setVisibility(View.INVISIBLE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frg4, new Fragment4())
                            .commit();
                }
            }
            else{
                visible = true;
                for (int i = 0; i < cv4.getChildCount(); i++) {
                    View child = cv4.getChildAt(i);
                    child.setVisibility(View.VISIBLE);
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