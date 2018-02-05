package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class MainMenuRightPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int BUTTON_WIDTH = 170;
	private final int BUTTON_HEIGHT = 145;
	private final int PAN_WIDTH = 715;
	private final int PAN_HEIGHT = 770;
	private final int PAN_1_WIDTH = 680;
	private final int PAN_1_HEIGHT = 120;
	private final int PAN_2_WIDTH = 680;
	private final int PAN_2_HEIGHT = 550;
	private final int LABEL_WIDTH = 680;
	private final int LABEL_HEIGHT = 108;
	private final int PAN_HORIZONTAL_GAP = 10;
	private final int PAN_VERTICAL_GAP = 35;
	private final int GRID_PAN_HORIZONTAL_GAP = 20;
	private final int GRID_PAN_VERTICAL_GAP = 20;

	private JPanel pan_1, pan_2;
	private JPanel gridPan_1,gridPan_2,gridPan_3,gridPan_4,gridPan_5,gridPan_6,gridPan_7,gridPan_8,gridPan_9;
	private JLabel userNameLbl;
	private JButton voidTicketBtn, allTicketsBtn, newTicketBtn, logOffBtn, recallTicketBtn, clockOutBtn, cashOutBtn;
	
	
	//constructor
	public MainMenuRightPanel(){
		
		
		//set up this panel
		this.setPreferredSize(new Dimension(PAN_WIDTH, PAN_HEIGHT));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, PAN_HORIZONTAL_GAP, PAN_VERTICAL_GAP));
		this.setBackground(UtilityParameters.FRAME_COLOR);
		
		//create and set up pan_1
		pan_1 = new JPanel();
		pan_1.setPreferredSize(new Dimension(PAN_1_WIDTH, PAN_1_HEIGHT));
		pan_1.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		pan_1.setBackground(UtilityParameters.PANELS_COLOR);
		
		//create and set up label
		userNameLbl = new JLabel("User: Denis L.", JLabel.CENTER);
		userNameLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		Font font = new Font("Areal", 0, 56);
		userNameLbl.setFont(font);
		userNameLbl.setForeground(Color.YELLOW);
		
		pan_1.add(userNameLbl);
		
		//add pan_1 to this panel
		this.add(pan_1);
		
		//create and set up pan_2
		pan_2 = new JPanel();
		pan_2.setPreferredSize(new Dimension(PAN_2_WIDTH, PAN_2_HEIGHT));
		pan_2.setLayout(new GridLayout(3,3));
		pan_2.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		
		//add pan_2 to this panel
		this.add(pan_2);
		
		//images for the buttons
		Icon voidTicketButtonImg = new ImageIcon(getClass().getResource("void_ticket_icon.png"));
		Icon allTicketsButtonImg = new ImageIcon(getClass().getResource("all_ticket_icon.png"));
		Icon newTicketButtonImg = new ImageIcon(getClass().getResource("new_ticket_icon.png"));
		Icon logOffButtonImg = new ImageIcon(getClass().getResource("monitor_icon.png"));
		Icon recallTicketButtonImg = new ImageIcon(getClass().getResource("recall_icon.png"));
		Icon clockOutButtonImg = new ImageIcon(getClass().getResource("alarm_icon.png"));
		Icon cashOutButtonImg = new ImageIcon(getClass().getResource("cash_icon.png"));
		
		//create and set up buttons to add to the panel
		voidTicketBtn = new JButton(voidTicketButtonImg);
		voidTicketBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		voidTicketBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		allTicketsBtn = new JButton(allTicketsButtonImg);
		allTicketsBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		allTicketsBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		newTicketBtn = new JButton(newTicketButtonImg);
		newTicketBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		newTicketBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		logOffBtn = new JButton(logOffButtonImg);
		logOffBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		logOffBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		recallTicketBtn = new JButton(recallTicketButtonImg);
		recallTicketBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		recallTicketBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		clockOutBtn = new JButton(clockOutButtonImg);
		clockOutBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		clockOutBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cashOutBtn = new JButton(cashOutButtonImg);
		cashOutBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cashOutBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//create panel to hold buttons
		gridPan_1 = new JPanel();
		gridPan_2 = new JPanel();
		gridPan_3 = new JPanel();
		gridPan_4 = new JPanel();
		gridPan_5 = new JPanel();
		gridPan_6 = new JPanel();
		gridPan_7 = new JPanel();
		gridPan_8 = new JPanel();
		gridPan_9 = new JPanel();
		
		//set background color for the panels
		gridPan_1.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_2.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_3.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_4.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_5.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_6.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_7.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_8.setBackground(UtilityParameters.PANELS_COLOR);
		gridPan_9.setBackground(UtilityParameters.PANELS_COLOR);
		
		//set layout for the panels
		gridPan_1.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_2.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_3.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_4.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_5.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_6.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_7.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_8.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		gridPan_9.setLayout(new FlowLayout(FlowLayout.CENTER,GRID_PAN_HORIZONTAL_GAP, GRID_PAN_VERTICAL_GAP));
		
		//add buttons to the panels
		gridPan_1.add(voidTicketBtn);
		gridPan_2.add(allTicketsBtn);
		gridPan_3.add(newTicketBtn);
		gridPan_5.add(logOffBtn);
		gridPan_6.add(recallTicketBtn);
		gridPan_7.add(clockOutBtn);
		gridPan_9.add(cashOutBtn);
		
		//add panels with buttons to the pan_2
		pan_2.add(gridPan_1);
		pan_2.add(gridPan_2);
		pan_2.add(gridPan_3);
		pan_2.add(gridPan_4);
		pan_2.add(gridPan_5);
		pan_2.add(gridPan_6);
		pan_2.add(gridPan_7);
		pan_2.add(gridPan_8);
		pan_2.add(gridPan_9);
	}
}
