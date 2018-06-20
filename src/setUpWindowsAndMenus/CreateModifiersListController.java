package setUpWindowsAndMenus;

import general_classes.ModifiersListItem;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import windows_and_menues.ItemSelectionMenuPanelButton;

public class CreateModifiersListController {

	private CreateModifiersListForm view;                             //create modifiers list form 
	private CreateModifierController modFormsController;
	private ArrayList<ModifiersListItem> modItems;                 //list of all modifiers in the new list
	
	public CreateModifiersListController(CreateModifiersListForm view,
			CreateModifierController modFormsController) {
		
		this.view = view;                      
		this.modFormsController = modFormsController;                 //Sets Create Modifier controller
		modFormsController.setParentForm(view);                       //Sets parent form for this form
		modFormsController.setParentController(this);                 //Sets parent controller
		//adds event listeners
		this.view.addAddButtonClickListener(new AddButtonClickListener());
		this.view.addRemoveButtonClickListener(new RemoveButtonClickListener());
		this.view.addLocationButtonClickListener(new LocationButtonClickListener());
		this.view.addPage1ButtonClickListener(new Page1ButtonClickListener());
		this.view.addPage2ButtonClickListener(new Page2ButtonClickListener());
		this.view.addSaveButtonClickListener(new SaveButtonClickListener());
		this.view.addCreateModifiersButtonClickListener(new CreateModifiersButtonClick());
		//initialize modifier objects list
		modItems = new ArrayList<ModifiersListItem>();
	}
	
	public CreateModifiersListForm getView() {
		return view;
	}

	public void setView(CreateModifiersListForm view) {
		this.view = view;
	}

	/**
	 *Gets the array of modifiers objects
	 *@return modItems The array of modifiers objects 
	 */
	public ArrayList<ModifiersListItem> getModItems(){
		return modItems;
	}
	/**
	 *Gets Connection to Menu database 
	 */
	public static Connection getDatabaseConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/menu", "postgres", "AK47M4M16MP5PX4M249");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Database driver was not found!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Connection can not be established!");
		}
		return con;
	}
	/**
	 *Adds modifiers list name to database 
	 *@param name The name of the list
	 */
	public static void addModListToDatabase(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		//adds list name to modifiers_list table
		statement = "INSERT INTO modifiers_list(name) "
				+ "VALUES ('"+ name +"')";
		try {
			stmt = con.prepareStatement(statement);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *Gets modifier's list Id from database 
	 *@param name The name of the list
	 *@return id The list's id
	 */
	public static int getModListId(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		int id = -1;
		//gets the Id of the list with the particular name
		statement = "SELECT id FROM modifiers_list "
				+ "WHERE name LIKE ('"+ name +"')";
		try {
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	/**
	 *Gets modifiers Id from database 
	 *@param name The name of the modifier
	 *@return id The modifier's id
	 */
	public static int getModifierId(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		int id = -1;
		//gets the modifier's Id with the particular name
		statement = "SELECT id FROM modifiers "
				+ "WHERE name_on_button LIKE('"+ name +"')";
		try {
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("id");
			}
			con.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return id;
	}
	/**
	 *Checks if the modifier is already in the list 
	 *@param model The list model
	 *@param item The name of the modifier item
	 *@return true If item already in the list
	 */
	public static  boolean itemInTheList(DefaultListModel model, String item) {
		List<String> list = Collections.list(model.elements());
		for(Object i: list) {
			if(i.equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 *Adds modifier to a new list 
	 *@param listOfMods The list of existing modifiers
	 *@param modList The new list
	 *@param buttons Buttons on the grid
	 *@param x The x coordinate of the button
	 *@param y The y coordinate of the button
	 *@param list The list of arrays of button coordinates on the grid
	 *@param page The page on which button is located 
	 *@param itemsList The list of modifier's objects added to the new modifiers list 
	 *@return true If item already in the list
	 */
	public static void addModToList(JList listOfMods,         //list of existing modifiers
			JList modList,                                    //new list
			ArrayList<ItemSelectionMenuPanelButton> buttons,  //buttons on the grid
			int x,                                            //x coordinate of the button
			int y,                                            //y coordinate of the button
			ArrayList<int []> list,                           //list of arrays of button coordinates on the grid
			int page,                                         //page on which button is located 
			ArrayList<ModifiersListItem> itemsList) {          //list of modifier's objects added to the new modifiers list                                       
		
		//get list models from both lists
		DefaultListModel<String> listOfModsModel = (DefaultListModel<String>) listOfMods.getModel();
		DefaultListModel<String> model = (DefaultListModel<String>) modList.getModel();
		//index of the selected item
		int index = listOfMods.getSelectedIndex();
		//name of the selected item
		String item = listOfModsModel.get(index);
		//if such item is not in the list 
		//and the location on the grid is available
		//then add item to the list
		if(!itemInTheList(model, item)) {
			if(!buttonExists(list, x, y, page)) {
				model.addElement(item);
				sortModList(model);
				//get information about modifier from database
				loadModFromDatabase(buttons, item, x, y, page);
				//add coordinates to the list so we they already taken
				addModCoordsToCoordList(list, x, y, page);
				//create and add modifier object to the list
				itemsList.add(new ModifiersListItem(item, x, y, page));
			}
			else {
				JOptionPane.showMessageDialog(null, "There is another modifier on this location."
						+ "\nPlease choose another location!");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, item + " is already in the list");
		}
	}
	/**
	 *Adds modifier's coordinates to the list
	 *@param list The list of modifiers coordinates
	 *@param x The x coordinate
	 *@param y The y coordinate
	 *@param page The page  
	 */
	public static void addModCoordsToCoordList(ArrayList<int []> list, int x, int y, int page) {
		int[] coords = {x, y, page};
		list.add(coords);
	}
	/**
	 *Removes modifier's coordinates from the list
	 *@param list The list of modifiers coordinates
	 *@param x The x coordinate
	 *@param y The y coordinate
	 *@param page The page  
	 */
	public static void removeModCoordsFromCoordList(ArrayList<int []> list, int x, int y, int page) {
		int[] coords = {x, y};
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i)[0] == x && list.get(i)[1] == y && list.get(i)[2] == page) {
				list.remove(i);
			}
		}
	}
	/**
	 *Loads modifier's information from the database
	 *@param list The list of buttons on the grid
	 *@param modName The name of modifier
	 *@param x The x coordinate
	 *@param y The y coordinate
	 *@param page The page  
	 */
	public static void loadModFromDatabase(ArrayList<ItemSelectionMenuPanelButton> list, String modName, int x, int y, int page) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		ItemSelectionMenuPanelButton button = findButton(list, x, y, page);
		statement = "SELECT * FROM modifiers "
				+ "WHERE name_on_button LIKE('"+ modName +"')";
		try {
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				button.setButtonGap(rs.getInt("gap"));
				button.setText(rs.getString("name_on_button"));
				
				statement = "SELECT filename FROM images WHERE id IN('"+ rs.getInt("image_id") +"')";
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				while(rs.next()) {
					button.setButtonIcon(rs.getString("filename"));
				}
			}			
			con.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	/**
	 *Finds button with particular parameters in the list of buttons
	 *@param list The list of buttons on the grid
	 *@param x The x coordinate
	 *@param y The y coordinate
	 *@param page The page  
	 *@return Returns ItemSelectionMenuPanelButton if it is in the list. Otherwise returns null.
	 */
	public static ItemSelectionMenuPanelButton findButton(ArrayList<ItemSelectionMenuPanelButton> list, int x, int y, int page){
	    final int NUM_BUTTONS_PER_PAGE = 25;   //number of buttons per page
	    
		int size = list.size();     //array size
		if(page == 1) {
			for(int i = 0; i < NUM_BUTTONS_PER_PAGE; i++) {
				//if there is button with the same x and y coords on the same page
				//return it
				if(list.get(i).getX() == x && list.get(i).getY() == y) {
					return list.get(i);
				}
			}
		}
		else {
			for(int i = NUM_BUTTONS_PER_PAGE; i < size; i++) {
				if(list.get(i).getX() == x && list.get(i).getY() == y) {
					return list.get(i);
				}
			}
		}
		return null;
	}
	/**
	 *Check's if location on the grid is already taken
	 *@param list The list of buttons on the grid
	 *@param x The x coordinate
	 *@param y The y coordinate
	 *@param page The page  
	 *@return Returns true if location on the grid is taken
	 */
	public static boolean buttonExists(ArrayList<int []> list, int x, int y, int page) {
		int size = list.size();
		for(int i = 0; i < size; i++) {
			if(list.get(i)[0] == x && list.get(i)[1] == y && list.get(i)[2] == page) {
				return true;
			}
		}
		return false;
	}
	/**
	 *Removes item from the list of modifiers objects and from the list model.
	 *Also, it clears the location button on which modifier is displayed
	 *@param itemsList The list of modifiers objects
	 *@param coordList The list of array of coordinates on the grid that are taken
	 *@param buttons The array of buttons on the grid
	 *@param list The JList on the form 
	 */
	public void removeModFromList(ArrayList<ModifiersListItem> itemsList, 
			ArrayList<int []> coordList,
			ArrayList<ItemSelectionMenuPanelButton> buttons,
			JList list) {
		
		DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		int index = list.getSelectedIndex();
		
		//check if any item is selected 
		//if yes remove it
		if(index != -1) {
			String name = model.getElementAt(index);
			model.removeElementAt(index);
			clearButton(coordList, buttons, name);
			for(int i = 0; i < itemsList.size(); i++) {
				if(itemsList.get(i).getName().equals(name)) {
					itemsList.remove(i);
				}
			}
		}
	}
	/**
	 *Removes item from the list of modifiers objects and from the list model.
	 *Also, it clears the location button on which modifier is displayed
	 *@param itemsList The list of modifiers objects
	 *@param coordList The list of array of coordinates on the grid that are taken
	 *@param buttons The array of buttons on the grid
	 *@param list The JList on the form 
	 */
	public void removeModFromList(ArrayList<ModifiersListItem> itemsList, 
			ArrayList<int []> coordList,
			ArrayList<ItemSelectionMenuPanelButton> buttons,
			JList list,          //list from which to remove item
			int index              
			) {
		DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		
		//check if any item is selected 
		//if yes remove it
		if(index != -1) {
			String name = model.getElementAt(index);
			model.removeElementAt(index);
			clearButton(coordList, buttons, name);
			for(int i = 0; i < itemsList.size(); i++) {
				if(itemsList.get(i).getName().equals(name)) {
					itemsList.remove(i);
				}
			}
		}
	}
	/**
	 *Clears location button on the grid
	 *@param list The list of buttons on the grid
	 *@param coordList The list of array of coordinates on the grid that are taken
	 *@param name The name of the modifier which button is to be cleared 
	 */
	public static void clearButton(ArrayList<int []> coordList, ArrayList<ItemSelectionMenuPanelButton> list, String name) {
		final int NUM_BUTTONS_PER_PAGE = 25;          //number of buttons per page
		ItemSelectionMenuPanelButton button = null;
		int x = 0;
		int y = 0;
		int page = 0;
		int size = list.size();
		for(int i = 0; i < size; i++) {
			if(list.get(i).getText().equals(name)) {
				button = list.get(i);
				x = button.getX();
				y = button.getY();
				//determine what page the location is on
				page = Integer.parseInt(button.getActionCommand()) < NUM_BUTTONS_PER_PAGE ? 1 : 2;
				break;
			}
		}
		//clear button
		button.setButtonGap(0);
		button.setButtonIcon("empty_img.png");
		button.setButtonText("");
		//free up the coordinates 
		removeModCoordsFromCoordList(coordList, x, y, page);
	}
	/**
	 *Sorts the list
	 *@param listModel The listModel 
	 */
	public static void sortModList(DefaultListModel<String> listModel) {
		//store list items in the list
		List<String> list = Collections.list(listModel.elements());
		//sort updated list in alphabetical order
		Collections.sort(list, new Comparator<String>(){

		@Override
		public int compare(String arg0, String arg1) {
					
			if(arg0.compareToIgnoreCase(arg1) < 0)
				return -1;
			else if(arg0.compareToIgnoreCase(arg1) > 0)
				return 1;
			return 0;
			}
		});
		//remove all items from the list model
		listModel.clear();
		//add sorted items in the list model
		for(String o: list) {
			listModel.addElement(o);
		}
	}
	/**
	 *Checks if list with this particular name already exists in the database
	 *@param listName The list name
	 *@return exists Exists equals true if list already exists
	 */
	public static boolean listExists(String listName) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		boolean exists = false;
		//get list name from database
		statement = "SELECT name FROM modifiers_list "
				+ "WHERE name LIKE('"+ listName +"')";
		try {
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				exists = true;
			}
			con.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return exists;
	}
	/**
	 *Add button click listener
	 * 
	 */
	class AddButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
			addModToList(view.getListOfMods(),
					view.getModList(),
					view.getButtons(),
					view.getXcoord(), 
					view.getYcoord(), 
					view.getListOfCoords(),
					view.getPage(),
					modItems);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Choose location on the grid for this modifier.");
			}
			
		}
		
	}
	/**
	 *Remove button click listener
	 * 
	 */
	class RemoveButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			removeModFromList(modItems, view.getListOfCoords(), view.getButtons(), view.getModList());	
		}
		
	}
	
	/**
	 *Event Listener for Location buttons on Edit Item form
	 */
	 class LocationButtonClickListener implements ActionListener{

		private static final int NUM_BUTTONS_PER_PAGE = 25;
		@Override
		public void actionPerformed(ActionEvent evt) {
			
			int page;
			int xCoord;
			int yCoord;
			int index = Integer.parseInt(evt.getActionCommand()); // index of the button in the buttons arrayList
			
			//get button's bounds
			Rectangle rect = view.getButtons().get(index).getBounds();
			xCoord = rect.x;
			yCoord = rect.y;
			if(index >= NUM_BUTTONS_PER_PAGE ) {
				page = 2;
			}
			else {
				page = 1;
			}
			view.setPageText(page);
			view.setLocationXCoord(xCoord);
			view.setLocationYCoord(yCoord);
		}
		
	}
	 /**
		 *Page1 button click listener
		 * 
		 */
	class Page1ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.getLocationPanelPage1().setVisible(true);
			view.getLocationPanelPage2().setVisible(false);
			
		}
		
	}
	/**
	 *Page2 button click listener
	 * 
	 */
	class Page2ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.getLocationPanelPage1().setVisible(false);
			view.getLocationPanelPage2().setVisible(true);
			
		}
		
	}
	/**
	 *Save button click listener
	 * 
	 */
	class SaveButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//get the name of the list from the user
			String listName = JOptionPane.showInputDialog("Enter list name:");
			//make sure it does not yet exist
			if(listName != null) {
				while(listExists(listName)) {
					JOptionPane.showMessageDialog(null, "List with this name already exists!\n"
							+ "Please choose another name","Error", JOptionPane.ERROR_MESSAGE);
					listName = JOptionPane.showInputDialog("Enter list name:");
				}
				//add list to the database
				addModListToDatabase(listName);
				int modListId = getModListId(listName);
				Connection con = getDatabaseConnection();
				PreparedStatement stmt;
				String statement;
				int modifierId;
			
				try {
					//create modifier items for the list
					//and store them in database
					for(int i = 0; i < modItems.size();i++) {
				
						modifierId = getModifierId(modItems.get(i).getName());
						statement = "INSERT INTO Modifiers_list_items(list_id, xcoord, ycoord, page, modifier_id) "
								+ "VALUES ('"+ modListId +"',"
								+ "'"+ modItems.get(i).getxCoord() +"',"
								+ "'"+ modItems.get(i).getyCoord() +"',"
								+ "'"+ modItems.get(i).getPage() +"',"
								+ "'"+ modifierId +"')";
				
				
						stmt = con.prepareStatement(statement);
						stmt.executeUpdate();
					}
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	/**
	 *Create Modifiers button click listener 
	 */
	class CreateModifiersButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.setVisible(false);
			modFormsController.getView().setVisible(true);   //Set Create modifier form visible
			
		}
		
	}
}
