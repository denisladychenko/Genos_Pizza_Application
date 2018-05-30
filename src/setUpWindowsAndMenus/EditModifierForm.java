package setUpWindowsAndMenus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import javax.swing.event.DocumentListener;

import pizzeria.exception_classes.PriceOutOfRange;
import utility.parameters.UtilityParameters;
import windows_and_menues.ItemSelectionMenuPanelButton;

public class EditModifierForm extends JFrame{

private static final long serialVersionUID = 1L;
	
	private ItemSelectionMenuPanelButton button;           // sample button
	private JTextField modNameTxt;                    //input field for modifier name
	private JTextField imageFileNameTxt;              //text field for image file input
	private JTextField gapTxt;                       //gap between text and image
	private JTextField priceTxt;                      //text field for the modifier price
	private JTextField nameOnTicketTxt;               //text field for the modifier name on ticket
	private JButton imageLookUpBtn;                   //button for image look up
	private JButton deleteBtn;                          //button to delete modifier
	private JButton saveBtn,                             //button to save modifier in the database
					finishedBtn;                         //finish and exit button
	private JScrollPane modListPane;                     //scroll pane for the list of modifiers
	private JList<DefaultListModel> modListLst;                    //list to hold modifier items
	private String selectedModName;                      //selected modifier
	private DefaultListModel listModel;
	
	public EditModifierForm(){
		
		button = new ItemSelectionMenuPanelButton("empty_img.png","",0, 80,50, 155, 155);
		
		loadModifiersArray();                  //load modifiers into array list
		
		
		JLabel titleLbl = new JLabel("Existing Modifiers ");
		titleLbl.setBounds(590, 15, 300, 35);
		titleLbl.setFont(new Font("Areal", Font.BOLD, 28));
		titleLbl.setForeground(Color.YELLOW);
		
		modListLst = new JList<DefaultListModel>(listModel);
		modListLst.setFont(new Font("Segoe UI", Font.BOLD, 22));
	
		
		
		modListPane = new JScrollPane(modListLst);
		modListPane.setBounds(500,60, 400, 700);
		
		JLabel nameLbl = new JLabel("Modifier Name");
		nameLbl.setBounds(10, 245, 280, 30);
		nameLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		nameLbl.setForeground(Color.YELLOW);
		
		JLabel maxNumMessageLbl = new JLabel("(18 symbols max)");
		maxNumMessageLbl.setBounds(90, 316, 280, 15);
		maxNumMessageLbl.setForeground(Color.YELLOW);
		modNameTxt = new JTextField();
		modNameTxt.setBounds(10, 280, 280, 35);
		modNameTxt.setFont(new Font("Areal", Font.BOLD, 24));
		modNameTxt.setHighlighter(null);
		modNameTxt.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		       if(modNameTxt.getText().length() >= 18 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		        	
		    	   modNameTxt.getToolkit().beep();
		            evt.consume();
		        }
		       
	
		       if(modNameTxt.getCaretPosition() < modNameTxt.getText().length()) {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			    		   modNameTxt.getText().length() >= 18) {
			    	   button.setButtonText(modNameTxt.getText());
			       }else {
			    	   //if caret placed in somewhere inside the text then find that position
			    	   //and insert symbol there
		        	StringBuilder txt = new StringBuilder(modNameTxt.getText());
		        	txt.insert(modNameTxt.getCaretPosition(), evt.getKeyChar());
		        	button.setButtonText(txt.toString());
			       }
		        
		       }else {
		    	 //if backspace or delete buttons typed do not add there symbols in the text
			       if(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||evt.getKeyChar() == KeyEvent.VK_DELETE ||
			    		   modNameTxt.getText().length() >= 18) {
			    	   button.setButtonText(modNameTxt.getText());
			       }else {
		        		//if characters other than backspace or delete are typed
				        //include them in the text 
			    	   button.setButtonText(modNameTxt.getText() + evt.getKeyChar());
		       		}
		        }
		        
		       button.revalidate();
	
			}
		
		});
		
		JLabel imageFileLbl = new JLabel("Image File Name");
		imageFileLbl.setBounds(10, 355, 280, 30);
		imageFileLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		imageFileLbl.setForeground(Color.YELLOW);
		
	
		imageFileNameTxt = new JTextField();
		imageFileNameTxt.setBounds(10, 390, 250, 35);
		imageFileNameTxt.setFont(new Font("Areal", Font.BOLD, 24));
		imageFileNameTxt.setEditable(false);
		
	
		ImageIcon lookUpImg = new ImageIcon(getClass().getResource("/images/mag_glass.png"));
		imageLookUpBtn = new JButton(lookUpImg);
		imageLookUpBtn.setBounds(265, 390, 40, 35);
		
		JLabel gapLbl = new JLabel("Gap");
		gapLbl.setBounds(10, 475, 280, 30);
		gapLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		gapLbl.setForeground(Color.YELLOW);
		
		JLabel messageLbl = new JLabel("(gap between image and text)");
		messageLbl.setBounds(70, 510, 280, 15);
		messageLbl.setForeground(Color.YELLOW);
		
		gapTxt = new JTextField();
		gapTxt.setBounds(70, 475, 170, 35);
		gapTxt.setFont(new Font("Areal", Font.BOLD, 24));
		gapTxt.setText("0");                  //set gap to 0(appropriate in most cases)
		gapTxt.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		        if(gapTxt.getText().length() >= 2 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
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
		
		JLabel priceLbl = new JLabel("Price");
		priceLbl.setBounds(10, 540, 280, 30);
		priceLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		priceLbl.setForeground(Color.YELLOW);
		
		priceTxt = new JTextField();
		priceTxt.setBounds(10, 575, 170, 35);
		priceTxt.setFont(new Font("Areal", Font.BOLD, 24));
		priceTxt.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		        if(priceTxt.getText().length() >= 6 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		            getToolkit().beep();
		            evt.consume();
		         }
		        //Prevents the user from entering more than one decimal point symbol
		        if(priceTxt.getText().indexOf(".") != -1 && (int)evt.getKeyChar() == 46){
		        	getToolkit().beep();
		        	evt.consume();
		        }
		        //this only allows digits and decimal point symbol to be entered
		        //48 is the ASCI code for '0' and 57 is the ASCI code for '9'
		        if(((int)evt.getKeyChar() < 48 || (int)evt.getKeyChar() > 57) && 
		        		(!(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE ||
		        		(int)evt.getKeyChar() == 46))) {
		        	getToolkit().beep();
		        	evt.consume();
		        }
		        
		     }
		});
		
		JLabel nameOnTicketLbl = new JLabel("Name on Ticket");
		nameOnTicketLbl.setBounds(10, 660, 280, 30);
		nameOnTicketLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		nameOnTicketLbl.setForeground(Color.YELLOW);
		
		nameOnTicketTxt = new JTextField();
		nameOnTicketTxt.setBounds(10, 690, 280, 35);
		nameOnTicketTxt.setFont(new Font("Areal", Font.BOLD, 24));
		nameOnTicketTxt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		       if(nameOnTicketTxt.getText().length() >= 18 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		        	
		    	   nameOnTicketTxt.getToolkit().beep();
		            evt.consume();
		        }
	
			}
		
		});
		
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
		
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(580, 23, 207, 120);
		deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		finishedBtn = new JButton("Finished");
		finishedBtn.setBounds(863, 23, 207, 120);
		finishedBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		
		buttonPanel.add(saveBtn);
		buttonPanel.add(finishedBtn);
		buttonPanel.add(deleteBtn);
		
		this.add(gapLbl);
		this.add(gapTxt);
		this.add(messageLbl);
		this.add(titleLbl);
		this.add(modListPane);
		this.add(maxNumMessageLbl);
		this.add(buttonPanel);
		this.add(nameOnTicketLbl);
		this.add(nameOnTicketTxt);
		this.add(priceLbl);
		this.add(priceTxt);
		this.add(imageLookUpBtn);
		this.add(imageFileNameTxt);
		this.add(imageFileLbl);
		this.add(modNameTxt);
		this.add(nameLbl);
		this.add(button);
		this.setSize(new Dimension(UtilityParameters.SET_UP_WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Edit Modifier Form");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
	}
	
	/**
	 *Gets sample button
	 *@return button The sample button converted to ItemSelectionMenuPanelButton
	 */
	public ItemSelectionMenuPanelButton getButton() {
		return  (ItemSelectionMenuPanelButton) button;
	}
	
	/**
	 *Gets image file name TextField 
	 *@return imageFileNameTxt The image file name text field
	 */
	public JTextField getImageFileNameTextField() {
		return imageFileNameTxt;
	}
	
	public JTextField getModNameTextField() {
		return modNameTxt;
	}
	public JTextField getGap() {
		return gapTxt;
	}
	public JTextField getPrice() {
		return priceTxt;
	}
	public JTextField getNameOnTicket() {
		return nameOnTicketTxt;
	}
	public void setSelectedModName(String modName) {
		selectedModName = modName;
	}
	public String getSelectedModName() {
		return selectedModName;
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
	 *Validates user input
	 *@return valid Returns valid if input is valid
	 * @throws PriceOutOfRange 
	 */
	public boolean validateInput() throws PriceOutOfRange {
		boolean valid = true;
		if(modNameTxt.getText().length() == 0){
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
		
		else if(priceTxt.getText().length() == 0 ) {
			JOptionPane.showMessageDialog(null, "Enter item's price!");
			valid = false;
		}
		else if(Double.parseDouble(priceTxt.getText()) < 0 || Double.parseDouble(priceTxt.getText()) > 999){
				throw new PriceOutOfRange();
		}
		else if(nameOnTicketTxt.getText().length() == 0 ) {
			JOptionPane.showMessageDialog(null, "Name on ticket input is needed!");
			valid = false;
		}
		return valid;
	}
	
	public void loadModifiersArray() {
		listModel = new DefaultListModel<String>();
		Connection con = getDatabaseConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String statement;
		
		try {
			
			statement = "SELECT name_on_button FROM modifiers "
					+ "ORDER BY name_on_button ASC";
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				listModel.addElement(rs.getString("name_on_button"));
			}		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *Change listener for the image name field
	 *@param dl The document listener
	 */
	public void addImageNameChangeListener(DocumentListener dl) {
		imageFileNameTxt.getDocument().addDocumentListener(dl);
	}
	public void addSaveButtonClickListener(ActionListener al) {
		saveBtn.addActionListener(al);
	}
}
