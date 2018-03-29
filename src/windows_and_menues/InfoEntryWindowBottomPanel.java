package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import utility.parameters.UtilityParameters;
/**
 * This class creates the HotelInfoEntryWindow bottom panel(panel with buttons)
 * */
public class InfoEntryWindowBottomPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	//bottom panel buttons
	private JButton backBtn, custStatusBtn, moreInfoBtn, prevOrdersBtn, nextBtn; 
	//constructor
	public InfoEntryWindowBottomPanel() {
		//setup the panel
		this.setPreferredSize(new Dimension(1080, 150));
		this.setLayout(null);
		this.setBounds(2, 810, 1080, 150);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(UtilityParameters.FRAME_COLOR);
		
		//image to be used on the button
		ImageIcon backBtnImg = new ImageIcon(getClass().getResource("/images/back.png"));
		backBtn = new JButton(backBtnImg);
		backBtn.setBounds(13, 20, 200, 110);
		
		//image to be used on the button
		ImageIcon custStatusBtnImg = new ImageIcon(getClass().getResource("/images/customer_status.png"));
		custStatusBtn = new JButton(custStatusBtnImg);
		custStatusBtn.setBounds(226, 20, 200, 110);
		custStatusBtn.addActionListener(new CustStatusButtonClickListener());
		
		//image to be used on the button
		ImageIcon moreInfoBtnImg = new ImageIcon(getClass().getResource("/images/more_info.png"));
		moreInfoBtn = new JButton(moreInfoBtnImg);
		moreInfoBtn.setBounds(439, 20, 200, 110);
		moreInfoBtn.addActionListener(new MoreInfoButtonClickListener());
		
		//image to be used on the button
		ImageIcon prevOrdersBtnImg = new ImageIcon(getClass().getResource("/images/prev_orders.png"));
		prevOrdersBtn = new JButton(prevOrdersBtnImg);
		prevOrdersBtn.setBounds(652, 20, 200, 110);
		prevOrdersBtn.addActionListener(new PrevOrdersButtonClickListener());
		
		//image to be used on the button
		ImageIcon nextBtnImg = new ImageIcon(getClass().getResource("/images/next.png"));
		nextBtn = new JButton(nextBtnImg);
		nextBtn.setBounds(863, 20, 200, 110);
		//add buttons to the panel
		this.add(backBtn);
		this.add(custStatusBtn);
		this.add(moreInfoBtn);
		this.add(prevOrdersBtn);
		this.add(nextBtn);
	}
	
	private class PrevOrdersButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<JPanel> list = InfoEntryWindow.getPanelList();
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setVisible(false);
			}
			list.get(1).setVisible(true);    //make previous orders panel visible
		}
		
	}
	private class MoreInfoButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<JPanel> list = InfoEntryWindow.getPanelList();
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setVisible(false);
			}
			list.get(0).setVisible(true);    //make more info panel visible
		}
		
	}
	private class CustStatusButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<JPanel> list = InfoEntryWindow.getPanelList();
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setVisible(false);
			}
			list.get(2).setVisible(true);    //make customer status panel visible
		}
		
	}
}
