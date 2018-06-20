package setUpWindowsAndMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddModifiersListToItemController {

	private CreateModifiersListController modListController;
	private AddModifiersListToItemForm view;
	
	public AddModifiersListToItemController(AddModifiersListToItemForm view, CreateModifiersListController modListController) {
		this.view = view;
		this.modListController = modListController;
		this.view.addNewListBtnListener(new NewListBtnListener());
	}
	
	private class NewListBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CreateModifiersListForm modListForm = new CreateModifiersListForm();
			new CreateModifiersListController(
					modListForm,
					new CreateModifierController(new CreateModifierForm(),
							new ModifierImageSelectorForm(),
							new EditModifierForm(),
							new EditModFormImageSelectorForm()));
			modListForm.setVisible(true);
			view.setVisible(false);
		}
		
		
	}
}
