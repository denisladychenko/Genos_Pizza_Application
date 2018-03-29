package windows_and_menues;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import utility.parameters.UtilityParameters;

public class InfoEntryWindowRightPanel extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private JLabel commentsTextLbl, directionsTextLbl, warningTextLbl;
	private JTextArea commentsTxta, directionsTxta;

	public InfoEntryWindowRightPanel(){
		
		this.setPreferredSize(new Dimension(435, 800));
		this.setLayout(null);
		this.setBounds(645, 5, 435, 800);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(UtilityParameters.FRAME_COLOR);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		//this.setVisible(false);
		
		commentsTextLbl = new JLabel("Comments:");
		commentsTextLbl.setBounds(20, 50, 150, 50);
		commentsTextLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		commentsTextLbl.setForeground(Color.YELLOW);
		
		//create uppercase filter
		DocumentFilter filter = new UppercaseDocumentFilter();
		
		

		commentsTxta = new JTextArea();
		//this converts all the text in the text area to upper case
		((AbstractDocument) commentsTxta.getDocument()).setDocumentFilter(filter);
		commentsTxta.setFont(UtilityParameters.DATA_ENTRY_FONT);
		JScrollPane commentsScrp = new JScrollPane(commentsTxta);
		commentsScrp.setBounds(10, 100, 415, 200);
		commentsScrp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		commentsTxta.setLineWrap(true);
		
		warningTextLbl = new JLabel();
		warningTextLbl.setBounds(10, 285, 415, 70);
		warningTextLbl.setForeground(Color.YELLOW);
		warningTextLbl.setText("<html>" + "WARNING: Storing credit card information is a violation"
				+ " of PCI compliance State and Federal Laws!" + "</html>");
		
		directionsTextLbl = new JLabel("Printed Directions:");
		directionsTextLbl.setBounds(20, 450, 380, 50);
		//directionsTextLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		directionsTextLbl.setFont(UtilityParameters.DATA_ENTRY_FONT);
		directionsTextLbl.setForeground(Color.YELLOW);
		
		
		directionsTxta = new JTextArea();
		//this converts all the text in the text area to upper case
	    ((AbstractDocument)directionsTxta.getDocument()).setDocumentFilter(filter);
		directionsTxta.setFont(UtilityParameters.DATA_ENTRY_FONT);
		JScrollPane directionsScrp = new JScrollPane(directionsTxta);
		directionsScrp.setBounds(10, 500, 415, 200);
		directionsScrp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		directionsTxta.setLineWrap(true);
		
		
		//add components to the panel
		this.add(commentsScrp);
		this.add(commentsTextLbl);
		this.add(warningTextLbl);
		this.add(directionsTextLbl);
		this.add(directionsScrp);
	}
	/**
	 * This class sets up the filter for text input.
	 * It converts letters to upper case
	 */
	private class UppercaseDocumentFilter extends DocumentFilter{
		public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
			      AttributeSet attr) throws BadLocationException {

			    fb.insertString(offset, text.toUpperCase(), attr);
			  }
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
			      AttributeSet attrs) throws BadLocationException {

			    fb.replace(offset, length, text.toUpperCase(), attrs);
			  }
	}
}
