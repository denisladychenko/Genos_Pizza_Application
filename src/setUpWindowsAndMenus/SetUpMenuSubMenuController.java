package setUpWindowsAndMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetUpMenuSubMenuController {

	private SetUpMenuSubMenu subMenuForm;             //set up main menu form
	
	
	public SetUpMenuSubMenuController(SetUpMenuSubMenu subMenuForm) {
		this.subMenuForm = subMenuForm;
		this.subMenuForm.addAddCatBtnClickListener(new AddCatBtnClick());
		this.subMenuForm.addAddItemBtnClickListener(new AddItemBtnClick());
		this.subMenuForm.addAddModifierBtnClickListener(new AddModifiersBtnClick());
	}
	
	private class AddCatBtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AddRemoveCategoryForm categoryForm = new AddRemoveCategoryForm();
			  new AddRemoveCategoryController(categoryForm,
					  new AddRemoveCategoryModel(categoryForm),
					  new AddRemoveCategoryImageSelectorForm());
			  categoryForm.setVisible(true);
			  subMenuForm.setVisible(false);

		}

	}
	
	private class AddItemBtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			 AddRemoveMenuItemForm addRemoveMenuItemForm = new AddRemoveMenuItemForm();
			  new AddRemoveMenuItemController(addRemoveMenuItemForm, 
					  new EditItemForm(),
					  new ItemImageSelectorForm(),
					  new AddNewItemController(new AddNewItemForm(),addRemoveMenuItemForm));
			  subMenuForm.setVisible(false);

		}

	}
	
	private class AddModifiersBtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CreateModifierForm modForm = new CreateModifierForm();
			new CreateModifierController(modForm,
					new ModifierImageSelectorForm(),
					new EditModifierForm(),
					new EditModFormImageSelectorForm());
			  subMenuForm.setVisible(false);
			  modForm.setVisible(true);
		}

	}
}
