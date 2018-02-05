package windows_and_menues;

import java.awt.Color;

import javax.swing.JFrame;

import utility.parameters.UtilityParameters;

public class EmptyWindow extends JFrame{

	
	
	public EmptyWindow(){
		
		this.setSize(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT);
		this.setBackground(UtilityParameters.FRAME_COLOR);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
	}
}
