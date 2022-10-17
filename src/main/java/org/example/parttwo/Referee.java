package org.example.parttwo;
import java.io.*;
/**
 * referee class that is used to start the game
 */
public class Referee {

    Player xPlayer;
    Player oPlayer;
    Board board;

    public Referee(){        
    }
    /**
     * 
     * this function sets the players, then displays the board and tells the first player to make the first move
     * This intigates the game
     * @throws IOException exception for improper text input from user
     */
    public void runTheGame() throws IOException {

        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        board.display();
        xPlayer.play();
    }
    /**
     * ensures the ref is using the same board as the players and game
     * @param board board instance that is used by all the other classes
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

}
