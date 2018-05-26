package setUpWindowsAndMenus;

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

public class AddNewItemController {

	private AddNewItemForm view;
	private ItemImageSelectorForm imageForm;
	private AddRemoveMenuItemForm mainForm;
	public AddNewItemController(AddNewItemForm view, AddRemoveMenuItemForm mainForm) {
		this.view = view;
		this.imageForm = new ItemImageSelectorForm();
		this.mainForm = mainForm;
		this.view.addItemNameKeyListener(new ItemNameKeyListener());
		this.view.addImageLookUpBtnListener(new ImageLookUpBtnListener());
		this.view.addImageNameChangeListener(new ImageNameChangeListener());
		this.view.addLocationButtonClickListener(new LocationButtonClickListener());
		this.view.addPage1ClickListener(new Page1ClickListener());
		this.view.addPage2ClickListener(new Page2ClickListener());
		this.view.addSaveButtonClickListener(new SaveButtonClickListener());
		this.view.addFinishedButtonListener(new FinishedButtonClickListener());
		this.imageForm.addOkBtnListener(new OkButtonClickListener());
		this.imageForm.addCancelBtnListener(new CancelButtonClickListener());
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
						+ "WHERE category_id IN(SELECT id FROM categories WHERE name_on_button LIKE('"+ mainForm.getCategoryName() +"')) "
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
	public AddNewItemForm getView() {
		return view;
	}
	public void setView(AddNewItemForm view) {
		this.view = view;
	}
	
	/**
	 *Checks if another button already exist on this particular location
	 *@return true if button already exists on this location
	 */
	public boolean buttonExist() {
		int x = view.getLocationXCoord();         //button x coordinate on the panel
		int y = view.getLocationYCoord();         //button y coordinate on the panel 
		int page = view.getPage();      //page
		Connection con = AddRemoveCategoryForm.getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
			
			try {
				statement = "SELECT category_id, xcoord, ycoord, page FROM items "
				+ "WHERE category_id = (SELECT id FROM categories WHERE name_on_button LIKE('"+ mainForm.getCategoryName() +"'))"
				+ " AND xcoord ='"+ x +"' AND ycoord ='"+ y +"' AND page ='" + page + "'";
				
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
	 *Adds item to the DB 
	 */
	public void addItem() {
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			//insert all the category info into DB
			String SQLStatement = "INSERT INTO items(category_id,image_id, name_on_button, name_on_ticket, price, xcoord, ycoord, gap, page) "
									+ "VALUES((SELECT id FROM categories WHERE name_on_button LIKE('"+ mainForm.getCategoryName() +"')) , "
									+ "(SELECT id FROM images WHERE filename LIKE('" + view.getImageFileNameTextField().getText()+ "')"
									+ " AND image_group LIKE('item'))"
									+ ", '" + view.getItemName() + "'"
									+ ", '" + view.getNameOnTicket() + "'"
									+ ", '" + view.getPriceText() + "'"
									+ ", '" + view.getLocationXCoord() + "'"
									+ ", '" + view.getLocationYCoord() + "'"
									+ ", '" + view.getGap() + "'"
									+ ", '" + view.getPage() + "')";
			stmt.executeUpdate(SQLStatement);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *Resets the button on the New Item Form location panel when button is added
	 */
	public void buttonReset() {
		ArrayList<ItemSelectionMenuPanelButton> buttons = view.getButtons();
		int size = buttons.size();
		//if on first page search the first half of the buttons array
		if(view.getPage() == 1) {
			for(int i = 0; i < (size / 2); i++) {
				ItemSelectionMenuPanelButton button = view.getButtons().get(i);
				if(button.getBounds().getX() == view.getLocationXCoord() &&
						button.getBounds().getY() == view.getLocationYCoord()) {
					//set up new button info
					button.setButtonText(view.getItemName());
					button.setButtonIcon(view.getImageFileNameTextField().getText());
					button.setButtonGap(view.getGap());
					button.revalidate();
				}
			}
		}
		else {
			//if on second page search the second half of the buttons array
			for(int i = 25; i < size; i++) {
				ItemSelectionMenuPanelButton button = view.getButtons().get(i);
				if(button.getBounds().getX() == view.getLocationXCoord() &&
						button.getBounds().getY() == view.getLocationYCoord()) {
					//set up new button info
					button.setText(view.getItemName());
					button.setButtonIcon(view.getImageFileNameTextField().getText());
					button.setButtonGap(view.getGap());
					button.revalidate();
				}
			}
		}
	}
	
	/**
	 *Change Listener for Item name field
	 */
	class ItemNameKeyListener extends KeyAdapter{

			public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		       if(view.getItemNameField().getText().length() >= 18 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		        	
		    	   view.getItemNameField().getToolkit().beep();
		            evt.consume();
		        }
		       
	
		       if(view.getItemNameField().getCaretPosition() < view.getItemNameField().getText().length()) {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			    		   view.getItemNameField().getText().length() >= 18) {
			    	   view.getButton().setButtonText(view.getItemNameField().getText());
			       }else {
			    	   //if caret placed in somewhere inside the text then find that position
			    	   //and insert symbol there
		        	StringBuilder txt = new StringBuilder(view.getItemNameField().getText());
		        	txt.insert(view.getItemNameField().getCaretPosition(), evt.getKeyChar());
		        	view.getButton().setButtonText(txt.toString());
			       }
		        
		       }else {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			    		   view.getItemNameField().getText().length() >= 18) {
			    	   view.getButton().setButtonText(view.getItemNameField().getText());
			       }else {
		        		//if characters other than backspace or delete are typed
				        //include them in the text 
			    	   view.getButton().setButtonText(view.getItemNameField().getText() + evt.getKeyChar());
		       		}
		        }
		        
		       view.getButton().revalidate();
	
			}
		
	
	}	
	class ImageLookUpBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.setVisible(true);	
		}
		
	}
	
	class OkButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                    imageForm.getImageTree().getLastSelectedPathComponent();
			 Object nodeInfo = node.getUserObject();
			 ImageInfo img = (ImageInfo)nodeInfo;
			 //insert image filename into the Category name field
			 view.getImageFileNameTextField().setText(img.getFileName());
			 imageForm.dispose();
			
		}
		
	}
	
	class CancelButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.dispose();
			
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
			view.getButton().setButtonIcon(view.getImageFileNameTextField().getText());
			view.getButton().revalidate();
			view.getButton().repaint();
			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			
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
		 *Listener for the Page1 button on the Add/Remove Item Form 
		 */
		class Page1ClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent evt) {
				view.getLocationPanelPage1().setVisible(true);
				view.getLocationPanelPage2().setVisible(false);
			}
			
		}
		/**
		 *Listener for the Page2 button on the Add/Remove Item Form 
		 */
		class Page2ClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent evt) {
				view.getLocationPanelPage2().setVisible(true);
				view.getLocationPanelPage1().setVisible(false);
			}
			
		}
		/**
		 *Listener for edit item form finished button 
		 */
		class FinishedButtonClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.dispose(); 
				mainForm.setVisible(true);     //display add/remove item form 
			}
			
		}
		
		class SaveButtonClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if input is valid save button info into DB
				//display button in location buttons panel and
				//reset the input fields 
				if(view.validateInput()) {
					if(!buttonExist()) {
						addItem();
						buttonReset();
						view.resetForm();
						updateItemButtons(mainForm.getButtons());          //update buttons on the main form
						view.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "There is a different button already exist on this spot."
								+ "Remove existing button first, then add another one");
					}
			
				}
				
			}
			
		}
}
