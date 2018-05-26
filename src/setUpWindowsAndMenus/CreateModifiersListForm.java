package setUpWindowsAndMenus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import utility.parameters.UtilityParameters;
import windows_and_menues.ItemSelectionMenuPanelButton;

public class CreateModifiersListForm extends JFrame{

	
	private static final long serialVersionUID = 1L;

	private static final int TOTAL_NUM_BUTTONS = 50;
	private static final int PANEL_WIDTH = 775;
	private static final int PANEL_HEIGHT = 775;
	private static final int BUTTON_WIDTH = 155;
	private static final int BUTTON_HEIGHT = 155;
	
	
	private ArrayList<String> modLists;
	private String[] modifierLists;
	private ArrayList<ItemSelectionMenuPanelButton> buttons;       //grid buttons
	private JPanel locationPanelPage1, locationPanelPage2;         //panels that hold location buttons
	private JButton page1Btn,                            //button to go to page 1
					page2Btn,                            //button to go to page 2
					saveBtn;                           //button to create new modifiers list
	private JButton finishedBtn;                         //finish and exit button
	private JButton newListBtn;                         //button to create new list of modifiers
	private JButton addBtn,                             //adds button to the list
					removeBtn;                         //removes item from the list
	private JList<String> listOfModLst;                              //list of existing modifiers
	private JScrollPane listOfModScr;            
	private JList<String> modListLst;                              //new modifiers list
	private JScrollPane modListScr;        
	
	private JTextField locationXTxt,                     //button x coordinate
					   locationYTxt,                     //button y coordinate
					   pageTxt;                          //page that button is placed on
	
	public CreateModifiersListForm() {
		
		JLabel titleLbl = new JLabel("List of Modifiers");
		titleLbl.setBounds(70, 5, 300, 30);
		titleLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		titleLbl.setForeground(Color.YELLOW);
		
		loadListsArray();
		converToArray();
		listOfModLst = new JList<String>(modifierLists);
		listOfModLst.setFont(new Font("Segoe UI", Font.BOLD, 22));
		if(modifierLists.length != 0)
			listOfModLst.setSelectedIndex(0);
		
		listOfModScr = new JScrollPane(listOfModLst);
		listOfModScr.setBounds(10, 40, 300, 220);
		
		modListLst = new JList<String>();
		modListLst.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		modListScr = new JScrollPane(modListLst);
		modListScr.setBounds(10, 300, 300, 240);
		
		locationPanelPage1 = new JPanel();
		locationPanelPage1.setBounds(320, 10, 775, 775);
		locationPanelPage1.setLayout(null);
			
		locationPanelPage2 = new JPanel();
		locationPanelPage2.setBounds(320, 10, 775, 775);
		locationPanelPage2.setLayout(null);
			
		buttons = new ArrayList<ItemSelectionMenuPanelButton>();
		
		loadArray(buttons);
		//add buttons to location panels
		for(int i = 0; i < buttons.size(); i++) {
			if(i < 25) {
				locationPanelPage1.add(buttons.get(i));
			}
			else {
				locationPanelPage2.add(buttons.get(i));
			}
		}
		
		this.add(locationPanelPage2);
		this.add(locationPanelPage1);
			
		//hide the second panel
		locationPanelPage2.setVisible(false);
		
		
		addBtn = new JButton("Add");
		addBtn.setBounds(85, 265, 70, 30);
		
		removeBtn = new JButton("Remove");
		removeBtn.setBounds(165, 265, 80, 30);
		
		JLabel locationLbl = new JLabel("Location");
		locationLbl.setBounds(10, 550, 280, 30);
		locationLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		locationLbl.setForeground(Color.YELLOW);
		
		
		
		locationXTxt = new JTextField();
		locationXTxt.setBounds(10, 585, 80, 35);
		locationXTxt.setFont(new Font("Areal", Font.BOLD, 24));
		locationXTxt.setEditable(false);
		
		JLabel locationXLbl = new JLabel("X");
		locationXLbl.setBounds(95, 585, 20, 30);
		locationXLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		locationXLbl.setForeground(Color.YELLOW);
		
		locationYTxt = new JTextField();
		locationYTxt.setBounds(130, 585, 80, 35);
		locationYTxt.setFont(new Font("Areal", Font.BOLD, 24));
		locationYTxt.setEditable(false);
		
		JLabel locationYLbl = new JLabel("Y");
		locationYLbl.setBounds(215, 585, 20, 30);
		locationYLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		locationYLbl.setForeground(Color.YELLOW);
		
		JLabel pageLbl = new JLabel("Page");
		pageLbl.setBounds(10, 650, 280, 30);
		pageLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		pageLbl.setForeground(Color.YELLOW);
		
		
		pageTxt = new JTextField();
		pageTxt.setBounds(90, 650, 170, 35);
		pageTxt.setFont(new Font("Areal", Font.BOLD, 24));
		pageTxt.setEditable(false);
		
		
		//panel to hold control buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(10, 790, 1085, 165);
				
		//create and set compound border
		Border border = new LineBorder(Color.WHITE, 2);
		Border borderInner = new LineBorder(Color.WHITE, 1);          //inner line border
		Border margin = new EmptyBorder(2,2,2,2);                     //margin between inner and outer line borders
		Border comp = (BorderFactory.createCompoundBorder(border, margin));
		Border compInner = (BorderFactory.createCompoundBorder(borderInner, margin));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(comp, compInner));
		buttonPanel.setBackground(UtilityParameters.SET_UP_MENU_COLOR);
				
		saveBtn = new JButton("Create");
		saveBtn.setBounds(15, 23, 207, 120);
		saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
				
		page1Btn = new JButton("Page 1");
		page1Btn.setBounds(227, 23, 207, 120);
		page1Btn.setFont(new Font("Segoe UI", Font.BOLD, 32));
				
		page2Btn = new JButton("Page 2");
		page2Btn.setBounds(439, 23, 207, 120);
		page2Btn.setFont(new Font("Segoe UI", Font.BOLD, 32));
				
				
		finishedBtn = new JButton("Finished");
		finishedBtn.setBounds(863, 23, 207, 120);
		finishedBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		
		newListBtn = new JButton(">>Create Modifiers<<");
		newListBtn.setBounds(10, 730, 300, 50);
		newListBtn.setFont(UtilityParameters.DATA_ENTRY_FONT);
				
		buttonPanel.add(saveBtn);
		buttonPanel.add(page1Btn);
		buttonPanel.add(page2Btn);
		buttonPanel.add(finishedBtn);
		this.add(modListLst);
		this.add(modListScr);
		this.add(locationLbl);
		this.add(pageLbl);
		this.add(locationXTxt);
		this.add(locationYTxt);
		this.add(locationXLbl);
		this.add(locationYLbl);
		this.add(pageTxt);
		this.add(addBtn);
		this.add(removeBtn);
		this.add(newListBtn);
		this.add(listOfModScr);
		this.add(titleLbl);
		this.add(buttonPanel);
		this.setSize(new Dimension(UtilityParameters.SET_UP_WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Create List of Modifiers");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		this.setVisible(true);
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
	
	public void loadListsArray() {
		modLists = new ArrayList<>();
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		
		try {
			
			statement = "SELECT name FROM modifiers_list "
					+ "ORDER BY name ASC";
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				modLists.add(rs.getString("name"));
			}		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void converToArray() {
		modifierLists = new String[modLists.size()];
		for(int i = 0; i < modifierLists.length; i++) {
			modifierLists[i] = modLists.get(i);
		}
	}
	
	/** 
	 * Loads the arrayList with buttons
	 * @param a an arrayList of JButtons
	 */
	public void loadArray(ArrayList<ItemSelectionMenuPanelButton> a) {
		int x = 0;         //button x coordinate on the panel
		int y = 0;         //button y coordinate on the panel 
		int page = 1;      //
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		ItemSelectionMenuPanelButton but = null;
		
		try {
			for(int i = 0; i < TOTAL_NUM_BUTTONS;i++) {
				if(i > 24) {
					page = 2;
				}
			
				statement = "SELECT mli.list_id, mli.xcoord, mli.ycoord, mli.page, m.name_on_button, ml.name, im.filename FROM Modifiers_list_items mli "
						+ "JOIN modifiers m ON m.id = mli.modifier_id "
						+ "JOIN modifiers_list ml ON ml.id = mli.list_id "
						+ "JOIN images im ON m.image_id = im.id "
						+ "WHERE ml.name LIKE('Pizza_list') AND mli.xcoord IN('"+ x +"') AND mli.ycoord IN('"+ y +"')";
				
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				//if no record in DB exist with this particular button parameters
				//then create an empty button
				if(!rs.next()) {
					
					but = new ItemSelectionMenuPanelButton("empty_img.png","", 0, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
				}
				//if such a record exists in the DB then create the button with particular parameters
				else {
					
					but = new ItemSelectionMenuPanelButton(rs.getString("filename"),
															rs.getString("name_on_button"),
															0,
															rs.getInt("xcoord"),
															rs.getInt("ycoord"),
															BUTTON_WIDTH,
															BUTTON_HEIGHT);
				}
				but.setActionCommand(Integer.toString(i)); //action command holds buttons Array index
				a.add(but);
				x += BUTTON_WIDTH;       //155 is the width of the buttons. Put buttons side by side
				
				if(x >= PANEL_WIDTH) {  //if the end of the row is reached go to the next row
					y += BUTTON_HEIGHT;   //add one size of the button to the y coordinate. 155  is the button height
					if(x == PANEL_WIDTH && y == PANEL_HEIGHT) {
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
}
