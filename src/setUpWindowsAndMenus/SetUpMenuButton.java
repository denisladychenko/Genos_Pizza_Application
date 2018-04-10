package setUpWindowsAndMenus;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SetUpMenuButton extends JButton {
	

	private static final long serialVersionUID = 1L;

	public SetUpMenuButton(String fp,             //image file path
			int xCoord,                    //button x coordinate
			int yCoord ) {                //button y coordinate				
		this.setIcon(new ImageIcon(getClass().getResource("/images/" + fp)));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setBounds(xCoord, yCoord, 250, 100);
	}
}
