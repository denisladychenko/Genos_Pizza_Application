package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.LineBorder;

import utility.parameters.UtilityParameters;

public class CustomerStatusPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;

	
	public CustomerStatusPanel() {
		
		JLabel custStatLbl = new JLabel("Customer Status");
		custStatLbl.setBounds(70, 10, 300, 50);
		custStatLbl.setFont(UtilityParameters.DATA_ENTRY_LABEL_FONT);
		custStatLbl.setForeground(Color.YELLOW);
		
		
		JCheckBox creditDueChk = new JCheckBox();
		creditDueChk.setBounds(370, 80, 20, 30);
		creditDueChk.setBackground(UtilityParameters.FRAME_COLOR);
		
		JLabel creditDueLbl = new JLabel("Credit Due");
		creditDueLbl.setBounds(220, 80, 200, 30);
		creditDueLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		creditDueLbl.setForeground(Color.YELLOW);
		creditDueLbl.setBackground(UtilityParameters.FRAME_COLOR);
		
		JCheckBox seeCommentsChk = new JCheckBox();
		seeCommentsChk.setBounds(370, 160, 20, 30);
		seeCommentsChk.setBackground(UtilityParameters.FRAME_COLOR);
		
		JLabel seeCommentsLbl = new JLabel("See Comments");
		seeCommentsLbl.setBounds(170, 160, 200, 30);
		seeCommentsLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		seeCommentsLbl.setForeground(Color.YELLOW);
		seeCommentsLbl.setBackground(UtilityParameters.FRAME_COLOR);
		
		JCheckBox frequentCustomerChk = new JCheckBox();
		frequentCustomerChk.setBounds(370, 240, 20, 30);
		frequentCustomerChk.setBackground(UtilityParameters.FRAME_COLOR);
		
		JLabel frequentCustomerLbl = new JLabel("Frequent Customer");
		frequentCustomerLbl.setBounds(120, 240, 230, 30);
		frequentCustomerLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		frequentCustomerLbl.setForeground(Color.YELLOW);
		frequentCustomerLbl.setBackground(UtilityParameters.FRAME_COLOR);
		
		JCheckBox getManagerChk = new JCheckBox();
		getManagerChk.setBounds(370, 320, 20, 30);
		getManagerChk.setBackground(UtilityParameters.FRAME_COLOR);
		
		JLabel getManagerLbl = new JLabel("Get Manager");
		getManagerLbl.setBounds(195, 320, 200, 30);
		getManagerLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		getManagerLbl.setForeground(Color.YELLOW);
		getManagerLbl.setBackground(UtilityParameters.FRAME_COLOR);
		
		JCheckBox noDeliveryChk = new JCheckBox();
		noDeliveryChk.setBounds(370, 400, 20, 30);
		noDeliveryChk.setBackground(UtilityParameters.FRAME_COLOR);
		
		JLabel noDeliveryLbl = new JLabel("No Delivery");
		noDeliveryLbl.setBounds(210, 400, 200, 30);
		noDeliveryLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		noDeliveryLbl.setForeground(Color.YELLOW);
		noDeliveryLbl.setBackground(UtilityParameters.FRAME_COLOR);
		
		this.setPreferredSize(new Dimension(435, 800));
		this.setLayout(null);
		this.setBounds(645, 5, 435, 800);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(UtilityParameters.FRAME_COLOR);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		
		this.add(custStatLbl);
		this.add(creditDueLbl);
		this.add(creditDueChk);
		this.add(seeCommentsLbl);
		this.add(seeCommentsChk);
		this.add(frequentCustomerLbl);
		this.add(frequentCustomerChk);
		this.add(getManagerLbl);
		this.add(getManagerChk);
		this.add(noDeliveryLbl);
		this.add(noDeliveryChk);
	}
}
