package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import utility.parameters.UtilityParameters;

public class PhoneNumberDialMenu extends JDialog{

	static final int WINDOW_WIDTH = 450;
	static final int WINDOW_HEIGHT = 865;
	
	private JPanel pan_1, pan_2, pan_3;
	private JTextPane label;
	private JTextField txtField;
	private JButton backSpcBtn;
	private JButton cancelBtn;
	private JButton enterBtn;
	
	
	public PhoneNumberDialMenu(String lblText){
		Font font = new Font("Calibri", Font.BOLD, 21);
		Font txtFieldFont = new Font("Areal", Font.BOLD, 60);
		Font labelFont = new Font("Areal", 0, 30);
		Font cancelBtnFont = new Font("Areal", 0, 20);
		
		
		pan_1 = new JPanel();
		pan_1.setLayout(new FlowLayout());
		pan_1.setPreferredSize(new Dimension(430, 200));
		pan_1.setBackground(UtilityParameters.PANELS_COLOR);
		pan_1.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		
		label = new JTextPane();
		label.setText(lblText);
		label.setPreferredSize(new Dimension(410, 90));
		label.setFont(labelFont);
		label.setEditable(false);
		label.setBackground(UtilityParameters.PANELS_COLOR);
		label.setForeground(Color.YELLOW);
		
		//centers text in the text pane
		StyledDocument doc = label.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		
		
		txtField = new JTextField();
		txtField.setHorizontalAlignment(JTextField.TRAILING);
		txtField.setPreferredSize(new Dimension(360, 90));
		txtField.setFont(txtFieldFont);
		txtField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//sets limit for the number of symbols entered
		//prevent user from entering non-digit symbols into the field
		txtField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent evt) {
		    	//this sets the limit for number of symbols user can type into the text field
		        if(txtField.getText().length() >= 10 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
		        		evt.getKeyChar() == KeyEvent.VK_DELETE)) {
		            getToolkit().beep();
		            evt.consume();
		         }
		        //this only allows digits to be entered
		        //48 is the ASCI code for '0' and 57 is the ASCI code for '9'
		        if(((int)evt.getKeyChar() < 48 || (int)evt.getKeyChar() > 57) && 
		        		(!(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE ))) {
		        	getToolkit().beep();
		        	evt.consume();
		        }
		     }
		});
		
		
		backSpcBtn = new JButton("<");
		backSpcBtn.setFont(font);
		backSpcBtn.setPreferredSize(new Dimension(44, 90));
		
		pan_1.add(label);
		pan_1.add(txtField);
		pan_1.add(backSpcBtn);
		
		
		pan_2 = new JPanel();
		pan_2.setLayout(new GridLayout(4, 3));
		pan_2.setPreferredSize(new Dimension(370, 400));
		pan_2.setBackground(UtilityParameters.PANELS_COLOR);
		pan_2.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setForeground(Color.RED);
		cancelBtn.setFont(cancelBtnFont);
		
		pan_2.add(new numButton("7"));
		pan_2.add(new numButton("8"));
		pan_2.add(new numButton("9"));
		pan_2.add(new numButton("4"));
		pan_2.add(new numButton("5"));
		pan_2.add(new numButton("6"));
		pan_2.add(new numButton("1"));
		pan_2.add(new numButton("2"));
		pan_2.add(new numButton("3"));
		pan_2.add(cancelBtn);
		pan_2.add(new numButton("0"));
		pan_2.add(new numButton("."));
		
		
		
		pan_3 = new JPanel();
		pan_3.setLayout(new GridLayout(1, 1));
		pan_3.setPreferredSize(new Dimension(370, 140));
		pan_3.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		ImageIcon enterBtnImg = new ImageIcon(getClass().getResource("/images/enter_btn.png"));
		enterBtn = new JButton(enterBtnImg);
		pan_3.add(enterBtn);
		
		
		this.add(pan_1);
		this.add(pan_2);
		this.add(pan_3);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		getContentPane().setBackground(UtilityParameters.PANELS_COLOR);
		this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private class numButton extends JButton{
		
		/**
		 * This class creates button
		 * @param btnText text to appear on the button
		 */
		private static final long serialVersionUID = 1L;

		public numButton(String btnText){
			Font font = new Font("Areal", Font.BOLD, 60);
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			this.setFont(font);
			this.setText(btnText);
		}
	}
}
