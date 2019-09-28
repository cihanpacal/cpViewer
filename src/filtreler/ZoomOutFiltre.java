package filtreler;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ZoomOutFiltre implements IFiltre{

	float[][] filtre;
	
	public ZoomOutFiltre() {
		this(2);
	}
	
	public ZoomOutFiltre(int kat) {
		filtre=new float[kat][kat];
		for(int i=0;i<filtre.length;i++) {
			for(int j=0;j<filtre.length;j++) {
				filtre[i][j]=1f/(filtre.length*filtre.length);
			}
		}
	}
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
		
		int genislikEk=filtre.length-bfKaynak.getWidth()%filtre.length;
		int yukseklikEk=filtre.length-bfKaynak.getHeight()%filtre.length;
		BufferedImage bfGecici=new BufferedImage(bfKaynak.getWidth()+genislikEk,bfKaynak.getHeight()+yukseklikEk, BufferedImage.TYPE_INT_RGB);
		bfGecici.createGraphics().drawImage(bfKaynak,0,0,null);
		bfKaynak=bfGecici;
		BufferedImage bfHedef=new BufferedImage(bfKaynak.getWidth()/filtre.length,bfKaynak.getHeight()/filtre.length,BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;
		
		
		for (int i = 0; i < bfKaynak.getWidth(); i=i+filtre.length) {
			for (int j = 0; j < bfKaynak.getHeight(); j=j+filtre.length) {
				float toplamk=0;
				float toplamy=0;
				float toplamm=0;
				for (int a = 0; a <=filtre.length-1 ; a++) {
					for (int b =0; b <=filtre.length-1; b++) {
						c=new Color(bfKaynak.getRGB(i+a,j+b));
						toplamk+=c.getRed()*filtre[a][b];
						toplamy+=c.getGreen()*filtre[a][b];
						toplamm+=c.getBlue()*filtre[a][b];
						
					}//4.for bitti
				}//3.for bitti

			
				c=new Color(Math.round(toplamk),Math.round(toplamy),Math.round(toplamm));
				bfHedef.setRGB(i/filtre.length,j/filtre.length,c.getRGB());
				
				
			}//2.for bitti
		}//1.for bitti
		
		return bfHedef;
	}

}
