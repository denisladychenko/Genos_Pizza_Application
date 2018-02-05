package windows_and_menues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class MainMenuBottomPanelButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int BUTTON_WIDTH = 270;
	private final int BUTTON_HEIGHT = 125;
	
	public MainMenuBottomPanelButton(String imagePath){
		
		//images for buttons
				ImageIcon buttonImg = new ImageIcon(getClass().getResource(imagePath));
				
				//create buttons
				
				
				
				//set button size
				this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
				
				//set button border style
				this.setBorder(BorderFactory.createRaisedBevelBorder());
				this.setIcon(buttonImg);
				
				
	}
	
}
