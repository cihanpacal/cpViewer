package util;

import javax.swing.JFileChooser;

public class CustomFileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5816931958608612925L;
	
	public CustomFileChooser() {
		this("C:\\Users\\Cihan\\Pictures\\");
	}
	
	public CustomFileChooser(String rootPath) {
		super(rootPath);
		setDialogTitle("Dosya Aç");
		//setFileFilter(new CustomFileFilter());
		setApproveButtonText("Aç");
		addChoosableFileFilter(new CustomFileFilter());
	}

}
