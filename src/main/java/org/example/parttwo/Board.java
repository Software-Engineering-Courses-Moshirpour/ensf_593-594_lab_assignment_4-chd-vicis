package org.example.parttwo;
/**This is the board class which represent a tik-tak-toe board so it encompasses a 2D array
 * that represents the boards rows and columns.  The class also contains all the major methods
 * that are needed to play tik-tak-toe such as checking if anyone has won, displaying the board and added marks
 * to the board 
 */

public class Board implements Constants {
	/**
	 * declare the board array along with a counter that tracks how many marks have been made(how many turns)
	 */
	private char theBoard[][];
	private int markCount;
	/**constructor that creates a 2D array and loops through each position on the board and assigns a space character to it
	 * 
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		/**loop through each row and create a new array of 3 char long
		 * 
		 */
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			/**
			 * loop through each column and assign each char a space character
			 */
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	/**
	 * @param row index for the row
	 * @param col index for the column
	 * @return returns the mark on the position
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	/**
	 * @return returns true if the board is fullbecause the game can't go beyong 9 turns
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	/**
	 * @return returns true if the X player won
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}
	//same as above but for player O
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * used to display the board by drawing the board out in the terminal
	 */
	public void display() {
		//calls the columnheaders and hyphens function to add the header and a row of hypens for formatting purposes
		displayColumnHeaders();
		addHyphens();
		//loop through each row and print out the row labels
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			//loop through each column and print out each mark
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}
	/**a method that assigns the respective char to the board array
	 * 
	 * @param row row index
	 * @param col column index
	 * @param mark the mark to be added
	 */
	//
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}

	public void removeMark(int row, int col, char mark){
		theBoard[row][col] = SPACE_CHAR;
		markCount--;
	}
	/**a method that loops through the board array and sets all char to nothing (space char in this case)
	 * 
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	/**a method to check if a certain player has won based on which mark they are.  Returns a 1 if they won and a  0 if not
	 * 
	 * @param mark which mark to check if a winner
	 * @return returns a 1 if they won and a 0 if not
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		//this section checks for a win across a row
		//loop through each row
		for (row = 0; result == 0 && row < 3; row++) {
			//reset the row result to 1 after cycling through one row
			int row_result = 1;
			//cycle through each column
			for (col = 0; row_result == 1 && col < 3; col++)
				//if any of the positions don't have the mark that is being tested then it cannot be a win, so set the row result to 0
				if (theBoard[row][col] != mark)
					row_result = 0;
			//if the row result stays as a 1 meaning a loop through an entire row all has the mark then it's a win, so assign a 1 to result signifiying a win
			if (row_result != 0)
				result = 1;
		}

		//this section is similar to the above, but it's checking for a win down a column
		//loop through each column
		for (col = 0; result == 0 && col < 3; col++) {
			//reset column result after the previous column is checked
			int col_result = 1;
			//loop through each row
			for (row = 0; col_result != 0 && row < 3; row++)
				//same as above except we are checking each position down a single column instead of a row
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}
		//this section checks for a diagonal win from the top left to the bottom right
		if (result == 0) {
			int diag1Result = 1;
			//loop through indicies (0,0), (1,1) and (2,2) and if all three have the same mark then set the result to 1
			for (row = 0; diag1Result != 0 && row < 3; row++)
				//if any of the positions are not the same as the mark then it's not a win and assign a diagonal win as a 0
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			//check to see if the diagonal win is true
			if (diag1Result != 0)
				result = 1;
		}
		//same as above except for the reverse diagonal from top right to bottom left
		if (result == 0) {
			int diag2Result = 1;
			//loop through indicies (0,2), (1,1) and (2,0)
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**prints out a header before displaying the results
	 * 
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}
	/**prints out hyphens across the screen to imitate horizontal lines
	 * 
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	/**prints out spaces and | to "break up" the board so it's easier to read.  Otherwise all the numbers and lines would be jammed together
	 * 
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
