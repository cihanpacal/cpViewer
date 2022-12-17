package filters.edge_dedection_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import filters.AbstractFilter;
import filters.IFilter;
import filters.KernelMatris;
import util.Mat;

public class LaplacianFiltre extends AbstractFilter implements IFilter {

	
	int filtre=IFilter.LAPLACIAN_ARTI_8;
	int boyut=3;
	int[][] kernel=KernelMatris.LAPLACIAN3;
	
	public LaplacianFiltre() {
	}
	
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
		return filtrele(bfKaynak,(colors)->{
			return new Color(Mat.yuvarla255(Math.abs(colors[0])),Mat.yuvarla255(Math.abs(colors[1])),Mat.yuvarla255(Math.abs(colors[2])));
		}, kernel);
		
	}

}
