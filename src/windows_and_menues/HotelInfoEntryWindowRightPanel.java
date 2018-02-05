package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

import utility.parameters.UtilityParameters;

public class HotelInfoEntryWindowRightPanel extends JPanel {
	
	private JLabel commentsTextLbl, directionsTextLbl, warningTextLbl, maxSpaceLbl;
	private JTextArea commentsTxta, directionsTxta;

	public HotelInfoEntryWindowRightPanel(){
		
		this.setPreferredSize(new Dimension(435, 800));
		this.setLayout(null);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(UtilityParameters.FRAME_COLOR);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		
		commentsTextLbl = new JLabel("Comments:");
		commentsTextLbl.setBounds(20, 50, 150, 50);
		commentsTextLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		commentsTextLbl.setFont(new Font("Areal", Font.BOLD,24));
		commentsTextLbl.setForeground(Color.YELLOW);
		
		commentsTxta = new JTextArea();
		JScrollPane commentsScrb = new JScrollPane(commentsTxta);
		commentsScrb.setBounds(10, 100, 415, 200);
		commentsScrb.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		commentsTxta.setLineWrap(true);
		
		
		this.add(commentsScrb);
		
		this.add(commentsTextLbl);
	}
}
