package windows_and_menues;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class ItemSelectionMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;


	public ItemSelectionMenuPanel(JButton... button) {
		//listOfButtons = new ArrayList<JButton>();

		for (int i = 0; i < button.length; i++) {
			this.add(button[i]);
		}

		
		this.setBounds(315, 5, 775, 800);
		this.setLayout(null);
		this.setBackground(UtilityParameters.FRAME_COLOR);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}

}
