package org.example.parttwo;

public class SmartPlayer extends BlockingPlayer{

    public SmartPlayer(String name, char mark){
        super(name, mark);
    }

    /** (non-Javadoc)
     * @spaceEmpty bool condition used to rerun makeMove if the random numbers chosen are taken
     * @
     */
    public void makeMove(){

        RandomGenerator r1 = new RandomGenerator();

        boolean spaceEmpty = true;

        while(spaceEmpty){
            //loop through each square and if a winning position is avaliable in an open square
            //then mark it for the win
            for(int i =0; i <= 2; i++){
                for(int j =0; j <= 2; j++){
                    if(this.board.getMark(i, j) == SPACE_CHAR && testForWin(i,j)){
                        board.addMark(i, j, mark);
                        return;
                    }
                }
            }


            //same as Blocking Planyer
            for(int i =0; i <= 2; i++){
                for(int j =0; j <= 2; j++){
                    if(this.board.getMark(i, j) == SPACE_CHAR && testForBlocking(i,j)){
                        board.addMark(i, j, mark);
                        return;
                    }
                }
            }
            int randRow  = r1.discrete(0, 2);
            int randCol = r1.discrete(0, 2);

            if(this.board.getMark(randRow, randCol) == SPACE_CHAR){
                board.addMark(randRow, randCol, mark);
                return;
            }
        }
    }

    public boolean testForBlocking(int row, int col){
        //add opponents mark to see if it's a win
        this.board.addMark(row, col, this.opponent.mark);
        //check to see if it's a winning move.  If so then return true otherwise return false
        if(this.board.checkWinner(this.opponent.mark) == 1){
            this.board.removeMark(row, col, this.opponent.mark);
            return true;
        }
        this.board.removeMark(row, col, this.opponent.mark);
        return false;
    }

    public boolean testForWin(int row, int col){
        //add mark to see if it's a win.  Remove the mark before exiting the method
        this.board.addMark(row, col, mark);
        //if it's a win return true, otherwise return false
        if(this.board.checkWinner(mark) == 1){
            this.board.removeMark(row, col, mark);
            return true;
        }
        this.board.removeMark(row, col, mark);
        return false;

    }
    
}
