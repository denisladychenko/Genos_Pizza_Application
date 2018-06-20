package setUpWindowsAndMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetUpMainMenuController {

	private SetUpMainMenu mainMenuForm;
	
	public SetUpMainMenuController(SetUpMainMenu mainMenuForm) {
		this.mainMenuForm = mainMenuForm;
		this.mainMenuForm.addMenuItemBtnClickListener(new SetUpMenuButtonClick());
	}
	
	 class SetUpMenuButtonClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			SetUpMenuSubMenu setUpMenu = new SetUpMenuSubMenu();
			new SetUpMenuSubMenuController(setUpMenu);
			setUpMenu.setVisible(true);
			mainMenuForm.setVisible(false);
		}
		
	}
}
