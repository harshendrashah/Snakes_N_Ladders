package com.shah.snakes_and_ladders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SnakesAndLaddersBoard extends AppCompatActivity {

    int count=0;
    int countP1=0;
    int countP2=0;
    int posP1=0;
    int posP2=0;
    int numberOfPlayers=2;
    int complete_num=0;     // it will store the number of players who have completed playing.

    Players[] plyr=new Players[numberOfPlayers];

    Block[] board=new Block[101];
    Board gameBoard=new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snakes_and_ladders_board);

        gameBoard.createBoard();
        for(int i=0;i<numberOfPlayers;i++){
            plyr[i]=new Players();
        }

    }



    public void diceRoll(View view){
        int value=throwDice();
        int playerPosition = Play(numberOfPlayers,value);
        int n = count % numberOfPlayers +1;
        if(n==1){
            movePlayer1(playerPosition);
        }
        
        else {
            movePlayer2(playerPosition);
        }

        if(value!=6) {
            count++;
        }

    }

    private void displayPlayer1(int number) {
        TextView onDiceRoll = (TextView) findViewById(R.id.player1_onRoll);
        onDiceRoll.setText(String.valueOf(number));
    }

    private void displayPlayer2(int number) {
        TextView onDiceRoll = (TextView) findViewById(R.id.player2_onRoll);
        onDiceRoll.setText(String.valueOf(number));
    }

    private void displayPlayerTurn(){
        TextView playerTurn = (TextView)findViewById(R.id.player_turn_text_view);
        if(count%2==0)
            playerTurn.setText("Player 1's Turn");
        else
            playerTurn.setText("Player 2's Turn");
    }

    private void movePlayer1(int value){

    }

    private void movePlayer2(int value){

    }

    /**
     * Thi method finds the value of the dice roll
     * @return value of the dice roll
     */
    public int throwDice(){
        int dice_no=0;
        Random r=new Random();
        dice_no=r.nextInt(6)+1;
        if(count%2==0) {
            displayPlayer1(dice_no);
            movePlayer1(dice_no);
            countP1++;
        }
        else {
            displayPlayer2(dice_no);
            movePlayer2(dice_no);
            countP2++;
        }

        displayPlayerTurn();
        return dice_no;
    }

    /**
     * This method finds the Posion of player after a single dice roll
     * @param nop is num of players.
     * @param value is the value of the dice roll
     * @return posision after a single roll
     */
    public int Play(int nop,int value){

        int positionAfterRoll=0;                    //Position of specific player after 1 roll
        int j=1;                                    // Player Number
        if(complete_num<nop-1){
            j=count%nop +1;
            if(j<=nop && complete_num!=nop-1){
                if(!plyr[j-1].IsWinner()){
                    int dice=value;
                    if(plyr[j-1].get_pos()+dice<=100)
                        plyr[j-1].update_pos(plyr[j-1].get_pos()+dice);
                    System.out.println(plyr[j-1].get_pos());
                    Block target=gameBoard.getBlock(gameBoard,plyr[j-1].get_pos());
                    if(target.getValue()!=0 ){
                        plyr[j-1].update_pos(target.getJump());
                        positionAfterRoll=plyr[j-1].get_pos();
                    }
                    if(plyr[j-1].get_pos()==100){
                        complete_num++;
                        plyr[j-1].setRank(complete_num);
                        plyr[j-1].set_win_stat(true);
                    }
                }
            }
        }
        if(complete_num==nop-1){
            plyr[j-2].setRank(complete_num);
        }
        if(!plyr[j-1].IsWinner()){
            plyr[j-1].setRank(nop);
        }

        return positionAfterRoll;

    }


}
