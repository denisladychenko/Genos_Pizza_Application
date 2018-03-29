package windows_and_menues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import utility.parameters.UtilityParameters;

public class OrderFormBottomPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;
	
	private JButton menuBtn,           
					modifiersBtn,           //modifiers for the menu item
					wholeBtn,
					firstHalfBtn,
					secondHalfBtn,
					page2Btn,
					page1Btn,
					finishedBtn;
	
	public OrderFormBottomPanel() {
		//menu button
		menuBtn = new BottomPanelButton("utensils.png");
		menuBtn.setBounds(12, 15, 150, 120);
		//modifiers button
		modifiersBtn = new BottomPanelButton("marker.png");
		modifiersBtn.setBounds(162, 15, 150, 120);
		//whole button
		wholeBtn = new BottomPanelButton("whole.png");
		wholeBtn.setBounds(312, 15, 150, 120);
		wholeBtn.addActionListener(new WholeBtnClick());
		//1st half button. Initially invisible
		firstHalfBtn = new BottomPanelButton("1st_half_icon.png");
		firstHalfBtn.setBounds(312, 15, 150, 120);
		firstHalfBtn.setVisible(false);
		firstHalfBtn.addActionListener(new FirstHalfBtnClick());
		//2nd half button. Initially invisible
		secondHalfBtn = new BottomPanelButton("2nd_half_icon.png");
		secondHalfBtn.setBounds(312, 15, 150, 120);
		secondHalfBtn.setVisible(false);
		secondHalfBtn.addActionListener(new SecondHalfBtnClick());
		//page 2 button
		page2Btn = new BottomPanelButton("page2_icon.png");
		page2Btn.setBounds(462, 15, 150, 120);
		page2Btn.addActionListener(new Page2BtnClick());
		//page 1 button. Initially invisible
		page1Btn = new BottomPanelButton("page1_icon.png");
		page1Btn.setBounds(462, 15, 150, 120);
		page1Btn.setVisible(false);
		page1Btn.addActionListener(new Page1BtnClick());
		//finished button
		finishedBtn = new BottomPanelButton("finished.png");
		finishedBtn.setBounds(612, 15, 150, 120);
		
		//set up panel
		this.setLayout(null);
		this.setBounds(315, 815, 775, 150);
		this.setBackground(UtilityParameters.PANELS_COLOR);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//add buttons to the panel
		this.add(menuBtn);
		this.add(modifiersBtn);
		this.add(wholeBtn);
		this.add(firstHalfBtn);
		this.add(secondHalfBtn);
		this.add(page2Btn);
		this.add(page1Btn);
		this.add(finishedBtn);
	}

	private class BottomPanelButton extends JButton {
		
		private static final long serialVersionUID = 1L;

		public BottomPanelButton(String fp) {
			this.setIcon(new ImageIcon(getClass().getResource("/images/" + fp)));
			this.setBorder(BorderFactory.createRaisedBevelBorder());
		}
	}
	private class WholeBtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			wholeBtn.setVisible(false);
			firstHalfBtn.setVisible(true);
			
		}
		
	}
	private class FirstHalfBtnClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			firstHalfBtn.setVisible(false);
			secondHalfBtn.setVisible(true);
			
		}
		
	}	
	private class SecondHalfBtnClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			secondHalfBtn.setVisible(false);
			wholeBtn.setVisible(true);
		}
		
	}	

	private class Page2BtnClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			page2Btn.setVisible(false);
			page1Btn.setVisible(true);
		}
		
	}	
	private class Page1BtnClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			page1Btn.setVisible(false);
			page2Btn.setVisible(true);
		}
		
	}	
}
