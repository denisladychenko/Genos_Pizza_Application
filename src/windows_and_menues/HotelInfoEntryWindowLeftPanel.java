package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utility.parameters.UtilityParameters;

public class HotelInfoEntryWindowLeftPanel extends JPanel{
	
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

	public HotelInfoEntryWindowLeftPanel(){
		
		Font labelFont = new Font("Areal", 0, 20);
		
		this.setPreferredSize(new Dimension(635, 800));
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		this.setBackground(UtilityParameters.FRAME_COLOR);
		
		pan_1 = new JPanel();
		pan_1.setPreferredSize(new Dimension(630, 300));
		pan_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		pan_1.setBackground(UtilityParameters.FRAME_COLOR);
		pan_1.setBorder(BorderFactory.createSoftBevelBorder(1));
		
		hotelPhoneLbl = new JLabel("Hotel Phone #: ", JLabel.LEFT);
		hotelPhoneLbl.setPreferredSize(new Dimension(180, 70));
		//hotelPhoneLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		hotelPhoneLbl.setFont(labelFont);
		hotelPhoneLbl.setForeground(Color.YELLOW);
		
		hotelNameLbl = new JLabel("Hotel Name: ", JLabel.LEFT);
		hotelNameLbl.setPreferredSize(new Dimension(180, 70));
		//hotelNameLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		hotelNameLbl.setFont(labelFont);
		hotelNameLbl.setForeground(Color.YELLOW);
		
		hotelAddressLbl = new JLabel("Hotel Address: ", JLabel.LEFT);
		hotelAddressLbl.setPreferredSize(new Dimension(180, 70));
		//roomNumLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		hotelAddressLbl.setFont(labelFont);
		hotelAddressLbl.setForeground(Color.YELLOW);
		
		roomNumLbl = new JLabel("Room #: ", JLabel.LEFT);
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
		//hotelPhoneTxtField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//hotelPhoneTxtField.setBorder(BorderFactory.createSoftBevelBorder(1));
		//hotelPhoneTxtField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		hotelPhoneTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		hotelAddressTxtField = new JTextField();
		hotelAddressTxtField.setPreferredSize(new Dimension(420, 60));
		hotelAddressTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		hotelNameTxtField = new JTextField();
		hotelNameTxtField.setPreferredSize(new Dimension(420, 60));
		hotelNameTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		roomNumTxtField = new JTextField();
		roomNumTxtField.setPreferredSize(new Dimension(420, 60));
		roomNumTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		custNameTxtField = new JTextField();
		custNameTxtField.setPreferredSize(new Dimension(420, 60));
		custNameTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		cellphoneTxtField = new JTextField();
		cellphoneTxtField.setPreferredSize(new Dimension(420, 60));
		cellphoneTxtField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
		pan_2 = new JPanel();
		pan_2.setPreferredSize(new Dimension(630, 380));
		pan_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		pan_2.setBackground(UtilityParameters.FRAME_COLOR);
		pan_2.setBorder(BorderFactory.createSoftBevelBorder(1));
		
		newCustomerPan = new JPanel();
		newCustomerPan.setPreferredSize(new Dimension(235, 100));
		newCustomerPan.setLayout(new GridLayout(1, 1));
		newCustomerPan.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		
		ImageIcon labelImg = new ImageIcon(getClass().getResource("new_customer_icon.png"));
		newCustLbl = new JLabel(labelImg);
		newCustLbl.setPreferredSize(new Dimension(235, 100));
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
		
		ImageIcon mapIcon = new ImageIcon(getClass().getResource("map_icon.png"));
		
		mapBtn = new JButton(mapIcon);
		mapBtn.setPreferredSize(new Dimension(90, 90));
		mapBtn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		pan_3.add(mapBtn);
		
		this.add(pan_1);
		this.add(pan_2);
		this.add(pan_3);
	}
}
