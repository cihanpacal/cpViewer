package filtreler;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class YesilFiltre implements IFiltre {
	
   
	@Override
	public BufferedImage filtrele(BufferedImage bf) {
		Color c1;
		Color c2;
		
		BufferedImage bfHedef=new BufferedImage(bf.getWidth(),bf.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < bf.getWidth(); i++) {
			for (int j = 0; j < bf.getHeight(); j++) {
				c1 = new Color(bf.getRGB(i, j));
				c2 = new Color(0,c1.getGreen(), 0);
				bfHedef.setRGB(i, j, c2.getRGB());
			}

		}
		return bfHedef;
	}

}
