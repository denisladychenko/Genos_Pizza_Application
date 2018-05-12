package setUpWindowsAndMenus;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;

import setUpWindowsAndMenus.AddRemoveCategoryImageSelectorForm.ImageInfo;
import windows_and_menues.ItemSelectionMenuPanelButton;

public class AddRemoveCategoryController {

	private AddRemoveCategoryForm view;      //the Form
	private AddRemoveCategoryModel model;    //action model
	private AddRemoveCategoryImageSelectorForm imageForm;    //image selection form
	
	//constructor
	public AddRemoveCategoryController(AddRemoveCategoryForm view, AddRemoveCategoryModel model,
										AddRemoveCategoryImageSelectorForm imageForm) {
		this.view = view;
		this.model = model;
		this.imageForm = imageForm;
		//add event listeners to forms
		this.view.addLocationButtonClickListener(new LocationButtonClick());
		this.view.addSaveButtonClickListener(new SaveButtonClick());
		this.view.addCatNameTxtChangeListener(new CatNameChangeListener());
		this.view.addImageNameChangeListener(new ImageNameChangeListener());
		this.view.addImageLookUpBtnListener(new ImageLookUpBtnClick());
		this.imageForm.addCancelBtnListener(new CancelBtnClick());
		this.imageForm.addOkBtnListener(new OkBtnClick());
		this.view.addPage1ClickListener(new Page1ButtonClick());
		this.view.addPage2ClickListener(new Page2ButtonClick());
		this.view.addFinishedButtonListener(new FinishedButtonClick());
		this.view.addDeleteButtonListener(new DeleteButtonClick());
	}
	
	/**
	 *Event Listener for Location buttons
	 */
	 class LocationButtonClick implements ActionListener{

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
	  *Event Listener for Delete button
	  */
	class DeleteButtonClick implements ActionListener{
	
	
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this category?") == 0) {
			int page = view.getPage();
			int xCoord = view.getLocationXCoord();
			int yCoord = view.getLocationYCoord();
			
			//delete record with these coordinates on this page from database
			model.deleteCategoryFromDatabase(xCoord, yCoord, page);
			clearButton();  //repaints the button
		}
		}	
	}
	/**
	 *Event Listener for Save button
	 */
	class SaveButtonClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//if input is valid save button info into DB
			//display button in location buttons panel and
			//reset the input fields 
			if(view.validateInput()) {
				model.setUpDataValues();
				if(!buttonExist()) {
					model.addCategory();
					buttonReset();
					view.resetForm();
				}else {
					JOptionPane.showMessageDialog(null, "There is a different button already exist on this spot."
							+ "Remove existing button first, then add another one");
				}
				
				
				
			}
			
		}
		
	}
	/**
	 *Event Listener for Page1 button
	 */
	class Page1ButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//switch to page 1
			view.getLocationPanelPage1().setVisible(true);
			view.getLocationPanelPage2().setVisible(false);
		}
		
	}
	/**
	 *Event Listener for Page2 button
	 */
	class Page2ButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//switch to page 2
			view.getLocationPanelPage2().setVisible(true);
			view.getLocationPanelPage1().setVisible(false);
		}
		
	}
	/**
	 *Event Listener for Finished button
	 */
	class FinishedButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.dispose();      //close the form
		}
		
	}
	/**
	 *Event Listener for ImageLookup button
	 */
	class ImageLookUpBtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			imageForm.setVisible(true); //display image lookup form
		}
		
	}
	/**
	 *Event Listener for Cancel button on ImageLookUpForm
	 */
	class CancelBtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			imageForm.dispose();
		}
		
	}
	/**
	 *Event Listener for Ok button on ImageLookUpForm
	 */
	class OkBtnClick implements ActionListener {

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
	
	/**
	 *Change Listener for Category name field
	 */
	class CatNameChangeListener extends KeyAdapter{

			public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		       if(view.getCatNameField().getText().length() >= 18 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		        	
		        	view.getCatNameField().getToolkit().beep();
		            evt.consume();
		        }
		       
	
		       if(view.getCatNameField().getCaretPosition() < view.getCatNameField().getText().length()) {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			        		view.getCatNameField().getText().length() >= 18) {
			        		view.getButton().setButtonText(view.getCatNameField().getText());
			       }else {
			    	   //if caret placed in somewhere inside the text then find that position
			    	   //and insert symbol there
		        	StringBuilder txt = new StringBuilder(view.getCatNameField().getText());
		        	txt.insert(view.getCatNameField().getCaretPosition(), evt.getKeyChar());
		        	view.getButton().setButtonText(txt.toString());
			       }
		        
		       }else {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			        		view.getCatNameField().getText().length() >= 18) {
			        		view.getButton().setButtonText(view.getCatNameField().getText());
			       }else {
		        		//if characters other than backspace or delete are typed
				        //include them in the text 
		        	view.getButton().setButtonText(view.getCatNameField().getText() + evt.getKeyChar());
		       		}
		        }
		        
		        
		        view.getButton().resetText();       //reset text on the button
		        view.getButton().revalidate();
	
			}
		
	
}			

	/**
	 *Change listener for Image name field
	 */
	class ImageNameChangeListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent evt) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			view.getButton().setButtonIcon(view.getImageFileNameTextField().getText());
			view.getButton().revalidate();
			view.getButton().repaint();
			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 *Resets the button on the location panel when button is added
	 */
	public void buttonReset() {
		ArrayList<JButton> buttons = view.getButtons();
		int size = buttons.size();
		//if on first page search the first half of the buttons array
		if(view.getPage() == 1) {
			for(int i = 0; i < (size / 2); i++) {
				ItemSelectionMenuPanelButton button = (ItemSelectionMenuPanelButton)view.getButtons().get(i);
				if(button.getBounds().getX() == view.getLocationXCoord() &&
						button.getBounds().getY() == view.getLocationYCoord()) {
					//set up new button info
					button.setText(view.getCategoryName());
					button.setButtonIcon(view.getImageFileNameTextField().getText());
					button.setButtonGap(view.getGap());
					button.revalidate();
				}
			}
		}
		else {
			//if on second page search the second half of the buttons array
			for(int i = 25; i < size; i++) {
				ItemSelectionMenuPanelButton button = (ItemSelectionMenuPanelButton)view.getButtons().get(i);
				if(button.getBounds().getX() == view.getLocationXCoord() &&
						button.getBounds().getY() == view.getLocationYCoord()) {
					//set up new button info
					button.setText(view.getCategoryName());
					button.setButtonIcon(view.getImageFileNameTextField().getText());
					button.setButtonGap(view.getGap());
					button.revalidate();
				}
			}
		}
	}
	/**
	 *Clears the button on the location panel when button is deleted
	 */
	public void clearButton() {
		ArrayList<JButton> buttons = view.getButtons();
		int size = buttons.size();
		if(view.getPage() == 1) {
			for(int i = 0; i < (size / 2); i++) {
				ItemSelectionMenuPanelButton button = (ItemSelectionMenuPanelButton)view.getButtons().get(i);
				if(button.getBounds().getX() == view.getLocationXCoord() &&
						button.getBounds().getY() == view.getLocationYCoord()) {
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
				if(button.getBounds().getX() == view.getLocationXCoord() &&
						button.getBounds().getY() == view.getLocationYCoord()) {
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
	 *Checks if another button already exist on this particular location
	 *@return true if button already exists on this location
	 */
	public boolean buttonExist() {
		int x = view.getLocationXCoord();         //button x coordinate on the panel
		int y = view.getLocationYCoord();         //button y coordinate on the panel 
		int page = view.getPage();      //
		Connection con = AddRemoveCategoryForm.getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
			
			try {
				statement = "SELECT name_on_button, xcoord, ycoord, gap, im.filename FROM categories c "
				+ "JOIN images im ON c.image_id = im.id WHERE xcoord ='"+ x +"' AND ycoord ='"+ y +"' AND page ='" + page + "'";
				
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					return true;
				}
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return false;
	}
	
}
