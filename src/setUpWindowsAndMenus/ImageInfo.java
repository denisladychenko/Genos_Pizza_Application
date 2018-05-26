package setUpWindowsAndMenus;

public class ImageInfo {

	
		private String name;            //image name
		private String filename;        //image filename
		
		//constructor
		ImageInfo(String name, String filename){
			this.name = name;
			this.filename = filename;
		}
		/**
		 *Gets image name
		 *@return name Image name 
		 */
		public String getName() {
			return name;
		}
		/**
		 *Gets image filename
		 *@return filename Image filename 
		 */
		public String getFileName() {
			return filename;
		}
		@Override
		public String toString() {
			return name;
		}
	
}
