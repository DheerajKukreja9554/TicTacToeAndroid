package com.example.tictactoe.gamesource;

import java.util.Random;

public class gameSource {
    char[][] board ;
    char pch1;
    char pch2;

    gameSource()
    {
        board=new char[3][3];
        for (int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                board[i][j]='-';
            pch1='X';
            pch2='O';
    }

    public void read(int row,int column,char symbol)
    {

        board[row][column]=symbol;
    }

    public boolean isWon(char symbol)
    {
        if(board[0][0]==symbol)
            if(board[0][0]==board[1][1]&&board[0][0]==board[2][2])
                return true;
        if(board[0][2]==symbol)
            if(board[0][2]==board[1][1]&&board[0][2]==board[2][0])
                return true;
        for(int i=0;i<3;++i)
        {
            if(board[i][0]==symbol)
                if(board[i][2]==board[i][1]&&board[i][2]==board[i][0])
                    return true;
            if(board[0][i]==symbol)
                if(board[2][i]==board[1][i]&&board[2][i]==board[0][i])
                    return true;
        }
        return false;
    }
    public boolean isWon(char board[][],char symbol)
    {
        if(board[0][0]==symbol)
            if(board[0][0]==board[1][1]&&board[0][0]==board[2][2])
                return true;
        if(board[0][2]==symbol)
            if(board[0][2]==board[1][1]&&board[0][2]==board[2][0])
                return true;
        for(int i=0;i<3;++i)
        {
            if(board[i][0]==symbol)
                if(board[i][2]==board[i][1]&&board[i][2]==board[i][0])
                    return true;
            if(board[0][i]==symbol)
                if(board[2][i]==board[1][i]&&board[2][i]==board[0][i])
                    return true;
        }
        return false;
    }
    public boolean couldWin(char symbol)
    {
        char[][] dup =new char[3][3];
        //printArray(dup);
        for(int i=0;i<=2;++i)
            for(int j=0;j<3;j++)
                if(board[i][j]=='-')
                    dup[i][j]=symbol;
                else
                    dup[i][j]=board[i][j];
        return isWon(dup, symbol);
    }

    public int compTurn()
    {
        int r=4, c=4;
        char symbol=pch2;
        char opp=pch1;
        while(true)
        {
            //conquer the mid block
            if(board[1][1]=='-')
            {
                r=1;
                c=1;
                break;
            }
            //computer win if two already filled
            for(int i=0;i<3;i++)
            {
                if(board[i][0]==symbol)
                {
                    if((board[i][1]==symbol)&&(board[i][2]=='-'))
                    {
                        r=i;
                        c=2;
                        break;
                    }
                    if((board[i][2]==symbol)&&(board[i][1]=='-'))
                    {
                        r=i;
                        c=1;
                        break;
                    }
                }
                if(board[0][i]==symbol)
                {
                    if((board[1][i]==symbol)&&(board[2][i]=='-'))
                    {
                        r=2;
                        c=i;
                        break;
                    }
                    if((board[2][i]==symbol)&&(board[1][i]=='-'))
                    {
                        r=1;
                        c=i;
                        break;
                    }
                }
                if(board[i][1]==symbol)
                {
                    if((board[i][2]==symbol)&&(board[i][0]=='-'))
                    {
                        r=i;
                        c=0;
                        break;
                    }
                }
                if(board[1][i]==symbol)
                {
                    if((board[2][i]==symbol)&&(board[0][i]=='-'))
                    {
                        r=0;
                        c=i;
                        break;
                    }
                }
            }
            if(r!=4)
                break;

            //special case
            if(((board[0][0]==opp)&&(board[2][2]==opp))||((board[0][2]==opp)&&(board[2][0]==opp)))
            {
                if(board[1][0]=='-')
                {
                    r=1;
                    c=0;
                    break;
                }

            }
            //opposition win minimum,block if two elements of diagnol already filled
            if(board[1][1]==opp)
            {
                if((board[0][0]==opp)&&(board[2][2]=='-'))
                {
                    r=2;
                    c=2;
                    break;
                }

                if((board[0][2]==opp)&&(board[2][0]=='-'))
                {
                    r=2;
                    c=0;
                    break;
                }
                if((board[2][0]==opp)&&(board[0][2]=='-'))
                {
                    r=0;
                    c=2;
                    break;
                }
                if((board[2][2]==opp)&&(board[0][0]=='-'))
                {
                    r=0;
                    c=0;
                    break;
                }
            }
            //opposition win minimum,block if two elements of row or column already filled
            for(int i=0;i<3;i++)
            {
                if(board[i][0]==opp)
                {
                    if((board[i][1]==opp)&&(board[i][2]=='-'))
                    {
                        r=i;
                        c=2;
                        break;
                    }
                    if((board[i][2]==opp)&&(board[i][1]=='-'))
                    {
                        r=i;
                        c=1;
                        break;
                    }
                }
                if(board[0][i]==opp)
                {
                    if((board[1][i]==opp)&&(board[2][i]=='-'))
                    {
                        r=2;
                        c=i;
                        break;
                    }
                    if((board[2][i]==opp)&&(board[1][i]=='-'))
                    {
                        r=1;
                        c=i;
                        break;
                    }
                }
                if(board[i][1]==opp)
                {
                    if((board[i][2]==opp)&&(board[i][0]=='-'))
                    {
                        r=i;
                        c=0;
                        break;
                    }
                }
                if(board[1][i]==opp)
                {
                    if((board[2][i]==opp)&&(board[0][i]=='-'))
                    {
                        r=0;
                        c=i;
                        break;
                    }
                }
            }
            if(r!=4)
                break;
            //computers win max
            if(board[1][1]==symbol)
            {
                if((board[0][0]==symbol)&&(board[2][2]=='-'))
                {
                    r=2;
                    c=2;
                    break;
                }

                if((board[0][2]==symbol)&&(board[2][0]=='-'))
                {
                    r=2;
                    c=0;
                    break;
                }
                if((board[2][0]==symbol)&&(board[0][2]=='-'))
                {
                    r=0;
                    c=2;
                    break;
                }
                if((board[2][2]==symbol)&&(board[0][0]=='-'))
                {
                    r=2;
                    c=2;
                    break;
                }
            }
            break;
        }
        if(r==4)
        {
            r=1;c=1;
            Random rand=new Random();
            while(board[r][c]!='-')
            {
                r=rand.nextInt(3);
                c=rand.nextInt(3);
            }
        }
        read(r,c,symbol);
        return (r*3+c);

    }
    String printboard()
    {
        String printable="";
        for(char b[]:board){
            printable  = printable + b+"\n";
        }
        return printable;
    }

}
