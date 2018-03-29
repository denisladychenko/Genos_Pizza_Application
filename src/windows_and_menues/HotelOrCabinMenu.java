package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class HotelOrCabinMenu extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	private final int PAN_HEIGHT = 190;
	private final int PAN_WIDTH = 200;
	private final int LABEL_PAN_HEIGHT = 100;
	private final int LABEL_PAN_WIDTH = 100;
	
	
	
	
	
	private JPanel leftButtonPan, labelPan, rightButtonPan;
	private JLabel orLabel;
	private JButton cabinButton, hotelButton;
	
	
	
	public HotelOrCabinMenu(){
		this.setTitle("Hotel Or Cabin");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setSize(600, 300);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 32));
		getContentPane().setBackground(UtilityParameters.POP_UP_MENU_PANEL_COLOR);
		
		ImageIcon cabinImg = new ImageIcon(getClass().getResource("/images/cabin_icon.png"));
		ImageIcon hotelImg = new ImageIcon(getClass().getResource("/images/hotel_icon.jpg"));
		
		leftButtonPan = new JPanel();
		leftButtonPan.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
		leftButtonPan.setLayout(new GridLayout(1, 1));
		//leftButtonPan.setBackground(FRAME_COLOR);
		leftButtonPan.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		hotelButton = new JButton(hotelImg);
		hotelButton.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
		leftButtonPan.add(hotelButton);
		
		
		labelPan = new JPanel();
		labelPan.setPreferredSize(new Dimension(LABEL_PAN_WIDTH, LABEL_PAN_HEIGHT));
		labelPan.setBackground(UtilityParameters.POP_UP_MENU_PANEL_COLOR);
		//labelPan.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		orLabel = new JLabel("OR", JLabel.CENTER);
		
		orLabel.setPreferredSize(new Dimension(LABEL_PAN_WIDTH, LABEL_PAN_HEIGHT));
		
		Font labelFont = new Font("Calibri", Font.BOLD, 48);
		orLabel.setFont(labelFont);
		labelPan.add(orLabel);
		
		rightButtonPan = new JPanel();
		rightButtonPan.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
		rightButtonPan.setLayout(new GridLayout(1, 1));
		//rightButtonPan.setBackground(FRAME_COLOR);
		rightButtonPan.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		cabinButton = new JButton(cabinImg);
		rightButtonPan.add(cabinButton);
		
		this.add(leftButtonPan);
		this.add(labelPan);
		this.add(rightButtonPan);
		
		
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
