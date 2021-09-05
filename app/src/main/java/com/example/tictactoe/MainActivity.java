package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoe.gamesource.gameSource;

public class MainActivity extends AppCompatActivity {

    int[] board;
    int activePlayer;
    TextView status;
    gameSource ob;
    int gamestate;
    boolean twoPlayer=true;

    @SuppressLint("SetTextI18n")
    public void reinitialize()
    {
        board= new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        activePlayer=0;
        if(twoPlayer)
            status.setText("Player 1's Turn");
        else
            status.setText("Player's Turn");
        ob=new gameSource();
        gamestate=0;
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status=findViewById(R.id.status);
        ImageButton modeButton=findViewById(R.id.modeButton);
        modeButton.setBackgroundResource(R.mipmap.twoplayer_foreground);
        reinitialize();
    }

    public void changeMode(View view){
        ImageButton mode= (ImageButton) findViewById(R.id.modeButton);
        if (twoPlayer) {
            twoPlayer= false;
            mode.setBackgroundResource(R.mipmap.computer_foreground);
        }
        else{
            twoPlayer= true;
            mode.setBackgroundResource(R.mipmap.twoplayer_foreground);
        }
        reinitialize();

    }
    public void twoPlayerGame(View view){
        ImageView img=(ImageView) view;
        int tapped=Integer.parseInt(img.getTag().toString());
        if  (gamestate!=0)
            reinitialize();
        else if(board[tapped]==2){

            if(activePlayer%2==0){
                ob.read(tapped / 3, tapped % 3, 'X');
                img.setImageResource(R.drawable.x);
                if(ob.isWon('X')){
                    status.setText("\tPlayer1 Won \n Tap to Reset");
                    ++gamestate;
                }
                else if (!ob.couldWin('X'))
                {
                    status.setText("Game would end in a draw \n Tap to Reset");
                    ++gamestate;
                }
                else{
                    status.setText("Player 2's Turn");
                    ++activePlayer;
                }
                board[tapped]=0;
            }
            else
            {
                ob.read(tapped / 3, tapped % 3, 'O');
                img.setImageResource(R.drawable.o);
                if(ob.isWon('O')){
                    status.setText("\tPlayer2 Won \nTap to Reset");
                    ++gamestate;
                }
                else if(!ob.couldWin('O'))
                {
                    status.setText("Game would end in a draw \nTap to Reset");
                    ++gamestate;
                }
                else{
                    status.setText("Player 1's Turn");
                    ++activePlayer;
                }
                board[tapped]=1;
            }
        }

    }
    public void playerTap(View view) {

        if (twoPlayer)
            twoPlayerGame(view);
        else
            vsComputerGame(view);

    }
    public void vsComputerGame(View view){

        ImageView img=(ImageView) view;
        int tapped=Integer.parseInt(img.getTag().toString());
        Log.d("touched",""+tapped+" "+board[tapped]);
        if(gamestate==1)
            reinitialize();
        else if (activePlayer%2==0) {

            if (board[tapped] == 2) {
//                Toast.makeText(this,"imageid="+tapped,Toast.LENGTH_SHORT).show();
                ob.read(tapped / 3, tapped % 3, 'X');
                img.setImageResource(R.drawable.x);
                if (ob.isWon('X')) {
                    status.setText("Player Won\nTap anywhere to reset");
                    ++gamestate;
                }
                else if(!ob.couldWin('X'))
                {
                    status.setText("Game would end in a draw \nTap to Reset");
                    ++gamestate;
                }
                else {
                    status.setText("Tap anywhere for app's turn");
                }
                ++activePlayer;
                board[tapped] = 1;
            }
        }

        else if(activePlayer%2==1){


            int cturn=ob.compTurn();
            String cid= "imageView"+(cturn+1);
            img=findViewById(this.getResources().getIdentifier(cid,"id",getPackageName()));
            Log.d("com p turn",""+cid);
            img.setImageResource(R.drawable.o);
            if (ob.isWon('O')) {
                status.setText("App Won\nTap anywhere to reset");
                ++gamestate;
            }
            else if(!ob.couldWin('O'))
            {
                status.setText("Game would end in a draw \nTap to Reset");
                ++gamestate;
            }
            else {
                status.setText("Player's Turn");
            }
            ++activePlayer;
            board[cturn]=0;
        }


    }
}