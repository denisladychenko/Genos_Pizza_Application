package genosPizza;



import javax.swing.*;


import windows_and_menues.*;


public class GenosPizza {

	private static MainMenu mainMenu;         //instance of the main frame
	
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	            	new InfoEntryWindow("Hotel Info Entry Form", "Hotel phone #", "Hotel name", "Hotel address", "Room #");
	        		//new InfoEntryWindow("Cabin Info Entry Form", "Cabin phone #", "Cabin name", "Cabin address", "Vehicle description");
	        		//mainMenu = new MainMenu("/images/dimmed_gear_icon.png");
	        		//new PhoneNumberDialMenu("Enter the phone number:");
	        		//new DialMenu("Enter the amount to subtract");
	        		//new HotelOrCabinMenu();
	        		//new OrderForm();
	            }
	        });
		
	}
	
	/**
	 * this method returns the instance of the main frame
	 * @return mainMenu
	 * */
	public static JFrame getMainFrame(){
		return mainMenu;
	}

}
