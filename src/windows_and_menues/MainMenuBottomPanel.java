package windows_and_menues;

import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;



import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuBottomPanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Color PANELS_COLOR = new Color(120,100,255);
	private final int PAN_WIDTH = 1070;
	private final int PAN_HEIGHT = 165;
	private final int PAN_HORIZONTAL_GAP = 75;
	private final int PAN_VERTICAL_GAP = 20;
	private final int BUTTON_WIDTH = 270;
	private final int BUTTON_HEIGHT = 125;
	
	private JButton exitBtn, reportsBtn, setUpBtn;
	private ImageIcon exitButtonImg, reportsButtonImg, setUpButtonImg;
	
	//constructor
	public MainMenuBottomPanel(){
		
		//create and set up panel
		this.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, PAN_HORIZONTAL_GAP, PAN_VERTICAL_GAP));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		this.setBackground(PANELS_COLOR);
		
		//images for buttons
		exitButtonImg = new ImageIcon(getClass().getResource("flag_icon.png"));
		reportsButtonImg = new ImageIcon(getClass().getResource("graph_icon.png"));
		setUpButtonImg = new ImageIcon(getClass().getResource("gear_icon.png"));
		
		//create buttons
		exitBtn = new JButton(exitButtonImg);
		reportsBtn = new JButton(reportsButtonImg);
		setUpBtn = new JButton(setUpButtonImg);
		
		//set button size
		exitBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		reportsBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		setUpBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		//add buttons to the panel
		this.add(exitBtn);
		this.add(reportsBtn);
		this.add(setUpBtn);
	}
}
