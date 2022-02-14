
// Team #2 Project: AutomatedTellerMachine 
// Phuoc Le
// Sydney Graham
// Tim Morris
// Veasna Bun

import java.util.Scanner;

public class AtmData {

	public static void main(String[] args) {
		// creating object to store and storing user data each should be unique
		// object are customer information for the bankOf" " in array of objects.
		// each object will be able to access their classes after the correct name and
		// accountNummber is enter.
		// AutomatedTellerMachine NAMEOFOBJECT = new AutomatedTellerMachine("
		// firstName", "lastName", pinNumber)

		AutomatedTellerMachine bankOfA = new AutomatedTellerMachine("Veasna", "Bun", 1234);
		AutomatedTellerMachine bankOfB = new AutomatedTellerMachine("Tim", "Morris", 5678);
		AutomatedTellerMachine bankOfC = new AutomatedTellerMachine("Phuoc", "Le", 910);
		AutomatedTellerMachine bankOfD = new AutomatedTellerMachine("Sydney", "Graham", 1112);

		// user will similar name will have different information and can only be access
		// using the correct pin number
		AutomatedTellerMachine bankOfE = new AutomatedTellerMachine("Veasna", "B", 1234);
		AutomatedTellerMachine bankOfF = new AutomatedTellerMachine("Sydney", "Graham", 131415);
		AutomatedTellerMachine bankOfG = new AutomatedTellerMachine("P", "Le", 161718);
		AutomatedTellerMachine bankOfH = new AutomatedTellerMachine("Morris", "Tim", 1920212223);
		// adding more user
		AutomatedTellerMachine bankOfI = new AutomatedTellerMachine("Jonny", "Tsunami", 1999);
		AutomatedTellerMachine bankOfJ = new AutomatedTellerMachine("Travis", "Scott", 2991);
		AutomatedTellerMachine bankOfK = new AutomatedTellerMachine("Ash", "Ketchum", 1997);
		AutomatedTellerMachine[] A = new AutomatedTellerMachine[11];
		A[0] = bankOfA;
		A[1] = bankOfB;
		A[2] = bankOfC;
		A[3] = bankOfD;
		A[4] = bankOfE;
		A[5] = bankOfF;
		A[6] = bankOfG;
		A[7] = bankOfH;
		A[8] = bankOfI;
		A[9] = bankOfJ;
		A[10] = bankOfK;
		// You can set the default balance of each user by calling the object and set
		// balance.
		bankOfA.setBalance(2478.23);
		// bankOfB.setBalance(); will have a default balance of zero dollars and zero
		// cent.
		bankOfC.setBalance(118.95);
		bankOfD.setBalance(1235);
		bankOfE.setBalance(2.00);
		bankOfF.setBalance(50);
		bankOfG.setBalance(100.00051);
		bankOfH.setBalance(0.01);
		bankOfI.setBalance(1065.00);
		bankOfJ.setBalance(45000000);
		bankOfK.setBalance(999.99);

		Scanner s = new Scanner(System.in);
		AutomatedTellerMachine user = new AutomatedTellerMachine("Dummy", "Dummy", 0);
		while (user.firstName.equals("Dummy")) {
			System.out.println("Enter your first name and last name: ");
			String inputName = s.nextLine();
			inputName = inputName.toUpperCase();

			// System.out.println(inputName);
			System.out.println("Please enter your pin number? ");
			int inputPinNumber = -1;
			if (s.hasNextInt()) {
				inputPinNumber = s.nextInt();
			}
			for (int i = 0; i < A.length; i++) {
				String userName = A[i].firstName + " " + A[i].lastName;
				int userNumber = A[i].pinNumber;
				userName = userName.toUpperCase();
				if (inputName.equals(userName) && inputPinNumber == userNumber) {
					user = A[i];
				}
			}

			if (user.firstName.equals("Dummy")) {
				System.out.println("Not a real user, please try again!");
				s.nextLine();
			} else {
				// System.out.println(user.firstName);
			}
		}

		if (!user.firstName.equals("Dummy")) {
			System.out.println("Hello " + user.firstName + " " + user.lastName + ". ");
			boolean programRunning = true;
			while (programRunning) {
				System.out.println("Please select the follow option: (1-5) ");
				System.out.println("Enter 1 to Check your account Balance. ");
				System.out.println("Enter 2 to Deposit funds into your account. ");
				System.out.println("Enter 3 to Withdraw funds from your account. ");
				System.out.println("Enter 4 to Change your account pin number. ");
				System.out.println("Enter 5 to Exit account. ");
				if (s.hasNextDouble()) {
					double userInput = s.nextDouble();
					if (userInput == 1) {
						user.accountDetail();
						System.out.println(" ");
					}
					if (userInput == 2) {
						while (true) {
							System.out.println("Please enter the total amount in dollars to DEPOSIT your account?");
							if (s.hasNextDouble()) {
								double totalDeposit = s.nextDouble();
								user.accountDeposit(totalDeposit);
								user.accountDetail();
								break;
							}
							s.nextLine();
						}

					}
					if (userInput == 3) {
						while (true) {
							System.out.println("Please enter the total amount to WITHDRAW from your account?");
							if (s.hasNextDouble()) {
								double totalWithdraw = s.nextDouble();
								if (totalWithdraw <= user.totalBalance) {
									user.accountWithdraw(totalWithdraw);
									user.accountDetail();
									if (totalWithdraw > 50) {
										System.out.println("Would you like to take a chance to get a small bonus");
										System.out.println("Enter 1 to say yes");
										System.out.println("Enter any numbers to say no");
										if (s.hasNextInt()) {
											userInput = s.nextInt();
											s.nextLine();
											if (userInput == 1) {
												while (true) {
													System.out.println("Please enter the a number from 1-20");
													if (s.hasNextInt()) {
														user.bonusProgram(s.nextInt());
														break;
													} 
													s.nextLine();
												}

											} else {
												break;
											}
										} else {
											s.nextLine();
										}
									}
									break;
								} 
								if (totalWithdraw > user.totalBalance) {
									System.out.println("Unsufficient funds, please try again.");
									user.accountDetail();
								} 
							}else {
								s.nextLine();
							}
							s.nextLine();
						}
					}
					if (userInput == 4) {
						int pinchanged_int = 0;
						System.out.println("Please enter the your current account pin number.");
						while (true) {
							if (pinchanged_int == 1) {
								break;
							} else if (s.hasNextDouble()) {
								double oldPinNumber = s.nextDouble();
								if (oldPinNumber == user.getPinNumber()) {
									while (true) {
										System.out.println("Please enter the your new pin number.");
										int newPinNumber = (s.nextInt());
										if (oldPinNumber == newPinNumber) {
											System.out.println("New PIN must be different from Old PIN");
										} else {
											System.out.println(
													"Press Y to confirm this new PIN is correct: " + newPinNumber);
											String pinchangeconfirm = s.next();
											pinchangeconfirm = pinchangeconfirm.toUpperCase();
											if (pinchangeconfirm.equals("Y")) {
												user.setPinNumber(newPinNumber);
												System.out.println("PIN Changed");
												pinchanged_int = 1;
												break;
											}
										}
									}
								} else {
									System.out.println("You have enter the incorrect pin please try again.");
								}
							}
							break;
						}
					}
					if (userInput == 5) {
						programRunning = false;
					}
				}
				s.nextLine();
			}

		}
		System.out.println("Thank you for visiting our ATM!");
	}
}
