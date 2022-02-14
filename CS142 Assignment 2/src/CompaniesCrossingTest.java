
/**
 * Test program for CS 142 Assignment 2 (Version of 1/19/2021 12:30 PM)
 * 
 * You may only use this program as a student of Martin Hock, CS 142 Winter 2021.
 * You may not give this program to any other people or companies (aside from
 * private storage). This program code is for personal educational use only and 
 * may not be redistributed without permission of the author.
 * 
 * Directions: Run this program from the same project source folder as your
 * CompniesCrossing.java file.
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class CompaniesCrossingTest {
	public static void main(String[] args) {
		// Please change the following variable to contain your Sortable Name as
		// found in your Canvas Account Settings. It should include a comma.
		String name = "Your name here";

		// Please don't change the stuff below.
		long[] filters = { -1342570513L, -294273097L, -173016337L, -671491803L, -243269665L, -151127621L, -1342449281L,
				-33287L, 1995177815L, -556417537L, -76288257L, 1073378803L, 2136723391L, -18154562L, -276928521L,
				-587484165L, 385613055L, 2146355190L, -1075068930L, -1610752905L, -1135097L, 2130165759L, 2079850270L,
				2143254431L, -143131930L, -538579379L, -1753743506L, -1208033383L, -84939781L, -404261891L, 2102251391L,
				-52692105L, -1220542659L, -62989317L, -579088394L, 1540108283L, -1523713L, -354615297L, -503333633L,
				-1342186497L };

		if (name.equals("Your name here") || !name.contains(",") || name.contains(":") || !name.contains(" ")) {
			System.out.println(
					"Please enter your Sortable Name inside the \"quotes\" next to the = in the name variable.");
			System.out.println("The name should match the Sortable Name shown in Canvas.");
			System.out.println("You can find it in Canvas Account -> Settings.");
			System.out.println("The link is found in the blue bar on the left side of Canvas.");
			System.out.println("The name will include a comma.");
			return;
		}
		SecureRandom sr;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			name = name.trim().replaceAll("[^A-Za-z]", "").toUpperCase();
			sr.setSeed(name.getBytes());
			for (int i = 0; i < filters.length; i++) {
				if ((filters[i] & (1 << sr.nextInt(64))) == 0) {
					System.out.println("I couldn't find you! Please double check that your name is correct.");
					System.out.println("The name should match the Sortable Name shown in Canvas.");
					System.out.println("You can find it in Canvas Account -> Settings.");
					System.out.println("The link is found in the blue bar on the left side of Canvas.");
					System.out.println("The name will include a comma.");
					System.out.println(
							"If your name has changed since the course began, please let your instructor know.");
					System.out.println("You may need to use the name you used at the start of the course.");
					return;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("I am unable to set up the random generator needed for your assignment.");
			System.out.println("Please make sure you are using the version of Java specified by your instructor.");
			return;
		}
		sr.nextInt(1 << 30);
		sr.nextInt(1 << 30);
		sr.nextInt(1 << 30);
		sr.nextInt(1 << 30);

		for (int i = 0; i < 5; i++) {
			int x = sr.nextInt(6 - i) + i;
			P = ((((P & (~(15 << (i * 4)))) | ((P & (15 << (x * 4))) >> (4 * (x - i)))) & (~(15 << (x * 4))))
					| ((P & (15 << (i * 4))) << 4 * (x - i)));
		}

		int score = 0;
		System.out.println("*** Please write down the following roles of each person");
		System.out.println("*** on a piece of paper so you can refer to them easily!");
		for (int x = 0; x < 24; x += 4) {
			for (int i = 0; i < 6; i++) {
				if ((P >> x & 0xF) != i)
					continue;
				System.out.println("Person " + (char) (0x41 + x / 4) + " is the " + (i < 3 ? "manager" : "engineer")
						+ " at company " + (i % 3 + 1) + ".");
				break;
			}
		}

		{
			int falseCount = 0, trueCount = 0;
			PrintStream oldOut = System.out;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(baos));
			for (int i = 0; i < 1 << 14; i++) {
				if (CompaniesCrossing.isMoveOkay((i & 1) + 1, ((i & 2) >> 1) + 1, ((i & 4) >> 2) + 1,
						((i & 8) >> 3) + 1, ((i & 16) >> 4) + 1, ((i & 32) >> 5) + 1, ((i & 64) >> 6) + 1,
						((i & 128) >> 7) + 1, ((i & 256) >> 8) + 1, ((i & 512) >> 9) + 1, ((i & 1024) >> 10) + 1,
						((i & 2048) >> 11) + 1, ((i & 4096) >> 12) + 1, ((i & 8192) >> 13) + 1))
					trueCount++;
				else
					falseCount++;
			}
			System.out.flush();
			System.setOut(oldOut);
			if (baos.size() == 0 && (falseCount == 0 || trueCount == 0)) {
				System.out.println(
						"Your isMoveOkay method appears to be empty (it never prints and always returns the same value).");
				System.out.println(
						"Please edit your CompaniesCrossing isOkay method to follow the assignment directions.");
				System.out.println("Since the method is empty, you have not scored points (your score is 0 / 100).");
				return;
			}
		}

		System.out.println("This program indicates the errors it sees.");
		System.out.println(
				"Please check announcements often to see if there is a new version. The version is at the top in a comment.");
		System.out.println(
				"This program only looks at the first line of output from your program. Make sure it is the appropriate error.");
		System.out.println(
				"Errors should be checked in the order they are described in the assignment (the manager recruitment in company number order)");
		System.out.println("Make sure the people follow the roles printed above!");
		System.out.println(
				"Make sure the parameter variables are in the right order (as provided with the starter code)!");

		try {
			System.out.println("Testing a solution and its opposite to make sure your program accepts it.");
			int[][] moves = { { 2, 1, 1, 2, 1, 1 }, { 1, 1, 1, 2, 1, 1 }, { 1, 1, 1, 2, 2, 2 }, { 1, 1, 1, 1, 2, 2 },
					{ 1, 2, 2, 1, 2, 2 }, { 1, 1, 2, 1, 1, 2 }, { 2, 2, 2, 1, 1, 2 }, { 2, 2, 2, 1, 1, 1 },
					{ 2, 2, 2, 2, 1, 2 }, { 2, 1, 2, 2, 1, 2 }, TWO };
			int[] start = { 1, 1, 1, 1, 1, 1, 1 };
			boolean solution = true;
			for (int i = 0; i < moves.length; i++) {
				if (i > 0) {
					System.arraycopy(moves[i - 1], 0, start, 0, moves[i - 1].length);
					start[6] = 3 - start[6];
					if (checkMove(n(start), n(moves[i]), null))
						score += 2;
					else
						solution = false;
				}
				if (checkMove(start, moves[i], null))
					score += 2;
				else
					solution = false;
			}
			if (solution)
				score += 10;
			boolean allPos = true;
			System.out.println("Testing some errors.");
			int[] c1 = { 1, 1, 1, 1, 1, 1, 1 }, n0 = { 1, 1, 1, 1, 1, 1 }, n1 = { 2, 2, 2, 2, 2, 2 };
			if (checkMove(c1, n0, "You must move one or two people!"))
				score += 2;
			for (int i = 0; i < n0.length; i++) {
				n0[i] = 3;
				if (!checkMove(c1, n0, "All positions must be 1 or 2!"))
					allPos = false;
				n0[i] = 0;
				if (!checkMove(c1, n0, "All positions must be 1 or 2!"))
					allPos = false;
				n0[i] = 1;
			}
			if (checkMove(c1, new int[] { 2, 2, 2, 2, 2, 2, 1 }, "The boat must move!"))
				score += 3;
			if (checkMove(c1, n1, "You must move one or two people!"))
				score += 2;
			if (checkMove(n(c1), n(n1), "You must move one or two people!"))
				score += 2;
			for (int i = 0; i < c1.length; i++) {
				c1[i] = 3;
				if (!checkMove(c1, n1, "All positions must be 1 or 2!"))
					allPos = false;
				c1[i] = 0;
				if (!checkMove(c1, n1, "All positions must be 1 or 2!"))
					allPos = false;
				c1[i] = 1;
			}
			if (allPos) {
				score += 5;
			}
			int[] c2 = { 1, 1, 1, 2, 2, 2, 1 }, n2 = { 2, 2, 2, 2, 2, 2 };
			if (checkMove(c2, n2, "You must move one or two people!"))
				score += 1;
			if (checkMove(n(c2), n(n2), "You must move one or two people!"))
				score += 1;
			int[] c2a = { 1, 2, 2, 1, 2, 2, 2 }, n2a = { 1, 1, 1, 1, 1, 1 };
			if (checkMove(c2a, n2a, "You must move one or two people!"))
				score += 2;
			if (checkMove(n(c2a), n(n2a), "You must move one or two people!"))
				score += 2;
			int[] c3 = { 1, 1, 1, 2, 2, 2, 1 }, n3 = { 2, 2, 2, 2, 2, 2 };
			if (checkMove(c3, n3, "You must move one or two people!"))
				score += 1;
			if (checkMove(n(c3), n(n3), "You must move one or two people!"))
				score += 1;

			int[] nw = { 1, 1, 1, 2, 2, 2, 2 };
			int[] nw1 = { 2, 1, 1, 2, 2, 2 };
			int[] nw2 = { 1, 2, 1, 2, 2, 2 };
			int[] nw3 = { 1, 1, 2, 2, 2, 2 };
			boolean notboat = true;
			if (!checkMove(nw, nw1, "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(n(nw), n(nw1), "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(nw, nw2, "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(n(nw), n(nw2), "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(nw, nw3, "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(n(nw), n(nw3), "You may not move a person who is not with the boat!"))
				notboat = false;

			if (notboat)
				score += 8;

			int[] ma = { 2, 1, 1, 1, 2, 1 };
			boolean mancruit = true;
			if (checkMove(c1, ma, "Company 1 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			if (checkMove(n(c1), n(ma), "Company 1 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			int[] ma2 = { 1, 2, 1, 2, 1, 1 };
			if (checkMove(c1, ma2, "Company 1 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			if (checkMove(n(c1), n(ma2), "Company 1 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			int[] pb = { 1, 2, 1, 1, 2, 1, 1 };
			int[] mb = { 1, 2, 1, 2, 2, 1 };
			if (checkMove(pb, mb, "Company 2 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			if (checkMove(n(pb), n(mb), "Company 2 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			int[] pc = { 1, 1, 1, 2, 2, 2, 1 };
			int[] mc = { 1, 1, 2, 2, 2, 2 };
			if (checkMove(pc, mc, "Company 3 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			if (checkMove(n(pc), n(mc), "Company 3 manager would try to recruit someone!"))
				score += 2;
			else
				mancruit = false;
			if (mancruit)
				score += 2;
		} finally {
			System.out.println("Calculated score: " + score + " / 100");
			System.out.println("Tentative score! Academic dishonesty would affect your score.");
			System.out
					.println("If any problems are found with the tester, it will be announced and you should retest.");
		}

	}

	private static int P = 5517840;

	public static int[] n(int[] a) {
		int[] b = Arrays.copyOf(a, a.length);
		for (int i = 0; i < b.length; i++)
			b[i] = 3 - b[i];
		return b;
	}

	public static void printError(int[] current, int[] next, String error, String received, boolean ret, boolean rret) {
		System.out.println();
		System.out.println("I saw a problem when I called isMoveOkay with these parameters,");
		System.out.println("passed in the following order and labeled according to the description:");
		System.out.println("beforeBoat = " + current[6]);
		System.out.println("beforeA = " + current[P & 0xF] + ", beforeB = " + current[P >> 4 & 0xF] + ", beforeC = "
				+ current[P >> 8 & 0xF]);
		System.out.println("beforeD = " + current[P >> 12 & 0xF] + ", beforeE = " + current[P >> 16 & 0xF]
				+ ", beforeF = " + current[P >> 20 & 0xF]);
		System.out.println("afterBoat = " + (next.length == 7 ? next[6] : 3 - current[6]));
		System.out.println(
				"afterA = " + next[P & 0xF] + ", afterB = " + next[P >> 4 & 0xF] + ", afterC = " + next[P >> 8 & 0xF]);
		System.out.println("afterD = " + next[P >> 12 & 0xF] + ", afterE = " + next[P >> 16 & 0xF] + ", afterF = "
				+ next[P >> 20 & 0xF]);
		if (error == null) {
			System.out.println("I expected no message and a return of " + ret + ".");
			if (received == null)
				System.out.println("I did see no message.");
			else
				System.out.println("I saw the following message: " + received);
		} else {
			System.out.println("I expected a message and a return of " + ret + ".");
			System.out.println("Expected message: " + error);
			System.out.println("I saw " + (received == null ? "no message" : ": " + received));
		}
		System.out.println("Return value: " + rret);
	}

	public static boolean checkMove(int[] current, int[] next, String error) {
		PrintStream oldOut = System.out;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(stream));
		boolean ret = CompaniesCrossing.isMoveOkay(current[6], current[P & 0xF], current[P >> 4 & 0xF],
				current[P >> 8 & 0xF], current[P >> 12 & 0xF], current[P >> 16 & 0xF], current[P >> 20 & 0xF],
				next.length == 7 ? next[6] : 3 - current[6], next[P & 0xF], next[P >> 4 & 0xF], next[P >> 8 & 0xF],
				next[P >> 12 & 0xF], next[P >> 16 & 0xF], next[P >> 20 & 0xF]);
		System.setOut(oldOut);
		Scanner scanner = new Scanner(new String(stream.toByteArray()));
		String firstLine = null;
		if (scanner.hasNextLine())
			firstLine = scanner.nextLine().trim();
		scanner.close();
		if (error != null) {
			if (error.equals(firstLine)) {
				if (ret) {
					printError(current, next, error, firstLine, false, ret);
					System.out.println("You returned true instead of false!");
					return false;
				} else {
					return true;
				}
			} else {
				printError(current, next, error, firstLine, false, ret);
				System.out.println("The error message differed from what was expected.");
				return false;
			}
		} else {
			if (next == TWO && !"You solved the puzzle!".equals(firstLine)) {
				printError(current, next, "You solved the puzzle!", firstLine, true, ret);
				return false;
			}
			if (!ret) {
				printError(current, next, (next == TWO ? "You solved the puzzle!" : error), firstLine, true, ret);
				System.out.println("You returned false instead of true!");
				return false;
			} else {
				if (next == TWO)
					error = "You solved the puzzle!";
				if (firstLine != error && (firstLine == null || !firstLine.equals(error))) {
					printError(current, next, error, firstLine, true, ret);
					return false;
				}
				return true;
			}
		}
	}

	private static int[] TWO = { 2, 2, 2, 2, 2, 2 };

}
