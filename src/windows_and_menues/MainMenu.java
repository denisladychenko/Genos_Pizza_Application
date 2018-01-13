package windows_and_menues;

import javax.swing.*;

import java.awt.*;

public class MainMenu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Color FRAME_COLOR = new Color(102,93,153);
	private final int WINDOW_WIDTH = 1100;
	private final int WINDOW_HEIGHT = 1000;
	
	//constructor
	public MainMenu(){
		
		//set up window
		this.setTitle("Main Menu");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		getContentPane().setBackground(FRAME_COLOR);
		
		//add panels to the window
		this.add(new MainMenuLeftPanel());
		this.add(new MainMenuRightPanel());
		this.add(new MainMenuBottomPanel());
		this.setVisible(true);
	}
}
