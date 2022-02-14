
// Team #2 Project 
// Phuoc Le
// Sydney Graham
// Tim Morris
// Veasna Bun

public class AutomatedTellerMachine {
	public String firstName; // last name and first name will be store need to be store to id account number.
	public String lastName;
	public double totalBalance; // will be store the total dollar in account start at zero.
	public int pinNumber; // account number must match with their user name.

	public AutomatedTellerMachine(String firstName, String lastName, int pinNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pinNumber = pinNumber;
	}
	public double getBalance(double newBalance) {
		return totalBalance;
	}
	public double setBalance(double newBalance) {
		totalBalance = newBalance;
		return totalBalance;
	}
	public double setPinNumber(int newpinNumber) {
		pinNumber = newpinNumber;
		return pinNumber;
	}
	public double getPinNumber() {
		return pinNumber;
	}
	public double accountDeposit(double totalDeposit) {
		totalBalance = totalBalance + totalDeposit;
		return totalBalance;
	}
	public double accountWithdraw(double totalWithdraw) {
		totalBalance = totalBalance - totalWithdraw;
		return totalBalance;
	}
	public void accountDetail() {
		System.out.print(firstName + " " + lastName + " your account " + " has a total balance of $");
		System.out.printf("%.2f", totalBalance);
		System.out.println(".");
	}
	public void bonusProgram(int guess) {
		if (guess == Math.ceil((Math.random() * 20))) {
			System.out.println("You won the bonus with 50 $!");
			totalBalance += 50; 
		} else {
			System.out.println("Unlucky, you did not guess the correct number.");
			
		}
	}
}