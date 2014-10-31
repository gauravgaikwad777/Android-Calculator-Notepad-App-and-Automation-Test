package com.robotium.test;

import com.robotium.solo.Solo;
import com.example.android.notepad.NotesList;
import android.test.ActivityInstrumentationTestCase2;

public class NotePadTest extends ActivityInstrumentationTestCase2<NotesList>{

	private Solo solo;

	public NotePadTest() {
		super(NotesList.class);
	}

	@Override
	public void setUp() throws Exception {
		//setUp() is run before a test case is started. 
		//This is where the solo object is created.
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		//tearDown() is run after a test case has finished. 
		//finishOpenedActivities() will finish all the activities that have been opened during the test execution.
		solo.finishOpenedActivities();
	}

	public void testAddNote() throws Exception {
		
		solo.clickOnMenuItem("Add note");
		//Assert that NoteEditor activity is opened
		solo.assertCurrentActivity("Expected NoteEditor activity", "NoteEditor"); 
		//In text field 0, enter Note 1
		solo.enterText(0, "Note 1");
		solo.goBack(); 
		solo.sendKey(Solo.MENU);
		//Clicks on menu item
		solo.clickOnMenuItem("Add note");
		//In text field 0, type Note 2
		solo.typeText(0, "Note 2");
		//Go back to first activity
		solo.goBack(); 
		//Takes a screenshot and saves it in "/sdcard/Robotium-Screenshots/".
		solo.takeScreenshot();
		boolean notesFound = solo.searchText("Note 1") && solo.searchText("Note 2");
		//Assert that Note 1 & Note 2 are found
		assertTrue("Note 1 and/or Note 2 are not found", notesFound); 

	}
	
	public void testEditNote() throws Exception {
		// Change title
		solo.clickLongInList(2);
		solo.clickOnMenuItem("Edit title");
		//In first text field (0), add test
		solo.enterText(0, " test");  
		solo.goBack();
		//Regular expression check can also be performed
		//(Regexp) case insensitive
		//boolean noteFound = solo.waitForText("(?i)note 1 test"); 
		//Assert that Note 1 test is found
		//assertTrue("Note 1 test is not found", noteFound); 

	}
	
	public void testRemoveNote() throws Exception {
		solo.clickLongOnText("Note 2");
		//Delete Note 1 test
		solo.clickOnMenuItem("Delete");
		//Note 1 test should not be found
		boolean noteFound = solo.searchText("Note 2");
		//Assert that Note 1 test is not found
		assertFalse("Note 2 Test is found", noteFound);
		solo.clickLongOnText("Note 1 test");
		//Clicks on Delete in the context menu
		solo.clickOnText("Delete");  
		//Will wait 100 milliseconds for the text: "Note 2"
		noteFound = solo.waitForText("Note 1 test", 1, 100);
		//Assert that Note 2 is not found
		assertFalse("Note 2 is found", noteFound);  
	}
}
