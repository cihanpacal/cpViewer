package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.DirectoryStream.Filter;
import java.util.Arrays;

public class OilPaintingFilter extends AbstractFilter implements IFilter {

	private int[][] kernel=KernelMatris.MEAN7x7;
	
	
	@Override
	public BufferedImage filtrele(BufferedImage sourceBufferedImage) {
		
		
		int kernelSize=kernel.length;
		int kernelCenterIndex=7/2;
		
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
				int intensityLevels=25;
				int[] intensityCount=new int[intensityLevels+1];
				int[] averageR=new int[intensityLevels+1];
				int[] averageG=new int[intensityLevels+1];
				int[] averageB=new int[intensityLevels+1];
				for (int a = -kernelCenterIndex; a <= kernelCenterIndex; a++) {
					for (int b = -kernelCenterIndex; b <= kernelCenterIndex; b++) {
						c=new Color(sourceBufferedImage.getRGB(i+a,j+b));
						double mean=(c.getRed()+c.getGreen()+c.getBlue())/3.0;
						int curIntensity = (int)((mean*intensityLevels)/255.0f);
						intensityCount[curIntensity]++;
						averageR[curIntensity] += c.getRed();
						averageG[curIntensity] += c.getGreen();
						averageB[curIntensity] += c.getBlue();
					}//4.for bitti
				}//3.for bitti
				
				int maxIndex=0;
				int curMax=intensityCount[0];
				for(int x=1;x<intensityCount.length;x++) {
					if(intensityCount[x]>curMax) {
						curMax=intensityCount[x];
						maxIndex=x;
					}
				}
				
				int finalR=averageR[maxIndex] / curMax;
				int finalG=averageG[maxIndex] / curMax;
				int finalB=averageB[maxIndex] / curMax;
			   c=new Color(finalR,finalG,finalB);
			   targetBufferedImage.setRGB(i,j,c.getRGB());
			}//2.for bitti
		}//1.
		
		return targetBufferedImage;
		
		
	}

}
