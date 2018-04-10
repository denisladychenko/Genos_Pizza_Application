package windows_and_menues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import utility.parameters.UtilityParameters;

public class OrderFormMenuPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private JButton pizzaBtn, sandwichBtn, dinnersBtn, stixBtn, hotWingsBtn, calzoneBtn, saladsBtn, beverageBtn,
					marketBtn, miscBtn, dessertsBtn, sidesBtn, serviceFeesBtn;
	
	public OrderFormMenuPanel() {
		//set up buttons
		pizzaBtn = new MenuPanelButton("pizza_icon.png");
		pizzaBtn.setBounds(0, 0, 155, 155);
		pizzaBtn.addActionListener(new PizzaButtonClick());
		
		sandwichBtn = new MenuPanelButton("sandwich.png");
		sandwichBtn.setBounds(311, 0, 155, 155);
		
		dinnersBtn = new MenuPanelButton("dinners.png");
		dinnersBtn.setBounds(621, 0, 155, 155);
		
		stixBtn = new MenuPanelButton("sticks.png");
		stixBtn.setBounds(156, 156, 155, 155);
		
		hotWingsBtn = new MenuPanelButton("wings.png");
		hotWingsBtn.setBounds(467, 156, 155, 155);
		
		calzoneBtn = new MenuPanelButton("calzone.png");
		calzoneBtn.setBounds(0, 311, 155, 155);
		
		saladsBtn = new MenuPanelButton("salad.png");
		saladsBtn.setBounds(311, 311, 155, 155);
		
		beverageBtn = new MenuPanelButton("beverages.png");
		beverageBtn.setBounds(621, 311, 155, 155);
		
		marketBtn = new MenuPanelButton("market_item.png");
		marketBtn.setBounds(156, 467, 155, 155);
		
		miscBtn = new MenuPanelButton("extra.png");
		miscBtn.setBounds(467, 467, 155, 155);
		
		dessertsBtn = new MenuPanelButton("chock_stix.png");
		dessertsBtn.setBounds(0, 621, 155, 155);
		
		sidesBtn = new MenuPanelButton("sides.png");
		sidesBtn.setBounds(311, 621, 155, 155);
		
		serviceFeesBtn = new MenuPanelButton("serv_fee.png");
		serviceFeesBtn.setBounds(621, 621, 155, 155);
		
		//add buttons to the panel
		this.add(pizzaBtn);
		this.add(sandwichBtn);
		this.add(dinnersBtn);
		this.add(stixBtn);
		this.add(hotWingsBtn);
		this.add(calzoneBtn);
		this.add(saladsBtn);
		this.add(beverageBtn);
		this.add(marketBtn);
		this.add(miscBtn);
		this.add(dessertsBtn);
		this.add(sidesBtn);
		this.add(serviceFeesBtn);
		
		this.setBounds(315, 5, 775, 800);
		this.setLayout(null);
		this.setBackground(UtilityParameters.FRAME_COLOR);
		//this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
private class MenuPanelButton extends JButton {
		
		private static final long serialVersionUID = 1L;

		public MenuPanelButton(String fp) {
			this.setIcon(new ImageIcon(getClass().getResource("/menu_item_images/" + fp)));
			this.setBorder(BorderFactory.createRaisedBevelBorder());
		}
	}

private class PizzaButtonClick implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int size = OrderForm.getListOfPanels().size();
		for(int i = 0; i < size;i++) {
			OrderForm.getListOfPanels().get(i).setVisible(false);
		}
		OrderForm.getListOfPanels().get(1).setVisible(true);
	}
	
}

}
