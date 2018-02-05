package windows_and_menues;

import java.awt.*;
import java.security.Policy.Parameters;

import javax.swing.*;

import utility.parameters.UtilityParameters;

public class HotelInfoEntryWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pan_1, pan_2, pan_3, pan_1_inside_pan_1;
	private JPanel pan_1_inside_pan_1_inside_pan_1, pan_2_inside_pan_1_inside_pan_1, pan_3_inside_pan_1_inside_pan_1;
	private JPanel pan_2_inside_pan_1, pan_1_inside_pan_2_inside_pan_1, pan_2_inside_pan_2_inside_pan_1, pan_3_inside_pan_2_inside_pan_1;
	private JPanel pan_3_inside_pan_1;
	private JButton mapBtn;
	private JLabel hotelPhoneLbl, hotelNameLbl;
	private JTextField hotelPhoneTxtField, hotelNameTxtField;
	
	public HotelInfoEntryWindow(){
		
		
		
		
		
		
		
		
		
		
		pan_3 = new JPanel();
		pan_3.setPreferredSize(new Dimension(1080, 300));
		pan_3.setLayout(new FlowLayout());
		pan_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.add(new HotelInfoEntryWindowLeftPanel());
		this.add(new HotelInfoEntryWindowRightPanel());
		this.add(pan_3);
		
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setTitle("Hotel Information Entry Form");
		this.setLayout(new FlowLayout());
		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
