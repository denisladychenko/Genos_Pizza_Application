package setUpWindowsAndMenus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import utility.parameters.UtilityParameters;
import windows_and_menues.ItemSelectionMenuPanelButton;

public class AddRemoveMenuItemForm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static final int TOTAL_NUM_BUTTONS = 50;
	static final int PANEL_WIDTH = 775;
	static final int PANEL_HEIGHT = 775;
	static final int BUTTON_WIDTH = 155;
	static final int BUTTON_HEIGHT = 155;
	
	private ArrayList<JButton> buttons;
	private ItemSelectionMenuPanelButton catButton;           // category button
	private ItemSelectionMenuPanelButton itemButton;           // item button
	private JPanel locationPanelPage1, locationPanelPage2;         //panels that hold location buttons
	private JPanel categoryPanel,                      //panel to hold category info
				   itemPanel;                         //Panel to hold item set up
	private JTextField catNameTxt;                    //input field for category name
	private JButton page1Btn,                            //button to go to page 1
					page2Btn,                            //button to go to page 2
					deleteBtn;                           //button to remove category
	private JButton finishedBtn;                         //finish and exit button
	private JButton leftArrowBtn, rightArrowBtn;               //category navigation buttons
	private JButton editItemBtn,                         //button to edit the existing item
					addNewItemBtn,                       //button to create new item
					addComponentsListBtn,                //button to add components list to the item
					addModifiersListBtn;                 //button to add modifiers
					
	
	
	
	public AddRemoveMenuItemForm() {
		
		catButton = new ItemSelectionMenuPanelButton("empty_img.png","",0, 10,10, 155, 155);
		
		
		JLabel catLbl = new JLabel("Category");
		catLbl.setBounds(175, 10, 150, 30);
		catLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		catLbl.setForeground(Color.YELLOW);
		
		//create and set compound border
		Border border = new LineBorder(Color.WHITE, 2);
		Border borderInner = new LineBorder(Color.WHITE, 1);          //inner line border
		Border margin = new EmptyBorder(2,2,2,2);                     //margin between inner and outer line borders
		Border comp = (BorderFactory.createCompoundBorder(border, margin));
		Border compInner = (BorderFactory.createCompoundBorder(borderInner, margin));
		
		catNameTxt = new JTextField();
		catNameTxt.setBounds(10, 180, 280, 35);
		catNameTxt.setFont(new Font("Areal", Font.BOLD, 24));
		catNameTxt.setEditable(false);
		
		ImageIcon lArrow = new ImageIcon(getClass().getResource("/images/left_arrow.png"));
		ImageIcon rArrow = new ImageIcon(getClass().getResource("/images/right_arrow.png"));
		
		
		leftArrowBtn = new JButton(lArrow);
		leftArrowBtn.setBounds(40, 230, 100, 80);
		rightArrowBtn = new JButton(rArrow);
		rightArrowBtn.setBounds(160, 230, 100, 80);
		
		categoryPanel = new JPanel();
		categoryPanel.setLayout(null);
		categoryPanel.setBounds(10, 10, 300, 330);
		categoryPanel.setBorder(BorderFactory.createCompoundBorder(comp, compInner));
		categoryPanel.setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		categoryPanel.add(catButton);
		categoryPanel.add(catLbl);
		categoryPanel.add(catNameTxt);
		categoryPanel.add(leftArrowBtn);
		categoryPanel.add(rightArrowBtn);
		
		
		itemButton = new ItemSelectionMenuPanelButton("empty_img.png","",0, 10,10, 155, 155);
		itemButton.setBounds(10, 10, 155, 155);
		
		JLabel itemLbl = new JLabel("Item");
		itemLbl.setBounds(175, 10, 150, 30);
		itemLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		itemLbl.setForeground(Color.YELLOW);
		
		editItemBtn = new JButton("Edit Item"); 
		editItemBtn.setBounds(10, 190, 280, 50);
		editItemBtn.setFont(UtilityParameters.DATA_ENTRY_FONT);
		addNewItemBtn = new JButton("Add New Item");
		addNewItemBtn.setBounds(10, 250, 280, 50);
		addNewItemBtn.setFont(UtilityParameters.DATA_ENTRY_FONT);
		addComponentsListBtn = new JButton("Add Components"); 
		addComponentsListBtn.setBounds(10, 310, 280, 50);
		addComponentsListBtn.setFont(UtilityParameters.DATA_ENTRY_FONT);
		addModifiersListBtn = new JButton("Add Modifiers");  
		addModifiersListBtn.setBounds(10, 370, 280, 50);
		addModifiersListBtn.setFont(UtilityParameters.DATA_ENTRY_FONT);
		
		
		itemPanel = new JPanel();
		itemPanel.setLayout(null);
		itemPanel.setBounds(10, 350, 300, 430);
		itemPanel.setBorder(BorderFactory.createCompoundBorder(comp, compInner));
		itemPanel.setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		itemPanel.add(itemButton);
		itemPanel.add(itemLbl);
		itemPanel.add(editItemBtn);
		itemPanel.add(addNewItemBtn);
		itemPanel.add(addComponentsListBtn);
		itemPanel.add(addModifiersListBtn);
		
		
		
		
		locationPanelPage1 = new JPanel();
		locationPanelPage1.setBounds(320, 10, 775, 775);
		locationPanelPage1.setLayout(null);
	
		
		locationPanelPage2 = new JPanel();
		locationPanelPage2.setBounds(320, 10, 775, 775);
		locationPanelPage2.setLayout(null);
		
		
		
		buttons = new ArrayList<JButton>();
		
		
		this.add(locationPanelPage2);
		this.add(locationPanelPage1);
		
		
		//hide the second panel
		locationPanelPage2.setVisible(false);
		
		
		
		
		
		
		//panel to hold control buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(10, 790, 1085, 165);
		
		
		
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(comp, compInner));
		buttonPanel.setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		
		
		
		page1Btn = new JButton("Page 1");
		page1Btn.setBounds(10, 23, 207, 120);
		page1Btn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		page2Btn = new JButton("Page 2");
		page2Btn.setBounds(222, 23, 207, 120);
		page2Btn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(651, 23, 207, 120);
		deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		finishedBtn = new JButton("Finished");
		finishedBtn.setBounds(863, 23, 207, 120);
		finishedBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		buttonPanel.add(page1Btn);
		buttonPanel.add(page2Btn);
		buttonPanel.add(deleteBtn);
		buttonPanel.add(finishedBtn);
		
		this.add(categoryPanel);
		this.add(itemPanel);
		this.add(buttonPanel);
		
		
		this.setSize(new Dimension(UtilityParameters.SET_UP_WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Add/Edit Item");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		this.setVisible(true);
	}
	
	public ItemSelectionMenuPanelButton getItemButton() {
		return itemButton;
	}

	public void setItemButton(ItemSelectionMenuPanelButton itemButton) {
		this.itemButton = itemButton;
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
	 *Gets Category name 
	 *@return Category name 
	 */
	public String getCategoryName() {
		return catNameTxt.getText();
	}
	
	
	/**
	 *Gets location panel page 1
	 *@return locationPanelPage1 The location panel page 1
	 */
	public JPanel getLocationPanelPage1() {
		return locationPanelPage1;
	}
	/**
	 *Gets location panel page 2
	 *@return locationPanelPage2 The location panel page 2
	 */
	public JPanel getLocationPanelPage2() {
		return locationPanelPage2;
	}
	/**
	 *Gets list of location buttons
	 *@return buttons The list of location buttons
	 */
	public ArrayList<JButton> getButtons(){
		return buttons;
	}
	/**
	 *Gets category name field
	 *@return catNameTxt The category name textField
	 */
	public JTextField getCatNameField() {
		return catNameTxt;
	}
	/**
	 *Location buttons click listener
	 *@param al The action listener for location buttons
	 */
	public void addLocationButtonClickListener(ActionListener al) {
		int size = buttons.size();
		for(int i = 0; i < size; i++) {
			buttons.get(i).addActionListener(al);
		}
	}
	/**
	 *Resets Sample button
	 */
	public void resetSampleButton() {
		getButton().setText("");
		getButton().setButtonIcon("empty_img.png");
		getButton().setButtonGap(0);
	}
	
	/**
	 *Change listener for the category name field
	 *@param kl The key listener
	 */
	public void addCatNameTxtChangeListener(KeyListener kl) {
		catNameTxt.addKeyListener(kl);
	}
	
	/**
	 *Gets sample button
	 *@return button The sample button converted to ItemSelectionMenuPanelButton
	 */
	public ItemSelectionMenuPanelButton getButton() {
		return  (ItemSelectionMenuPanelButton) catButton;
	}
	public void addEditItemButtonListener(ActionListener al) {
		editItemBtn.addActionListener(al);
	}
	public void addNewItemButtonListener(ActionListener al) {
		addNewItemBtn.addActionListener(al);
	}
	public void addModifiersListener(ActionListener al) {
		addModifiersListBtn.addActionListener(al);
	}
	public void addComponentsListener(ActionListener al) {
		addComponentsListBtn.addActionListener(al);
	}
	/**
	 *Click listener for the Page1 button
	 *@param al The action listener
	 */
	public void addPage1ClickListener(ActionListener al) {
		page1Btn.addActionListener(al);
	}
	/**
	 *Click listener for the Page2 button
	 *@param al The action listener
	 */
	public void addPage2ClickListener(ActionListener al) {
		page2Btn.addActionListener(al);
	}
	/**
	 *Click listener for the Delete button
	 *@param al The action listener
	 */
	public void addDeleteButtonListener(ActionListener al) {
		deleteBtn.addActionListener(al);
	}
	/**
	 *Click listener for the Finished button
	 *@param al The action listener
	 */
	public void addFinishedButtonListener(ActionListener al) {
		finishedBtn.addActionListener(al);
	}
	public ItemSelectionMenuPanelButton getCatButton() {
		return (ItemSelectionMenuPanelButton)catButton;
	}
	public void LeftArrowButtonListener(ActionListener al) {
		leftArrowBtn.addActionListener(al);
	}
	public void RightArrowButtonListener(ActionListener al) {
		rightArrowBtn.addActionListener(al);
	}
}
