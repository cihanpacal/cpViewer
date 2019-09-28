package filtreler;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class KeskinlestirmeFiltre implements IFiltre {
	
	int filtre=IFiltre.FILTRE5;
	int boyut=5;
	
	public KeskinlestirmeFiltre() {
		this(IFiltre.FILTRE5);
		
	}
	
	public KeskinlestirmeFiltre(final int filtre) {
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
						filtrek[boyut*(a+(boyut/2))+(b+(boyut/2))]= c.getRed();
						filtrey[boyut*(a+(boyut/2))+(b+(boyut/2))]= c.getGreen();
						filtrem[boyut*(a+(boyut/2))+(b+(boyut/2))]= c.getBlue();
					}//4.for bitti
				}//3.for bitti

				Arrays.sort(filtrek);
				Arrays.sort(filtrey);
				Arrays.sort(filtrem);
				c=new Color(bfKaynak.getRGB(i,j));
				int kirmiziMinFark=Math.abs(c.getRed()-filtrek[0]);
				int yesilMinFark=Math.abs(c.getGreen()-filtrey[0]);
				int maviMinFark=Math.abs(c.getBlue()-filtrem[0]);
				int kirmiziMaxFark=Math.abs(c.getRed()-filtrek[filtrek.length-1]);
				int yesilMaxFark=Math.abs(c.getGreen()-filtrey[filtrey.length-1]);
				int maviMaxFark=Math.abs(c.getBlue()-filtrem[filtrem.length-1]);
				c2=new Color(kirmiziMinFark<kirmiziMaxFark?filtrek[0]:filtrek[filtrek.length-1],
						yesilMinFark<yesilMaxFark?filtrey[0]:filtrey[filtrey.length-1],
								maviMinFark<maviMaxFark?filtrem[0]:filtrem[filtrem.length-1]);
				/*Mat.renkSirala(filtre);
				c2=filtre[(boyut*boyut)/2];*/
				bfHedef.setRGB(i,j,c2.getRGB());
				
			}//2.for bitti
		}//1.for bitti
		
		return bfHedef;
		
	}

}
