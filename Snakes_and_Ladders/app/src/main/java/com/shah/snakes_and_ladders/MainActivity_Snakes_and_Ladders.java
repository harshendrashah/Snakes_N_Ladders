package com.shah.snakes_and_ladders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity_Snakes_and_Ladders extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__snakes_and__ladders);


        RelativeLayout image = (RelativeLayout) findViewById(R.id.start_page);

        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent board = new Intent(MainActivity_Snakes_and_Ladders.this, SnakesAndLaddersBoard.class);
                startActivity(board);
            }
        });
    }

}
