package general_classes;

public class ModifiersListItem {

	private String name;          //mod name
	private int xCoord;           //x coordinate on the location button
	private int yCoord;           //y coordinate on the location button
	private int page;             //page on which modifier is located

	//constructor
	public ModifiersListItem(String name, int xCoord, int yCoord, int page) {
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.page = page;

	}

	
	/**
	 *Gets the modifier's name
	 *@return name The name of the modifier 
	 */
	public String getName() {
		return name;
	}
	/**
	 *Sets the modifier's name
	 *@param name The modifier's name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *Gets the modifier's x coordinate
	 *@return xCoord The modifier's x coordinate 
	 */
	public int getxCoord() {
		return xCoord;
	}
	/**
	 *Sets the modifier's x coordinate
	 *@param xCoord The modifier's x coordinate 
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	 *Gets the modifier's y coordinate
	 *@return yCoord The modifier's y coordinate 
	 */
	public int getyCoord() {
		return yCoord;
	}
	/**
	 *Sets the modifier's y coordinate
	 *@param yCoord The modifier's y coordinate 
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	/**
	 *Gets the modifier's page
	 *@return page The modifier's page 
	 */
	public int getPage() {
		return page;
	}
	/**
	 *Sets the modifier's page
	 *@param page The modifier's page 
	 */
	public void setPage(int page) {
		this.page = page;
	}

	
	
	
}
