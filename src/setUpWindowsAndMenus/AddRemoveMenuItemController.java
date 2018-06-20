package setUpWindowsAndMenus;

import general_classes.MenuItem;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;

import windows_and_menues.ItemSelectionMenuPanelButton;

public class AddRemoveMenuItemController {

	private AddRemoveMenuItemForm view;
	private EditItemForm editItemForm;
	private AddNewItemForm newItemForm;
	private AddNewItemController newItemController;
	private ItemImageSelectorForm imageForm;
	private int categoryIndex;                       //index to track current category in the list of categories
	private ArrayList<CategoryInfo> catList;         //list of all categories
	private MenuItem item;                           //item that had been selected
	
	public AddRemoveMenuItemController(AddRemoveMenuItemForm view, EditItemForm editItemForm,
			ItemImageSelectorForm imageForm, AddNewItemController newItemController) {
		this.view = view;
		this.editItemForm = editItemForm;
		this.newItemController = newItemController;
		this.newItemForm = newItemController.getView();
		this.imageForm = imageForm;
		categoryIndex = 0;
		item = new MenuItem();
		catList = getCategoryList();
		displayCatInfo();
		loadArray(view.getButtons());
		addButtonsToForm();               //creates grid of buttons 
		this.view.LeftArrowButtonListener(new LeftArrowButtonClickListener());
		this.view.RightArrowButtonListener(new RightArrowButtonClickListener());
		this.view.addPage1ClickListener(new Page1ButtonClickListener());
		this.view.addPage2ClickListener(new Page2ButtonClickListener());
		this.view.addLocationButtonClickListener(new LocationButtonClick());
		this.view.addDeleteButtonListener(new DeleteButtonClickListener());
		this.view.addEditItemButtonListener(new EditItemButtonClick());
		this.view.addNewItemButtonListener(new NewItemButtonClickListener());
		this.view.addModifiersListener(new AddModifiersClickListener());
		this.editItemForm.addItemNameTxtChangeListener(new ItemNameChangeListener());
		this.editItemForm.addImageLookUpBtnListener(new ImageLookUpBtnClickListener());
		this.editItemForm.addImageNameChangeListener(new ImageNameChangeListener());
		this.editItemForm.addPage1ClickListener(new EditItemFormPage1ClickListener());
		this.editItemForm.addPage2ClickListener(new EditItemFormPage2ClickListener());
		this.editItemForm.addLocationButtonClickListener(new EditItemFormLocationButtonClickListener());
		this.editItemForm.addSaveButtonClickListener(new EditItemFormSaveButtonClickListener());
		this.editItemForm.addFinishedButtonListener(new EditItemFormFinishedButtonListener());
		this.imageForm.addCancelBtnListener(new CancelButtonClickListener());
		this.imageForm.addOkBtnListener(new OkButtonClickListener());
		
	}
	/**
	 *Returns copy of the selected item object
	 *@return The copy of selected item object 
	 */
	public MenuItem getItem() {
		return new MenuItem(item.getCategoryId(), item.getImageId(), item.getNameOnButton(),
				item.getImageFileName(), item.getNameOnTicket(), item.getPrice(), item.getGap(),
				item.getxCoord(), item.getyCoord(), item.getPage());
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
			JOptionPane.showMessageDialog(null, "Database driver was not found!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection can not be established!");
		}
		return con;
	}
	
	/**
	 *Checks if another button already exist on this particular location
	 *@return true if button already exists on this location
	 */
	public boolean buttonExist() {
		int x = editItemForm.getLocationXCoord();         //button x coordinate on the panel
		int y = editItemForm.getLocationYCoord();         //button y coordinate on the panel 
		int page = editItemForm.getPage();      //page
		Connection con = AddRemoveCategoryForm.getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
			
			try {
				statement = "SELECT category_id, xcoord, ycoord, page FROM items "
				+ "WHERE category_id = '"+ item.getCategoryId() +"' AND xcoord ='"+ x +"' AND ycoord ='"+ y +"' AND page ='" + page + "'";
				
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				//if button with particular x and y coordinates on the same page exists
				//return true
				if(rs.next()) {
					return true;
				}
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return false;
	}
	/**
	 *Returns ArrayList of category objects
	 *@return catList The list of all categories 
	 */
	public static ArrayList<CategoryInfo> getCategoryList(){
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		ArrayList<CategoryInfo> catList = new ArrayList<CategoryInfo>();
		
		try {
			statement = "SELECT cat.name_on_button, im.filename FROM categories cat " + 
					"JOIN images im ON cat.image_id = im.id";
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			while(rs.next()) {
				catList.add(new CategoryInfo(rs.getString(1),rs.getString(2)));
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return catList;
	}
	/**
	 *Returns the next category object in the list if there is one
	 *@return  catList.get(categoryIndex) The next category in the list.
	 *If there is none, returns the same category
	 */
	public CategoryInfo getNextCategory() {
		if((categoryIndex + 1) <= (catList.size() - 1)) {
			categoryIndex++;
		}
		return catList.get(categoryIndex);
	}
	/**
	 *Returns previous category object in the list if there is one
	 *@return  catList.get(categoryIndex) The previous category in the list.
	 *If there is none, returns the same category
	 */
	public CategoryInfo getPreviousCategory() {
		if((categoryIndex - 1) >= 0) {
			categoryIndex--;
		}
		return catList.get(categoryIndex);
	}
	/**
	 *Displays category information on the Add/Remove Item Form 
	 */
	public void displayCatInfo() {
		String catName = catList.get(categoryIndex).getCatName();
		view.getCatButton().setText(catName);
		view.getCatButton().setButtonIcon(catList.get(categoryIndex).getImageFileName());
		view.getCatNameField().setText(catName);
	}
	/**
	 *Load information about an item into the EditItemForm 
	 */
	public void loadItemInfo() {
		editItemForm.getItemNameField().setText(item.getNameOnButton());
		editItemForm.getImageFileNameTextField().setText(item.getImageFileName());
		editItemForm.setGap(Integer.toString(item.getGap()));
		editItemForm.setLocationXCoord(item.getxCoord());
		editItemForm.setLocationYCoord(item.getyCoord());
		editItemForm.setPageText(item.getPage());
		editItemForm.setPriceText(item.getPrice());
		editItemForm.setNameOnTicket(item.getNameOnTicket());;
	}
	
	/**
	 *Adds item to the DB 
	 */
	public void addItem() {
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			//insert all the category info into DB
			String SQLStatement = "INSERT INTO items(category_id,image_id, name_on_button, name_on_ticket, price, xcoord, ycoord, gap, page) "
									+ "VALUES('"+ item.getCategoryId() +"', "
									+ "(SELECT id FROM images WHERE name LIKE('" + editItemForm.getImageFileNameTextField().getText()+ "'))"
									+ ", '" + editItemForm.getItemName() + "'"
									+ ", '" + editItemForm.getNameOnTicket() + "'"
									+ ", '" + editItemForm.getPriceText() + "'"
									+ ", '" + editItemForm.getLocationXCoord() + "'"
									+ ", '" + editItemForm.getLocationYCoord() + "'"
									+ ", '" + editItemForm.getGap() + "'"
									+ ", '" + editItemForm.getPage() + "')";
			stmt.executeUpdate(SQLStatement);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *Edits item in the DB 
	 */
	public void editItem() {
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			//insert all the category info into DB
			String SQLStatement = "UPDATE items  "
									+ "SET image_id = (SELECT id FROM images WHERE filename LIKE('" + editItemForm.getImageFileNameTextField().getText()+ "'))"
									+ ", name_on_button = '"+ editItemForm.getItemName() +"'"
									+ ", name_on_ticket = '"+ editItemForm.getNameOnTicket() +"'"
									+ ", price = '"+ editItemForm.getPriceText() +"'"
									+ ", xcoord = '"+ editItemForm.getLocationXCoord() +"'"
									+ ", ycoord = '"+ editItemForm.getLocationYCoord() +"'"
									+ ", gap = '"+ editItemForm.getGap() +"'"
									+ ", page = '"+ editItemForm.getPage() +"'"
									+ " WHERE name_on_button LIKE('"+ item.getNameOnButton() +"')";
			stmt.executeUpdate(SQLStatement);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * Loads the arrayList with buttons into the Edit Item Form
	 * @param a an arrayList of ItemSelectionMenuPanelButton
	 */
	public  void loadArray(ArrayList<ItemSelectionMenuPanelButton> a) {
		int x = 0;         //button x coordinate on the panel
		int y = 0;         //button y coordinate on the panel 
		int page = 1;      //
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		ItemSelectionMenuPanelButton but = null;
		
		try {
			for(int i = 0; i < AddRemoveMenuItemForm.TOTAL_NUM_BUTTONS;i++) {
				if(i > 24) {
					page = 2;
				}
			
			
				statement = "SELECT name_on_button, xcoord, ycoord, gap, im.filename FROM items it "
				+ "JOIN images im ON it.image_id = im.id "
				+ "WHERE category_id IN( SELECT id FROM categories WHERE name_on_button LIKE('" + view.getCategoryName() + "')) "
				+ "AND xcoord ='"+ x +"' AND ycoord ='"+ y +"' AND page ='" + page + "'";
				
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				//if no record in DB exist with this particular button parameters
				//then create an empty button
				if(!rs.next()) {
					but = new ItemSelectionMenuPanelButton("empty_img.png","", 0, x, y, 
							AddRemoveMenuItemForm.BUTTON_WIDTH, AddRemoveMenuItemForm.BUTTON_HEIGHT);
				}
				//if such a record exists in the DB then create the button with particular parameters
				else {
					but = new ItemSelectionMenuPanelButton(rs.getString("filename"),
															rs.getString("name_on_button"),
															rs.getInt("gap"),
															rs.getInt("xcoord"),
															rs.getInt("ycoord"),
															AddRemoveMenuItemForm.BUTTON_WIDTH,
															AddRemoveMenuItemForm.BUTTON_HEIGHT);
				}
				but.setActionCommand(Integer.toString(i)); //action command holds buttons Array index
				a.add(but);
				x += AddRemoveMenuItemForm.BUTTON_WIDTH;       //155 is the width of the buttons. Put buttons side by side
				
				if(x >= AddRemoveMenuItemForm.PANEL_WIDTH) {  //if the end of the row is reached go to the next row
					y += AddRemoveMenuItemForm.BUTTON_HEIGHT;   //add one size of the button to the y coordinate. 155  is the button height
					if(x == AddRemoveMenuItemForm.PANEL_WIDTH && y == AddRemoveMenuItemForm.PANEL_HEIGHT) {
						x = 0;
						y = 0;
					}
					else
						x = 0;
				}
		}
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
	}
	/**
	 *Updates buttons on the grid on Add/Remove Item Form
	 *if anything changed or deleted
	 */
	public void updateItemButtons(ArrayList<ItemSelectionMenuPanelButton> a) {
		int page = 1;      //
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		ItemSelectionMenuPanelButton but = null;
		
		try {
			for(int i = 0; i < AddRemoveMenuItemForm.TOTAL_NUM_BUTTONS;i++) {
				but = a.get(i);
				if(i > 24) {
					page = 2;
				}
			
			
				statement = "SELECT name_on_button, xcoord, ycoord, gap, im.filename FROM items it "
				+ "JOIN images im ON it.image_id = im.id "
				+ "WHERE category_id IN( SELECT id FROM categories WHERE name_on_button LIKE('" + view.getCategoryName() + "')) "
				+ "AND xcoord ='"+ but.getX() +"' AND ycoord ='"+ but.getY() +"' AND page ='" + page + "'";
				
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				//if no record in DB exist with this particular button parameters
				//then create an empty button
				if(!rs.next()) {
					but.setText("");
					but.setButtonIcon("empty_img.png");
					but.setButtonGap(0);
				}
				//if such a record exists in the DB then create the button with particular parameters
				else {
					but.setButtonIcon(rs.getString("filename"));
					but.setButtonText(rs.getString("name_on_button"));
					but.setButtonGap(rs.getInt("gap"));
					but.revalidate();
				}
		}
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
	}
	/**
	 *Adds buttons to Add/Remove Item Form (The grid) 
	 */
	public void addButtonsToForm() {
		//add buttons to location panels
				for(int i = 0; i < view.getButtons().size(); i++) {
					if(i < 25) {
						view.getLocationPanelPage1().add(view.getButtons().get(i));
					}
					else {
						view.getLocationPanelPage2().add(view.getButtons().get(i));
					}
				}
	}
	/**
	 *Deletes item from the DB 
	 */
	public void deleteItemFromDatabase(int xc, int yc, int p) {
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			//delete category with these coordinates on this page 
			//from the DB
			String SQLStatement = "DELETE FROM items "
								+ "WHERE category_id IN(SELECT id FROM categories WHERE name_on_button "
								+ "LIKE('" + view.getCategoryName() +"')) "
								+ "AND xcoord =" + xc + " "
								+ "AND ycoord =" + yc + " "
								+ "AND page =" + p;
			stmt.executeUpdate(SQLStatement);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Clears the button on the location panel when button is deleted
	 */
	public void clearButton() {
		ArrayList<ItemSelectionMenuPanelButton> buttons = view.getButtons();
		int size = buttons.size();
		if(item.getPage() == 1) {
			for(int i = 0; i < (size / 2); i++) {
				ItemSelectionMenuPanelButton button = view.getButtons().get(i);
				if(button.getBounds().getX() == item.getxCoord() &&
						button.getBounds().getY() == item.getyCoord()) {
					//clear button info
					button.setText("");
					button.setButtonIcon("empty_img.png");   //set empty icon 
					button.setButtonGap(0);
					button.revalidate();
				}
			}
		}
		else {
			//search the second half of the buttons arrayList
			for(int i = 25; i < size; i++) {
				ItemSelectionMenuPanelButton button = view.getButtons().get(i);
				if(button.getBounds().getX() == item.getxCoord() &&
						button.getBounds().getY() == item.getyCoord()) {
					//clear button info
					button.setText("");
					button.setButtonIcon("empty_img.png");     //set empty icon
					button.setButtonGap(0);
					button.revalidate();
				}
			}
		}
	}
	
	/**
	 *Clears the button on the location panel if button 
	 *is saved to a different location on the grid
	 */
	public void editItemFormClearButton() {
		ArrayList<ItemSelectionMenuPanelButton> buttons = editItemForm.getButtons();
		int size = buttons.size();
		if(item.getPage() == 1) {
			for(int i = 0; i < (size / 2); i++) {
				ItemSelectionMenuPanelButton button = editItemForm.getButtons().get(i);
				if(button.getBounds().getX() == item.getxCoord() &&
						button.getBounds().getY() == item.getyCoord()) {
					//clear button info
					button.setText("");
					button.setButtonIcon("empty_img.png");   //set empty icon 
					button.setButtonGap(0);
					button.revalidate();
				}
			}
		}
		else {
			//search the second half of the buttons arrayList
			for(int i = 25; i < size; i++) {
				ItemSelectionMenuPanelButton button = editItemForm.getButtons().get(i);
				if(button.getBounds().getX() == item.getxCoord() &&
						button.getBounds().getY() == item.getyCoord()) {
					//clear button info
					button.setText("");
					button.setButtonIcon("empty_img.png");     //set empty icon
					button.setButtonGap(0);
					button.revalidate();
				}
			}
		}
	}
	
	/**
	 *Resets the button on the Edit Item Form location panel when button is added
	 */
	public void buttonReset() {
		ArrayList<ItemSelectionMenuPanelButton> buttons = editItemForm.getButtons();
		int size = buttons.size();
		//if on first page search the first half of the buttons array
		if(editItemForm.getPage() == 1) {
			for(int i = 0; i < (size / 2); i++) {
				ItemSelectionMenuPanelButton button = editItemForm.getButtons().get(i);
				if(button.getBounds().getX() == editItemForm.getLocationXCoord() &&
						button.getBounds().getY() == editItemForm.getLocationYCoord()) {
					//set up new button info
					button.setButtonText(editItemForm.getItemName());
					button.setButtonIcon(editItemForm.getImageFileNameTextField().getText());
					button.setButtonGap(editItemForm.getGap());
					button.revalidate();
				}
			}
		}
		else {
			//if on second page search the second half of the buttons array
			for(int i = 25; i < size; i++) {
				ItemSelectionMenuPanelButton button = editItemForm.getButtons().get(i);
				if(button.getBounds().getX() == editItemForm.getLocationXCoord() &&
						button.getBounds().getY() == editItemForm.getLocationYCoord()) {
					//set up new button info
					button.setText(editItemForm.getItemName());
					button.setButtonIcon(editItemForm.getImageFileNameTextField().getText());
					button.setButtonGap(editItemForm.getGap());
					button.revalidate();
				}
			}
		}
	}
	/**
	 *Listener for the leftArrow button on the Add/Remove Item Form 
	 */
	class LeftArrowButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			getPreviousCategory();
			displayCatInfo();
			updateItemButtons(view.getButtons());
			view.getLocationPanelPage1().setVisible(true);
			view.getLocationPanelPage2().setVisible(false);
			view.getItemButton().setButtonIcon("empty_img.png");
			view.getItemButton().setText("");
			item = new MenuItem();
		}
		
	}
	/**
	 *Listener for the rightArrow button on the Add/Remove Item Form 
	 */
	class RightArrowButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			getNextCategory();
			displayCatInfo();
			updateItemButtons(view.getButtons());
			view.getLocationPanelPage1().setVisible(true);
			view.getLocationPanelPage2().setVisible(false);
			view.getItemButton().setButtonIcon("empty_img.png");
			view.getItemButton().setText("");
			item = new MenuItem();
		}
		
	}
	/**
	 *Listener for the Page1 button on the Add/Remove Item Form 
	 */
	class Page1ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			view.getLocationPanelPage1().setVisible(true);
			view.getLocationPanelPage2().setVisible(false);
		}
		
	}
	/**
	 *Listener for the Page2 button on the Add/Remove Item Form 
	 */
	class Page2ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			view.getLocationPanelPage2().setVisible(true);
			view.getLocationPanelPage1().setVisible(false);
		}
		
	}
	/**
	 *Listener for the Page1 button on the Edit Item Form 
	 */
	class EditItemFormPage1ClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			editItemForm.getLocationPanelPage1().setVisible(true);
			editItemForm.getLocationPanelPage2().setVisible(false);
		}
		
	}
	/**
	 *Listener for the Page1 button on the Edit Item Form 
	 */
	class EditItemFormPage2ClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			editItemForm.getLocationPanelPage2().setVisible(true);
			editItemForm.getLocationPanelPage1().setVisible(false);
			
		}
		
	}
	
	/**
	 *Event Listener for Save button on editItem form
	 */
	class EditItemFormSaveButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean onSameSpot = (item.getxCoord() == editItemForm.getLocationXCoord() &&
					item.getyCoord() == editItemForm.getLocationYCoord()&&
					item.getPage() == editItemForm.getPage());
			//if input is valid update button info in DB
			//display updated button in location buttons panel and
			if(editItemForm.validateInput()) {
				
				if(!onSameSpot && buttonExist()) {
					
					JOptionPane.showMessageDialog(null, "There is a different button already exist on this spot."
							+ "Remove existing button first, then add another one");
				}else if(!onSameSpot){
					editItem();
					buttonReset();
					editItemFormClearButton();
				    updateItemButtons(view.getButtons());
				    //clear item button info on Add/Remove Form
				    view.getItemButton().setButtonText("");
					view.getItemButton().setButtonIcon("empty_img.png");
					view.setVisible(true);					
					editItemForm.dispose();
					item = new MenuItem();          //reset item info
				}else {
					editItem();
					buttonReset();
					updateItemButtons(view.getButtons());
					//clear item button info on Add/Remove Form
					view.getItemButton().setButtonText("");
					view.getItemButton().setButtonIcon("empty_img.png");
					view.setVisible(true);
					editItemForm.dispose();
					item = new MenuItem();//reset item info
					
				}
		
			}
			
		}
		
	}
	/**
	 *Listener for buttons on the grid click on Add/Remove Item Form
	 */
	class LocationButtonClick implements ActionListener{
		private final int NUM_BUTTONS_PER_PAGE = 25;
		
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			Connection con = getDatabaseConnection();
			Statement stmt;
			String statement;
			ResultSet rs;
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
			item.setNameOnButton(view.getButtons().get(index).getButtonText());
			item.setxCoord(xCoord);
			item.setyCoord(yCoord);
			item.setPage(page);
			
			try {
				stmt = con.createStatement();
				statement = "SELECT category_id, image_id, name_on_ticket, price, gap FROM items "
						+ "WHERE category_id IN(SELECT id FROM categories WHERE name_on_button LIKE('"+ view.getCategoryName() +"')) "
						+ "AND xcoord =" + xCoord + " "
						+ "AND ycoord =" + yCoord+ " "
						+ "AND page =" + page;
				rs = stmt.executeQuery(statement);
				if(rs.next()) {
					item.setCategoryId(rs.getInt("category_id"));
					item.setNameOnTicket(rs.getString("name_on_ticket"));
					item.setPrice(rs.getDouble("price"));
					item.setGap(rs.getInt("gap"));
					statement = "SELECT filename FROM images "
							+ "WHERE id IN('" + rs.getInt("image_id") + "')";
					
					rs = stmt.executeQuery(statement);
						if(rs.next()) {
							item.setImageFileName(rs.getString("filename"));
						}
				}
				con.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			
			
			view.getItemButton().setText(view.getButtons().get(index).getText());
			view.getItemButton().setIcon(view.getButtons().get(index).getIcon());
		}
		
	}
	
	/**
	 *Event Listener for Location buttons on Edit Item form
	 */
	 class EditItemFormLocationButtonClickListener implements ActionListener{

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
			editItemForm.setPageText(page);
			editItemForm.setLocationXCoord(xCoord);
			editItemForm.setLocationYCoord(yCoord);
		}
		
	}
	/**
	 *Listener for the EditItem button on the Add/Remove item form 
	 */
	class EditItemButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//if no item chosen then display error message
			if(item.getCategoryId() != -1) {
				view.setVisible(false);
				editItemForm.setMenuItem(getItem());      //store info about the item chosen at item variable
				editItemForm.updateItemButton();       //update sample button
				editItemForm.updateItemButtons(editItemForm.getButtons());    //update buttons on the grid 
				editItemForm.setVisible(true);
				loadItemInfo();              //load item info into edit item form
			}else {
				JOptionPane.showMessageDialog(null, "Please choose the item that you would like to edit!");
			}
		}
		
	} 
	
	class NewItemButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			newItemForm.setVisible(true);
			newItemForm.setCategoryName(view.getCategoryName());
			newItemController.updateItemButtons(newItemForm.getButtons());
		}
		
	}
	
	/**
	 *Listener for Delete button on the Add/Remove Item Form
	 */
	class DeleteButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			deleteItemFromDatabase(item.getxCoord(),item.getyCoord(), item.getPage());
			clearButton();        //display clear button in place of one that was deleted
			view.getItemButton().setText("");
			view.getItemButton().setButtonIcon("empty_img.png");
			item = new MenuItem();            //clear current item info
		}
		
	}
	/**
	 *Listener for edit item form finished button 
	 */
	class EditItemFormFinishedButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			editItemForm.dispose(); 
			view.setVisible(true);     //display add/remove item form 
		}
		
	}
	
	private class AddModifiersClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AddModifiersListToItemForm addModListToItemForm = new AddModifiersListToItemForm();
			new AddModifiersListToItemController(addModListToItemForm,
					new CreateModifiersListController(
							new CreateModifiersListForm(),
							new CreateModifierController(new CreateModifierForm(),
									new ModifierImageSelectorForm(),
									new EditModifierForm(),
									new EditModFormImageSelectorForm())));
			view.setVisible(false);
			addModListToItemForm.setVisible(true);
		}
		
	}
	
	/**
	 *Change Listener for Item name field
	 */
	class ItemNameChangeListener extends KeyAdapter{

			public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		       if(editItemForm.getItemNameField().getText().length() >= 18 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		        	
		    	   editItemForm.getItemNameField().getToolkit().beep();
		            evt.consume();
		        }
		       
	
		       if(editItemForm.getItemNameField().getCaretPosition() < editItemForm.getItemNameField().getText().length()) {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			    		   editItemForm.getItemNameField().getText().length() >= 18) {
			        		editItemForm.getButton().setButtonText(editItemForm.getItemNameField().getText());
			       }else {
			    	   //if caret placed in somewhere inside the text then find that position
			    	   //and insert symbol there
		        	StringBuilder txt = new StringBuilder(editItemForm.getItemNameField().getText());
		        	txt.insert(editItemForm.getItemNameField().getCaretPosition(), evt.getKeyChar());
		        	editItemForm.getButton().setButtonText(txt.toString());
			       }
		        
		       }else {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			    		   editItemForm.getItemNameField().getText().length() >= 18) {
			    	   editItemForm.getButton().setButtonText(editItemForm.getItemNameField().getText());
			       }else {
		        		//if characters other than backspace or delete are typed
				        //include them in the text 
			    	   editItemForm.getButton().setButtonText(editItemForm.getItemNameField().getText() + evt.getKeyChar());
		       		}
		        }
		        
		       editItemForm.getButton().revalidate();
	
			}
		
	
	}	
	
	/**
	 *Change listener for Image name field
	 */
	class ImageNameChangeListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent evt) {
			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			editItemForm.getButton().setButtonIcon(editItemForm.getImageFileNameTextField().getText());
			editItemForm.getButton().revalidate();
			editItemForm.getButton().repaint();
			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			
		}
		
	}
	/**
	 *Listener for the imageLookUpButton 
	 */
	class ImageLookUpBtnClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.setVisible(true);     //make form visible
		}
		
	}
	/**
	 *Listener for the Ok button on imageLookUp Form
	 */
	class OkButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                    imageForm.getImageTree().getLastSelectedPathComponent();
			 Object nodeInfo = node.getUserObject();
			 ImageInfo img = (ImageInfo)nodeInfo;
			 //insert image filename into the Category name field
			 editItemForm.getImageFileNameTextField().setText(img.getFileName());
			 imageForm.dispose();
			
		}
		
	}
	/**
	 *Listener for the Cancel button on imageLookUp Form
	 */
	class CancelButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.dispose();    //close form
			
		}
		
	}
}
