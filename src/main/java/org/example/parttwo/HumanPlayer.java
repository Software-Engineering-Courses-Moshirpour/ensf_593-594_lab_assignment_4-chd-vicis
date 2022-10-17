package org.example.parttwo;
import java.io.*;

//Same as my code from assignment 3
public class HumanPlayer extends Player{

    public HumanPlayer(String name, char mark){
        super(name, mark);
    }

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

    public void makeMove() throws IOException {
        // declare bufferedreader and ask user for input on which row and col to place
        // their mark
        BufferedReader stdin;

        System.out.println(name + ", what row should your next " + mark + " be played in?");
        stdin = new BufferedReader(new InputStreamReader(System.in));
        String row = stdin.readLine();
        while (row == null) {
            System.out.print("Please try again: ");
            row = stdin.readLine();
        }

        System.out.println(name + ", what column should your next " + mark + " be played in?");
        stdin = new BufferedReader(new InputStreamReader(System.in));
        String col = stdin.readLine();
        while (col == null) {
            System.out.print("Please try again: ");
            col = stdin.readLine();
        }

        // make mark on the board based on their input
        board.addMark(Integer.valueOf(row), Integer.valueOf(col), mark);
    }
    
}
