package filters.edge_dedection_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

import filters.IFilter;
import filters.sorting_filters.MaxFiltre;
import filters.sorting_filters.MinFiltre;

public class MinMaxMutlakFarkFiltre implements IFilter{

	int boyut=3;
	public MinMaxMutlakFarkFiltre() {
		
	}
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
		BufferedImage bfMin=new MinFiltre().filtrele(bfKaynak);
		BufferedImage bfMax=new MaxFiltre().filtrele(bfKaynak);
		
		BufferedImage bfHedef = new BufferedImage(bfKaynak.getWidth(), bfKaynak.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Color cmin;
		Color cmax;
		Color c2;
		// Kenar çerçeveyi daha sonra düzelt
		//Bu forlar gereksiz yere tüm resmi taramamasý ve hýzlandýrma için çerçeve için
		//yeni hesaplama yap
		/*for (int i = (boyut / 2); i < bfHedef.getWidth() - (boyut / 2); i++) {
			for (int j = (boyut / 2); j < bfHedef.getHeight() - (boyut / 2); j++) {
				c2 = new Color(0, 0, 0);
				bfHedef.setRGB(i, j, c2.getRGB());
			}
		}*/
		
		
		for (int i = (boyut / 2); i < bfKaynak.getWidth() - (boyut / 2); i++) {
			for (int j = (boyut / 2); j < bfKaynak.getHeight() - (boyut / 2); j++) {
				cmin=new Color(bfMin.getRGB(i,j));
				cmax=new Color(bfMax.getRGB(i,j));
				int kmin=cmin.getRed(),ymin=cmin.getGreen(),mmin=cmin.getBlue();
				int kmax=cmax.getRed(),ymax=cmax.getGreen(),mmax=cmax.getBlue();
				int kfark=Math.abs(kmax-kmin);
				int yfark=Math.abs(mmax-mmin);
				int mfark=Math.abs(mmax-mmin);
				c2=new Color(kfark,yfark,mfark);
				bfHedef.setRGB(i, j,c2.getRGB());
			}//2.for bitti
		}//1.for bitti

		return bfHedef;
	}

}
