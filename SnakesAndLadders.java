/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

/**
 *
 * @author Rohit
 */
import java.util.*;

class Block{
    public Block(){
        block_id=0;
        block_value=0;
        jump_pos=0;
    }
    void setBlock_id(Block b,int i){
        b.block_id=i;
    }
    void setValue(Block b,int i){
        b.block_value=i;
    }
    void setJumpPos(Block b,int i){
        b.jump_pos=i;
    }
    int getValue(){
        return this.block_value;
    }
    int getJump(){
        return this.jump_pos;
    }
    int get_id(){
        return this.block_id;
    }
    private int block_id;
    private int block_value;
    private int jump_pos;
}

class Board{
    public Board(){}
    Block b_obj=new Block();
        Block[] SL_board=new Block[101];
    public void createBoard(){
        for(int i=0;i<101;i++){
           SL_board[i]=new Block(); 
        }
        for(int i=0;i<=100;i++){
          b_obj.setBlock_id(SL_board[i],i);
          b_obj.setJumpPos(SL_board[i],i);
        }
        // Setting jump positions for those blocks which have ladders climbing up.
        b_obj.setValue(SL_board[5],1);
        b_obj.setJumpPos(SL_board[5],25);
        b_obj.setValue(SL_board[10],1);
        b_obj.setJumpPos(SL_board[10],29);
        b_obj.setValue(SL_board[22],1);
        b_obj.setJumpPos(SL_board[22],41);
        b_obj.setValue(SL_board[28],1);
        b_obj.setJumpPos(SL_board[28],55);
        b_obj.setValue(SL_board[44],1);
        b_obj.setJumpPos(SL_board[44],95);
        b_obj.setValue(SL_board[70],1);
        b_obj.setJumpPos(SL_board[70],89);
        b_obj.setValue(SL_board[79],1);
        b_obj.setJumpPos(SL_board[79],81);
        
        // Setting jump positions for blocks having snake faces.
        b_obj.setValue(SL_board[31],-1);
        b_obj.setJumpPos(SL_board[31],14);
        b_obj.setValue(SL_board[37],-1);
        b_obj.setJumpPos(SL_board[37],17);
        b_obj.setValue(SL_board[73],-1);
        b_obj.setJumpPos(SL_board[73],53);
        b_obj.setValue(SL_board[78],-1);
        b_obj.setJumpPos(SL_board[78],29);
        b_obj.setValue(SL_board[92],-1);
        b_obj.setJumpPos(SL_board[92],35);
        b_obj.setValue(SL_board[99],-1);
        b_obj.setJumpPos(SL_board[99],7);
    }
    Block[] getMyBlocks(Board b1){
        return b1.SL_board;
    }
    Block getBlock(Board b,int pos){
        return b.SL_board[pos];
    }
}

class Players{
    private int player_pos;
    private boolean win_stat;
    private int rank;
    public Players(){
        player_pos=0;
        win_stat=false;
        rank=0;
    }
    public int get_pos(){
        return this.player_pos;
    }
    public void update_pos(int pos){
        this.player_pos=pos;
    }
    public boolean IsWinner(){
        return this.win_stat;
    }
    public int getRank(){
        return this.rank;
    }
     public void setRank(int i){
        this.rank=i;
    }
     public void set_win_stat(boolean ws){
         this.win_stat=ws;
     }
}
class GamePlay{
    Block[] board=new Block[101];
    Board gameBoard=new Board();
       public GamePlay(){
    }
    public int throwDice(){
        int count=0;
        int dice_no=0;
        Random r=new Random();
        dice_no=r.nextInt(5)+1;
        if(dice_no==6){
            count++;
            while(count<3){
                dice_no+=r.nextInt(5)+1;
                    if(dice_no%6==0)
                         count++;
                    else 
                        break;
            }
           if(count==3){
               dice_no=0;
           } 
        }
        return dice_no;
    }
    public void Play(int nop){             // nop= num of players.
        gameBoard.createBoard();
        Block z=new Block();
        z=gameBoard.getBlock(gameBoard,10);
        System.out.println("@@@@");
        System.out.println(z.getValue());
        int complete_num=0;     // it will store the number of players who have completed playing.
        Players[] plyr=new Players[nop];
        for(int i=0;i<nop;i++){
            plyr[i]=new Players();
        }
        int i=0;
        int j=1;
        while(complete_num<nop-1){
             j=1;
            for(j=1;j<=nop && complete_num!=nop-1;j++){
                if(!plyr[j-1].IsWinner()){
                System.out.println("Player "+j);
                int dice=throwDice();
                System.out.println("Dice is "+dice);
                System.out.println(plyr[j-1].get_pos());
                if(plyr[j-1].get_pos()+dice<=100)
                plyr[j-1].update_pos(plyr[j-1].get_pos()+dice);
                System.out.println(plyr[j-1].get_pos());
                Block target=gameBoard.getBlock(gameBoard,plyr[j-1].get_pos());
                if(target.getValue()!=0 ){
                    plyr[j-1].update_pos(target.getJump());
                    System.out.println("**"+plyr[j-1].get_pos());
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
        for(i=0;i<nop;i++){
            if(!plyr[i].IsWinner()){
                plyr[i].setRank(nop);
            }
        }
        for(i=0;i<nop;i++){
            System.out.println("*******Player "+(i+1)+" ranks "+plyr[i].getRank());
        }
    }
}

public class SnakesAndLadders{
    public static void main(String arg[]){
       Scanner sc=new Scanner(System.in);
       System.out.println("Enter number of players");
       int n=sc.nextInt();
       GamePlay g=new GamePlay();
       g.Play(n);
    }
}
