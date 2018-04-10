package setUpWindowsAndMenus;

import java.awt.Dimension;

import javax.swing.JFrame;

import utility.parameters.UtilityParameters;

public class AddRemoveCategoryForm extends JFrame{

	public AddRemoveCategoryForm() {
		
		
		
		
		
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Add/Remove Category");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		this.setVisible(true);
	}
}
