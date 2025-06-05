package filters;

import util.ImageScaler;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MozaikFilter extends AbstractFilter implements IFilter {

	
	private TreeMap<Integer,Integer[][]> kernelTree=new TreeMap<Integer, Integer[][]>();
	
	public void addImages(List<BufferedImage> bufferedImages) {
		kernelTree.clear();
		bufferedImages = bufferedImages.stream()
		    .map(bufferedImage -> ImageScaler.resizeImage(bufferedImage, 15, 15))
		    .collect(Collectors.toList());
		Mozaik[] mozaiks=new Mozaik[bufferedImages.size()];
		int totalMean=0;
		
		for(int x=0;x<bufferedImages.size();x++) {
			BufferedImage bufferedImage=bufferedImages.get(x);
			Mozaik mozaik=new Mozaik();
			Integer[][] matrix=new Integer[15][15];
			double total=0;
			int pixelCount=0;
			Color c;
			for(int i=0;i<bufferedImage.getWidth();i++) {
				for(int j=0;j<bufferedImage.getHeight();j++) {
					pixelCount++;
					c = new Color(bufferedImage.getRGB(i , j));
					total+=(0.299*c.getRed() + 0.587*c.getGreen() + 0.114*c.getBlue());
					matrix[i][j]=(int)(0.299*c.getRed() + 0.587*c.getGreen() + 0.114*c.getBlue());
				}
			}
			mozaik.setMean((int)(Math.round(total/pixelCount)));
			mozaik.setMatrix(matrix);
			mozaiks[x]=mozaik;
			totalMean+=mozaik.getMean();
		}
		
		
		double step=(255/(double)totalMean);
		Arrays.sort(mozaiks);
		
		for(int i=0,j=0;i<mozaiks.length;i++,j+=(mozaiks[i-1].getMean()*step)) {
			kernelTree.put((int)Math.ceil(j),mozaiks[i].getMatrix());
		}
	}
	
	@Override
	public BufferedImage filtrele(BufferedImage sourceBufferedImage) {

		int kernelCenterIndex = 15 / 2;

		BufferedImage targetBufferedImage = new BufferedImage(sourceBufferedImage.getWidth(),
				sourceBufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		Color c;
		Color c2;


		for (int i = 0; i < targetBufferedImage.getWidth(); i++) {
			for (int j = 0; j < targetBufferedImage.getHeight(); j++) {
				c = new Color(0, 0, 0);
				targetBufferedImage.setRGB(i, j, c.getRGB());
			}
		}

		for (int i = kernelCenterIndex; i < sourceBufferedImage.getWidth() - kernelCenterIndex; i+=15) {
			for (int j = kernelCenterIndex; j < sourceBufferedImage.getHeight() - kernelCenterIndex; j+=15) {

				double total=0;
				for (int a = -kernelCenterIndex; a <= kernelCenterIndex; a++) {
					for (int b = -kernelCenterIndex; b <= kernelCenterIndex; b++) {
						c = new Color(sourceBufferedImage.getRGB(i + a, j + b));
						total+=(int)(0.299*c.getRed() + 0.587*c.getGreen() + 0.114*c.getBlue());
					} // 4.for bitti
				} // 3.for bitti

				int mean=(int)(Math.round(total/(15*15)));
				int key=-1;
				try {
					key=kernelTree.floorKey(mean);
				} catch (NullPointerException e) {
					System.out.println(mean);
				}
				
				Integer[][] matrix=(Integer[][])kernelTree.get(key);
				
				for (int a = -kernelCenterIndex; a <= kernelCenterIndex; a++) {
					for (int b = -kernelCenterIndex; b <= kernelCenterIndex; b++) {
						int colorValue=matrix[a+kernelCenterIndex][b+kernelCenterIndex];
						c=new Color(colorValue, colorValue,colorValue);
						targetBufferedImage.setRGB(i+a,j+b,c.getRGB());
					} // 4.for bitti
				}
			} // 2.for bitti
		} // 1.

		return targetBufferedImage;
	}

}
