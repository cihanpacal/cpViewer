package filters.edge_dedection_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

import filters.IFilter;
import util.Mat;

public class PrewittFiltre implements IFilter {

	
     int boyut=3;
	
	public PrewittFiltre(){
		
	}
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
		BufferedImage bfYatay = new PrewittYatayFiltre().filtrele(bfKaynak);
		BufferedImage bfDikey = new PrewittDikeyFiltre().filtrele(bfKaynak);

		BufferedImage bfHedef = new BufferedImage(bfKaynak.getWidth(), bfKaynak.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Color cd;
		Color cy;
		Color c2;

		// Kenar çerçeveyi daha sonra düzelt
		for (int i = (boyut / 2); i < bfHedef.getWidth() - (boyut / 2); i++) {
			for (int j = (boyut / 2); j < bfHedef.getHeight() - (boyut / 2); j++) {
				c2= new Color(0, 0, 0);
				bfHedef.setRGB(i, j, c2.getRGB());
			}
		}
		
		for (int i = (boyut / 2); i < bfKaynak.getWidth() - (boyut / 2); i++) {
			for (int j = (boyut / 2); j < bfKaynak.getHeight() - (boyut / 2); j++) {
				cd=new Color(bfDikey.getRGB(i,j));
				cy=new Color(bfYatay.getRGB(i,j));
				int ky=cy.getRed(),yy=cy.getGreen(),my=cy.getBlue();
				int kd=cd.getRed(),yd=cd.getGreen(),md=cd.getBlue();
				int magk=(int)Math.round(Math.sqrt(Math.pow(kd,2)+Math.pow(ky,2)));
				int magy=(int)Math.round(Math.sqrt(Math.pow(yd,2)+Math.pow(yy,2)));
				int magm=(int)Math.round(Math.sqrt(Math.pow(md,2)+Math.pow(my,2)));
				int k=Mat.yuvarla255(magk);
				int y=Mat.yuvarla255(magy);
				int m=Mat.yuvarla255(magm);
				c2=new Color(k,y,m);
				bfHedef.setRGB(i, j,c2.getRGB());
			}//2.for bitti
		}//1.for bitti

		return bfHedef;
	}

}
