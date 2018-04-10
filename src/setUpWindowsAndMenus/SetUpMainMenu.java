package setUpWindowsAndMenus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import utility.parameters.UtilityParameters;

public class SetUpMainMenu extends JFrame{


	private static final long serialVersionUID = 1L;

	public SetUpMainMenu() {
		
		JButton menuItemBtn = new SetUpMenuButton("utensils150x100.png", 20, 20);
		menuItemBtn.addActionListener(new SetUpMenuButtonClick());
		
		
		this.add(menuItemBtn);
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Set Up Menu");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		this.setVisible(true);
	}
	
	private class SetUpMenuButtonClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new SetUpMenuSubMenu();
			
		}
		
	}
}
