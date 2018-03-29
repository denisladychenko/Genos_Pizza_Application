package windows_and_menues;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

public class TicketItemsList extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private int width;                           //width of the scrollpane
	private int height;                          //height of the scrollpane
	private BufferedImage background;            //background image
	
	//constructor for TicketItemsList class
	public TicketItemsList(Component a, int yAxisPolicy, int xAxisPolicy, String fp, int width, int height) {
		super(a, yAxisPolicy, xAxisPolicy);
		getBG(fp);             //reads image
		this.width = width;
		this.height = height;
	}
	/**
	 *This function read the image into the background variable
	 *@param fp file path to the image 
	 */
	public void getBG(String fp) {
		try {
			 //read the image in
          background = ImageIO.read(new File(fp));
      } catch (IOException ex) {
          ex.printStackTrace();
      }
		
	}

	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(width, height);
     }
	 @Override
     protected void paintComponent(Graphics g) {
     	
         super.paintComponent(g);
         if (background != null) {
             Graphics2D g2d = (Graphics2D) g.create();
             int x = getWidth() - background.getWidth();
             int y = getHeight() - background.getHeight();
             g2d.drawImage(background, x, y, this);
             g2d.dispose();
         }
     }

}
