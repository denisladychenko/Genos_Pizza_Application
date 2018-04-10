package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utility.parameters.UtilityParameters;

public class InfoEntryWindowLeftPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel pan_1, pan_2, pan_3;
	private JPanel newCustomerPan, hotelPhonePan, hotelNamePan;
	private JPanel hotelAddressPan, roomNumPan, custNamePan, cellPan;
	private JLabel hotelPhoneLbl, hotelNameLbl;
	private JLabel hotelAddressLbl, roomNumLbl, custNameLbl, cellphoneLbl;
	private JTextField hotelAddressTxtField, roomNumTxtField, custNameTxtField, cellphoneTxtField;
	private JTextField hotelPhoneTxtField, hotelNameTxtField;
	private JButton mapBtn;
	private JLabel newCustLbl;
	private JLabel[] custLabelsArr = new JLabel[2];         //collection of customer status labels

	public InfoEntryWindowLeftPanel(String lbl_1, String lbl_2, String lbl_3, String lbl_4){
		
		Font labelFont = new Font("Areal", 0, 20);
		
		this.setPreferredSize(new Dimension(635, 800));
		this.setLayout(new FlowLayout());
		this.setBounds(5, 5, 635, 800);
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		this.setBackground(UtilityParameters.FRAME_COLOR);
		
		pan_1 = new JPanel();
		pan_1.setPreferredSize(new Dimension(630, 300));
		pan_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		pan_1.setBackground(UtilityParameters.FRAME_COLOR);
		pan_1.setBorder(BorderFactory.createSoftBevelBorder(1));
		
		hotelPhoneLbl = new JLabel(lbl_1, JLabel.LEFT);
		hotelPhoneLbl.setPreferredSize(new Dimension(180, 70));
		hotelPhoneLbl.setFont(labelFont);
		hotelPhoneLbl.setForeground(Color.YELLOW);
		
		hotelNameLbl = new JLabel(lbl_2, JLabel.LEFT);
		hotelNameLbl.setPreferredSize(new Dimension(180, 70));
		//hotelNameLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		hotelNameLbl.setFont(labelFont);
		hotelNameLbl.setForeground(Color.YELLOW);
		
		hotelAddressLbl = new JLabel(lbl_3, JLabel.LEFT);
		hotelAddressLbl.setPreferredSize(new Dimension(180, 70));
		//roomNumLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		hotelAddressLbl.setFont(labelFont);
		hotelAddressLbl.setForeground(Color.YELLOW);
		
		roomNumLbl = new JLabel(lbl_4, JLabel.LEFT);
		roomNumLbl.setPreferredSize(new Dimension(180, 70));
		//roomNumLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		roomNumLbl.setFont(labelFont);
		roomNumLbl.setForeground(Color.YELLOW);
		custNameLbl = new JLabel("Customer Name: ", JLabel.LEFT);
		custNameLbl.setPreferredSize(new Dimension(180, 70));
		//custNameLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		custNameLbl.setFont(labelFont);
		custNameLbl.setForeground(Color.YELLOW);
		cellphoneLbl = new JLabel("CellPhone #: ", JLabel.LEFT);
		cellphoneLbl.setPreferredSize(new Dimension(180, 70));
		
		cellphoneLbl.setFont(labelFont);
		cellphoneLbl.setForeground(Color.YELLOW);
		
		
		hotelPhoneTxtField = new JTextField();
		hotelPhoneTxtField.setPreferredSize(new Dimension(420, 60));
		hotelPhoneTxtField.setFont(UtilityParameters.DATA_ENTRY_FONT);
		hotelPhoneTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		//register key listener event for the text field
		hotelPhoneTxtField.addKeyListener(new KeyAdapter() {
						    public void keyTyped(KeyEvent evt) {
						    	//this sets the limit for number of symbols user can type into the text field
						        if(hotelPhoneTxtField.getText().length() >= 10 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
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
		
		
		hotelAddressTxtField = new JTextField();
		hotelAddressTxtField.setPreferredSize(new Dimension(420, 60));
		hotelAddressTxtField.setFont(UtilityParameters.DATA_ENTRY_FONT);
		hotelAddressTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		hotelNameTxtField = new JTextField();
		hotelNameTxtField.setPreferredSize(new Dimension(420, 60));
		hotelNameTxtField.setFont(UtilityParameters.DATA_ENTRY_FONT);
		hotelNameTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		roomNumTxtField = new JTextField();
		roomNumTxtField.setPreferredSize(new Dimension(420, 60));
		roomNumTxtField.setFont(UtilityParameters.DATA_ENTRY_FONT);
		roomNumTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		custNameTxtField = new JTextField();
		custNameTxtField.setPreferredSize(new Dimension(420, 60));
		custNameTxtField.setFont(UtilityParameters.DATA_ENTRY_FONT);
		custNameTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		cellphoneTxtField = new JTextField();
		cellphoneTxtField.setPreferredSize(new Dimension(420, 60));
		cellphoneTxtField.setFont(UtilityParameters.DATA_ENTRY_FONT);
		cellphoneTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		//register key listener event for the text field
		cellphoneTxtField.addKeyListener(new KeyAdapter() {
				    public void keyTyped(KeyEvent evt) {
				    	//this sets the limit for number of symbols user can type into the text field
				        if(cellphoneTxtField.getText().length() >= 10 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
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
		
		
		pan_2 = new JPanel();
		pan_2.setPreferredSize(new Dimension(630, 380));
		pan_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		pan_2.setBackground(UtilityParameters.FRAME_COLOR);
		pan_2.setBorder(BorderFactory.createSoftBevelBorder(1));
		
		newCustomerPan = new JPanel();
		newCustomerPan.setPreferredSize(new Dimension(235, 100));
		newCustomerPan.setLayout(null);
		newCustomerPan.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		
		ImageIcon labelImg = new ImageIcon(getClass().getResource("/images/new_customer_icon.png"));
		newCustLbl = new JLabel(labelImg);
		newCustLbl.setBounds(0, 0, 235, 100);
		newCustLbl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		newCustLbl.setVisible(false);
		
		ImageIcon custInfoLabelImg = new ImageIcon(getClass().getResource("/images/cust_info_icon.png"));
		JLabel custInfoLbl = new JLabel(custInfoLabelImg);
		custInfoLbl.setBounds(0, 0, 235, 100);
		custInfoLbl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		//custInfoLbl.setVisible(false);
		
		//add labels to the collection
		custLabelsArr[1] = custInfoLbl;
		custLabelsArr[0] = newCustLbl;
		//add labels to the panel
		newCustomerPan.add(custInfoLbl);
		newCustomerPan.add(newCustLbl);
		
		
		hotelPhonePan = new JPanel();
		hotelPhonePan.setPreferredSize(new Dimension(620, 80));
		hotelPhonePan.setLayout(new FlowLayout());
		hotelPhonePan.setBackground(UtilityParameters.FRAME_COLOR);
		//hotelPhonePan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		hotelPhonePan.add(hotelPhoneLbl);
		hotelPhonePan.add(hotelPhoneTxtField);
		
		hotelNamePan = new JPanel();
		hotelNamePan.setPreferredSize(new Dimension(620, 80));
		hotelNamePan.setLayout(new FlowLayout());
		hotelNamePan.setBackground(UtilityParameters.FRAME_COLOR);
		//hotelNamePan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		hotelNamePan.add(hotelNameLbl);
		hotelNamePan.add(hotelNameTxtField);
		
		hotelAddressPan = new JPanel();
		hotelAddressPan.setPreferredSize(new Dimension(620, 80));
		hotelAddressPan.setLayout(new FlowLayout());
		hotelAddressPan.setBackground(UtilityParameters.FRAME_COLOR);
		
		hotelAddressPan.add(hotelAddressLbl);
		hotelAddressPan.add(hotelAddressTxtField);
		
		roomNumPan = new JPanel();
		roomNumPan.setPreferredSize(new Dimension(620, 80));
		roomNumPan.setLayout(new FlowLayout());
		roomNumPan.setBackground(UtilityParameters.FRAME_COLOR);
		//roomNumPan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		roomNumPan.add(roomNumLbl);
		roomNumPan.add(roomNumTxtField);
		
		custNamePan = new JPanel();
		custNamePan.setPreferredSize(new Dimension(620, 80));
		custNamePan.setLayout(new FlowLayout());
		custNamePan.setBackground(UtilityParameters.FRAME_COLOR);
		//custNamePan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		
		custNamePan.add(custNameLbl);
		custNamePan.add(custNameTxtField);
		
		cellPan = new JPanel();
		cellPan.setPreferredSize(new Dimension(620, 80));
		cellPan.setLayout(new FlowLayout());
		cellPan.setBackground(UtilityParameters.FRAME_COLOR);
		//cellPan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		cellPan.add(cellphoneLbl);
		cellPan.add(cellphoneTxtField);
		
		pan_1.add(newCustomerPan);
		pan_1.add(hotelPhonePan);
		pan_1.add(hotelNamePan);
		
		pan_2.add(hotelAddressPan);
		pan_2.add(roomNumPan);
		pan_2.add(custNamePan);
		pan_2.add(cellPan);
		
		
		
		
		pan_3 = new JPanel();
		pan_3.setPreferredSize(new Dimension(620, 100));
		pan_3.setLayout(new FlowLayout());
		pan_3.setBackground(UtilityParameters.FRAME_COLOR);
		//pan_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		ImageIcon mapIcon = new ImageIcon(getClass().getResource("/images/map_icon.png"));
		
		mapBtn = new JButton(mapIcon);
		mapBtn.setPreferredSize(new Dimension(90, 90));
		mapBtn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		pan_3.add(mapBtn);
		
		this.add(pan_1);
		this.add(pan_2);
		this.add(pan_3);
	}
}
