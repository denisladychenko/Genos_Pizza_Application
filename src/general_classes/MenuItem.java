package general_classes;

public class MenuItem {
	
	private int categoryId;
	private int imageId;
	private String imageFileName;
	private String nameOnButton;
	private String nameOnTicket;
	private double price;
	private int gap;
	private int xCoord;
	private int yCoord;
	private int page;
	
	public MenuItem() {
		imageFileName = "";
		categoryId = -1;
		imageId = -1;
		nameOnTicket = "";
		price = -1;
		gap = -1;
		xCoord = -1;
		yCoord = -1;
		page = -1;
	}
	
	public MenuItem(int categoryId, int imageId, String nameOnButton, String imageFileName,
			String nameOnTicket, double price, int gap, int xCoord, int yCoord, int page) {
		super();
		this.imageFileName = imageFileName;
		this.categoryId = categoryId;
		this.imageId = imageId;
		this.nameOnButton = nameOnButton;
		this.nameOnTicket = nameOnTicket;
		this.price = price;
		this.gap = gap;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.page = page;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getNameOnButton() {
		return nameOnButton;
	}

	public void setNameOnButton(String nameOnButton) {
		this.nameOnButton = nameOnButton;
	}

	public String getNameOnTicket() {
		return nameOnTicket;
	}

	public void setNameOnTicket(String nameOnTicket) {
		this.nameOnTicket = nameOnTicket;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
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

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	
}
