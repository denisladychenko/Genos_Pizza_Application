package setUpWindowsAndMenus;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddRemoveCategoryController {

	private AddRemoveCategoryForm view;
	private AddRemoveCategoryModel model;
	
	public AddRemoveCategoryController(AddRemoveCategoryForm view, AddRemoveCategoryModel model) {
		this.view = view;
		this.model = model;
		this.view.addLocationButtonClickListener(new LocationButtonClick());
		this.view.addSaveButtonClickListener(new SaveButtonClick());
		this.view.addCatNameTxtChangeListener(new CatNameChangeListener());
		this.view.addImageNameChangeListener(new ImageNameChangeListener());
		
	}
	
	
	class LocationButtonClick implements ActionListener{

		private static final int NUM_BUTTONS_PER_PAGE = 25;
		@Override
		public void actionPerformed(ActionEvent evt) {
			
			int page;
			int xCoord;
			int yCoord;
			int index = Integer.parseInt(evt.getActionCommand());
			
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
	
	class DeleteButtonClick implements ActionListener{

		private static final int NUM_BUTTONS_PER_PAGE = 25;
		@Override
		public void actionPerformed(ActionEvent evt) {
			
			int page;
			int xCoord;
			int yCoord;
			//index in the buttons array where the button is located
			int index = Integer.parseInt(evt.getActionCommand());
			
			Rectangle rect = view.getButtons().get(index).getBounds(); //button coordinates
			xCoord = rect.x;         //x coordinate
			yCoord = rect.y;         //y coordinate
			//if index is greater than the number of buttons on the page
			//set the page that means that the button is located on page 2
			if(index >= NUM_BUTTONS_PER_PAGE ) {
				page = 2;
			}
			else {
				page = 1;
			}
			//delete record with these coordinates on this page from database
			model.deleteCategoryFromDatabase(xCoord, yCoord, page);
			view.revalidate();;
			view.repaint();
		}
		
	}
	class SaveButtonClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(view.validateInput()) {
				model.setUpDataValues();
				model.addCategory();
				view.revalidate();
				view.repaint();
				
			}
			
		}
		
	}
	
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

	
	class ImageNameChangeListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent evt) {
			// TODO Auto-generated method stub
			view.getButton().setButtonIcon(view.getImageFileNameTextField().getText());
			view.getButton().revalidate();
			view.getButton().repaint();
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
	
}
