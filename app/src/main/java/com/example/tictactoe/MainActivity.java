package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int[] board;
    private int activePlayer;
    TextView status;
    gameSource ob;
    int gamestate;
    boolean twoPlayer=true;
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
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status=findViewById(R.id.status);
        reinitialize();
    }
    public void changeMode(View view){
        Button mode=(Button)findViewById(R.id.modebutton);
        if(twoPlayer)
        {
            twoPlayer=false;
            mode.setText("VS AI");
        }
        else
        {
            twoPlayer=true;
            mode.setText("TWO PLAYER");
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
                    status.setText("\tPlayer2 Won \n Tap to Reset");
                    ++gamestate;
                }
                else if(!ob.couldWin('O'))
                {
                    status.setText("Game would end in a draw \n Tap to Reset");
                    ++gamestate;
                }
                else{
                    status.setText("Player 1's Turn");
                    ++activePlayer;
                }
                board[tapped]=1;
            }
//            Toast.makeText(this,ob.printboard(),Toast.LENGTH_SHORT).show();;
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
                    status.setText("Game would end in a draw \n Tap to Reset");
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
//            int cid=ob.compTurn();
//            if(cid==0&&board[cid]==2)
//                img=findViewById(R.id.imageView1);
//            else if(cid==1&&board[cid]==2)
//                img=findViewById(R.id.imageView2);
//            else if(cid==2&&board[cid]==2)
//                img=findViewById(R.id.imageView3);
//            else if(cid==3&&board[cid]==2)
//                img=findViewById(R.id.imageView4);
//            else if(cid==4&&board[cid]==2)
//                img=findViewById(R.id.imageView5);
//            else if(cid==5&&board[cid]==2)
//                img=findViewById(R.id.imageView6);
//            else if(cid==6&&board[cid]==2)
//                img=findViewById(R.id.imageView7);
//            else if(cid==7&&board[cid]==2)
//                img=findViewById(R.id.imageView8);
//            else if(cid==8&&board[cid]==2)
//                img=findViewById(R.id.imageView9);
            img=findViewById(this.getResources().getIdentifier(cid,"id",getPackageName()));
            Log.d("com p turn",""+cid);
            img.setImageResource(R.drawable.o);
            if (ob.isWon('O')) {
                status.setText("App Won\nTap anywhere to reset");
                ++gamestate;
            }
            else if(!ob.couldWin('O'))
            {
                status.setText("Game would end in a draw \n Tap to Reset");
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