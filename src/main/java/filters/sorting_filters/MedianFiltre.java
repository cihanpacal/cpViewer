package filters.sorting_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JOptionPane;

import filters.IFilter;

public class MedianFiltre implements IFilter{

	
	
	int filtre=IFilter.FILTRE5;
	int boyut=5;
	
	public MedianFiltre() {
		this(IFilter.FILTRE5);
		
	}
	
	public MedianFiltre(final int filtre) {
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
		
		BufferedImage bfHedef=new BufferedImage(bfKaynak.getWidth(),bfKaynak.getHeight(),BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;
		for (int i =(boyut/2); i < bfHedef.getWidth()-(boyut/2); i++) {
			for (int j=(boyut/2); j < bfHedef.getHeight()-(boyut/2); j++) {
				c=new Color(0,0,0);
				bfHedef.setRGB(i,j,c.getRGB());
			}
		}
		
		//max min median filtrelerde piksellerin büyüklük küçüklük deðerleri renge göre deðiþebilir
		//o yüzden alýnan deðer çekirdek bölgesindeki farklý piksellerden gelmiþ olabilir
		//daha sonra renklerin ortalamasýna göre sýralama yapýp iki yöntei karþýlaþtýr
		
		for (int i = (boyut / 2); i < bfKaynak.getWidth() - (boyut / 2); i++) {
			for (int j = (boyut / 2); j < bfKaynak.getHeight() - (boyut / 2); j++) {
				//çekirdeði sýralamak için tek boyutlu dizi oluþturma
				int[] filtrek =new int[boyut*boyut];
				int[] filtrey =new int[boyut*boyut];
				int[] filtrem =new int[boyut*boyut];
				//Color[] filtre=new Color[boyut*boyut];
				for (int a = -(boyut / 2); a <= (boyut / 2); a++) {
					for (int b = -(boyut / 2); b <= (boyut / 2); b++) {
						c=new Color(bfKaynak.getRGB(i+a,j+b));
						//2 boyutlu cekirdeði tekboyutlu dizi için göreli konum çevirme
						filtrek[boyut*(a+(boyut/2))+(b+(boyut/2))]= c.getRed();
						filtrey[boyut*(a+(boyut/2))+(b+(boyut/2))]= c.getGreen();
						filtrem[boyut*(a+(boyut/2))+(b+(boyut/2))]= c.getBlue();
						//filtre[boyut*(a+(boyut/2))+(b+(boyut/2))]=new Color(bfKaynak.getRGB(i+a,j+b));
					}//4.for bitti
				}//3.for bitti

				Arrays.sort(filtrek);
				Arrays.sort(filtrey);
				Arrays.sort(filtrem);
				c2=new Color(filtrek[(boyut*boyut)/2],filtrey[(boyut*boyut)/2],filtrem[(boyut*boyut)/2]);
				/*Mat.renkSirala(filtre);
				c2=filtre[(boyut*boyut)/2];*/
				bfHedef.setRGB(i,j,c2.getRGB());
				
			}//2.for bitti
		}//1.for bitti
		
		return bfHedef;
		
	}

}
