package setUpWindowsAndMenus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import utility.parameters.UtilityParameters;

public class SetUpMenuSubMenu extends JFrame{

public SetUpMenuSubMenu() {
		
		JButton addCatBtn = new SetUpMenuButton("add_category.png", 20, 20);
		addCatBtn.addActionListener(new AddCatBtnClick());
		JButton addItemBtn = new SetUpMenuButton("add_item.png", 20, 140);
		
		
		this.add(addCatBtn);
		this.add(addItemBtn);
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Menu Set Up");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		this.setVisible(true);
	}
	
	private class AddCatBtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new AddRemoveCategoryForm();
			
		}
		
	}
}
