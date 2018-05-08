package setUpWindowsAndMenus;

public class CategoryInfo {
	
		private String catName;
		private String imageFileName;
		
		CategoryInfo(String cn, String ifn){
			catName = cn;
			imageFileName = ifn;
		}

		public String getCatName() {
			return catName;
		}

		public void setCatName(String catName) {
			this.catName = catName;
		}

		public String getImageFileName() {
			return imageFileName;
		}

		public void setImageFileName(String imageFileName) {
			this.imageFileName = imageFileName;
		}
	
}
