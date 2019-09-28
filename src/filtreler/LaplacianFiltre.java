package filtreler;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import goruntuisleme.JGoruntu;
import goruntuisleme.Mat;

public class LaplacianFiltre implements IFiltre {

	
	int filtre=IFiltre.LAPLACIAN_ARTI_8;
	int boyut=3;
	
	public LaplacianFiltre() {
		this(IFiltre.LAPLACIAN_ARTI_8);
	}
	
	public LaplacianFiltre(int filtre) {
		
		if(!(filtre==IFiltre.LAPLACIAN_ARTI_4||filtre==IFiltre.LAPLACIAN_EKSI_4||filtre==IFiltre.LAPLACIAN_ARTI_8||filtre==IFiltre.LAPLACIAN_EKSI_8)) {
			JOptionPane.showMessageDialog(null,"Hatalý Filtre Türü","Hata", JOptionPane.DEFAULT_OPTION);
		}else {
			this.filtre=filtre;
		}
	}
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {

		int[][] laplacianEksi4 = { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };
		int[][] laplacianArti4 = { { 0, -1, 0 }, { -1, 4, -1 }, { 0, -1, 0 } };
		int[][] laplacianEksi8 = {{ 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 }  };
		int[][] laplacianArti8 = { {-1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 }  };
		int[][][] filtreler = new int[4][][];
		filtreler[0] = laplacianEksi4;
		filtreler[1] = laplacianArti4;
		filtreler[2] = laplacianEksi8;
		filtreler[3] = laplacianArti8;
		BufferedImage bfHedef=new BufferedImage(bfKaynak.getWidth(),bfKaynak.getHeight(),BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;
		
		
		//Kenar çerçeveyi daha sonra düzelt
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
						vk+=c.getRed()*filtreler[filtre][a + (boyut / 2)][b + (boyut / 2)];
						vy+=c.getGreen()*filtreler[filtre][a + (boyut / 2)][b + (boyut / 2)];
						vm+=c.getBlue()*filtreler[filtre][a + (boyut / 2)][b + (boyut / 2)];
					}//4.for bitti
				}//3.for bitti
				c2=new Color(Mat.yuvarla255(Math.abs(vk)),Mat.yuvarla255(Math.abs(vy)),Mat.yuvarla255(Math.abs(vm)));
				bfHedef.setRGB(i,j,c2.getRGB());
				
			}//2.for bitti
		}//1.for bitti
		
		return bfHedef;
		
	}

}
