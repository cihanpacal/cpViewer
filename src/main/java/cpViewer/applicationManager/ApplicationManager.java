package cpViewer.applicationManager;


public class ApplicationManager {
	
	
	private static ApplicationManager applicationManager=new ApplicationManager();
	
	private ApplicationManager() {
		
	}
	
	public static ApplicationManager getApplicationManager() {
		return applicationManager;
	}

}
