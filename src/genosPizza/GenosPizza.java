package genosPizza;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import windows_and_menues.MainMenu;
import windows_and_menues.MainMenuController;

public class GenosPizza {

	private static MainMenu mainMenu; // instance of the main frame

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// new InfoEntryWindow("Hotel Info Entry Form", "Hotel phone #",
				// "Hotel name", "Hotel address", "Room #");
				// new InfoEntryWindow("Cabin Info Entry Form", "Cabin phone #",
				// "Cabin name", "Cabin address", "Vehicle description");
				mainMenu = new MainMenu();
				new MainMenuController(mainMenu);
				// new PhoneNumberDialMenu("Enter the phone number:");
				// new DialMenu("Enter the amount to subtract");
				// new HotelOrCabinMenu();
				// new OrderForm();

				
				  
				 
				// new EditItemForm();
				// new AddNewItemForm();
				
				 
				 
				// new AddModifiersListToItemForm();

			}
		});

	}

	/**
	 * this method returns the instance of the main frame
	 * 
	 * @return mainMenu
	 * */
	public static JFrame getMainFrame() {
		return mainMenu;
	}

}
