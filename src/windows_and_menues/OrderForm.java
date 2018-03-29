package windows_and_menues;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class OrderForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel ticketPanel, menuPanel, bottomPanel;

	public OrderForm() {
		//create panels
		ticketPanel = new OrderFormTicketPanel();
		menuPanel = new OrderFormMenuPanel();
		bottomPanel = new OrderFormBottomPanel();
		
		//set up form
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setTitle("Order Form");
		this.setLayout(null);
		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(ticketPanel);
		this.add(menuPanel);
		this.add(bottomPanel);
		this.setVisible(true);
	}
}
