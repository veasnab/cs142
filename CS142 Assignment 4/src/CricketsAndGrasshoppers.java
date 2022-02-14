
//import java.util.Arrays;
import java.util.Scanner;
// Bun, Veasna

public class CricketsAndGrasshoppers{
	
	/**
	 * Print the prompt using System.out.print(...). If the next piece of
	 * information in the Scanner represents an integer which is at least 1 and at
	 * most max, return the number, but make sure the Scanner also reads in the rest
	 * of the line before returning. Otherwise (if the next piece of information
	 * doesn’t represent the above requirements), read in the rest of the line,
	 * print the line That was not a valid number! Please try again. and repeat the
	 * process described in this box.
	 * 
	 * @param s
	 * @param prompt
	 * @param max
	 * @return
	 */
	
	public static int promptNumberReadLine(Scanner s, String prompt, int max) {
		// Working
		while (true) {
			if(s.hasNextInt()) {
				int numberS = s.nextInt();
				if (numberS <= max && numberS >= 1) {
					s.nextLine();
					return numberS; 
				}
				if (numberS >= max || numberS <= 1) {
					System.out.println("That was not a valid number! Please try again.");
					System.out.print(prompt);
					s.nextLine();
				}	
			}
			else {
				System.out.println("That was not a valid number! Please try again.");
				System.out.print(prompt);
				s.nextLine();	
			}
		}	
	}
	
	/**
	 * Create and return an array representing a new game with the number of pieces
	 * for each player indicated. The pieces should be on the ends of the board with
	 * the specified empty spaces
	 * 
	 * @param piecesPerPlayer
	 * @param spacesInMiddle
	 * @return
	 */
	
	public static int[] createBoard(int piecesPerPlayer, int spacesInMiddle) {
		// Working
		int [] array = new int [piecesPerPlayer * 2 + spacesInMiddle+2]; 
		int playerOne = piecesPerPlayer;
		int playerTwo = piecesPerPlayer;
		int middleOfBoard = spacesInMiddle;
		
		for (int boardSize = 0; boardSize < array.length; boardSize++) {
			
			if (playerOne != 0) {
				array [boardSize + 1] = 1;
				playerOne = playerOne - 1;
			}
			if (playerOne == 0 && middleOfBoard !=0) {
				array [boardSize + 2] = 2;
				middleOfBoard = middleOfBoard - 1;	 
			}
			if (playerOne == 0 && middleOfBoard == 0 && playerTwo != 0) {
				array [boardSize + 3] = 3;
				playerTwo = playerTwo - 1;	
			}
		}	
		return array; 	
	}

	/**
	 * Create and return a String that represents the game board, all on one line.
	 * Don’t print it! Crickets are specified with C, grasshoppers with G, and empty
	 * spaces with . (period) Hint: Use string concatenation +
	 * 
	 * @param board
	 * @return
	 */

	public static String boardToString(int[] board) {
		// Working
		String s = "";
		String crickets = "C";
		String grassHopper = "G";
		String emptySpaces = ".";
		s = s + "";
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 1) {
				s = s + crickets;
			}
			if (board[i] == 2) {
				s = s + emptySpaces;
			}
			if (board[i] == 3) {
				s = s + grassHopper;
			}
		}
		s = s + "";
		return s;
	}

	/**
	 * Return true if the given player has any move they can make. Cricket is player
	 * 1 and grasshopper is 2
	 * 
	 * @param board
	 * @param player
	 * @return
	 */

	public static boolean canMove(int[] board, int player) {
		// working
		if (player == 1) {
			for (int position = 0; position < board.length; position++) {
				if (board[position] == 1 && board[position + 1] == 2) {
					return true;	
				}
				if (board[position] == 1 && board[position + 1] == 3 && board[position + 2] == 2) {
					return true;		
				}
			}
		}
		else if (player == 2) {
			for (int position = 0; position < board.length; position++) {
				if ((board[position] == 3 && board[position - 1] == 2)) {
					return true;		
				}
				if (board[position] == 3  && board[position - 1] == 1 && board[position - 2] == 2) {
					return true;	
				}
			}
		
		}
		 return false;
	}


	/**
	 * The player moves their piece in the given position (numbered 1 through n). If
	 * the specified move is allowed, modify the board and return true. Otherwise,
	 * don’t modify board and return false. Don’t print!
	 * 
	 * @param board
	 * @param player
	 * @param position
	 * @return
	 */

	public static boolean move(int[] board, int player, int position) {
		//working
		//player One (crickets) make a move
		if (player == 1 && board[position] == 1 && board[position + 1] == 2) {
			board[position] = 2;
			board[position + 1] = 1;
			return true;	
		}
		if (player == 1 && board[position] == 1 && board[position + 1] == 3 && board[position + 2] == 2){
			board[position] = 2;
			board[position + 2] = 1;
			return true;
		}	
		//player Two (grass hopper) make a move.
		if (player == 2 && board[position] == 3 && board[position - 1] == 2) {
			board[position] = 2;
			board[position - 1] = 3;
			return true;
		}
		if (player == 2 && board[position] == 3  && board[position - 1] == 1 && board[position - 2] == 2) {
			board[position] = 2;
			board[position - 2] = 3;
			return true;
		}	
	return false;
	}

	/**
	 * Write a main method which creates the Crickets and Grasshoppers game with the
	 * wording provided
	 * 
	 * @param strings
	 */

	public static void main(String[] strings) {
		//working
		
		//called the promptNumberReadLine (method)to check if input from scanner is valid.
		//input from scanner is place in the parameter to createBoard (method) creating the board.
		//displaying the createBoard by printing the boardTo String (method).
		Scanner s = new Scanner(System.in);	
		System.out.print("Please enter the number of pieces for each player (1-10): ");
		int piecesPerPlayer = promptNumberReadLine(s,"Please enter the number of pieces for each player (1-10): ",10);
		System.out.print("Please enter the number of spaces in the middle (1-9): ");
		int spacesInMiddle = promptNumberReadLine(s,"Please enter the number of spaces in the middle (1-9): ",9);
		int array [] = createBoard(piecesPerPlayer , spacesInMiddle);
		System.out.println(boardToString(array)); 
		// the next line is place in Comments but it print the value store in the array as integers and not as string. DONOTPRINT! 
		//System.out.println(Arrays.toString(createBoard(piecesPerPlayer,spacesInMiddle)));
		
		//called the promptNumberReadLine (method) to check if input from scanner is valid.
		//also using the move (method) to update and print boardToString (method).
		// while loop will test if a move can be made and return true if a move can be made.
		// the integer "playerTurn" will keep track on who turn it is starting with player one: Crickets.
		int playerTurn = 1;
		boolean moveAble = true;
		boolean isRunning = true;
		while (isRunning) {
			while (playerTurn == 1 ) {
				//Crickets
				System.out.print("Crickets, please enter your move (1-" + (array.length - 2) + "): ");
				int cricketsMove = promptNumberReadLine(s, "Crickets, please enter your move (1-" + (array.length - 2) + "): ", array.length);
				//this line a code will only enter if the user input cause it to be true.
				if (move(array,1 ,cricketsMove)) {
					playerTurn = 2;
					moveAble = canMove(array, playerTurn);
					if (moveAble) {
						System.out.println(boardToString(array));	
					}
				}
				else {
					System.out.println("That space does not contain a piece you can move! Please try again.");
				}
				if (!moveAble) {
					System.out.println("Crickets win!");
					isRunning = false;
					playerTurn = 0;
				}
			}
			while (playerTurn == 2) {
				//Grasshoppers
				System.out.print("Grasshoppers, please enter your move (1-" + (array.length - 2) + "): ");
				int grasshoppersMove = promptNumberReadLine(s, "Grasshoppers, please enter your move (1-" + (array.length - 2) + "): ", array.length);
				//this line a code will only enter if the user input cause it to be true.
				if (move(array,2 ,grasshoppersMove)) {
					playerTurn = 1;
					moveAble = canMove(array, playerTurn);
					if (moveAble) {
						System.out.println(boardToString(array));	
					}
				}
				else {
					System.out.println("That space does not contain a piece you can move! Please try again.");
				}
				if (!moveAble) {
					System.out.println("Grasshoppers win!");
					isRunning = false;
					playerTurn = 0;
				}
			}
		}
	}
}

