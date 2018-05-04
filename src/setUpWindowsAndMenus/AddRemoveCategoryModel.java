package setUpWindowsAndMenus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AddRemoveCategoryModel {

	private String catName;
	private String imageFileName;
	private int gap;
	private int xCoord;
	private int yCoord;
	private int page;
	private AddRemoveCategoryForm theView;
	
	public AddRemoveCategoryModel(AddRemoveCategoryForm form) {
		theView = form;
	}
	/**
	 *Stores all the user input into the fields 
	 */
	public void setUpDataValues() {               
		catName = theView.getCategoryName();
		imageFileName = theView.getFileName();
		gap = theView.getGap();
		xCoord = theView.getLocationXCoord();
		yCoord = theView.getLocationYCoord();
		page = theView.getPage();
}
	
	/**
	 *Adds category to the DB 
	 */
	public void addCategory() {
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			//insert all the category info into DB
			String SQLStatement = "INSERT INTO categories(image_id, name_on_button, xcoord, ycoord, gap, page) "
									+ "VALUES('" + getImageCategoryId() + "'"
									+ ", '" + catName + "'"
									+ ", '" + xCoord + "'"
									+ ", '" + yCoord + "'"
									+ ", '" + gap + "'"
									+ ", '" + page + "')";
			stmt.executeUpdate(SQLStatement);
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Invalid Filename!");
		}
		
	}
	/**
	 *Deletes category from the DB 
	 */
	public void deleteCategoryFromDatabase(int xc, int yc, int p) {
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			//delete category with these coordinates on this page 
			//from the DB
			String SQLStatement = "DELETE FROM categories "
								+ "WHERE xcoord =" + xc + " "
								+ "AND ycoord =" + yc + " "
								+ "AND page =" + p;
			stmt.executeUpdate(SQLStatement);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *Gets image ID from the DB
	 *@return imageId The id of the image
	 */
	public int getImageCategoryId() {
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int imageId = 0;
		try {
			stmt = con.createStatement();
			String SQLStatement = "SELECT id FROM images "
								+ "WHERE filename LIKE '" + imageFileName + "'";
			rs = stmt.executeQuery(SQLStatement);
			while(rs.next()) {
				imageId = rs.getInt("id");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageId;
	}
	/**
	 *Gets connection to the DB
	 *@return con Connection object
	 */
	public static Connection getDatabaseConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/menu", "postgres", "AK47M4M16MP5PX4M249");
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Database driver was not found!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection can not be established!");
		}
		return con;
	}
	
	
	
}
