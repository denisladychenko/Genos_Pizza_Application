package windows_and_menues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import setUpWindowsAndMenus.CreateModifierController;
import setUpWindowsAndMenus.CreateModifierForm;
import setUpWindowsAndMenus.CreateModifiersListController;
import setUpWindowsAndMenus.CreateModifiersListForm;
import setUpWindowsAndMenus.EditModFormImageSelectorForm;
import setUpWindowsAndMenus.EditModifierForm;
import setUpWindowsAndMenus.ModifierImageSelectorForm;
import setUpWindowsAndMenus.SetUpMainMenu;
import setUpWindowsAndMenus.SetUpMainMenuController;

public class MainMenuController {

	private MainMenu mainMenuForm;

	public MainMenuController(MainMenu mainMenuForm) {
		this.mainMenuForm = mainMenuForm;
		mainMenuForm.setVisible(true);
		mainMenuForm.getBottomPanel().addExitBtnListener(
				new ExitBtnClickListener());
		mainMenuForm.getBottomPanel().addSetUpBtnListener(
				new SetUpBtnClickListener());
	}

	private class ExitBtnClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	private class SetUpBtnClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			SetUpMainMenu setUpMainMenuForm = new SetUpMainMenu();
			SetUpMainMenuController setUpMainMenuController = new SetUpMainMenuController(setUpMainMenuForm);
			setUpMainMenuForm.setVisible(true);
			mainMenuForm.setVisible(false);
			
			
			//AddModifiersListToItemController addModListToItemController = new AddModifiersListToItemController();
			CreateModifiersListController createModListController = new CreateModifiersListController(
					new CreateModifiersListForm(),
					new CreateModifierController(new CreateModifierForm(),
							new ModifierImageSelectorForm(),
							new EditModifierForm(),
							new EditModFormImageSelectorForm()));
			

		}

	}
}
