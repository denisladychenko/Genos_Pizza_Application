package windows_and_menues;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import utility.parameters.UtilityParameters;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private MainMenuLeftPanel leftPanel;
	private MainMenuRightPanel rightPanel;
	private MainMenuBottomPanel bottomPanel;

	// constructor
	public MainMenu() {

		// set up window
		this.setTitle("Main Menu");
		this.setSize(UtilityParameters.WINDOW_WIDTH,
				UtilityParameters.WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);

		leftPanel = new MainMenuLeftPanel();
		rightPanel = new MainMenuRightPanel();
		bottomPanel = new MainMenuBottomPanel();

		// add panels to the window
		this.add(leftPanel);
		this.add(rightPanel);
		this.add(bottomPanel);

	}

	// one arg constructor
	public MainMenu(String imagePath) {

		// set up window
		this.setTitle("Main Menu");
		this.setSize(UtilityParameters.WINDOW_WIDTH,
				UtilityParameters.WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);

		leftPanel = new MainMenuLeftPanel();
		rightPanel = new MainMenuRightPanel();
		bottomPanel = new MainMenuBottomPanel(imagePath);

		// add panels to the window
		this.add(leftPanel);
		this.add(rightPanel);
		this.add(bottomPanel);

	}

	// 3 arg constructor
	public MainMenu(String imagePath_1, String imagePath_2, String imagePath_3) {

		// set up window
		this.setTitle("Main Menu");
		this.setSize(UtilityParameters.WINDOW_WIDTH,
				UtilityParameters.WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);

		leftPanel = new MainMenuLeftPanel();
		rightPanel = new MainMenuRightPanel();
		bottomPanel = new MainMenuBottomPanel(imagePath_1, imagePath_2,
				imagePath_3);

		// add panels to the window
		this.add(leftPanel);
		this.add(rightPanel);
		this.add(bottomPanel);
	}

	public MainMenuLeftPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(MainMenuLeftPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public MainMenuRightPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(MainMenuRightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public MainMenuBottomPanel getBottomPanel() {
		return bottomPanel;
	}

	public void setBottomPanel(MainMenuBottomPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

}
