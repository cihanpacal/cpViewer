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
		setDialogTitle("Dosya A�");
		//setFileFilter(new CustomFileFilter());
		setApproveButtonText("A�");
		addChoosableFileFilter(new CustomFileFilter());
	}

}
