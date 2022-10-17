package org.example.parttwo;

//inherit from RandomPlayer 
public class BlockingPlayer extends RandomPlayer{

    //Constructor for Blocking Player.  Calls constrcutor for RandomPlayer
    public BlockingPlayer(String name, char mark){
        super(name, mark);
    }

    
    public void makeMove(){

        RandomGenerator r1 = new RandomGenerator();

        boolean spaceEmpty = true;

        while(spaceEmpty){

            //loop through every sqaure on the board
            for(int i =0; i <= 2; i++){
                for(int j =0; j <= 2; j++){
                    //if the square on the board is open and it can block an opponent win then add
                    //a mark and exit the function
                    if(this.board.getMark(i, j) == SPACE_CHAR && testForBlocking(i,j)){
                        board.addMark(i, j, mark);
                        return;
                    }
                }
            }
            //same as for RandomPlayer.  If a block isn't found add to a random square
            int randRow  = r1.discrete(0, 2);
            int randCol = r1.discrete(0, 2);

            if(this.board.getMark(randRow, randCol) == SPACE_CHAR){
                board.addMark(randRow, randCol, mark);
                return;
            }
        }
    }

    public boolean testForBlocking(int row, int col){
        //add opponents mark to see if it's a win.  It is removed before exiting the function
        this.board.addMark(row, col, this.opponent.mark);
        //check to see if it's a winning move.  If so then return true otherwise return false
        if(this.board.checkWinner(this.opponent.mark) == 1){
            this.board.removeMark(row, col, this.opponent.mark);
            return true;
        }
        this.board.removeMark(row, col, this.opponent.mark);
        return false;
    }
    
}
