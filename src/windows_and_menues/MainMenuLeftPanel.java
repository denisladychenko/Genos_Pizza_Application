package windows_and_menues;

import genosPizza.GenosPizza;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class MainMenuLeftPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int HORIZONTAL_GAP = 10;
	private final int VERTICAL_GAP = 35;
	private final int PAN_WIDTH = 350;
	private final int PAN_HEIGHT = 770;
	private final int PAN_1_WIDTH = 320;
	private final int PAN_1_HEIGHT = 220;
	private final int PAN_2_HORIZONTAL_GAP = 20;
	private final int PAN_2_VERTICAL_GAP = 54;
	private final int PAN_2_WIDTH = 250;
	private final int PAN_2_HEIGHT = 450;
	private final int BUTTON_WIDTH = 170;
	private final int BUTTON_HEIGHT = 145;
	
	private JPanel pan_1, pan_2;
	private JButton genosBtn;
	private JButton phoneOrderBtn, driverMgmtBtn;
	
	
	//constructor
	public MainMenuLeftPanel(){
		
		
		//set up this panel
		this.setPreferredSize(new Dimension(PAN_WIDTH,PAN_HEIGHT));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
		this.setBackground(UtilityParameters.FRAME_COLOR);
		//set pan_1 of this panel
		pan_1 = new JPanel();
		pan_1.setLayout(new GridLayout(1,0));
		pan_1.setBorder(BorderFactory.createEtchedBorder(1));
		pan_1.setPreferredSize(new Dimension(PAN_1_WIDTH, PAN_1_HEIGHT));
		
		Icon genosButtonImg = new ImageIcon(getClass().getResource("gen_pizza470x260.jpg"));
		Icon phoneOrderButtonImg = new ImageIcon(getClass().getResource("phone_icon.png"));
		Icon deliveryMgmtButtonImg = new ImageIcon(getClass().getResource("car_icon.png"));
		
		
		//create genos button and set it up
		genosBtn = new JButton(genosButtonImg);
		genosBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		pan_1.add(genosBtn);
		
		//add pan_1 to this panel
		this.add(pan_1);
		
		//create and set up pan_2
		pan_2 = new JPanel();
		pan_2.setLayout(new FlowLayout(FlowLayout.CENTER, PAN_2_HORIZONTAL_GAP, PAN_2_VERTICAL_GAP));
		pan_2.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		pan_2.setPreferredSize(new Dimension(PAN_2_WIDTH, PAN_2_HEIGHT));
		pan_2.setBackground(UtilityParameters.PANELS_COLOR);
		
		//create and set up buttons for pan_2 of this panel
		phoneOrderBtn = new JButton(phoneOrderButtonImg);
		phoneOrderBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		phoneOrderBtn.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
		phoneOrderBtn.addActionListener(new PhoneOrderBtnListener());
		driverMgmtBtn = new JButton(deliveryMgmtButtonImg);
		driverMgmtBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		driverMgmtBtn.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
		
		//add buttons to the pan_2
		pan_2.add(phoneOrderBtn);
		pan_2.add(driverMgmtBtn);
		
		//add pan_2 to this panel
		this.add(pan_2);
		
		
		
	}
	
	private class PhoneOrderBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//GenosPizza.getMainFrame().dispose();
			//new EmptyWindow();
			new HotelOrCabinMenu();
			
		}
		
	}
	
}
