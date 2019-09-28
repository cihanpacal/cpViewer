package gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DosyaFiltresi extends FileFilter{
	
	 private final String[] uzantilar = new String[] {"jpg", "png", "gif"};;
	private final String aciklama="resim dosyalarý";
	
	public DosyaFiltresi() {
	
	}

	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) {
			return true;
		}
		
		for (String uzanti : uzantilar){
	      if (f.getName().toLowerCase().endsWith(uzanti)){
	        return true;
	      }
	    }
		
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return aciklama+String.format(" (*%s), (*%s) , (*%s)",uzantilar[0],uzantilar[1],uzantilar[2]);
	}

}
