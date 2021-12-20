package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ZoomIn1Filtre implements IFilter {
	
	
	int[][] filtre;
	
	
	
	public ZoomIn1Filtre() {
		this(2);
	}

	public ZoomIn1Filtre(int kat) {
		super();
		filtre=new int[kat][kat];
		for (int i = 0; i < filtre.length; i++) {
			for (int j = 0; j < filtre[i].length; j++) {
				filtre[i][j]=1;
			}
		}
	}





	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {

		
		
		BufferedImage bfHedef=new BufferedImage(bfKaynak.getWidth()*filtre.length+1,bfKaynak.getHeight()*filtre.length+1,BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;
		for (int i =0; i < bfHedef.getWidth(); i++) {
			for (int j=0; j < bfHedef.getHeight(); j++) {
				if((i%filtre.length==filtre.length-1)&&(j%filtre.length==filtre.length-1)) {
					bfHedef.setRGB(i,j,bfKaynak.getRGB(i/filtre.length,j/filtre.length));
				}else {
					bfHedef.setRGB(i,j,new Color(0,0,0).getRGB());
				}
			}
		}

		//bfKaynak.setData(bfHedef.getData());
		bfKaynak=new BufferedImage(bfHedef.getWidth(),bfHedef.getHeight(),BufferedImage.TYPE_INT_RGB);
		bfHedef.copyData(bfKaynak.getRaster());

		for (int i = 0; i < bfKaynak.getWidth()-filtre.length+1; i++) {
			for (int j = 0; j < bfKaynak.getHeight()-filtre.length+1; j++) {
				int toplamk=0;
				int toplamy=0;
				int toplamm=0;
				for (int a = 0; a <=filtre.length-1 ; a++) {
					for (int b =0; b <=filtre.length-1; b++) {
						c=new Color(bfKaynak.getRGB(i+a,j+b));
						toplamk+=c.getRed()*filtre[a][b];
						toplamy+=c.getGreen()*filtre[a][b];
						toplamm+=c.getBlue()*filtre[a][b];
						
					}//4.for bitti
				}//3.for bitti

			
				c=new Color(toplamk,toplamy,toplamm);
				bfHedef.setRGB(i+filtre.length-1,j+filtre.length-1,c.getRGB());
				
				
			}//2.for bitti
		}//1.for bitti
		
		
		
		return bfHedef.getSubimage(filtre.length-1,filtre.length-1,bfHedef.getWidth()-filtre.length-1,bfHedef.getHeight()-filtre.length-1);
	}
	

}
