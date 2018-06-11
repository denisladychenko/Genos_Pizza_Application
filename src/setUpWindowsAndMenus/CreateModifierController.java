package setUpWindowsAndMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import pizzeria.exception_classes.PriceOutOfRange;

public class CreateModifierController {

	private CreateModifierForm view;
	private ModifierImageSelectorForm imageForm;
	private EditModifierForm editModForm;
	private EditModFormImageSelectorForm editModImageForm;
	private CreateModifiersListForm parentForm;           //form that opens this form
	
	
	// constructor
	public CreateModifierController(CreateModifierForm view,
			ModifierImageSelectorForm imageForm,            //image form
			EditModifierForm editModForm,                   //edit modifier form
			EditModFormImageSelectorForm editModImageForm) {
		this.view = view;
		this.imageForm = imageForm;
		this.editModForm = editModForm;
		this.editModImageForm = editModImageForm;
		this.view.addImageButtonListener(new ImageButtonClickListener());
		this.view.addImageNameChangeListener(new ImageNameChangeListener());
		this.view.addCreateButtonListener(new CreateButtonListener());
		this.view.addModifierListSelectionListener(new ModifierListSelectionListener());
		this.view.addEditButtonListener(new EditButtonListener());
		this.view.addDeleteButtonListener(new DeleteButtonClickListener());
		this.view.addFinishedButtonListener(new FinishedButtonClickListener());
		this.imageForm.addOkBtnListener(new OkButtonClickListener());
		this.imageForm.addCancelBtnListener(new CancelButtonClickListener());
		this.editModForm.addImageNameChangeListener(new EditFormImageChangeListener());
		this.editModForm.addSaveButtonClickListener(new SaveButtonClickListener());
		this.editModForm.addFinishedButtonClickListener(new EditModFormFinishedClickListener());
		this.editModForm.addImageLookUpButtonClickListener(new EditFormImageButtonClickListener());
		this.editModImageForm.addOkBtnListener(new ImageFormOkButtonClickListener());
		this.editModImageForm.addCancelBtnListener(new ImageFormCancelButtonClickListener());
	}
	/**
	 *Sets parent of the form
	 *@param parentForm parent form
	 **/
	public void setParentForm(CreateModifiersListForm parentForm) {
		this.parentForm = parentForm;
	}
	/**
	 *method to get view form
	 *@return view CreateModifierForm
	 **/
	public CreateModifierForm getView() {
		return view;
	}
	/**
	 *method to get EditModifierForm form
	 *@return editModForm EditModifierForm
	 **/
	public EditModifierForm getEditModForm() {
		return editModForm;
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
	 *Replaces the old item with the new one
	 *Inserts new item into the modifiers list in alphabetical order 
	 *@param listModel The list of items in the list
	 *@param prevName The name of the item before change
	 *@param newName The new name of the item
	 */
	
	public void updateModList(DefaultListModel<String> listModel, String prevName, String newName) {
		//remove the old item
		listModel.removeElementAt(listModel.indexOf(prevName));
		//add new element
		listModel.addElement(newName);
		sortModList(listModel);
	}
	/**
	 *Sorts list model in an alphabetical order
	 *@param listModel Model for the JList 
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
	 *method to delete modifier from database
	 *@param name The name of modifier to be deleted from db 
	 */
	public void deleteModifier(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		//delete modifier with particular name
		statement = "DELETE FROM modifiers "
				+ "WHERE name_on_button LIKE('"+ name +"')";
		try {
			stmt = con.prepareStatement(statement);
			stmt.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 *Method to edit modifier's info in the database
	 */
	public void editModifierInfo() {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		//updates modifier's into at database to whatever user entered into input fields
		statement = "UPDATE modifiers "
				+ "SET name_on_button = '"+ editModForm.getModNameTextField().getText() +"', "
				+ "name_on_ticket = '"+ editModForm.getNameOnTicket().getText() +"', "
				+ "image_id = (SELECT id FROM images WHERE filename LIKE('"+ editModForm.getImageFileNameTextField().getText() +"')), "
				+ "price = '"+ editModForm.getPrice().getText() +"', "
				+ "gap = '"+ editModForm.getGap().getText() +"'"
				+ "WHERE name_on_button LIKE('"+ editModForm.getSelectedModName() +"')";
		try {
			stmt = con.prepareStatement(statement);
			stmt.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 *Method to add modifier to the list data model
	 *@param form The form at which list model should be updated 
	 */
	public void addItemToModList(JFrame form) {
	//determine at which form to update listModel.
		if(form instanceof CreateModifierForm) {
			CreateModifierForm frame = (CreateModifierForm) form;
			frame.getListModel().addElement(frame.getModNameTextField().getText());
			sortModList(frame.getListModel());
		}
		else if(form instanceof EditModifierForm) {
			EditModifierForm frame = (EditModifierForm) form;
			frame.getListModel().addElement(view.getModNameTextField().getText());
			sortModList(frame.getListModel());
		}
		else {
			JOptionPane.showMessageDialog(null, "Can't do anything with the item you passed to the function");
		}
	}
	/**
	 *Method to remove item from the list model
	 *@param form The form at which list model should be updated
	 *@index of selected element 
	 */
	public void removeItemFromModList(JFrame form, int index) {
		if(form instanceof CreateModifierForm) {
			CreateModifierForm frame = (CreateModifierForm) form;
			frame.getListModel().removeElementAt(frame.getModList().getSelectedIndex());
		}
		else if(form instanceof EditModifierForm) {
			EditModifierForm frame = (EditModifierForm) form;
			frame.getListModel().removeElementAt(index);
		}
	}
	/**
	 *Method to load modifier's info into the Edit Form
	 * 
	 */
	public void loadModifierInfo() {
		if(editModForm.getSelectedModName() == null)
			return;
		String modName = editModForm.getSelectedModName();    //the one that was selected from the list
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		
		//retrieve modifier's info from db
		statement = "SELECT * FROM modifiers "
				+ "WHERE name_on_button LIKE('"+ modName +"')";
		try {
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			rs.next(); 
			editModForm.getModNameTextField().setText(rs.getString("name_on_button"));
			editModForm.getGap().setText(Integer.toString(rs.getInt("gap")));
			editModForm.getButton().setButtonGap(rs.getInt("gap"));
			editModForm.getPrice().setText(String.format("%.2f",rs.getDouble("price")));
			editModForm.getNameOnTicket().setText(rs.getString("name_on_ticket"));
			//get image filename form images table in db
			statement = "SELECT filename FROM images "
					+ "WHERE id IN('"+ rs.getInt("image_id") +"')";
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			rs.next(); 
			editModForm.getImageFileNameTextField().setText(rs.getString("filename"));
			
			con.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		editModForm.getButton().setButtonText(modName);		
	}
	/**
	 *Method to add modifier to database
	 */
	public void addModifierToDatabase() {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		//adds modifier into database
		statement = "INSERT INTO modifiers (name_on_button, name_on_ticket, image_id, price, gap) "
				+ "VALUES ('"+ view.getModNameTextField().getText() +"',"
				+ "'"+ view.getNameOnTicket().getText() +"',"
				+ "(SELECT id FROM images WHERE filename LIKE('"+ view.getImageFileNameTextField().getText() +"') AND image_group LIKE('modifier')),"
				+ "'"+ view.getPrice().getText() +"',"
				+ "'"+ view.getGap().getText() +"')";
		
		try {
			stmt = con.prepareStatement(statement);
			stmt.executeUpdate();
			con.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 *Checks if another modifier already exists with the same name
	 *@return true if modifier already exists 
	 */
	public boolean modifierExist() {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		String modName = view.getModNameTextField().getText();
			//check if name input is at least 2 character 
			//if so, capitalize the first letter of the string
			//and convert the rest of the string to lower case
			if(modName.length() >= 2)
				modName = modName.substring(0,1).toUpperCase() + modName.substring(1).toLowerCase();
				
			try {
				statement = "SELECT name_on_button FROM modifiers "
				+ "WHERE name_on_button LIKE('"+ view.getModNameTextField().getText() +"') "
				+ "OR name_on_button LIKE('"+ view.getModNameTextField().getText().toLowerCase() +"') "
				+ "OR name_on_button LIKE('"+ view.getModNameTextField().getText().toUpperCase() +"')"
				+ "OR name_on_button LIKE('"+ modName +"')";
				
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				//if modifier with the same name exists
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
	 *Click listener for Ok button at the imageForm  for Create Modifier Form
	 */
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
	/**
	 *Click listener for Ok button at the imageForm for Edit Modifier Form
	 */
	class ImageFormOkButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					editModImageForm.getImageTree().getLastSelectedPathComponent();
			 Object nodeInfo = node.getUserObject();
			 ImageInfo img = (ImageInfo)nodeInfo;
			 //insert image filename into the Category name field
			 editModForm.getImageFileNameTextField().setText(img.getFileName());
			 editModImageForm.dispose();
			
		}	
	}
	/**
	 *Click listener for Cancel button at the imageForm for Create Modifier Form
	 */
	class CancelButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.dispose();		
		}
		
	}
	/**
	 *Click listener for Cancel button at the imageForm for Edit Modifier Form
	 */
	class ImageFormCancelButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			editModImageForm.dispose();		
		}
		
	}
	/**
	 *Click listener for image button for Create Modifier Form
	 */
	class ImageButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.setVisible(true);			
		}
		
	}
	/**
	 *Click listener for image button for Edit Modifier Form
	 */
	class EditFormImageButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			editModImageForm.setVisible(true);			
		}
		
	}
	
	/**
	 *Change listener for Image name field for Create Modifier Form
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
	 *Change listener for Image name field for Edit Modifier Form
	 */
	class EditFormImageChangeListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent evt) {
			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			editModForm.getButton().setButtonIcon(editModForm.getImageFileNameTextField().getText());
			editModForm.getButton().revalidate();
			editModForm.getButton().repaint();
			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			
		}
	
	}
	/**
	 *Create button listener
	 */
	class CreateButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				//if input is valid and modifier does not exist
				//create modifier
				if(view.validateInput()){
					if(!modifierExist()) {
						addModifierToDatabase();
						//update JLists 
						addItemToModList(view);
						addItemToModList(editModForm);
						view.clearForm();
						//update JList at parent form
						parentForm.updateListOfMods(parentForm.getModListModel());
					}
					else {
						JOptionPane.showMessageDialog(null, "Modifier with this name already exists!");
					}
				}
			}catch(PriceOutOfRange ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}
	/**
	 *Save button listener at Edit Modifier Form
	 */
	class SaveButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//updates modifiers info in the database
			editModifierInfo();
			//updates modifiers list at Edit Modifier Form
			updateModList(editModForm.getListModel(),
					editModForm.getSelectedModName(),
					editModForm.getModNameTextField().getText());
			//updates modifiers list at Create Modifier Form
			updateModList(view.getListModel(),
					editModForm.getSelectedModName(),
					editModForm.getModNameTextField().getText());
					editModForm.dispose();	
					//update JList at parent form
					parentForm.updateListOfMods(parentForm.getModListModel());
			parentForm.setVisible(true);		
		}
		
	}
	/**
	 *Edit button listener
	 */
	class EditButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(editModForm.getSelectedModName() != null) {				
				editModForm.setVisible(true);
				loadModifierInfo();
				view.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Select item from the list to edit!");
			}
				
		}
		
	}
	/**
	 *Delete button listener
	 */
	class DeleteButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//if any item chosen then get confirmation from the user and delete item
			if(editModForm.getSelectedModName() != null) {
				if(JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete " + editModForm.getSelectedModName() + " \nfrom Modifiers?") == 0) {
				
					int index;    //index of selected list item
					index = view.getModList().getSelectedIndex();
					//delete modifier from database
					deleteModifier(view.getListModel().getElementAt(index));
					//update lists
					removeItemFromModList(view, index);
					removeItemFromModList(editModForm, index);
					//update parent form list
					parentForm.updateListOfMods(parentForm.getModListModel());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Choose item from the list to delete!");
			}
		}
		
		
	}
	/**
	 *Finished button listener for Edit Modifier Form
	 */
	class EditModFormFinishedClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			editModForm.dispose();
			view.setVisible(true);
		}
		
	}
	/**
	 *Modifier list selection listener for Create Modifier Form
	 */
	class ModifierListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent evt) {	
			//if edit button is not displayed then display it
			if(!view.getEditButton().isVisible())
				view.getEditButton().setVisible(true);
			//if change is complete get selected value from the list
			//set selected modifier name 
			if(!evt.getValueIsAdjusting()) {
				JList<String> list = (JList<String>)evt.getSource();
				editModForm.setSelectedModName(list.getSelectedValue());
			}
				
		}
		
	} 
	/**
	 *Finished button click  listener for Create Modifier Form
	 */
	class FinishedButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.dispose();
			parentForm.setVisible(true);
		}
		
	}
}
