package filtreler;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import goruntuisleme.JGoruntu;
import goruntuisleme.Mat;

public class MaxFiltre implements IFiltre{
	
	
	int filtre=IFiltre.FILTRE5;
	int boyut=5;
	
	public MaxFiltre() {
		this(IFiltre.FILTRE5);
		
	}
	
	public MaxFiltre(final int filtre) {
		if(!(filtre==IFiltre.FILTRE3||filtre==IFiltre.FILTRE5||filtre==IFiltre.FILTRE7)) {
			JOptionPane.showMessageDialog(null,"Hatalý Filtre Boyutu","Hata", JOptionPane.DEFAULT_OPTION);
			return;
		}else {
			this.filtre=filtre;
			this.boyut=filtre;
		}
		
	}
	

	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
		
		
		
		BufferedImage bfHedef=new BufferedImage(bfKaynak.getWidth(),bfKaynak.getHeight(),BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;
		for (int i =(boyut/2); i < bfHedef.getWidth()-(boyut/2); i++) {
			for (int j=(boyut/2); j < bfHedef.getHeight()-(boyut/2); j++) {
				c=new Color(0,0,0);
				bfHedef.setRGB(i,j,c.getRGB());
			}
		}

		

		for (int i = (boyut / 2); i < bfKaynak.getWidth() - (boyut / 2); i++) {
			for (int j = (boyut / 2); j < bfKaynak.getHeight() - (boyut / 2); j++) {
				//int min = griTonKaynak[i-(boyut/2)][j-(boyut/2)];
				c=new Color(bfKaynak.getRGB(i-(boyut/2),j-(boyut/2)));
				int maxk = c.getRed();
				int maxy = c.getGreen();
				int maxm = c.getBlue();
				//Color[] filtre=new Color[boyut*boyut];
				for (int a = -(boyut / 2); a <= (boyut / 2); a++) {
					for (int b = -(boyut / 2); b <= (boyut / 2); b++) {
						c=new Color(bfKaynak.getRGB(i+a,j+b));
						int yenik=c.getRed();
						int yeniy=c.getGreen();
						int yenim=c.getBlue();
						if(yenik>=maxk)
							maxk=yenik;
						if(yeniy>=maxy)
							maxy=yeniy;
						if(yenim>=maxm)
							maxm=yenim;
						//filtre[boyut*(a+(boyut/2))+(b+(boyut/2))]=new Color(bfKaynak.getRGB(i+a,j+b));
					}//4.for bitti
				}//3.for bitti

				
				/*Mat.renkSirala(filtre);
				c2=filtre[boyut*boyut-1];*/
				c2=new Color(maxk,maxy,maxm);
				bfHedef.setRGB(i,j,c2.getRGB());
				
			}//2.for bitti
		}//1.for bitti
		
		return bfHedef;
	}
	
	
	

}
