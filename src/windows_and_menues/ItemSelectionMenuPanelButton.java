package windows_and_menues;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ItemSelectionMenuPanelButton extends JButton {
	
	private static final long serialVersionUID = 1L;

	public ItemSelectionMenuPanelButton(String fp,         //image file path
										String text,        //text on the button
										int gap,            //gap between icon and text
										int xCoord,         //button x coordinate
										int yCoord,         //button y coordinate
										int w,              //button width
										int h) {            //button height
		this.setIcon(new ImageIcon(getClass().getResource("/menu_item_images/" + fp)));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setText("<html><center>" + text + "</center></html>");  //html helps to wrap up and center the text in the button
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setIconTextGap(gap);
		
		this.setFont(new Font("Segoe UI", Font.BOLD, 22));
		this.setForeground(Color.BLACK);
		this.setBounds(xCoord, yCoord, w, h);
	}
}
