package setUpWindowsAndMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import windows_and_menues.ItemSelectionMenuPanelButton;

public class AddRemoveMenuItemController {

	private AddRemoveMenuItemForm view;
	private int categoryIndex;              //index to track current category in the list of categories
	private ArrayList<CategoryInfo> catList;
	
	public AddRemoveMenuItemController(AddRemoveMenuItemForm view) {
		this.view = view;
		categoryIndex = 0;
		catList = getCategoryList();
		displayCatInfo();
		loadArray(view.getButtons());
		addButtonsToForm();
		this.view.LeftArrowButtonListener(new LeftArrowButtonClickListener());
		this.view.RightArrowButtonListener(new RightArrowButtonClickListener());
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
		
		
		for(int i = 0; i < AddRemoveMenuItemForm.TOTAL_NUM_BUTTONS;i++) {
			if(i > 24) {
				page = 2;
			}
			
			try {
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
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
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
	
	class LeftArrowButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			getPreviousCategory();
			displayCatInfo();
			
		}
		
	}
	class RightArrowButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			getNextCategory();
			displayCatInfo();
			
		}
		
	}
}
