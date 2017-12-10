package com.shah.snakes_and_ladders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LastPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
    }

    public void onClickReplay(View view){
        Intent replay = new Intent(LastPageActivity.this,SnakesAndLaddersBoard.class);
        startActivity(replay);
    }
}
