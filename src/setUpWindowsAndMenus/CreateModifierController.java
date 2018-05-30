package setUpWindowsAndMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public CreateModifierController(CreateModifierForm view, ModifierImageSelectorForm imageForm, EditModifierForm editModForm) {
		this.view = view;
		this.imageForm = imageForm;
		this.editModForm = editModForm;
		this.view.addImageButtonListener(new ImageButtonClickListener());
		this.view.addImageNameChangeListener(new ImageNameChangeListener());
		this.view.addCreateButtonListener(new CreateButtonListener());
		this.view.addModifierListSelectionListener(new ModifierListSelectionListener());
		this.view.addEditButtonListener(new EditButtonListener());
		this.view.addDeleteButtonListener(new DeleteButtonClickListener());
		this.imageForm.addOkBtnListener(new OkButtonClickListener());
		this.imageForm.addCancelBtnListener(new CancelButtonClickListener());
		this.editModForm.addImageNameChangeListener(new EditFormImageChangeListener());
		this.editModForm.addSaveButtonClickListener(new SaveButtonClickListener());
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
	
	public void deleteModifier(String name) {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		
		statement = "DELETE FROM modifiers "
				+ "WHERE name_on_button LIKE('"+ name +"')";
		try {
			stmt = con.prepareStatement(statement);
			stmt.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void editModifierInfo() {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		
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
	
	public void addItemToModList() {
		view.getListModel().addElement(view.getModNameTextField().getText());
	}
	
	public void removeItemFromModList() {
		view.getListModel().removeElementAt(view.getModList().getSelectedIndex());
	}
	
	public void loadModifierInfo() {
		String modName = editModForm.getSelectedModName();    //the one that was selected from the list
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		
		
		statement = "SELECT * FROM modifiers "
				+ "WHERE name_on_button LIKE('"+ modName +"')";
		try {
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			rs.next(); 
			editModForm.getModNameTextField().setText(rs.getString("name_on_button"));
			editModForm.getGap().setText(Integer.toString(rs.getInt("gap")));
			editModForm.getPrice().setText(String.format("%.2f",rs.getDouble("price")));
			editModForm.getNameOnTicket().setText(rs.getString("name_on_ticket"));
			
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
	
	public void addModifierToDatabase() {
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		String statement;
		
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
	class ImageButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			imageForm.setVisible(true);			
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
	
	class CreateButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if(view.validateInput()){
					if(!modifierExist()) {
						addModifierToDatabase();
						addItemToModList();
						view.clearForm();
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
	
	class SaveButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			editModifierInfo();
			
		}
		
	}
	
	class EditButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			editModForm.setVisible(true);
			loadModifierInfo();
		}
		
	}
	class DeleteButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index;    //index of selected list item
			index = view.getModList().getSelectedIndex();
			deleteModifier(view.getListModel().getElementAt(index));
			removeItemFromModList();
		}
		
	}
	
	class ModifierListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent evt) {	
			if(!view.getEditButton().isVisible())
				view.getEditButton().setVisible(true);
			if(!evt.getValueIsAdjusting()) {
				JList<String> list = (JList<String>)evt.getSource();
				editModForm.setSelectedModName(list.getSelectedValue());
			}
				
		}
		
	}
	
}
