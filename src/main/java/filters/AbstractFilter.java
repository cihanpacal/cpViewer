package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.function.Function;

public abstract class AbstractFilter implements IFilter {

	
	public BufferedImage filtrele(BufferedImage sourceBufferedImage,Function<int[],Color> colorFunction,int[][] kernel) {
		
		int kernelSize=kernel.length;
		int kernelCenterIndex=kernelSize/2;
		
		BufferedImage targetBufferedImage=new BufferedImage(
				sourceBufferedImage.getWidth()
				,sourceBufferedImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;
//		for (int i =(boyut/2); i < bfHedef.getWidth()-(boyut/2); i++) {
//			for (int j=(boyut/2); j < bfHedef.getHeight()-(boyut/2); j++) {
//				c=new Color(0,0,0);
//				bfHedef.setRGB(i,j,c.getRGB());
//			}
//		}
		
		
		for(int i=0;i<targetBufferedImage.getWidth();i++) {
			for(int j=0;j<targetBufferedImage.getHeight();j++) {
				c=new Color(0, 0, 0);
				targetBufferedImage.setRGB(i, j, c.getRGB());
			}
		}
		
		for (int i = kernelCenterIndex; i < sourceBufferedImage.getWidth() - kernelCenterIndex; i++) {
			for (int j = kernelCenterIndex; j < sourceBufferedImage.getHeight() - kernelCenterIndex; j++) {
				int red = 0;
				int green = 0;
				int blue = 0;
				for (int a = -kernelCenterIndex; a <= kernelCenterIndex; a++) {
					for (int b = -kernelCenterIndex; b <= kernelCenterIndex; b++) {
						c=new Color(sourceBufferedImage.getRGB(i+a,j+b));
						red+=c.getRed()*kernel[a + kernelCenterIndex][b + kernelCenterIndex];
						green+=c.getGreen()*kernel[a + kernelCenterIndex][b + kernelCenterIndex];
						blue+=c.getBlue()*kernel[a + kernelCenterIndex][b + kernelCenterIndex];
					}//4.for bitti
				}//3.for bitti
				
			   c=colorFunction.apply(new int[] {red,green,blue});
			   targetBufferedImage.setRGB(i,j,c.getRGB());
			}//2.for bitti
		}//1.
		
		return targetBufferedImage;
	}
	

}
