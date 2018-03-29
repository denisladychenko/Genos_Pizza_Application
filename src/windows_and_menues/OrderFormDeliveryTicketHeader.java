package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class OrderFormDeliveryTicketHeader extends JPanel{

	private JLabel nameLbl;
	public OrderFormDeliveryTicketHeader() {
		this.setPreferredSize(new Dimension(283, 60));
		this.setBorder(new LineBorder(Color.BLACK,1));
		
		
	}
}
