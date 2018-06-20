package setUpWindowsAndMenus;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import utility.parameters.UtilityParameters;

public class SetUpMenuSubMenu extends JFrame {

	private JButton addModifierBtn;
	private JButton addCatBtn;
	private JButton addItemBtn;

	public SetUpMenuSubMenu() {

		addCatBtn = new SetUpMenuButton("add_category.png", 20, 20);
		addItemBtn = new SetUpMenuButton("add_item.png", 20, 140);
		addModifierBtn = new SetUpMenuButton("add_modifier.png", 20, 260);

		this.add(addCatBtn);
		this.add(addItemBtn);
		this.add(addModifierBtn);
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH,
				UtilityParameters.WINDOW_HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("Menu Set Up");
		getContentPane().setBackground(UtilityParameters.SET_UP_MENU_COLOR);
	}

	public void addAddCatBtnClickListener(ActionListener al) {
		addCatBtn.addActionListener(al);
	}
	public void addAddItemBtnClickListener(ActionListener al) {
		addItemBtn.addActionListener(al);
	}
	public void addAddModifierBtnClickListener(ActionListener al) {
		addModifierBtn.addActionListener(al);
	}
	
	
}
