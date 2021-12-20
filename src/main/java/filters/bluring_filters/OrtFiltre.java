package filters.bluring_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import filters.IFilter;

public class OrtFiltre implements IFilter {

	
	int filtre=IFilter.FILTRE5;
	int boyut=5;
	
	public OrtFiltre() {
		this(IFilter.FILTRE5);
	}
	
	public OrtFiltre(final int filtre) {
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
		
		
		int[][] filtre3 = { { 1, 1, 1 },
							{ 1, 1, 1 }, 
							{ 1, 1, 1 } };
		int[][] filtre5 = { { 1, 1, 1, 1, 1 },
							{ 1, 1, 1, 1, 1 },
							{ 1, 1, 1, 1, 1 },
							{ 1, 1, 1, 1, 1 },
							{ 1, 1, 1, 1, 1 } };
		int[][] filtre7 = { { 1, 1, 1, 1, 1, 1, 1 },
							{ 1, 1, 1, 1, 1, 1, 1 },
							{ 1, 1, 1, 1, 1, 1, 1 },
				            { 1, 1, 1, 1, 1, 1, 1 },
				            { 1, 1, 1, 1, 1, 1, 1 }, 
				            { 1, 1, 1, 1, 1, 1, 1 },
				            { 1, 1, 1, 1, 1, 1, 1 }, };
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

				System.out.println(" "+vk +" "+vy+" "+vm);
				c2=new Color(Math.round(vk/(boyut*boyut)),Math.round(vy/(boyut*boyut)),Math.round(vm/(boyut*boyut)));
				bfHedef.setRGB(i,j,c2.getRGB());
				
			}//2.for bitti
		}//1.for bitti
		
		return bfHedef;
		
	}

	

}
