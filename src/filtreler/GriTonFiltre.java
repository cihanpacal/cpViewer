package filtreler;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GriTonFiltre implements IFiltre{

	@Override
	public BufferedImage filtrele(BufferedImage bf) {
		Color c1;
		Color c2;
		BufferedImage bfHedef=new BufferedImage(bf.getWidth(),bf.getHeight(),BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bf.getWidth(); i++) {
			for (int j = 0; j < bf.getHeight(); j++) {
				c1 = new Color(bf.getRGB(i, j));
				int toplam=(int)(0.299*c1.getRed() + 0.587*c1.getGreen() + 0.114*c1.getBlue());
				c2 = new Color(toplam,toplam,toplam);
				bfHedef.setRGB(i, j, c2.getRGB());
			}

		}
	   
		return bfHedef;
		
	}

}
