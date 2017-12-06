package com.shah.snakes_and_ladders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity_Snakes_and_Ladders extends AppCompatActivity {

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__snakes_and__ladders);
    }

    public void onTappingHomePage(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snakes_and_ladders_grid);
    }

    public void diceRoll(View view){
        Random rand=new Random();
        int value=rand.nextInt(6) + 1;
        if(count%2==0) {
            displayPlayer1(value);

        }
        else {
            displayPlayer2(value);
        }
        if(value!=6) {
            count++;
        }
        displayPyayerTurn();
    }

    private void displayPlayer1(int number) {
        TextView onDiceRoll = (TextView) findViewById(R.id.player1_onRoll);
        onDiceRoll.setText(String.valueOf(number));
    }

    private void displayPlayer2(int number) {
        TextView onDiceRoll = (TextView) findViewById(R.id.player2_onRoll);
        onDiceRoll.setText(String.valueOf(number));
    }

    private void displayPyayerTurn(){
        TextView playerTurn = (TextView)findViewById(R.id.player_turn_text_view);
        if(count%2==0)
            playerTurn.setText("Player1's Turn");
        else
            playerTurn.setText("Player2's Turn");
    }

}
