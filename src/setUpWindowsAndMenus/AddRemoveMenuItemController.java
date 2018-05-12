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

import javax.swing.JButton;
import javax.swing.JOptionPane;

import windows_and_menues.ItemSelectionMenuPanelButton;

public class AddRemoveMenuItemController {

	private AddRemoveMenuItemForm view;
	private EditItemForm editItemForm;
	private EditItemImageSelectorForm imageForm;
	private int categoryIndex;                       //index to track current category in the list of categories
	private ArrayList<CategoryInfo> catList;         //list of all categories
	private MenuItem item;                           //item that had been selected
	
	public AddRemoveMenuItemController(AddRemoveMenuItemForm view, EditItemForm editItemForm, EditItemImageSelectorForm imageForm) {
		this.view = view;
		this.editItemForm = editItemForm;
		this.imageForm = imageForm;
		categoryIndex = 0;
		item = new MenuItem();
		catList = getCategoryList();
		displayCatInfo();
		loadArray(view.getButtons());
		addButtonsToForm();
		this.view.LeftArrowButtonListener(new LeftArrowButtonClickListener());
		this.view.RightArrowButtonListener(new RightArrowButtonClickListener());
		this.view.addPage1ClickListener(new Page1ButtonClickListener());
		this.view.addPage2ClickListener(new Page2ButtonClickListener());
		this.view.addLocationButtonClickListener(new LocationButtonClick());
		this.view.addDeleteButtonListener(new DeleteButtonClickListener());
		this.view.addEditItemButtonListener(new EditItemButtonClick());
		this.editItemForm.addItemNameTxtChangeListener(new ItemNameChangeListener());
		this.editItemForm.addImageLookUpBtnListener(new ImageLookUpBtnClickListener());
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
	
	public CategoryInfo getNextCategory() {
		if((categoryIndex + 1) <= (catList.size() - 1)) {
			categoryIndex++;
		}
		return catList.get(categoryIndex);
	}
	
	public CategoryInfo getPreviousCategory() {
		if((categoryIndex - 1) >= 0) {
			categoryIndex--;
		}
		return catList.get(categoryIndex);
	}
	public void displayCatInfo() {
		String catName = catList.get(categoryIndex).getCatName();
		view.getCatButton().setText(catName);
		view.getCatButton().setButtonIcon(catList.get(categoryIndex).getImageFileName());
		view.getCatNameField().setText(catName);
	}
	
	/** 
	 * Loads the arrayList with buttons
	 * @param a an arrayList of JButtons
	 */
	public  void loadArray(ArrayList<JButton> a) {
		int x = 0;         //button x coordinate on the panel
		int y = 0;         //button y coordinate on the panel 
		int page = 1;      //
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		JButton but = null;
		
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
	
	public void updateItemButtons(ArrayList<JButton> a) {
		int page = 1;      //
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		ItemSelectionMenuPanelButton but = null;
		
		try {
			for(int i = 0; i < AddRemoveMenuItemForm.TOTAL_NUM_BUTTONS;i++) {
				but = (ItemSelectionMenuPanelButton) a.get(i);
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
					but.setText(rs.getString("name_on_button"));
					but.setButtonGap(rs.getInt("gap"));
					but.revalidate();
				}
		}
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
	}
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *Clears the button on the location panel when button is deleted
	 */
	public void clearButton() {
		ArrayList<JButton> buttons = view.getButtons();
		int size = buttons.size();
		if(item.getPage() == 1) {
			for(int i = 0; i < (size / 2); i++) {
				ItemSelectionMenuPanelButton button = (ItemSelectionMenuPanelButton)view.getButtons().get(i);
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
				ItemSelectionMenuPanelButton button = (ItemSelectionMenuPanelButton)view.getButtons().get(i);
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
	class Page1ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			view.getLocationPanelPage1().setVisible(true);
			view.getLocationPanelPage2().setVisible(false);
		}
		
	}
	class Page2ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			view.getLocationPanelPage2().setVisible(true);
			view.getLocationPanelPage1().setVisible(false);
		}
		
	}
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
			item.setNameOnButton(view.getButtons().get(index).getText());
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
	
	class EditItemButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.setVisible(false);
			editItemForm.setMenuItem(item);
			editItemForm.updateItemButton();
			editItemForm.updateItemButtons(editItemForm.getButtons());
			editItemForm.setVisible(true);
		}
		
	} 
	
	class DeleteButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			deleteItemFromDatabase(item.getxCoord(),item.getyCoord(), item.getPage());
			clearButton();
			view.getItemButton().setText("");
			view.getItemButton().setButtonIcon("empty_img.png");
			item = new MenuItem();            //clear current item info
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
		        
		        
		       editItemForm.getButton().resetText();       //reset text on the button
		       editItemForm.getButton().revalidate();
	
			}
		
	
	}	
	
	class ImageLookUpBtnClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.setVisible(true);
		}
		
	}
}
