package genosPizza;



import javax.swing.*;

import windows_and_menues.*;


public class GenosPizza {

	private static MainMenu mainMenu;         //instance of the main frame
	
	public static void main(String[] args) {
		
		new HotelInfoEntryWindow();
		//mainMenu = new MainMenu("dimmed_gear_icon.png");
	
		//new DialMenu("Enter the amount to subtract");
		//new HotelOrCabinMenu();
	}
	
	/**
	 * this method returns the instance of the main frame
	 * @return mainMenu
	 * */
	public static JFrame getMainFrame(){
		return mainMenu;
	}

}
