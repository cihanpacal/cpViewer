package filters.color_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

import filters.IFilter;

public class RedChannelFilter implements IFilter {

	@Override
	public BufferedImage filtrele(BufferedImage sourceBufferedImage) {
		Color color1;
		Color color2;
		BufferedImage targetBufferedImage=
				new BufferedImage(sourceBufferedImage.getWidth(),sourceBufferedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < sourceBufferedImage.getWidth(); i++) {
			for (int j = 0; j < sourceBufferedImage.getHeight(); j++) {
				color1 = new Color(sourceBufferedImage.getRGB(i, j));
				color2 = new Color(color1.getRed(), 0, 0);
				targetBufferedImage.setRGB(i, j, color2.getRGB());
			}

		}
		return targetBufferedImage;
	}

}
