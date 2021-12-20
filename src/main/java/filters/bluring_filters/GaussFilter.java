package filters.bluring_filters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import filters.IFilter;

public class GaussFilter implements IFilter {

	
	int filtre=IFilter.FILTRE5;
	int boyut=5;
	
	public GaussFilter(){
		this(IFilter.FILTRE5);
	}
	
	public GaussFilter(final int filtre) {
		if(!(filtre==IFilter.FILTRE3||filtre==IFilter.FILTRE5||filtre==IFilter.FILTRE7)) {
			JOptionPane.showMessageDialog(null,"Hatalý Filtre Boyutu","Hata", JOptionPane.DEFAULT_OPTION);
			return;
		}else {
			this.filtre=filtre;
			this.boyut=filtre;
		}
		
	}
	
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
	
		int[][] filtre3 = { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };
		int[][] filtre5 = { { 1, 4, 7, 4, 1 }, { 4, 16, 26, 16, 4 }, { 7, 26, 41, 26, 7 }, { 4, 16, 26, 16, 4 },
				{ 1, 4, 7, 4, 1 } };
		int[][] filtre7 = { { 0, 0, 1, 2, 1, 0, 0 }, { 0, 3, 13, 22, 13, 3, 0 }, { 1, 13, 59, 97, 59, 13, 1 },
				{ 2, 22, 97, 159, 97, 22, 2 }, { 1, 13, 59, 97, 59, 13, 1 }, { 0, 3, 13, 22, 13, 3, 0 },
				{ 0, 0, 1, 2, 1, 0, 0 }, };
		int[][][] filtreler = new int[3][][];
		filtreler[0] = filtre3;
		filtreler[1] = filtre5;
		filtreler[2] = filtre7;
		
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
				int vk = 0;
				int vy = 0;
				int vm = 0;
				for (int a = -(boyut / 2); a <= (boyut / 2); a++) {
					for (int b = -(boyut / 2); b <= (boyut / 2); b++) {
						c=new Color(bfKaynak.getRGB(i+a,j+b));
						vk+=c.getRed()*filtreler[filtre/2-1][a + (boyut / 2)][b + (boyut / 2)];
						vy+=c.getGreen()*filtreler[filtre/2-1][a + (boyut / 2)][b + (boyut / 2)];
						vm+=c.getBlue()*filtreler[filtre/2-1][a + (boyut / 2)][b + (boyut / 2)];
					}//4.for bitti
				}//3.for bitti
				if(boyut==this.FILTRE3) {
					c2=new Color(Math.round(vk/16.0f),Math.round(vy/16.0f),Math.round(vm/16.0f));
					bfHedef.setRGB(i,j,c2.getRGB());
				}else if(boyut==this.FILTRE5) {
					c2=new Color(Math.round(vk/273.0f),Math.round(vy/273.0f),Math.round(vm/273.0f));
					bfHedef.setRGB(i,j,c2.getRGB());
				}else if(boyut==this.FILTRE7) {
					c2=new Color(Math.round(vk/1003.0f),Math.round(vy/1003.0f),Math.round(vm/1003.0f));
					bfHedef.setRGB(i,j,c2.getRGB());
				}
				
				
			}//2.for bitti
		}//1.for bitti
		
		return bfHedef;
	}

	
}
