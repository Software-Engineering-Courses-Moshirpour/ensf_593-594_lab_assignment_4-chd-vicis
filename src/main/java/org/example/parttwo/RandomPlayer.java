package org.example.parttwo;
import java.io.*;
//inherit from the Player class
public class RandomPlayer extends Player {
    //constructor for RandomPlayer.  Call the super consutrctor to initalize member variables from Player
    public RandomPlayer(String name, char mark){
        super(name, mark);
    }

    //same as previous assignment
    public void play() throws IOException{
        //check to make sure the game isn't over
        if(!(board.xWins() || board.oWins() || board.isFull())){
            this.makeMove();
        }
        //assign player names to strings to be used once game ends
        String xPlayer;
        String oPlayer;

        if(mark == LETTER_X){
            xPlayer = name;
            oPlayer = opponent.name;
        } else {
            xPlayer = opponent.name;
            oPlayer = name;
        }

        //if the game ended then display the correct meesage
        if(board.xWins()){
            System.out.println("THE GAMIE IS OVER: " + xPlayer + " has won.");
            System.out.println("Game Ended ...");
        } else if (board.oWins()){
            System.out.println("THE GAMIE IS OVER: " + oPlayer + " has won.");
            System.out.println("Game Ended ...");
        } else if(board.isFull()){
            System.out.println("THE GAMIE IS OVER and it's a tie");
            System.out.println("Game Ended ...");
        }

        //display board
        board.display();

        if(!(board.xWins() || board.oWins() || board.isFull())){
            opponent.play();
        }

    }

    public void makeMove(){
        //create instance of random number generator
        RandomGenerator r1 = new RandomGenerator();

        int row;
        int col;
        //create a bool to watch if an empty position is open
        boolean spaceEmpty = true;

        //keep searching for random numbers until an empty spot is found
        while(spaceEmpty){
            //create two random numbers from 0-2
            int randRow  = r1.discrete(0, 2);
            int randCol = r1.discrete(0, 2);
            //if the position on the board is empty then have this player fill in their mark
            if(this.board.getMark(randRow, randCol) == SPACE_CHAR){
                row = randRow;
                col = randCol;
                board.addMark(row, col, mark);
                //end the while loop
                spaceEmpty = false;
            }
        }
    }
}
