package general_classes;

public class ModifiersListItem {

	private String name;
	private int xCoord;
	private int yCoord;
	private int page;

	
	public ModifiersListItem(String name, int xCoord, int yCoord, int page) {
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.page = page;

	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	
	
	
}
