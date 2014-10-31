package edu.ilstu.itk275.mjain3.myatm.test;

import com.robotium.solo.Solo;

import edu.ilstu.itk275.mjain3.myatm.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Toast;

public class ATMTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public ATMTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		//setUp() is run before a test case is started. 
		//This is where the solo object is created.
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testOpenDepositeAccount() throws Exception {
		//Click on Open Button to open new Account
		solo.clickOnButton("Open");
		//Assert open account
		assertTrue(solo.searchText("open"));
		//Enter 2000 in amount 
		solo.enterText(3, "2000");
		//Deposit Amount
		solo.clickOnButton("Deposit");
		//Assert Deposit Toast
		assertTrue(solo.waitForText("Deposit Success!!"));		
	}
	
	public void testOpenNewAccount() throws Exception {
		//Click on Open Button to open new Account
		solo.clickOnButton("Open");
		solo.clickOnButton("Open");
		//Wait and Assert for New account Toast
		assertTrue(solo.waitForText("New account opened!!"));
		//Assert for new account
		assertTrue(solo.searchText("3"));
		//Enter 3000 amount and Deposit
		solo.enterText(3, "3000");
		solo.clickOnButton("Deposit");
		//Clear Amount Text field
		solo.clearEditText(3);
		solo.enterText(3, "3000");
		solo.clickOnButton("Deposit");
		//Assert Amount 
		assertTrue(solo.searchText("6000.0"));
		//Assert Deposit Toast
		assertTrue(solo.waitForText("Deposit Success!!"));
	}
}
