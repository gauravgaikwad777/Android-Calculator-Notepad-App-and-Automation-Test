package edu.ilstu.itk275.mjain3.myatm;

import android.os.Bundle;
import android.R.bool;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final boolean False = false;
	private EditText accountNbr;
	private EditText accountBalance;
	private EditText accountStatus;
	private EditText transactionAmount;

	private BankAccount bankAccount;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        accountNbr = (EditText)findViewById(R.id.accNbrTF);
        accountBalance = (EditText)findViewById(R.id.acctBalanceTF);
        accountStatus = (EditText)findViewById(R.id.acctStatusTF);
        transactionAmount = (EditText)findViewById(R.id.transactionAmtTF);
        
        accountBalance.setClickable(false);
        accountBalance.setEnabled(false);
        
        accountStatus.setClickable(false);
        accountStatus.setEnabled(false);
        
        setOpenActionListener();
        setDepositActionListener();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    // Here we will outline the behavior of the Open Account  Button !! Yeah !!
    private void setOpenActionListener(){
    	Button openButton=(Button)findViewById(R.id.openAcctB);
    	openButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				bankAccount=BankAccount.openBankAccount();
				accountNbr.setText(Short.valueOf(bankAccount.getAccountNumber()).toString());
				accountBalance.setText(Double.valueOf(bankAccount.getBalance()).toString());
				accountStatus.setText(bankAccount.getStatus());
				
				 Toast myToast= Toast.makeText(MainActivity.this, "Hey My Open Button Works !!", Toast.LENGTH_LONG);
				myToast.show();
				
			}
		});
    	// get, close, withdraw, deposit, same thing,,, button listeners for each of these.. ado this on all these buttons..
    	
    }
    private void setDepositActionListener(){
    	Button depositButton=(Button)findViewById(R.id.depositB);
    	depositButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				double transAmount=Double.parseDouble(transactionAmount.getText().toString());
				bankAccount.deposit(transAmount);
				accountBalance.setText(Double.valueOf(bankAccount.getBalance()).toString());
				Toast deposit=Toast.makeText(MainActivity.this, "Deposit Success", Toast.LENGTH_LONG);
				deposit.show();
			}
		});
    	
    	
    }
}
