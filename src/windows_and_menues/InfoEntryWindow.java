package windows_and_menues;

import java.awt.*;
import java.security.Policy.Parameters;
import java.util.ArrayList;

import javax.swing.*;

import utility.parameters.UtilityParameters;

public class InfoEntryWindow extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel custStatusPnl, moreInfoPnl, prevOrdersPnl;
	private static ArrayList<JPanel> panelList;                //list of panels
	
	/**
	 * Constructor
	 * @param fname the title for the form
	 * @param lbl_1 form label text
	 * @param lbl_2 form label text
	 * @param lbl_3 form label text
	 * @param lbl_4 form label text
	 * */
	public InfoEntryWindow(String fname, String lbl_1, String lbl_2, String lbl_3, String lbl_4){
		//put right panels into collection
		panelList = new ArrayList<JPanel>();
		moreInfoPnl = new InfoEntryWindowRightPanel();
		prevOrdersPnl = new PrevOrdersRightPanel();
		custStatusPnl = new CustomerStatusPanel(); 
		panelList.add(moreInfoPnl);
		panelList.add(prevOrdersPnl);
		panelList.add(custStatusPnl);
		
		this.add(new InfoEntryWindowLeftPanel(lbl_1, lbl_2, lbl_3, lbl_4));
		this.add(moreInfoPnl);
		this.add(prevOrdersPnl);
		this.add(custStatusPnl);
		//make all right panels other than moreInfo panel invisible
		//moreInfo panel is the start up panel
		prevOrdersPnl.setVisible(false);
		custStatusPnl.setVisible(false);
		this.add(new InfoEntryWindowBottomPanel());
		
		//set up form
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setTitle(fname);
		this.setLayout(null);
		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static ArrayList<JPanel> getPanelList() {
		return panelList;
	}
}
