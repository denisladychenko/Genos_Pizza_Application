package windows_and_menues;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import utility.parameters.UtilityParameters;



public class OrderFormTicketPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private JList<String> list;
    private JPanel ticketHeader;
    private TicketItemsList scrollPane, totalsLst;        //scrollpane with a background
    private int incr = 0;
    private JButton cancelOrderBtn, smartDiscBtn, advFuncBtn, backToOrderBtn;     //upper buttons
    private JButton downScroll, upScroll;                         //scroll buttons
    
	public OrderFormTicketPanel() {
		
		this.setLayout(null);
		this.setBackground(UtilityParameters.FRAME_COLOR);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setBounds(5,5,300, 960);          //location of the panel
        	
			ImageIcon cancelIcon = new ImageIcon(getClass().getResource("/images/cancel_order1.png"));
			ImageIcon smartDiscountIcon = new ImageIcon(getClass().getResource("/images/smart_discount.png"));
			ImageIcon advFunctionsIcon = new ImageIcon(getClass().getResource("/images/adv_functions.png"));
			ImageIcon backToOrderIcon = new ImageIcon(getClass().getResource("/images/back_to_order.png"));
        	
        	cancelOrderBtn = new JButton(cancelIcon);
            cancelOrderBtn.setBounds(0, 0, 100, 80);
            cancelOrderBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            cancelOrderBtn.setVisible(false);
            //cancelOrderBtn.addActionListener();
            
            smartDiscBtn = new JButton(smartDiscountIcon);
            smartDiscBtn.setBounds(101, 0, 100, 80);
            smartDiscBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            smartDiscBtn.setVisible(false);
            //smartDiscBtn.addActionListener());
            
            advFuncBtn = new JButton(advFunctionsIcon);
            advFuncBtn.setBounds(202, 0, 100, 80);
            advFuncBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            //advFuncBtn.addActionListener();
            
            backToOrderBtn = new JButton(backToOrderIcon);
            backToOrderBtn.setBounds(0, 0, 100, 80);
            backToOrderBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            //backToOrderBtn.addActionListener();
            
            /*int count = 64;
            String[] values = new String[count];
            for (int index = 0; index < count; index++) {
                values[index] = "999          Lg Pepperoni    " + (index + 1);
            }*/
            JLabel upperLabel = new JLabel("Delayed Order");
            upperLabel.setBounds(10,87, 283, 32);
            upperLabel.setBackground(Color.WHITE);
            upperLabel.setOpaque(true);
            upperLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
            
            //ticket header panel
            ticketHeader = new OrderFormDeliveryTicketHeader();
            ticketHeader.setBounds(10, 115, 283, 58);
            
            
            //list items
            DefaultListModel<String> items = new DefaultListModel<String>();
            list = new JList<String>(items);
            /*for(int i = 0; i < 100; i++){
            	items.addElement("Lg Cheese" + i);
            }*/
            //set transparency 
            list.setOpaque(false);
            //set cell dimensions
            list.setFixedCellHeight(30);
            list.setFixedCellWidth(283);
            list.setFont(new Font("Areal", 0, 19));
            //set list color to transparent 
            list.setBackground(new Color(0, 0, 0, 0));
            //selected item background color
            list.setSelectionBackground(new Color(0.9f,0.5f,0.9f,0.7f));
            list.setForeground(Color.BLACK);
            //removes borders from selected item
            UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
            
            //ticket items list scrollpane
            scrollPane = new TicketItemsList(list, JScrollPane.VERTICAL_SCROLLBAR_NEVER, 
            		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, "src\\images\\check11.png",
            		283,       //width of the list
            		602);      //height of the list
            //set transparency to scrollpane
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBounds(10, 173, 283, 602);
            scrollPane.setBorder(new LineBorder(Color.BLACK, 1));
            
            
            //totals list items
            DefaultListModel<String> itemsModel = new DefaultListModel<String>();
            itemsModel.addElement(String.format("%18s", "Subtotal"));
            JList<String> totalListItems = new JList<String>(itemsModel);
            totalsLst = new TicketItemsList(totalListItems, JScrollPane.VERTICAL_SCROLLBAR_NEVER, 
            		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, "src\\images\\totals_list.png",
            		283,       //width of the list
            		93);       //height of the list
            //set transparency to list
            totalListItems.setOpaque(false);
            //set cell dimensions
            totalListItems.setFixedCellHeight(30);
            totalListItems.setFixedCellWidth(283);
            totalListItems.setFont(new Font("Areal", 0, 19));
            //set background color to fully transparent 
            totalListItems.setBackground(new Color(0, 0, 0, 0));
            //selection background color
            totalListItems.setSelectionBackground(new Color(0f,0f,0f,0f));
            totalListItems.setForeground(Color.BLACK);
            //remove borders from selected item
            UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
            //set transparency to the scrollpane and the viewport
            totalsLst.setOpaque(false);
            totalsLst.getViewport().setOpaque(false);
            totalsLst.setBounds(10, 775, 283, 93);
            totalsLst.setBorder(new LineBorder(Color.BLACK, 1));
            
            ImageIcon upArrowIcon = new ImageIcon(getClass().getResource("/images/arrow_up_icon.png")); 
            ImageIcon downArrowIcon = new ImageIcon(getClass().getResource("/images/arrow_down_icon.png"));
            
            downScroll = new JButton(downArrowIcon);
            downScroll.setBounds(52, 868, 100, 90);
            upScroll = new JButton(upArrowIcon);
            upScroll.setBounds(153, 868, 100, 90);
            
            if((list.getModel().getSize() * 30) > 601)
				cancelOrderBtn.setVisible(true);
            
            //component to the panel
            this.add(cancelOrderBtn);
            this.add(smartDiscBtn);
            this.add(advFuncBtn);
            this.add(backToOrderBtn);
            this.add(upperLabel);
            this.add(ticketHeader);
            this.add(scrollPane);
            this.add(totalsLst);
            this.add(downScroll);
            this.add(upScroll);
            
            

        }

        
        private class ButtonScroll implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Point pt = new Point();
				if(scrollPane.getViewport().getViewPosition().y == 968)
					incr = 0;
				incr += 60;
				
				pt.x = 0;
				pt.y = incr;
				System.out.println(scrollPane.getViewport().getViewPosition());
				System.out.println(scrollPane.getViewport().getHeight());
				System.out.println("List count" + list.getModel().getSize());
				scrollPane.getViewport().setViewPosition(pt);
				
			}
        	
        }
	
}
