package setUpWindowsAndMenus;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import utility.parameters.UtilityParameters;

public class EditModFormImageSelectorForm extends JFrame{

private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private JButton OkBtn, cancelBtn;
	private JScrollPane imagePane;
	private JButton imageBtn;
	private JTree imageTree;
	private DefaultMutableTreeNode rootNode;
	

	public EditModFormImageSelectorForm() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 700, 330);
		mainPanel.setBackground(UtilityParameters.SET_UP_MENU_COLOR);
		
		
		
		imageBtn = new JButton();
		imageBtn.setBounds(520, 10, 150, 100);
	
		
		OkBtn = new JButton("Ok");
		OkBtn.setBounds(520, 250, 150, 30);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(520, 290, 150, 30);
		
		rootNode = new DefaultMutableTreeNode("Modifiers");   //root node
		createNodes(rootNode);
		imageTree = new JTree(rootNode);
		imageTree.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		imageTree.addTreeSelectionListener(new TreeListener());
		imagePane = new JScrollPane(imageTree);
		imagePane.setBounds(10, 10, 500, 310);
		
		
		mainPanel.add(OkBtn);
		mainPanel.add(cancelBtn);
		mainPanel.add(imageBtn);
		mainPanel.add(imagePane);
		this.add(mainPanel);
		this.setSize(new Dimension(700, 370));
		this.setTitle("Select Image");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
	}
	
	/**
	 *Sets up connection with  Menu database 
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
	/**
	 *Creates nodes and add them to the root node of the Tree 
	 */
	public void createNodes(DefaultMutableTreeNode root) {
		
		Connection con = getDatabaseConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String SQLStatement = "SELECT name, filename FROM images "
								+ "WHERE image_group LIKE ('modifier') "
								+ "ORDER BY name ASC";
			//get data from the DB
			rs = stmt.executeQuery(SQLStatement);
			//create nodes using names from database
			while(rs.next()) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(new ImageInfo(rs.getString("name"), rs.getString("filename")));
				root.add(node);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *Gets Tree
	 *@return imageTree the Tree 
	 */
	public JTree getImageTree() {
		return imageTree;
	}
	/**
	 *Listens for the Tree selection events
	 */
	private class TreeListener implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent arg0) {
			 DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                    imageTree.getLastSelectedPathComponent();
			 Object nodeInfo = node.getUserObject();
			 if(node.isLeaf()) {
				 ImageInfo img = (ImageInfo)nodeInfo;
				 imageBtn.setIcon(new ImageIcon(getClass().getResource("/menu_item_images/" + img.getFileName())));
			 }
		}
		
	}
	/**
	 *Cancel button event listener
	 */
	public void addCancelBtnListener(ActionListener al) {
		cancelBtn.addActionListener(al);
	}
	/**
	 *Ok button event listener
	 */
	public void addOkBtnListener(ActionListener al) {
		OkBtn.addActionListener(al);
	}
}
