package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.parameters.UtilityParameters;

public class CabinInfoEntryWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pan_1, pan_2, pan_3, pan_1_inside_pan_1;
	private JPanel pan_1_inside_pan_1_inside_pan_1, pan_2_inside_pan_1_inside_pan_1, pan_3_inside_pan_1_inside_pan_1;
	private JPanel pan_2_inside_pan_1, pan_1_inside_pan_2_inside_pan_1, pan_2_inside_pan_2_inside_pan_1, pan_3_inside_pan_2_inside_pan_1;
	
	public CabinInfoEntryWindow(){
		
		pan_1 = new JPanel();
		pan_1.setPreferredSize(new Dimension(635, 800));
		pan_1.setLayout(new FlowLayout());
		pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_1_inside_pan_1 = new JPanel();
		pan_1_inside_pan_1.setPreferredSize(new Dimension(630, 340));
		pan_1_inside_pan_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		pan_1_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_2_inside_pan_1 = new JPanel();
		pan_2_inside_pan_1.setPreferredSize(new Dimension(630, 340));
		pan_2_inside_pan_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		pan_2_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_1_inside_pan_1_inside_pan_1 = new JPanel();
		pan_1_inside_pan_1_inside_pan_1.setPreferredSize(new Dimension(250, 100));
		pan_1_inside_pan_1_inside_pan_1.setLayout(new FlowLayout());
		pan_1_inside_pan_1_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_2_inside_pan_1_inside_pan_1 = new JPanel();
		pan_2_inside_pan_1_inside_pan_1.setPreferredSize(new Dimension(620, 80));
		pan_2_inside_pan_1_inside_pan_1.setLayout(new FlowLayout());
		pan_2_inside_pan_1_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_3_inside_pan_1_inside_pan_1 = new JPanel();
		pan_3_inside_pan_1_inside_pan_1.setPreferredSize(new Dimension(620, 80));
		pan_3_inside_pan_1_inside_pan_1.setLayout(new FlowLayout());
		pan_3_inside_pan_1_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_1_inside_pan_2_inside_pan_1 = new JPanel();
		pan_1_inside_pan_2_inside_pan_1.setPreferredSize(new Dimension(620, 80));
		pan_1_inside_pan_2_inside_pan_1.setLayout(new FlowLayout());
		pan_1_inside_pan_2_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_2_inside_pan_2_inside_pan_1 = new JPanel();
		pan_2_inside_pan_2_inside_pan_1.setPreferredSize(new Dimension(620, 80));
		pan_2_inside_pan_2_inside_pan_1.setLayout(new FlowLayout());
		pan_2_inside_pan_2_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_3_inside_pan_2_inside_pan_1 = new JPanel();
		pan_3_inside_pan_2_inside_pan_1.setPreferredSize(new Dimension(620, 80));
		pan_3_inside_pan_2_inside_pan_1.setLayout(new FlowLayout());
		pan_3_inside_pan_2_inside_pan_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_1_inside_pan_1.add(pan_1_inside_pan_1_inside_pan_1);
		pan_1_inside_pan_1.add(pan_2_inside_pan_1_inside_pan_1);
		pan_1_inside_pan_1.add(pan_3_inside_pan_1_inside_pan_1);
		
		pan_2_inside_pan_1.add(pan_1_inside_pan_2_inside_pan_1);
		pan_2_inside_pan_1.add(pan_2_inside_pan_2_inside_pan_1);
		pan_2_inside_pan_1.add(pan_3_inside_pan_2_inside_pan_1);
		
		
		
		pan_1.add(pan_1_inside_pan_1);
		pan_1.add(pan_2_inside_pan_1);
		
		
		
		
		
		
		pan_2 = new JPanel();
		pan_2.setPreferredSize(new Dimension(435, 800));
		pan_2.setLayout(new FlowLayout());
		pan_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pan_3 = new JPanel();
		pan_3.setPreferredSize(new Dimension(1080, 300));
		pan_3.setLayout(new FlowLayout());
		pan_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.add(pan_1);
		this.add(pan_2);
		this.add(pan_3);
		
		this.setSize(new Dimension(UtilityParameters.WINDOW_WIDTH, UtilityParameters.WINDOW_HEIGHT));
		this.setLayout(new FlowLayout());
		getContentPane().setBackground(UtilityParameters.FRAME_COLOR);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
