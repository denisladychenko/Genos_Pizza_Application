package windows_and_menues;

import javax.swing.*;

import utility.parameters.UtilityParameters;

import java.awt.*;

public class MainMenu extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	
	//constructor
	public MainMenu(){
		
		
		
		//set up window
		this.setTitle("Main Menu");
		this.setSize(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
		
		//add panels to the window
		this.add(new MainMenuLeftPanel());
		this.add(new MainMenuRightPanel());
		this.add(new MainMenuBottomPanel());
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		
		this.setVisible(true);
	}
	
	//one arg constructor
	public MainMenu(String imagePath){

		//set up window
				this.setTitle("Main Menu");
				this.setSize(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT);
				this.setLocationRelativeTo(null);
				this.setResizable(false);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setLayout(new FlowLayout(FlowLayout.LEFT));
				
				getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
				
				//add panels to the window
				this.add(new MainMenuLeftPanel());
				this.add(new MainMenuRightPanel());
				this.add(new MainMenuBottomPanel(imagePath));
				//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				//this.setUndecorated(true);
				this.setVisible(true);
	}
	//3 arg constructor
	public MainMenu(String imagePath_1, String imagePath_2, String imagePath_3){

		//set up window
				this.setTitle("Main Menu");
				this.setSize(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT);
				this.setLocationRelativeTo(null);
				this.setResizable(false);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setLayout(new FlowLayout(FlowLayout.LEFT));
				
				getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
				
				//add panels to the window
				this.add(new MainMenuLeftPanel());
				this.add(new MainMenuRightPanel());
				this.add(new MainMenuBottomPanel(imagePath_1, imagePath_2, imagePath_3));
				//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				//this.setUndecorated(true);
				this.setVisible(true);
	}
	
}
