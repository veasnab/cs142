
/**
 * Test of CS 142 Assignment 4 by Martin Hock (Version of 3:00 PM 11/3/2019)
 * 
 * This code is for personal educational use only and may not be redistributed
 * without permission of the author.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CricketsAndGrasshoppersTest {

	public static int fb(int a) {
		for (int i = 0; i < a; i++) {
			if (((1 << i) & a) != 0)
				return i;
		}
		throw new RuntimeException("Test error");
	}

	public static int lb(int a) {
		for (int i = 0; a > 0; a >>= 1, i++) {
			if (a == 1)
				return i;
		}
		throw new RuntimeException("Test error");
	}

	public static int bb(int a, int b, int c) {
		return ((b & c) >> (c - 1)) == 0 ? fb(a) : lb(a);
	}

	public static void main(String[] args) {
		int pnrlScore = 0, cbScore = 0, btsScore = 0, cmScore = 0, moveScore = 0, mainScore = 0;
		int idCount = 0;
		int exCount = 0;
		try {
			String[] lines = { "1\n", "2\n1\n", "a\n1\n", "0\na\n60\n6\n5\n", "a b c d\n10 20\n3\n" };
			String[] prompts = { "Please enter something: ", "This is a test prompt (1-9999): " };
			String[][] outs = { { "Please enter something: " }, { "This is a test prompt (1-9999): " },
					{ "Please enter something: That was not a valid number! Please try again.",
							"Please enter something: " },
					{ "This is a test prompt (1-9999): That was not a valid number! Please try again.",
							"This is a test prompt (1-9999): That was not a valid number! Please try again.",
							"This is a test prompt (1-9999): That was not a valid number! Please try again.",
							"This is a test prompt (1-9999): " },
					{ "Please enter something: That was not a valid number! Please try again.",
							"Please enter something: That was not a valid number! Please try again.",
							"Please enter something: " } };
			int[] maxs = { 1, 2, 1, 6, 9 };
			int[] rets = { 1, 2, 1, 6, 3 };
			for (int i = 0; i < lines.length; i++) {
				try {
					System.out.println("Testing promptNumberReadLine method with this keyboard input:");
					System.out.print(lines[i]);
					System.out.println("this prompt: \"" + prompts[i % 2] + "\"");
					System.out.println("and this max value: " + maxs[i] + "...");
					PrintStream oldOut = System.out;
					Scanner s = new Scanner(lines[i]);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					System.setOut(new PrintStream(out));
					int ret;
					try {
						ret = CricketsAndGrasshoppers.promptNumberReadLine(s, prompts[i % 2], maxs[i]);
					} finally {
						System.setOut(oldOut);
					}
					if (ret != rets[i]) {
						System.out.println("Return value: expected " + rets[i] + " got " + ret);
					} else {
						pnrlScore += 2;
					}
					if (out.toString().equals(String.join(System.lineSeparator(), outs[i]))) {
						System.out.println("Output identical.");
						idCount++;
						pnrlScore += 2;
					} else if (out.toString().replaceAll("\\s*", "")
							.equalsIgnoreCase(String.join("", outs[i]).replaceAll("\\s*", ""))) {
						System.out.println("Output equivalent.");
						pnrlScore += 2;
					} else if (!out.toString().contains(prompts[i % 2])) {
						System.out.println(
								"Your output does not include the provided prompt! Please use the prompt parameter.");
					} else {
						Scanner outReader = null;
						try {
							outReader = new Scanner(out.toString());
							boolean finished = false;
							lineloop: for (int j = 0; j < outs[i].length; j++) {
								if (!outReader.hasNext()) {
									System.out.println(
											"Should have " + outs[i].length + " lines of output but only has " + j);
									break;
								}
								Scanner currentLine = new Scanner(outs[i][j]);
								String myLine = null;
								String cLine = null;
								while (currentLine.hasNext()) {
									String myout = outReader.next();
									if (myLine == null)
										myLine = myout;
									else
										myLine += " " + myout;
									String currentWord = currentLine.next();
									if (cLine == null)
										cLine = currentWord;
									else
										cLine += " " + currentWord;
									if (!currentWord.equals(myout)) {
										System.out.println(
												"Line " + (j + 1) + ": expected a line beginning \"" + cLine + "\"");
										System.out.println(
												"but your code outputs a line beginning this way: \"" + myLine + "\"");
										break lineloop;
									}
								}
								if (j == 0) {
									pnrlScore++;
								}
								if (j == outs[i].length - 1) {
									pnrlScore++;
									finished = true;
								}
							}
							if (finished && outReader.hasNext()) {
								System.out.println("Unexpected extra output from your code:");
								while (outReader.hasNextLine()) {
									System.out.println(outReader.nextLine());
								}
								pnrlScore--;
							}

						} finally {
							if (outReader != null)
								outReader.close();
						}
					}
				} catch (Exception e) {
					System.out.println(
							"The following error in your code occurred while testing promptNumberReadLine, continuing to next case");
					e.printStackTrace(System.out);
					exCount++;
				}
			}
			int[] pieces = { 1, 2, 2, 5, 10 };
			int[] middles = { 1, 1, 2, 4, 9 };
			String[] strs = { "C.G", "CC.GG", "CC..GG", "CCCCC....GGGGG", "CCCCCCCCCC.........GGGGGGGGGG" };
			for (int i = 0; i < pieces.length; i++) {
				try {
					System.out.print("Testing createBoard(" + pieces[i] + ", " + middles[i] + "): ");
					int[] board = CricketsAndGrasshoppers.createBoard(pieces[i], middles[i]);
					int[] bcopy = board.clone();
					String str = CricketsAndGrasshoppers.boardToString(board);
					if (!Arrays.equals(bcopy, board)) {
						System.out.println("boardToString is changing your board!");
					} else {
						if (strs[i].equals(str.trim())) {
							System.out.println("OK.");
							cbScore++;
							btsScore++;
						} else {
							System.out.println("Uh oh.");
							System.out.println("When calling createBoard(" + pieces[i] + ", " + middles[i]
									+ "), your returned string from boardToString() should look like this:");
							System.out.println(strs[i]);
							System.out.println("Instead, it looked like this:");
							System.out.println(str);
						}
					}
				} catch (Exception e) {
					System.out.println(
							"The following error in your code occurred while testing createBoard and boardToString, continuing to next case");
					e.printStackTrace(System.out);
					exCount++;
				}
			}
			int[] mpieces = { 1, 2, 2, 2, 3 };
			int[] mmiddles = { 1, 1, 2, 2, 4 };
			int[] strats = { 0, 0, 0, 1, 3 };
			int[][] allowedMoves = { { 2, 8, 4 }, { 4, 16, 8, 32, 16 }, { 4, 32, 10, 64 },
					{ 4, 32, 10, 16, 2, 72, 4, 64, 32, 16, 8 },
					{ 8, 256, 20, 640, 36, 1152, 4, 128, 74, 288, 138, 544, 266, 32, 522, 80, 2, 128 } };
			String[][] amBoards = { { ".CG", "GC.", "G.C" }, { "C.CGG", "CGC.G", "CG.CG", "CGGC.", "CGG.C" },
					{ "C.C.GG", "C.CG.G", ".CCG.G", ".CCGG." },
					{ "C.C.GG", "C.CG.G", "C..GCG", "C.G.CG", ".CG.CG", "GC..CG", "G.C.CG", "G.CGC.", "G.CG.C",
							"GGC..C", "GG.C.C" },
					{ "CC.C...GGG", "CC.C..G.GG", "CC..C.G.GG", "CC..C.GG.G", "CC...CGG.G", "CC...CGGG.", "C.C..CGGG.",
							"C.C.GC.GG.", "C.C.G.CGG.", "C.C.GGC.G.", "C.C.GG.CG.", "C.C.GGGC..", "C.C.GGG.C.",
							"C.CG.GG.C.", "C.CG.GG..C", "C.CGG.G..C", ".CCGG.G..C", ".CCGGG...C" } };
			String[][] mainlines = {
					{ "Please enter the number of pieces for each player (1-10): ",
							"Please enter the number of spaces in the middle (1-9): ", "C.G" + System.lineSeparator(),
							"Crickets, please enter your move (1-3): ", ".CG" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-3): ", "GC." + System.lineSeparator(),
							"Crickets, please enter your move (1-3): ", "Crickets win!" + System.lineSeparator() },
					{ "Please enter the number of pieces for each player (1-10): ",
							"Please enter the number of spaces in the middle (1-9): ", "CC.GG" + System.lineSeparator(),
							"Crickets, please enter your move (1-5): ", "C.CGG" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-5): ", "CGC.G" + System.lineSeparator(),
							"Crickets, please enter your move (1-5): ", "CG.CG" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-5): ", "CGGC." + System.lineSeparator(),
							"Crickets, please enter your move (1-5): ", "Crickets win!" + System.lineSeparator(), },
					{ "Please enter the number of pieces for each player (1-10): ",
							"Please enter the number of spaces in the middle (1-9): ",
							"CC..GG" + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							"C.C.GG" + System.lineSeparator(), "Grasshoppers, please enter your move (1-6): ",
							"C.CG.G" + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							".CCG.G" + System.lineSeparator(), "Grasshoppers, please enter your move (1-6): ",
							"Grasshoppers win!" + System.lineSeparator() },
					{ "Please enter the number of pieces for each player (1-10): ",
							"Please enter the number of spaces in the middle (1-9): ",
							"CC..GG" + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							"C.C.GG" + System.lineSeparator(), "Grasshoppers, please enter your move (1-6): ",
							"C.CG.G" + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							"C..GCG" + System.lineSeparator(), "Grasshoppers, please enter your move (1-6): ",
							"C.G.CG" + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							".CG.CG" + System.lineSeparator(), "Grasshoppers, please enter your move (1-6): ",
							"GC..CG" + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							"G.C.CG" + System.lineSeparator(), "Grasshoppers, please enter your move (1-6): ",
							"G.CGC." + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							"G.CG.C" + System.lineSeparator(), "Grasshoppers, please enter your move (1-6): ",
							"GGC..C" + System.lineSeparator(), "Crickets, please enter your move (1-6): ",
							"Crickets win!" + System.lineSeparator() },
					{ "Please enter the number of pieces for each player (1-10): "
							+ "Please enter the number of spaces in the middle (1-9): " + "CCC....GGG"
							+ System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "CC.C...GGG" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "CC.C..G.GG" + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "CC..C.G.GG" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "CC..C.GG.G" + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "CC...CGG.G" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "CC...CGGG." + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "C.C..CGGG." + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "C.C.GC.GG." + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "C.C.G.CGG." + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "C.C.GGC.G." + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "C.C.GG.CG." + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "C.C.GGGC.." + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "C.C.GGG.C." + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "C.CG.GG.C." + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + "C.CG.GG..C" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "C.CGG.G..C" + System.lineSeparator(),
							"Crickets, please enter your move (1-10): " + ".CCGG.G..C" + System.lineSeparator(),
							"Grasshoppers, please enter your move (1-10): " + "Grasshoppers win!"
									+ System.lineSeparator() } };

			for (int i = 0; i < allowedMoves.length; i++) {
				try {
					boolean ok = true;
					System.out.print("Testing moves on createBoard(" + mpieces[i] + ", " + mmiddles[i] + "): ");
					int[] board = CricketsAndGrasshoppers.createBoard(mpieces[i], mmiddles[i]);
					int goodFMoveCount = 0;
					int goodTMoveCount = 0;
					int canMoveCount = 0;
					int goodBoardCount = 0;
					for (int j = 0; j < allowedMoves[i].length; j++) {
						int[] bcopy = board.clone();
						for (int k = 0; k <= board.length + 2; k++) {
							if (((1 << k) & allowedMoves[i][j]) != 0)
								continue;
							try {
								if (k == 0) {
									if (!CricketsAndGrasshoppers.canMove(board, 1 + (j & 1))) {
										System.out.println("canMove(" + Arrays.toString(bcopy) + ", " + (1 + (j & 1))
												+ ") reports that a move can't be made, but you can!");
										System.out.println("move(" + Arrays.toString(bcopy) + ", " + (1 + (j & 1))
												+ ", " + bb(allowedMoves[i][j], strats[i], 1 + (j & 1))
												+ ") is possible.");
										ok = false;
										break;
									} else if (!Arrays.equals(board, bcopy)) {
										System.out.println("canMove(" + Arrays.toString(bcopy) + ", " + (1 + (j & 1))
												+ ") modified the board!");
										System.out.println(
												"It should only check if a move can be made, not change anything.");
										ok = false;
										break;
									} else {
										canMoveCount++;
									}
								}
								if (k == board.length + 2) {
									if (!CricketsAndGrasshoppers.move(board, 1 + (j & 1),
											bb(allowedMoves[i][j], strats[i], 1 + (j & 1)))) {
										System.out.println("move(" + Arrays.toString(bcopy) + ", " + (1 + (j & 1))
												+ ", " + bb(allowedMoves[i][j], strats[i], 1 + (j & 1))
												+ ") returned false but move should be allowed!");
										ok = false;
									} else if (Arrays.equals(board, bcopy)) {
										System.out.println("move(" + Arrays.toString(bcopy) + ", " + (1 + (j & 1))
												+ ", " + bb(allowedMoves[i][j], strats[i], 1 + (j & 1))
												+ ") returned true but didn't change the board!");
										ok = false;
									} else if (!amBoards[i][j]
											.equals(CricketsAndGrasshoppers.boardToString(board).trim())) {
										System.out.println("move(" + Arrays.toString(bcopy) + ", " + (1 + (j & 1))
												+ ", " + bb(allowedMoves[i][j], strats[i], 1 + (j & 1))
												+ ") returned true but changed the board incorrectly!");
										System.out.println("Should look like: " + amBoards[i][j]);
										System.out.println("Yours looks like: "
												+ CricketsAndGrasshoppers.boardToString(board).trim());
										ok = false;
									} else {
										goodTMoveCount++;
										goodBoardCount++;
									}
								} else {
									if (CricketsAndGrasshoppers.move(board, 1 + (j & 1), k)) {
										System.out.println("move(" + Arrays.toString(bcopy) + ", " + (1 + (j & 1))
												+ ", " + k + ") returned true but move should not be allowed!");
										if (Arrays.equals(board, bcopy)) {
											System.out.println("The board didn't change, however.");
										} else {
											System.out.println(
													"After the move, the array looks like: " + Arrays.toString(board));
											board = bcopy.clone();
										}
										ok = false;
										continue;
									}
									if (!Arrays.equals(board, bcopy)) {
										System.out.println("move(" + Arrays.toString(bcopy) + ", 1, " + i
												+ ") returned false but board changed!");
										System.out.println(
												"After the move, the array looks like: " + Arrays.toString(board));
										board = bcopy.clone();
										ok = false;
										continue;
									}
								}
							} catch (Exception e) {
								System.out.println("Error in your code!");
								System.out.println("When calling canMove or move(" + Arrays.toString(bcopy) + ", "
										+ (1 + (j & 1)) + ", "
										+ (k < board.length + 2 ? k : bb(allowedMoves[i][j], strats[i], 1 + (j & 1)))
										+ ") the following exception occurred in your code:");
								e.printStackTrace(System.out);
								exCount++;
								if (!Arrays.equals(board, bcopy)) {
									System.out.println("Board changed, moving to next example...");
									break;
								} else {
									System.out.println("Continuing with tests for this board...");
								}
								continue;
							}
							goodFMoveCount++;
						}
					}
					int[] bcopy = board.clone();
					if (goodFMoveCount == 3 * (board.length + 2)) {
						moveScore += 2;
					} else if (goodFMoveCount >= board.length) {
						moveScore++;
					}

					if (goodTMoveCount == allowedMoves[i].length) {
						moveScore += 4;
					} else if (goodTMoveCount >= allowedMoves[i].length - 1) {
						moveScore += 3;
					} else if (goodTMoveCount >= allowedMoves[i].length / 2) {
						moveScore += 2;
					} else if (goodTMoveCount > 0) {
						moveScore++;
					}

					if (canMoveCount == allowedMoves[i].length) {
						cmScore++;

					}
					if (goodBoardCount == allowedMoves[i].length) {
						btsScore += 2;
					} else if (goodBoardCount > 0) {
						btsScore++;
					}
					if (CricketsAndGrasshoppers.canMove(board, 1 + (allowedMoves[i].length & 1))) {
						System.out
								.println("canMove(" + Arrays.toString(bcopy) + ", " + (1 + (allowedMoves[i].length & 1))
										+ ") reports that a move can be made, but you can't!");
						ok = false;
					} else if (!Arrays.equals(bcopy, board)) {
						System.out.println("canMove(" + Arrays.toString(bcopy) + ", "
								+ (1 + (allowedMoves[i].length & 1)) + ") changed the board!");
						System.out.println("It should only check if a move can be made, not change anything.");
						ok = false;
					} else {
						cmScore++;
					}
					if (ok)
						System.out.println("OK.");

				} catch (Exception e) {
					System.out.println("Error in your code, continuing to next example");
					e.printStackTrace(System.out);
					exCount++;
				}
			}
			try {
				for (int i = 0; i < allowedMoves.length; i++) {
					System.out.println("Testing main method on a run with " + mpieces[i] + " pieces per player and "
							+ mmiddles[i] + " in the middle: ");
					String s = mpieces[i] + System.lineSeparator() + mmiddles[i] + System.lineSeparator();
					for (int j = 0; j < allowedMoves[i].length; j++) {
						s += bb(allowedMoves[i][j], strats[i], 1 + (j & 1)) + System.lineSeparator();
					}
					InputStream oldIn = System.in;
					PrintStream oldOut = System.out;
					ByteArrayInputStream bis = new ByteArrayInputStream(s.getBytes());
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					System.setIn(bis);
					System.setOut(new PrintStream(bos));
					try {
						CricketsAndGrasshoppers.main(new String[] {});
					} catch (Exception e) {
						System.setIn(oldIn);
						System.setOut(oldOut);
						System.out.println("Exception occurred! Your program's output:");
						System.out.println(bos);
						if (bis.available() == 0 && e instanceof NoSuchElementException) {
							System.out.println("Your program is asking for more input when the game should be over!");
						}
						System.out.println("See the stack trace below for more information:");
						e.printStackTrace(System.out);
						exCount++;
					} finally {
						System.setIn(oldIn);
						System.setOut(oldOut);
					}
					Scanner outReader = null;
					try {
						if (bos.toString().replaceAll("\\s*", "")
								.equalsIgnoreCase(String.join("", mainlines[i]).replaceAll("\\s*", ""))) {
							if (bos.toString().equals(String.join("", mainlines[i]))) {
								System.out.println("Output identical.");
								idCount++;
							} else {
								System.out.println("Output equivalent.");
							}
							for (int j = 0; j < mainlines[i].length; j++) {
								if (j == 0) {
									mainScore++;
								}
								if (j == mainlines[i].length / 2) {
									mainScore++;
								}
								if (j == mainlines[i].length - 1) {
									mainScore += 3;
								}
							}
						} else {

							outReader = new Scanner(bos.toString());
							boolean finished = false;
							lineloop: for (int j = 0; j < mainlines[i].length; j++) {
								if (!outReader.hasNextLine()) {
									System.out.println("Should have " + mainlines[i].length
											+ " lines of output but only has " + j);
									break;
								}
								// String myout = outReader.nextLine();
								Scanner currentLine = new Scanner(mainlines[i][j]);
								String myLine = null;
								String cLine = null;
								while (currentLine.hasNext()) {
									String myout = outReader.next();
									if (myLine == null)
										myLine = myout;
									else
										myLine += " " + myout;
									String currentWord = currentLine.next();
									if (cLine == null)
										cLine = currentWord;
									else
										cLine += " " + currentWord;
									if (!currentWord.equals(myout)) {
										System.out.println(
												"Line " + (j + 1) + ": expected a line beginning \"" + cLine + "\"");
										System.out.println(
												"but your code outputs a line beginning this way: \"" + myLine + "\"");
										break lineloop;
									}
								}
								if (j == 0) {
									mainScore++;
								}
								if (j == mainlines[i].length / 2) {
									mainScore++;
								}
								if (j == mainlines[i].length - 1) {
									mainScore += 3;
									finished = true;
								}
							}
							if (finished && outReader.hasNext()) {
								System.out.println("Unexpected extra output from your code:");
								while (outReader.hasNextLine()) {
									System.out.println(outReader.nextLine());
								}
								mainScore--;
							}
						}
					} finally {
						if (outReader != null)
							outReader.close();
					}
				}

			} catch (Exception e) {
				System.out.println("The following error in your code occurred while testing main:");
				e.printStackTrace(System.out);
				exCount++;
			}
		} finally {
			System.out.println("promptNumberReadLine method: " + pnrlScore + " / 20");
			System.out.println("createBoard method: " + cbScore + " / 5");
			System.out.println("boardToString method: " + btsScore + " / 15");
			System.out.println("move method: " + moveScore + " / 26");
			System.out.println("canMove method: " + cmScore + " / 10");
			System.out.println("main method: " + mainScore + " / 25");
			int total = (pnrlScore + cbScore + btsScore + moveScore + cmScore + mainScore);
			if (exCount > 0) {
				total--;
				System.out.println("1 point penalty for the " + exCount + " exception" + (exCount > 1 ? "s" : "")
						+ " mentioned above.");
			}
			if (total == 101 && idCount == 10 && exCount == 0)
				total++;
			if (total <= 100) {
				System.out.println("If you earn all the above points, you'll earn an extra credit point (101 / 100)!");
			} else if (total == 101) {
				System.out.println(
						"To get one more extra credit point, make sure your output is identical to what is expected.");
				System.out.println(
						"Understand the use of print versus println, and include correct spacing and capitalization.");
			} else if (total == 102) {
				System.out.println("You earned all the extra credit! Awesome!");
			}
			System.out.println("Total: " + total + " / 100");
		}

	}

}
