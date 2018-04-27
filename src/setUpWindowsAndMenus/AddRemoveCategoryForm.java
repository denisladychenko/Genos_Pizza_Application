package setUpWindowsAndMenus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.event.DocumentListener;

import utility.parameters.UtilityParameters;
import windows_and_menues.ItemSelectionMenuPanelButton;

public class AddRemoveCategoryForm extends JFrame{


	private static final long serialVersionUID = 1L;
	private static final int TOTAL_NUM_BUTTONS = 50;
	private static final int PANEL_WIDTH = 775;
	private static final int PANEL_HEIGHT = 775;
	private static final int BUTTON_WIDTH = 155;
	private static final int BUTTON_HEIGHT = 155;
	
	private ArrayList<JButton> buttons;
	private JLabel nameLbl;
	private JButton button;           // sample button
	private JPanel locationPanelPage1, locationPanelPage2;         //panels that hold location buttons
	private JTextField catNameTxt;                    //input field for category name
	private JTextField imageFileNameTxt;              //text field for image file input
	private JTextField gapTxt;                        //text field for gap input
	private JTextField locationXTxt;                   //text field for category location x coordinate
	private JTextField locationYTxt;                   //text field for category location y coordinate
	private JTextField pageTxt;                       //text field for a page number
	private JButton imageLookUpBtn;                   //button for image look up
	private JButton page1Btn,                            //button to go to page 1
					page2Btn,                            //button to go to page 2
					deleteBtn;                           //button to remove category
	private JButton saveBtn,                             //button to save category in the database
					finishedBtn;                         //finish and exit button
	private JPanel buttonPanel;                        //panel to hold control buttons
	
	
	public AddRemoveCategoryForm() {
		
		button = new ItemSelectionMenuPanelButton("empty_img.png","",0, 80,10, 155, 155);
		//button.setEnabled(false);
		
		locationPanelPage1 = new JPanel();
		locationPanelPage1.setBounds(320, 10, 775, 775);
		locationPanelPage1.setLayout(null);
	
		
		locationPanelPage2 = new JPanel();
		locationPanelPage2.setBounds(320, 10, 775, 775);
		locationPanelPage2.setLayout(null);
		
		
		
		buttons = new ArrayList<JButton>();
		loadArray(buttons);
		
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
		
		
		//locationPanelPage1.setVisible(false);
		locationPanelPage2.setVisible(false);
		
		nameLbl = new JLabel("Category Name");
		nameLbl.setBounds(10, 185, 280, 30);
		nameLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		nameLbl.setForeground(Color.YELLOW);
		
		JLabel maxNumMessageLbl = new JLabel("(18 symbols max)");
		maxNumMessageLbl.setBounds(90, 256, 280, 15);
		maxNumMessageLbl.setForeground(Color.YELLOW);
		catNameTxt = new JTextField();
		catNameTxt.setBounds(10, 220, 280, 35);
		catNameTxt.setFont(new Font("Areal", Font.BOLD, 24));
		catNameTxt.setHighlighter(null);
		
		
		JLabel imageFileLbl = new JLabel("Image File Name");
		imageFileLbl.setBounds(10, 305, 280, 30);
		imageFileLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		imageFileLbl.setForeground(Color.YELLOW);
		
	
		imageFileNameTxt = new JTextField();
		imageFileNameTxt.setBounds(10, 340, 250, 35);
		imageFileNameTxt.setFont(new Font("Areal", Font.BOLD, 24));
		
	
		ImageIcon lookUpImg = new ImageIcon(getClass().getResource("/images/mag_glass.png"));
		imageLookUpBtn = new JButton(lookUpImg);
		imageLookUpBtn.setBounds(265, 340, 40, 35);
		
		
		JLabel gapLbl = new JLabel("Gap");
		gapLbl.setBounds(10, 420, 280, 30);
		gapLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		gapLbl.setForeground(Color.YELLOW);
		
		JLabel messageLbl = new JLabel("(gap between image and text)");
		messageLbl.setBounds(20, 490, 280, 15);
		messageLbl.setForeground(Color.YELLOW);
		gapTxt = new JTextField();
		gapTxt.setBounds(10, 455, 200, 35);
		gapTxt.setFont(new Font("Areal", Font.BOLD, 24));
		gapTxt.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		        if(gapTxt.getText().length() >= 3 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		        	
		        	getToolkit().beep();
		            evt.consume();
		        }
		      //this only allows digits to be entered
		        //48 is the ASCI code for '0' and 57 is the ASCI code for '9'
		        if(((int)evt.getKeyChar() < 48 || (int)evt.getKeyChar() > 57) && 
		        		(!(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE))) {
		        	getToolkit().beep();
		        	evt.consume();
		        }
		     }
		});
		
		
		JLabel locationLbl = new JLabel("Location");
		locationLbl.setBounds(10, 530, 280, 30);
		locationLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		locationLbl.setForeground(Color.YELLOW);
		
		JLabel locMessageLbl = new JLabel("<html>(Click the button on the grid where "
				+ "you want your category to be set)<html>");
		locMessageLbl.setBounds(20, 600, 180, 55);
		locMessageLbl.setForeground(Color.YELLOW);
		locationXTxt = new JTextField();
		locationXTxt.setBounds(10, 565, 80, 35);
		locationXTxt.setFont(new Font("Areal", Font.BOLD, 24));
		locationXTxt.setEditable(false);
		
		JLabel locationXLbl = new JLabel("X");
		locationXLbl.setBounds(95, 565, 20, 30);
		locationXLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		locationXLbl.setForeground(Color.YELLOW);
		
		locationYTxt = new JTextField();
		locationYTxt.setBounds(130, 565, 80, 35);
		locationYTxt.setFont(new Font("Areal", Font.BOLD, 24));
		locationYTxt.setEditable(false);
		
		JLabel locationYLbl = new JLabel("Y");
		locationYLbl.setBounds(215, 565, 20, 30);
		locationYLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		locationYLbl.setForeground(Color.YELLOW);
		
		JLabel pageLbl = new JLabel("Page");
		pageLbl.setBounds(10, 680, 280, 30);
		pageLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		pageLbl.setForeground(Color.YELLOW);
		
		JLabel pageMessageLbl = new JLabel("(Page to set the category on)");
		pageMessageLbl.setBounds(20, 750, 280, 15);
		pageMessageLbl.setForeground(Color.YELLOW);
		pageTxt = new JTextField();
		pageTxt.setBounds(10, 715, 200, 35);
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
		
		saveBtn = new JButton("Save");
		saveBtn.setBounds(15, 23, 207, 120);
		saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		page1Btn = new JButton("Page 1");
		page1Btn.setBounds(227, 23, 207, 120);
		page1Btn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		page2Btn = new JButton("Page 2");
		page2Btn.setBounds(439, 23, 207, 120);
		page2Btn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(651, 23, 207, 120);
		deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		finishedBtn = new JButton("Finished");
		finishedBtn.setBounds(863, 23, 207, 120);
		finishedBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		buttonPanel.add(saveBtn);
		buttonPanel.add(page1Btn);
		buttonPanel.add(page2Btn);
		buttonPanel.add(deleteBtn);
		buttonPanel.add(finishedBtn);
		
		this.add(button);
		this.add(catNameTxt);
		this.add(nameLbl);
		this.add(maxNumMessageLbl);
		this.add(imageFileLbl);
		this.add(imageFileNameTxt);
		this.add(imageLookUpBtn);
		this.add(gapLbl);
		this.add(gapTxt);
		this.add(messageLbl);
		this.add(locationLbl);
		this.add(locationXTxt);
		this.add(locationYTxt);
		this.add(locationXLbl);
		this.add(locationYLbl);
		this.add(locMessageLbl);
		this.add(pageLbl);
		this.add(pageTxt);
		this.add(pageMessageLbl);
		this.add(buttonPanel);
		
		
		this.setSize(new Dimension(UtilityParameters.SET_UP_WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Add/Remove Category");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		this.setVisible(true);
	}
	
	
	
	
	/** 
	 * Loads the arrayList with buttons
	 * @param a an arrayList of JButtons
	 */
	public static void loadArray(ArrayList<JButton> a) {
		int x = 0;         //button x coordinate on the panel
		int y = 0;         //button y coordinate on the panel 
		int page = 1;      //
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		JButton but = null;
		
		
		for(int i = 0; i < TOTAL_NUM_BUTTONS;i++) {
			if(i > 24) {
				page = 2;
			}
			
			try {
				statement = "SELECT name_on_button, xcoord, ycoord, gap, im.filename FROM categories c "
				+ "JOIN images im ON c.image_id = im.id WHERE xcoord ='"+ x +"' AND ycoord ='"+ y +"' AND page ='" + page + "'";
				
				stmt = con.prepareStatement(statement);
				rs = stmt.executeQuery();
				
				if(!rs.next()) {
					but = new ItemSelectionMenuPanelButton("empty_img.png","", 20, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
				}
				else {
					but = new ItemSelectionMenuPanelButton(rs.getString("filename"),
															rs.getString("name_on_button"),
															rs.getInt("gap"),
															rs.getInt("xcoord"),
															rs.getInt("ycoord"),
															BUTTON_WIDTH,
															BUTTON_HEIGHT);
				}
				but.setActionCommand(Integer.toString(i));
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
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
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
	
	public String getCategoryName() {
		return catNameTxt.getText();
	}
	
	public String getFileName() {
		return imageFileNameTxt.getText();
	}
	
	public JTextField getImageFileNameTextField() {
		return imageFileNameTxt;
	}
	
	public int getGap() {
		return Integer.parseInt(gapTxt.getText());
	}
	
	public int getLocationXCoord() {
		return Integer.parseInt(locationXTxt.getText());
	}
	
	public void setLocationXCoord(int xc) {
		locationXTxt.setText(Integer.toString(xc));
	}
	
	public void setLocationYCoord(int yc) {
		locationYTxt.setText(Integer.toString(yc));
	}
	
	public int getLocationYCoord() {
		return Integer.parseInt(locationYTxt.getText());
	}
	
	public int getPage() {
		return Integer.parseInt(pageTxt.getText());
	}
	
	public void setPageText(int p) {
		pageTxt.setText(Integer.toString(p));
	}
	
	public JPanel getLocationPanelPage1() {
		return locationPanelPage1;
	}
	
	public JPanel getLocationPanelPage2() {
		return locationPanelPage2;
	}
	
	public void setLocationPanelPage1(JPanel p) {
		locationPanelPage1 = p;
	}
	
	public void setLocationPanelPage2(JPanel p) {
		locationPanelPage2 = p;
	}
	
	public ArrayList<JButton> getButtons(){
		return buttons;
	}
	
	public JTextField getCatNameField() {
		return catNameTxt;
	}
	
	public void addLocationButtonClickListener(ActionListener al) {
		int size = buttons.size();
		for(int i = 0; i < size; i++) {
			buttons.get(i).addActionListener(al);
		}
	}
	public boolean validateInput() {
		boolean valid = true;
		if(catNameTxt.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "Category Name input is needed!");
			valid = false;
		}
		else if(imageFileNameTxt.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Image File Name input is needed!");
			valid = false;
		}
		else if(gapTxt.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Gap input is needed!");
			valid = false;
		}
		else if(locationXTxt.getText().length() == 0 || locationYTxt.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Choose location for the category please!");
			valid = false;
		}
		
		return valid;
	}
	
	public void addSaveButtonClickListener(ActionListener al) {
		saveBtn.addActionListener(al);
	}
	
	public void addCatNameTxtChangeListener(KeyListener kl) {
		catNameTxt.addKeyListener(kl);
	}
	
	
	public ItemSelectionMenuPanelButton getButton() {
		return  (ItemSelectionMenuPanelButton) button;
	}
	
	public void addImageNameChangeListener(DocumentListener dl) {
		imageFileNameTxt.getDocument().addDocumentListener(dl);
	}
}