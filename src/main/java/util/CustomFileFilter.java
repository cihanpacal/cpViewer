package util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CustomFileFilter extends FileFilter {

	private final String[] extensionList = new String[] {"jpg", "png", "gif"};
	private final String description="image files";
	
	@Override
	public boolean accept(File file) {
		if(file.isDirectory()) {
			return true;
		}
		
		for (String extension : extensionList){
	      if (file.getName().toLowerCase().endsWith(extension)){
	        return true;
	      }
	    }
		
		return false;
	}

	@Override
	public String getDescription() {
		return description+String.format(" (*%s), (*%s) , (*%s)",extensionList[0],extensionList[1],extensionList[2]);
	}

}
