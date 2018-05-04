package windows_and_menues;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class OrderForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel ticketPanel, menuPanel, bottomPanel;
	private JPanel pizzaSelectionPanelPage1, pizzaSelectionPanelPage2, toppingsPanel;
	private static ArrayList<JPanel> listOfPanels; // list of interchangeable
													// panels on the right

	public OrderForm() {
		// collection of interchangeable panels on the right
		listOfPanels = new ArrayList<JPanel>();
		// create panels
		ticketPanel = new OrderFormTicketPanel();
		menuPanel = new OrderFormMenuPanel();           // start up right panel
		bottomPanel = new OrderFormBottomPanel();
		
		//panel to select pizza (page 1)
		pizzaSelectionPanelPage1 = new ItemSelectionMenuPanel(
				new ItemSelectionMenuPanelButton("med_cheese_pizza.png",                    //medium cheese button
						"Med Cheese", 20, 0, 0, 155, 155),
				new ItemSelectionMenuPanelButton("lg_cheese_pizza.png",                    //large cheese button
						"Large Cheese", 15, 311, 0, 155, 155),
				new ItemSelectionMenuPanelButton("exLg_cheese_pizza.png",                  //extra large cheese button
						"Ex. Lg. Cheese", 15, 621, 0, 155, 155),
				new ItemSelectionMenuPanelButton("spec.png", "Med Special", 20,            //medium special button
						0, 156, 155, 155),
				new ItemSelectionMenuPanelButton("spec.png", "Large Special", 20,          //large special button
						311, 156, 155, 155),
				new ItemSelectionMenuPanelButton("spec.png", "Ex. Lg. Special", 20,           //Extra large special button
						621, 156, 155, 155),
				new ItemSelectionMenuPanelButton("meat.png", "Med Meat", 20,            //medium meat button
						0, 311, 155, 155),
				new ItemSelectionMenuPanelButton("meat.png", "Large Meat", 20,           //large meat button
						311, 311, 155, 155),
				new ItemSelectionMenuPanelButton("meat.png", "Ex. Lg. Meat", 20,           //extra large meat button
						621, 311, 155, 155),
				new ItemSelectionMenuPanelButton("veggie.png", "Med Veggie", 20,           //medium veggie button
						0, 466, 155, 155),
				new ItemSelectionMenuPanelButton("veggie.png", "Large Veggie", 20,           //large veggie button
						311, 466, 155, 155),
				new ItemSelectionMenuPanelButton("veggie.png", "Ex. Lg. Veggie", 20,           //extra large veggie button
						621, 466, 155, 155),
				new ItemSelectionMenuPanelButton("bbq.png", "Med BBQ", 10,                   //medium bbq button
						0, 621, 155, 155),
				new ItemSelectionMenuPanelButton("bbq.png", "Large BBQ", 10,             //large bbq button
						311, 621, 155, 155),
				new ItemSelectionMenuPanelButton("bbq.png", "Ex. Lg. BBQ", 10,           //extra large bbq button
						621, 621, 155, 155));

		//panel to select pizza (page 2)
		pizzaSelectionPanelPage2 = new ItemSelectionMenuPanel(
				new ItemSelectionMenuPanelButton("steak.png", "Med Steak", 20,             //medium steak button
						0, 0, 155, 155),
				new ItemSelectionMenuPanelButton("steak.png", "Large Steak", 20,             //large steak button
						311, 0, 155, 155),
				new ItemSelectionMenuPanelButton("steak.png", "Ex. Lg. Steak", 20,             //extra large steak button
						621, 0, 155, 155),
				new ItemSelectionMenuPanelButton("pepperoni.png", 
						"Med Loaded Pepperoni", -8,         //medium pepperoni button
						0, 156, 155, 155),
				new ItemSelectionMenuPanelButton("pepperoni.png", 
						"Large Loaded Pepperoni", -8,        //large  pepperoni button
						311, 156, 155, 155),
				new ItemSelectionMenuPanelButton("pepperoni.png", 
						"Ex.Lg. Loaded Pepperonmm", -8,      //extra large  pepperoni button
						621, 156, 155, 155));
		
		//toppings panel
		toppingsPanel = new ItemSelectionMenuPanel();
		
		pizzaSelectionPanelPage1.setVisible(false);
		pizzaSelectionPanelPage2.setVisible(false);
		
		listOfPanels.add(menuPanel);                         //index 0
		listOfPanels.add(pizzaSelectionPanelPage1);          //index 1
		listOfPanels.add(pizzaSelectionPanelPage2);          //index 2

		// set up form
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH,
				UtilityParameters.WINDOW_HEIGHT));
		this.setTitle("Order Form");
		this.setLayout(null);
		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(ticketPanel);
		this.add(menuPanel);
		this.add(bottomPanel);
		this.add(pizzaSelectionPanelPage1);
		this.add(pizzaSelectionPanelPage2);
		this.setVisible(true);
	}

	public static ArrayList<JPanel> getListOfPanels() {
		return listOfPanels;
	}
}
