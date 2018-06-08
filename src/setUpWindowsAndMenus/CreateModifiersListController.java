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
		this.modFormsController = modFormsController;               
		this.view.addAddButtonClickListener(new AddButtonClickListener());
		this.view.addRemoveButtonClickListener(new RemoveButtonClickListener());
		this.view.addLocationButtonClickListener(new LocationButtonClickListener());
		this.view.addPage1ButtonClickListener(new Page1ButtonClickListener());
		this.view.addPage2ButtonClickListener(new Page2ButtonClickListener());
		this.view.addSaveButtonClickListener(new SaveButtonClickListener());
		this.view.addCreateModifiersButtonClickListener(new CreateModifiersButtonClick());
		modItems = new ArrayList<ModifiersListItem>();
	}
	
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
	
	public static void addModListToDatabase(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		
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
	
	public static int getModListId(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		int id = -1;
		
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
	
	public static int getModifierId(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		int id = -1;
		
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
	
	public static boolean itemInTheList(DefaultListModel model, String item) {
		List<String> list = Collections.list(model.elements());
		for(Object i: list) {
			if(i.equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	public static void addModToList(JList listOfMods,         //list of existing modifiers
			JList modList,                                    //new list
			ArrayList<ItemSelectionMenuPanelButton> buttons,  //buttons on the grid
			int x,                                            //x coordinate of the button
			int y,                                            //y coordinate of the button
			ArrayList<int []> list,                           //list of arrays of button coordinates on the grid
			int page,                                         //page on which button is located 
			ArrayList<ModifiersListItem> itemsList) {          //list of modifier's objects added to the new modifiers list                                       
		
		
		DefaultListModel<String> listOfModsModel = (DefaultListModel<String>) listOfMods.getModel();
		DefaultListModel<String> model = (DefaultListModel<String>) modList.getModel();
		int index = listOfMods.getSelectedIndex();
		String item = listOfModsModel.get(index);

		if(!itemInTheList(model, item)) {
			if(!buttonExists(list, x, y, page)) {
				model.addElement(item);
				sortModList(model);
				loadModFromDatabase(buttons, item, x, y, page);
				addModCoordsToCoordList(list, x, y, page);
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
	
	public static void addModCoordsToCoordList(ArrayList<int []> list, int x, int y, int page) {
		int[] coords = {x, y, page};
		list.add(coords);
	}
	
	public static void removeModCoordsFromCoordList(ArrayList<int []> list, int x, int y, int page) {
		int[] coords = {x, y};
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i)[0] == x && list.get(i)[1] == y && list.get(i)[2] == page) {
				list.remove(i);
			}
		}
	}
	
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
	
	public static ItemSelectionMenuPanelButton findButton(ArrayList<ItemSelectionMenuPanelButton> list, int x, int y, int page){
	    final int NUM_BUTTONS_PER_PAGE = 25;
		int size = list.size();
		if(page == 1) {
			for(int i = 0; i < NUM_BUTTONS_PER_PAGE; i++) {
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
	
	public static boolean buttonExists(ArrayList<int []> list, int x, int y, int page) {
		int size = list.size();
		for(int i = 0; i < size; i++) {
			if(list.get(i)[0] == x && list.get(i)[1] == y && list.get(i)[2] == page) {
				return true;
			}
		}
		return false;
	}
	
	public static void removeModFromList(ArrayList<ModifiersListItem> itemsList, 
			ArrayList<int []> coordList,
			ArrayList<ItemSelectionMenuPanelButton> buttons,
			JList list) {
		
		DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		int index = list.getSelectedIndex();
		
		
		if(index != -1) {
			String name = model.getElementAt(index);
			model.removeElementAt(index);
			clearButton(coordList, buttons, name);
			int size = itemsList.size();
			for(int i = 0; i < size; i++) {
				if(itemsList.get(i).getName().equals(name)) {
					itemsList.remove(i);
				}
			}
		}
	}
	
	public static void clearButton(ArrayList<int []> coordList, ArrayList<ItemSelectionMenuPanelButton> list, String name) {
		final int NUM_BUTTONS_PER_PAGE = 25;
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
				page = Integer.parseInt(button.getActionCommand()) < NUM_BUTTONS_PER_PAGE ? 1 : 2;
				break;
			}
		}
		button.setButtonGap(0);
		button.setButtonIcon("empty_img.png");
		button.setButtonText("");
		removeModCoordsFromCoordList(coordList, x, y, page);
	}
	
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
	
	public static boolean listExists(String listName) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		boolean exists = false;
		
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
	class Page1ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.getLocationPanelPage1().setVisible(true);
			view.getLocationPanelPage2().setVisible(false);
			
		}
		
	}
	class Page2ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.getLocationPanelPage1().setVisible(false);
			view.getLocationPanelPage2().setVisible(true);
			
		}
		
	}
	
	class SaveButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String listName = JOptionPane.showInputDialog("Enter list name:");
			while(listExists(listName)) {
				JOptionPane.showMessageDialog(null, "List with this name already exists!\n"
						+ "Please choose another name","Error", JOptionPane.ERROR_MESSAGE);
				listName = JOptionPane.showInputDialog("Enter list name:");
			}
			addModListToDatabase(listName);
			int modListId = getModListId(listName);
			Connection con = getDatabaseConnection();
			PreparedStatement stmt;
			String statement;
			int modifierId;
			
			try {
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
	
	class CreateModifiersButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			modFormsController.getView().setVisible(true);
			
		}
		
	}
}
