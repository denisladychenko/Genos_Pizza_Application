package windows_and_menues;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class MainMenuBottomPanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int PAN_WIDTH = 1070;
	private final int PAN_HEIGHT = 165;
	private final int PAN_HORIZONTAL_GAP = 75;
	private final int PAN_VERTICAL_GAP = 20;
	
	
	private JButton exitBtn, reportsBtn, setUpBtn, dimmedExitBtn, dimmedReportsBtn, dimmedSetUpBtn;
	
	
	//constructor
	public MainMenuBottomPanel(){
		
		//create and set up panel
		this.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, PAN_HORIZONTAL_GAP, PAN_VERTICAL_GAP));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		this.setBackground(UtilityParameters.PANELS_COLOR);
		
		exitBtn = new MainMenuBottomPanelButton("flag_icon.png");
		reportsBtn = new MainMenuBottomPanelButton("graph_icon.png");
		setUpBtn = new MainMenuBottomPanelButton("gear_icon.png");
		
		exitBtn.addActionListener(new BtnListener());
		reportsBtn.addActionListener(new BtnListener());
		setUpBtn.addActionListener(new BtnListener());
		
		
		//add buttons to the panel
		this.add(exitBtn);
		this.add(reportsBtn);
		this.add(setUpBtn);
	}
	//constructor
		public MainMenuBottomPanel(String imagePath){
			
			//create and set up panel
			this.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
			this.setLayout(new FlowLayout(FlowLayout.CENTER, PAN_HORIZONTAL_GAP, PAN_VERTICAL_GAP));
			this.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
			this.setBackground(UtilityParameters.PANELS_COLOR);
			
			exitBtn = new MainMenuBottomPanelButton("flag_icon.png");
			reportsBtn = new MainMenuBottomPanelButton("graph_icon.png");
			dimmedSetUpBtn = new MainMenuBottomPanelButton(imagePath);
			
			exitBtn.addActionListener(new BtnListener());
			reportsBtn.addActionListener(new BtnListener());
			dimmedSetUpBtn.addActionListener(new BtnListener());
			
			
			//add buttons to the panel
			this.add(exitBtn);
			this.add(reportsBtn);
			this.add(dimmedSetUpBtn);
		}
		//constructor
		public MainMenuBottomPanel(String imagePath_1, String imagePath_2, String imagePath_3){
			
			//create and set up panel
			this.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
			this.setLayout(new FlowLayout(FlowLayout.CENTER, PAN_HORIZONTAL_GAP, PAN_VERTICAL_GAP));
			this.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
			this.setBackground(UtilityParameters.PANELS_COLOR);
			
			dimmedExitBtn = new MainMenuBottomPanelButton(imagePath_1);
			dimmedReportsBtn = new MainMenuBottomPanelButton(imagePath_2);
			dimmedSetUpBtn = new MainMenuBottomPanelButton(imagePath_3);
			
			dimmedExitBtn.addActionListener(new BtnListener());
			dimmedReportsBtn.addActionListener(new BtnListener());
			dimmedSetUpBtn.addActionListener(new BtnListener());
			
			//add buttons to the panel
			this.add(dimmedExitBtn);
			this.add(dimmedReportsBtn);
			this.add(dimmedSetUpBtn);
		}
		private class BtnListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == exitBtn)
					System.exit(0);
				
			}
			
		}
}
