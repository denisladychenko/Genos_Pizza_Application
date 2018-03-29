package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



import utility.parameters.UtilityParameters;


public class PrevOrdersRightPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel prevOrdersTextLbl, orderNumberLbl, chkBoxLbl;
	private JTextArea prevOrdersTxta, orderInfoTxta;
	private JButton prevBtn, nextBtn;
	private JCheckBox repeatOrderChk;

	public PrevOrdersRightPanel(){
		
		this.setPreferredSize(new Dimension(435, 800));
		this.setLayout(null);
		this.setBounds(645, 5, 435, 800);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(UtilityParameters.FRAME_COLOR);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		
		prevOrdersTextLbl = new JLabel("Previous Orders", JLabel.CENTER);
		//prevOrdersTextLbl.setHorizontalTextPosition(Label.CENTER);
		prevOrdersTextLbl.setBounds(20, 20, 410, 50);
		prevOrdersTextLbl.setFont(UtilityParameters.DATA_ENTRY_LABEL_FONT);
		prevOrdersTextLbl.setForeground(Color.YELLOW);
		

		prevOrdersTxta = new JTextArea();
		//this converts all the text in the text area to upper case
		prevOrdersTxta.setFont(UtilityParameters.ORDER_FONT);
		JScrollPane prevOrdersScrp = new JScrollPane(prevOrdersTxta);
		prevOrdersScrp.setBounds(10, 80, 415, 150);
		prevOrdersScrp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		prevOrdersTxta.setLineWrap(true);
		prevOrdersTxta.setEditable(false);
		
		ImageIcon prevBtnImg = new ImageIcon(getClass().getResource("/images/prev_record.png"));
		prevBtn = new JButton(prevBtnImg);
		prevBtn.setBounds(10, 270, 100, 80);
		
		orderNumberLbl = new JLabel("1 of 1", JLabel.CENTER);
		orderNumberLbl.setFont(UtilityParameters.DATA_ENTRY_LABEL_FONT);
		orderNumberLbl.setBounds(120, 270, 195, 80);
		orderNumberLbl.setBorder(BorderFactory.createLoweredBevelBorder());
	
		ImageIcon nextBtnImg = new ImageIcon(getClass().getResource("/images/next_record.png"));
		nextBtn = new JButton(nextBtnImg);
		nextBtn.setBounds(325, 270, 100, 80);
		
		orderInfoTxta = new JTextArea();
		orderInfoTxta.setFont(UtilityParameters.ORDER_FONT);
		JScrollPane orderInfoScrp = new JScrollPane(orderInfoTxta);
		orderInfoScrp.setBounds(10, 370, 415, 350);
		orderInfoScrp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		orderInfoTxta.setLineWrap(true);
		orderInfoTxta.setEditable(false);
		
		repeatOrderChk = new JCheckBox();
		repeatOrderChk.setBounds(10, 750, 20, 30);
		repeatOrderChk.setBackground(UtilityParameters.FRAME_COLOR);
		
		chkBoxLbl = new JLabel("Repeat This Order");
		chkBoxLbl.setBounds(40, 750, 300, 30);
		chkBoxLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		chkBoxLbl.setForeground(Color.YELLOW);
		chkBoxLbl.setBackground(UtilityParameters.FRAME_COLOR);
		
		
		//add components to the panel
		this.add(prevOrdersScrp);
		this.add(prevOrdersTextLbl);
		this.add(orderInfoScrp);
		this.add(prevBtn);
		this.add(orderNumberLbl);
		this.add(nextBtn);
		this.add(repeatOrderChk);
		this.add(chkBoxLbl);
	}
	
}
