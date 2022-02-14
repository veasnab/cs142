//Bun, Veasna
public class CompaniesCrossing {
	public static boolean isMoveOkay(int beforeBoat, int beforeA, int beforeB, int beforeC, int beforeD, int beforeE,
			int beforeF, int afterBoat, int afterA, int afterB, int afterC, int afterD, int afterE, int afterF) {
		// Please fill in this method according to the directions given below. Note that
		// at most one error message should be printed, and the order of the
		// checks determines which error message to print.

		// First, make sure that all parameter variables are 1 or 2. If not, print the
		// following error message and return false:
		// All positions must be 1 or 2!
		if (beforeBoat != 1) {
			if (beforeBoat != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (beforeA != 1) {
			if (beforeA != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (beforeB != 1) {
			if (beforeB != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (beforeC != 1) {
			if (beforeC != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (beforeD != 1) {
			if (beforeD != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (beforeE != 1) {
			if (beforeE != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (beforeF != 1) {
			if (beforeF != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		
		if (afterBoat != 1) {
			if (afterBoat != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (afterA != 1) {
			if (afterA != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}

		}
		if (afterB != 1) {
			if (afterB != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (afterC != 1) {
			if (afterC != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (afterD != 1) {
			if (afterD != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			} 
		}
		if (afterE != 1) {
			if (afterE != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		if (afterF != 1) {
			if (afterF != 2) {
				System.out.println("All positions must be 1 or 2!");
				return false; 
			}
		}
		
		// Second, make sure that the boat is changing position. If not, print the
		// following error message and return false:
		// The boat must move!
		if (beforeBoat == afterBoat) {
			System.out.println ("The boat must move!");
			return false;
		}
		
		// Third, make sure that each person you're trying to move is on the same side
		// as the boat. If not, print the following error message and return false:
		// You may not move a person who is not with the boat! 
		int moveCount = 0; 
		if (beforeA != afterA) { 
			if (beforeBoat != beforeA) { 
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
			else {
				moveCount = moveCount + 1;
			}
			
		}
		if (beforeB != afterB) { 
			if (beforeBoat != beforeB) { 
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
			else {
				moveCount = moveCount + 1;
			}
		}
		if (beforeC != afterC) { 
			if (beforeBoat != beforeC) { 
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
			else {
				moveCount = moveCount + 1;
			}
			
		}
		if (beforeD != afterD) { 
			if (beforeBoat != beforeD) { 
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
			else {
				moveCount = moveCount + 1;
			} 
		}
		if (beforeE != afterE) { 
			if (beforeBoat != beforeE) { 
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
			else {
				moveCount = moveCount + 1;
			}
		}
		if (beforeF != afterF) { 
			if (beforeBoat != beforeF) { 
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
			else {
				moveCount = moveCount + 1;
			}
		}			
			
		// Fourth, make sure you're moving one or two people. If not, print the
		// following error message and return false:
		// You must move one or two people!
		if (moveCount != 1) {
			if (moveCount != 2) {
				System.out.println("You must move one or two people!");
				return false; 
			}
		}
		
		// Fifth, make sure that no manager would recruit anyone in the after positions.
		// There will be three separate checks, one for the managers from companies 1,
		// 2, and 3, in that order. For each check, if a recruitment would happen, print
		// the following error message, replacing X with 1, 2, or 3 as appropriate to
		// the manager's company and return false:
		// Company X manager would try to recruit someone!
		if ((afterE == afterA) && (afterD != afterA)) {
			System.out.println("Company 1 manager would try to recruit someone!");
			return false;
		}
		if ((afterE == afterB) && (afterC != afterB)) {
			System.out.println("Company 1 manager would try to recruit someone!");
			return false;
		}
		if ((afterD == afterF) && (afterE != afterF)) {
			System.out.println("Company 2 manager would try to recruit someone!");
			return false;
		}
		if ((afterD == afterB) && (afterC != afterB)) {
			System.out.println("Company 2 manager would try to recruit someone!");
			return false;
		}
		if ((afterC == afterF) && (afterE != afterF)) {
			System.out.println("Company 3 manager would try to recruit someone!");
			return false;
		}
		if ((afterC == afterA) && (afterD != afterB)) {
			System.out.println("Company 3 manager would try to recruit someone!");
			return false;
		}
		
		// Finally, if none of the errors above occurred, check to see if the after
		// positions solve the puzzle. If so, print:
		// You solved the puzzle!
		if ((afterA == 2) && (afterB == 2) && (afterC == 2) && (afterD == 2) && (afterE == 2) && (afterF == 2)) {
			System.out.println("You solved the puzzle!");
			return true;
		}
		
		// If no errors occurred, you should return true to indicate that the move is
		// okay
		// The method should return true sometimes and false sometimes, depending on the
		// parameter variables.
		return true; // The method should only return false sometimes!
	}
}
