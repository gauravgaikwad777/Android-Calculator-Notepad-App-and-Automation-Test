package edu.ilstu.itk275.mjain3.myatm;
public class BankAccount {
	
	private String status;
	private double balance;
	private short accountNumber;
	private static short nextAccountNumber=1;
	
	public static BankAccount openBankAccount(){
		
		BankAccount retVal=new BankAccount();
		
		return retVal;
	}
	private BankAccount(){
		
		status="open";
		balance=0.0;
		accountNumber=nextAccountNumber++;
		
	}
	public String getStatus() {
		return status;
	}
	public double getBalance() {
		return balance;
	}
	public short getAccountNumber() {
		return accountNumber;
	}
	
	public void  deposit(double amt){
		balance+=amt;
	}
	
}
