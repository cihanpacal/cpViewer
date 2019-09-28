package filtreler;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ZoomIn2Filtre implements IFiltre {
	
	float[][] filtre;
	
	public ZoomIn2Filtre() {
		super();
		filtre=new float[][] {{0.25f,0.5f,0.25f},{0.5f,1f,0.5f},{0.25f,0.5f,0.25f}};
	}

	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {

		
		
		BufferedImage bfHedef=new BufferedImage(bfKaynak.getWidth()*2+1,bfKaynak.getHeight()*2+1,BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;
		for (int i =0; i < bfHedef.getWidth(); i++) {
			for (int j=0; j < bfHedef.getHeight(); j++) {
				if((i%2==1)&&(j%2==1)) {
					bfHedef.setRGB(i,j,bfKaynak.getRGB(i/2,j/2));
				}else {
					bfHedef.setRGB(i,j,new Color(0,0,0).getRGB());
				}
			}
		}

		//bfKaynak.setData(bfHedef.getData());
		bfKaynak=new BufferedImage(bfHedef.getWidth(),bfHedef.getHeight(),BufferedImage.TYPE_INT_RGB);
		bfHedef.copyData(bfKaynak.getRaster());
		for (int i = (filtre.length / 2); i < bfKaynak.getWidth() - (filtre.length/ 2); i++) {
			for (int j = (filtre.length / 2); j < bfKaynak.getHeight() - (filtre.length / 2); j++) {
				float toplamk = 0;
				float toplamy = 0;
				float toplamm = 0;
				for (int a = -(filtre.length / 2); a <= (filtre.length / 2); a++) {
					for (int b = -(filtre.length / 2); b <= (filtre.length / 2); b++) {
						c=new Color(bfKaynak.getRGB(i+a,j+b));
						toplamk+=c.getRed()*filtre[a + (filtre.length / 2)][b + (filtre.length / 2)];
						toplamy+=c.getGreen()*filtre[a + (filtre.length / 2)][b + (filtre.length / 2)];
						toplamm+=c.getBlue()*filtre[a + (filtre.length / 2)][b + (filtre.length/ 2)];
					}//4.for bitti
				}//3.for bitti
					c2=new Color(Math.round(toplamk),Math.round(toplamy),Math.round(toplamm));
					bfHedef.setRGB(i,j,c2.getRGB());

			}//2.for bitti
		}//1.for bitti
		
		
		return bfHedef.getSubimage(filtre.length-1,filtre.length-1,bfHedef.getWidth()-filtre.length,bfHedef.getHeight()-filtre.length);
	}

}
