package org.example.parttwo;
import java.io.*;
/**
 * @namne name of player 
 * @board an instance of the board
 * @opponent the opponent player
 * @mark the players mark which is with "x" or "o"
 */
abstract class Player implements Constants{

    String name;
    Board board;
    Player opponent;
    char mark;
    //change the method defintions to only declarations with implementations being moved to the different types of player
    abstract protected void play() throws IOException;
    abstract protected void makeMove() throws IOException;

    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public void setOpponent(Player player) {
        opponent = player;

    }

    public void setBoard(Board theBoard) {
        board = theBoard;

    }

}
